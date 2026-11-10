import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        TipCamera tip1 = new TipCamera("Standard", 1, "Cameră simplă");
        TipCamera tip2 = new TipCamera("Double", 2, "Două persoane");
        TipCamera tip3 = new TipCamera("VIP Suite", 3, "Lux și Jacuzzi");

        Hotel hotel1 = new Hotel(1, "Grand Crystal Resort", "București");
        Hotel hotel2 = new Hotel(2, "Mountain Breeze Spa", "Brașov");

        Camera camera1 = new Camera(1, 101, 250, true, tip1, hotel1, "WiFi, TV");
        Camera camera2 = new Camera(2, 102, 250, true, tip1, hotel1, "WiFi, TV");
        Camera camera3 = new Camera(3, 201, 500, true, tip2, hotel1, "WiFi, Minibar");
        Camera camera4 = new Camera(4, 501, 1500, true, tip3, hotel1, "Jacuzzi, Terasă");
        Camera camera5 = new Camera(5, 101, 300, true, tip1, hotel2, "Vedere Munte");
        Camera camera6 = new Camera(6, 102, 300, true, tip1, hotel2, "Vedere Munte");
        Camera camera7 = new Camera(7, 201, 600, true, tip2, hotel2, "Șemineu");
        Camera camera8 = new Camera(8, 202, 600, true, tip2, hotel2, "Șemineu");
        Camera camera9 = new Camera(9, 301, 1000, true, tip3, hotel2, "Saună proprie");
        Camera camera10 = new Camera(10, 302, 1000, true, tip3, hotel2, "Saună proprie");

        Client client1 = new Client(1, "Marin Sorescu", "marin@mail.com", "0711", 0);
        Client client2 = new Client(2, "Adrian Dobrescu", "adrian@mail.com", "0722", 5);
        Client client3 = new Client(3, "Silvia Eremițiu", "silvia@mail.com", "0733", 10);
        Client client4 = new Client(4, "Nadia Birău", "nadia@mail.com", "0744", 0);
        Client client5 = new Client(5, "Sabina Chirilă", "sabina@mail.com", "0755", 0);
        Client client6 = new Client(6, "Robert Dumitrescu", "robert@mail.com", "0766", 15);
        Client client7 = new Client(7, "Valentin Dragos", "valentin@mail.com", "0777", 0);
        Client client8 = new Client(8, "Mădălina Găbureanu", "madalina@mail.com", "0788", 5);
        Client client9 = new Client(9, "Emanuel Mihăilescu", "emanuel@mail.com", "0799", 0);
        Client client10 = new Client(10, "Sergiu Dobre", "sergiu@mail.com", "0700", 0);

        Personal p1 = new Personal(1, "Elvira Drăgan", "Recepționer", 4000, hotel1);
        Personal p2 = new Personal(2, "Robert Negoiță", "Manager", 7000, hotel1);
        Personal p3 = new Personal(3, "Sofia Stăruială", "Recepționer", 4000, hotel2);
        Personal p4 = new Personal(4, "Ionuț Negoiu", "Ghid", 3500, hotel2);
        Personal p5 = new Personal(5, "Corina Lămboiu", "Recepționer", 4000, hotel2);

        List<Camera> listaCamere = new ArrayList<>();
        listaCamere.add(camera1); listaCamere.add(camera2); listaCamere.add(camera3); listaCamere.add(camera4);
        listaCamere.add(camera5); listaCamere.add(camera6); listaCamere.add(camera7); listaCamere.add(camera8);
        listaCamere.add(camera9); listaCamere.add(camera10);

        List<Client> listaClienti = new ArrayList<>();
        listaClienti.add(client1); listaClienti.add(client2); listaClienti.add(client3); listaClienti.add(client4);
        listaClienti.add(client5); listaClienti.add(client6); listaClienti.add(client7); listaClienti.add(client8);
        listaClienti.add(client9); listaClienti.add(client10);

        System.out.println("--- JURNAL REZERVĂRI HOTELIERE ---");

        Rezervare rez1 = new Rezervare(1, client1, camera1, camera1.getTipCamera().getDenumire(), LocalDate.now(), LocalDate.now().plusDays(2), 500);
        camera1.setDisponibil(false);
        System.out.println("Procesat: " + rez1);

        Rezervare rez2 = new Rezervare(2, client2, camera2, camera2.getTipCamera().getDenumire(), LocalDate.now(), LocalDate.now().plusDays(3), 750);
        camera2.setDisponibil(false);
        System.out.println("Procesat: " + rez2);

        Rezervare rez3 = new Rezervare(3, client3, camera5, camera5.getTipCamera().getDenumire(), LocalDate.now(), LocalDate.now().plusDays(1), 300);
        camera5.setDisponibil(false);
        System.out.println("Procesat: " + rez3);

        Rezervare rez4 = new Rezervare(4, client4, camera4, camera4.getTipCamera().getDenumire(), LocalDate.now(), LocalDate.now().plusDays(5), 7500);
        camera4.setDisponibil(false);
        System.out.println("Procesat: " + rez4);

        System.out.println("\n--- STATISTICI FINALE ---");
        System.out.println("Total clienți înregistrați: " + listaClienti.size());

        System.out.println("Status Camera 101 (" + hotel1.getNume() + "): " + (camera1.isDisponibil() ? "Liberă" : "Ocupată"));
        System.out.println("Status Camera 101 (" + hotel2.getNume() + "): " + (camera5.isDisponibil() ? "Liberă" : "Ocupată"));

        System.out.println("Responsabil hotel 1: " + p1.getNume());
        System.out.println("Responsabil hotel 2: " + p3.getNume());
    }
}