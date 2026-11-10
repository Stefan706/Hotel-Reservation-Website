import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.*;

public class JavaApiServer {
    private static final String URL = "jdbc:postgresql://localhost:5432/hotel";


    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/hoteluri", new HoteluriHandler());
        server.createContext("/api/camere", new CamereHandler());
        server.createContext("/api/clienti", new ClientiHandler());
        server.createContext("/api/rezervari", new RezervariHandler());
        server.createContext("/api/reviews", new ReviewHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("--- Server Hotel corectat (Multi-Hotel) pe portul 8080 ---");
    }

    static class RezervariHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            setHeaders(ex);
            if ("OPTIONS".equals(ex.getRequestMethod())) { sendRes(ex, 204, ""); return; }
            if ("GET".equals(ex.getRequestMethod())) {
                String sql = "SELECT r.id, cl.nume as client, h.nume as hotel, cam.numar_camera as camera, " +
                        "r.camera_id, r.data_check_in, r.data_check_out FROM rezervare r " +
                        "JOIN client_abonat cl ON r.client_id = cl.id JOIN camera cam ON r.camera_id = cam.id " +
                        "JOIN hotel h ON cam.hotel_id = h.id ORDER BY r.id DESC";
                sendRes(ex, 200, convertToJson(fetch(sql)));
            } else if ("POST".equals(ex.getRequestMethod())) {
                try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    Map<String, String> d = parseJson(new String(ex.getRequestBody().readAllBytes(), "UTF-8"));
                    int camId = Integer.parseInt(d.get("camera_id"));
                    java.sql.Date start = java.sql.Date.valueOf(d.get("data_check_in"));
                    java.sql.Date end = java.sql.Date.valueOf(d.get("data_check_out"));

                    String checkSql = "SELECT COUNT(*) FROM rezervare WHERE camera_id = ? AND NOT (data_check_out <= ? OR data_check_in >= ?)";
                    try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                        ps.setInt(1, camId); ps.setDate(2, start); ps.setDate(3, end);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next() && rs.getInt(1) > 0) { sendRes(ex, 400, "{\"error\":\"Camera este ocupata in acest hotel!\"}"); return; }
                    }
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO rezervare (client_id, camera_id, data_check_in, data_check_out, pret_total) VALUES (?, ?, ?, ?, 0)")) {
                        ps.setInt(1, Integer.parseInt(d.get("client_id"))); ps.setInt(2, camId); ps.setDate(3, start); ps.setDate(4, end); ps.executeUpdate();
                    }
                    sendRes(ex, 200, "{\"msg\":\"ok\"}");
                } catch (Exception e) { sendRes(ex, 500, "{\"error\":\"" + e.getMessage() + "\"}"); }
            }
        }
    }

    static class ClientiHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            setHeaders(ex);
            if ("OPTIONS".equals(ex.getRequestMethod())) { sendRes(ex, 204, ""); return; }
            if ("GET".equals(ex.getRequestMethod())) { sendRes(ex, 200, convertToJson(fetch("SELECT * FROM client_abonat ORDER BY id DESC"))); }
            else if ("POST".equals(ex.getRequestMethod())) {
                try {
                    Map<String, String> d = parseJson(new String(ex.getRequestBody().readAllBytes(), "UTF-8"));
                    try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = c.prepareStatement("INSERT INTO client_abonat (nume, email, telefon) VALUES (?, ?, ?)")) {
                        ps.setString(1, d.get("nume")); ps.setString(2, d.get("email")); ps.setString(3, d.get("telefon")); ps.executeUpdate();
                    }
                    sendRes(ex, 200, "{\"msg\":\"ok\"}");
                } catch (Exception e) { sendRes(ex, 500, "{\"error\":\"" + e.getMessage() + "\"}"); }
            }
        }
    }

    static class ReviewHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            setHeaders(ex);
            if ("OPTIONS".equals(ex.getRequestMethod())) { sendRes(ex, 204, ""); return; }
            if ("GET".equals(ex.getRequestMethod())) { sendRes(ex, 200, convertToJson(fetch("SELECT r.id, cl.nume as client, cam.numar_camera as camera, r.rating, r.comentariu FROM review r JOIN client_abonat cl ON r.client_id = cl.id JOIN camera cam ON r.camera_id = cam.id ORDER BY r.id DESC"))); }
            else if ("POST".equals(ex.getRequestMethod())) {
                try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement("INSERT INTO review (client_id, camera_id, rating, comentariu) VALUES (?, ?, ?, ?)")) {
                    Map<String, String> d = parseJson(new String(ex.getRequestBody().readAllBytes(), "UTF-8"));
                    ps.setInt(1, Integer.parseInt(d.get("client_id"))); ps.setInt(2, Integer.parseInt(d.get("camera_id"))); ps.setInt(3, Integer.parseInt(d.get("rating"))); ps.setString(4, d.get("comentariu")); ps.executeUpdate();
                    sendRes(ex, 200, "{\"msg\":\"ok\"}");
                } catch (Exception e) { sendRes(ex, 500, "{\"error\":\"" + e.getMessage() + "\"}"); }
            }
        }
    }

    static class HoteluriHandler implements HttpHandler { public void handle(HttpExchange ex) throws IOException { setHeaders(ex); sendRes(ex, 200, convertToJson(fetch("SELECT id, nume FROM hotel ORDER BY nume"))); } }
    static class CamereHandler implements HttpHandler { public void handle(HttpExchange ex) throws IOException { setHeaders(ex); sendRes(ex, 200, convertToJson(fetch("SELECT c.id, c.numar_camera, c.hotel_id, c.pret_noapte, t.denumire as tip FROM camera c JOIN tip_camera t ON c.tip_camera_id = t.id"))); } }

    private static void setHeaders(HttpExchange ex) { ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); ex.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); ex.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type"); ex.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8"); }
    private static void sendRes(HttpExchange ex, int code, String res) throws IOException { byte[] b = res.getBytes("UTF-8"); ex.sendResponseHeaders(code, b.length > 0 ? b.length : -1); if (b.length > 0) { try (OutputStream os = ex.getResponseBody()) { os.write(b); } } }
    private static Map<String, String> parseJson(String json) { Map<String, String> m = new HashMap<>(); String clean = json.trim().replace("{", "").replace("}", ""); String[] pairs = clean.split(","); for (String p : pairs) { String[] kv = p.split(":"); if (kv.length == 2) m.put(kv[0].trim().replace("\"", ""), kv[1].trim().replace("\"", "")); } return m; }
    private static List<Map<String, Object>> fetch(String sql) { List<Map<String, Object>> l = new ArrayList<>(); try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD); ResultSet rs = c.createStatement().executeQuery(sql)) { ResultSetMetaData md = rs.getMetaData(); while (rs.next()) { Map<String, Object> row = new HashMap<>(); for (int i = 1; i <= md.getColumnCount(); i++) row.put(md.getColumnName(i), rs.getObject(i)); l.add(row); } } catch (Exception e) { e.printStackTrace(); } return l; }
    private static String convertToJson(List<Map<String, Object>> data) { StringBuilder sb = new StringBuilder("["); for (int i = 0; i < data.size(); i++) { sb.append("{"); int j = 0; for (Map.Entry<String, Object> e : data.get(i).entrySet()) { sb.append("\"").append(e.getKey()).append("\":"); Object v = e.getValue(); if (v instanceof String || v instanceof java.sql.Date) sb.append("\"").append(v).append("\""); else sb.append(v); if (++j < data.get(i).size()) sb.append(","); } sb.append("}"); if (i < data.size() - 1) sb.append(","); } return sb.append("]").toString(); }
}