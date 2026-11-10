public class Pret
{
    private int id;
    private String tipCamera;
    private String sezon;
    private double pret;
    private TipCamera tip;

    public Pret() {}

    public Pret(int id, String tipCamera, String sezon, double pret, TipCamera tip) {
        this.id = id;
        this.tipCamera = tipCamera;
        this.sezon = sezon;
        this.pret = pret;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipCamera() {
        return tipCamera;
    }

    public void setTipCamera(String tipCamera) {
        this.tipCamera = tipCamera;
    }

    public String getSezon() {
        return sezon;
    }

    public void setSezon(String sezon) {
        this.sezon = sezon;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public TipCamera getTip() {
        return tip;
    }

    public void setTip(TipCamera tip) {
        this.tip = tip;
    }
    @Override
    public String toString() {
        return "Pret{" +
                "id=" + id +
                ", tipCamera='" + tipCamera + '\'' +
                ", sezon='" + sezon + '\'' +
                ", pret=" + pret +
                ", tip=" + tip +
                '}';
    }
}
