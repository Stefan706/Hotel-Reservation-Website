import java.sql.*;

public class DBConnection {
    public static void main(String[] args) {
        

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            System.out.println("⏳ Se resetează baza de date...");

            String[] dropTables = {
                    "DROP TABLE IF EXISTS review CASCADE",
                    "DROP TABLE IF EXISTS rezervare CASCADE",
                    "DROP TABLE IF EXISTS loc_parcare CASCADE",
                    "DROP TABLE IF EXISTS preturi CASCADE",
                    "DROP TABLE IF EXISTS personal CASCADE",
                    "DROP TABLE IF EXISTS camera CASCADE",
                    "DROP TABLE IF EXISTS client_abonat CASCADE",
                    "DROP TABLE IF EXISTS tip_camera CASCADE",
                    "DROP TABLE IF EXISTS hotel CASCADE"
            };
            for (String sql : dropTables) statement.execute(sql);

            statement.execute("CREATE TABLE hotel (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nume VARCHAR(100) NOT NULL, " +
                    "adresa VARCHAR(200) NOT NULL)");

            statement.execute("INSERT INTO hotel (nume, adresa) VALUES " +
                    "('Grand Crystal Resort', 'Calea Victoriei 123, București'), " +
                    "('Mountain Breeze Spa', 'Strada Alpină 45, Poiana Brașov'), " +
                    "('Seaside Luxury Inn', 'Bulevardul Mamaia 10, Constanța')");

            statement.execute("CREATE TABLE tip_camera (" +
                    "id SERIAL PRIMARY KEY, " +
                    "denumire VARCHAR(50) NOT NULL, " +
                    "descriere VARCHAR(200))");

            statement.execute("INSERT INTO tip_camera (denumire, descriere) VALUES " +
                    "('Standard Single', 'Cameră pentru o persoană, pat individual, birou'), " +
                    "('Double Executive', 'Pat matrimonial King Size, balcon, minibar'), " +
                    "('Family Suite', 'Două camere interconectate, capacitate 4 persoane'), " +
                    "('Presidential Suite', 'Living, terasă privată, jacuzzi, vedere panoramică')");

            statement.execute("CREATE TABLE camera (" +
                    "id SERIAL PRIMARY KEY, " +
                    "numar_camera INT NOT NULL, " +
                    "pret_noapte NUMERIC(8,2) NOT NULL CHECK (pret_noapte > 0), " +
                    "disponibil BOOLEAN DEFAULT TRUE, " +
                    "facilitati VARCHAR(200), " +
                    "tip_camera_id INT REFERENCES tip_camera(id), " +
                    "hotel_id INT REFERENCES hotel(id), " +
                    "CONSTRAINT uq_camera_numar_hotel UNIQUE (numar_camera, hotel_id))");

            statement.execute("INSERT INTO camera (numar_camera, pret_noapte, disponibil, facilitati, tip_camera_id, hotel_id) VALUES " +
                    "(101, 250.00, true, 'WiFi, TV, AC', 1, 1), " +
                    "(102, 250.00, true, 'WiFi, TV, AC', 1, 1), " +
                    "(103, 450.00, true, 'WiFi, minibar, balcon', 2, 1), " +
                    "(104, 450.00, false, 'WiFi, minibar, balcon', 2, 1), " +
                    "(201, 800.00, true, 'Bucătărie mică, 2 băi', 3, 1), " +
                    "(501, 1500.00, true, 'Jacuzzi, terasă', 4, 1), " +
                    "(101, 300.00, true, 'WiFi, vedere munte', 1, 2), " +
                    "(102, 500.00, true, 'WiFi, balcon, spa access', 2, 2), " +
                    "(103, 500.00, true, 'WiFi, balcon, spa access', 2, 2), " +
                    "(101, 350.00, true, 'WiFi, vedere mare', 1, 3), " +
                    "(201, 550.00, false, 'WiFi, balcon mare, AC', 2, 3)");

            statement.execute("CREATE TABLE personal (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nume VARCHAR(100) NOT NULL, " +
                    "functie VARCHAR(50), " +
                    "salariu NUMERIC(8,2) CHECK (salariu > 0), " +
                    "hotel_id INT REFERENCES hotel(id))");

            statement.execute("INSERT INTO personal (nume, functie, salariu, hotel_id) VALUES " +
                    "('Ionel Ionescu', 'Manager General', 9000.00, 1), " +
                    "('Elena Radu', 'Receptioner Șef', 4500.00, 1), " +
                    "('Andrei Vasile', 'Chef Bucătar', 6500.00, 1), " +
                    "('Mihaela Stan', 'Receptioner', 3800.00, 1), " +
                    "('George Ducu', 'Mentenanță', 3500.00, 1), " +
                    "('Maria Popa', 'Curățenie Șef', 3500.00, 2), " +
                    "('Cristian Nica', 'Ghid Montan', 4000.00, 2), " +
                    "('Ana Sandu', 'Manager Spa', 5500.00, 2), " +
                    "('Radu Miron', 'Salvamar', 3500.00, 3)");

            statement.execute("CREATE TABLE client_abonat (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nume VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) UNIQUE, " +
                    "telefon VARCHAR(20), " +
                    "discount INT DEFAULT 0 CHECK (discount BETWEEN 0 AND 100))");

            statement.execute("INSERT INTO client_abonat (nume, email, telefon, discount) VALUES " +
                    "('Ion Popescu', 'ion.popescu@gmail.com', '0722000111', 10), " +
                    "('Maria Ionescu', 'maria.ionescu@yahoo.com', '0722000222', 15), " +
                    "('Andrei Radu', 'andrei.radu@outlook.com', '0722000333', 0), " +
                    "('Elena Dumitru', 'elena.dumitru@gmail.com', '0722000444', 5), " +
                    "('Alexandru Stoica', 'alex.stoica@firma.ro', '0722000555', 20), " +
                    "('Gabriela Stan', 'gabriela.s@yahoo.com', '0722000666', 10), " +
                    "('Mihai Gheorghe', 'mihai.gheorghe@gmail.com', '0722000777', 0), " +
                    "('Cristina Matei', 'cristina.m@gmail.com', '0722000888', 15), " +
                    "('Vlad Florea', 'vlad.florea@tech.ro', '0722000999', 5), " +
                    "('Diana Toma', 'diana.toma@yahoo.com', '0722001000', 0), " +
                    "('George Dinu', 'george.dinu@outlook.com', '0722001111', 25), " +
                    "('Ioana Marin', 'ioana.marin@gmail.com', '0722001222', 10), " +
                    "('Stefan Rusu', 'stefan.rusu@gmail.com', '0722001333', 5), " +
                    "('Raluca Lazar', 'raluca.lazar@yahoo.com', '0722001444', 0), " +
                    "('Bogdan Moldovan', 'bogdan.m@firma.ro', '0722001555', 20)");

            statement.execute("CREATE TABLE loc_parcare (" +
                    "id SERIAL PRIMARY KEY, " +
                    "numar_loc INT UNIQUE, " +
                    "ocupat BOOLEAN DEFAULT FALSE, " +
                    "client_id INT REFERENCES client_abonat(id))");

            statement.execute("INSERT INTO loc_parcare (numar_loc, ocupat, client_id) VALUES " +
                    "(1, true, 1), " +
                    "(2, true, 2), " +
                    "(3, false, null), " +
                    "(4, true, 4), " +
                    "(5, false, null), " +
                    "(6, true, 6)");

            statement.execute("CREATE TABLE preturi (" +
                    "id SERIAL PRIMARY KEY, " +
                    "sezon VARCHAR(50), " +
                    "pret NUMERIC(8,2) CHECK (pret > 0), " +
                    "tip_camera_id INT REFERENCES tip_camera(id))");

            statement.execute("INSERT INTO preturi (sezon, pret, tip_camera_id) VALUES " +
                    "('Iarnă', 350.00, 1), " +
                    "('Vară', 400.00, 1), " +
                    "('Revelion', 1800.00, 4), " +
                    "('Paște', 900.00, 3)");

            statement.execute("CREATE TABLE rezervare (" +
                    "id SERIAL PRIMARY KEY, " +
                    "client_id INT NOT NULL REFERENCES client_abonat(id), " +
                    "camera_id INT NOT NULL REFERENCES camera(id) ON DELETE CASCADE, " +
                    "data_check_in DATE NOT NULL, " +
                    "data_check_out DATE NOT NULL, " +
                    "pret_total NUMERIC(8,2) CHECK (pret_total >= 0), " +
                    "CONSTRAINT chk_rezervare_date CHECK (data_check_out > data_check_in))");

            statement.execute("INSERT INTO rezervare (client_id, camera_id, data_check_in, data_check_out, pret_total) VALUES " +
                    "(1, 1, '2026-02-10', '2026-02-15', 1250.00), " +
                    "(2, 6, '2026-03-01', '2026-03-05', 6000.00), " +
                    "(4, 4, '2026-01-20', '2026-01-25', 2250.00), " +
                    "(15, 11, '2026-06-15', '2026-06-20', 2750.00)");

            statement.execute("CREATE TABLE review (" +
                    "id SERIAL PRIMARY KEY, " +
                    "client_id INT NOT NULL REFERENCES client_abonat(id) ON DELETE CASCADE, " +
                    "camera_id INT NOT NULL REFERENCES camera(id) ON DELETE CASCADE, " +
                    "rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5), " +
                    "comentariu TEXT, " +
                    "data_postare TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

            statement.execute("INSERT INTO review (client_id, camera_id, rating, comentariu) VALUES " +
                    "(1, 1, 5, 'O experiență de neuitat! Camera 101 este foarte spațioasă.'), " +
                    "(2, 6, 4, 'Jacuzzi-ul este excelent, vederea este incredibilă.'), " +
                    "(4, 4, 3, 'Camera 104 a fost ok, dar balconul era cam mic.')");

            System.out.println("✅ Baza de date a fost reconstruită cu succes! (15 Clienți Noi)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}