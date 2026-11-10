public class Personal
{
    private int id;
    private String nume;
    private String functie;
    private double salariu;
    private Hotel hotel;
    public Personal() {}

    public Personal(int id, String nume, String functie, double salariu, Hotel hotel) {
        this.id = id;
        this.nume = nume;
        this.functie = functie;
        this.salariu = salariu;
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    @Override
    public String toString() {
        return "Personal{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", functie='" + functie + '\'' +
                ", salariu=" + salariu +
                ", hotel=" + hotel +
                '}';
    }
}
