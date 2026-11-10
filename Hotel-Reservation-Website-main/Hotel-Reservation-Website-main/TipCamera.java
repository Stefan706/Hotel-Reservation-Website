public class TipCamera
{
    private int id;
    private String denumire;
    private String descriere;

    public TipCamera(String denumire, int id, String descriere) {
        this.denumire = denumire;
        this.id = id;
        this.descriere = descriere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
    @Override
    public String toString() {
        return "TipCamera{" + "id=" + id + ", denumire='" + denumire + '\'' + ", descriere='" + descriere + '\'' + '}';
    }
}
