    public class Camera
    {
        private int id;
        private int numarCamera;
        private double pretNoapte;
        private boolean disponibil;
        private TipCamera tipCamera;
        private Hotel hotel;
        private String facilitati;

        public Camera()
        {
        }

        public Camera(int id, int numarCamera, double pretNoapte, boolean disponibil, TipCamera tipCamera, Hotel hotel, String facilitati)
        {
            this.id = id;
            this.numarCamera = numarCamera;
            this.pretNoapte = pretNoapte;
            this.disponibil = disponibil;
            this.tipCamera = tipCamera;
            this.hotel = hotel;
            this.facilitati = facilitati;
        }

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public int getNumarCamera()
        {
            return numarCamera;
        }

        public void setNumarCamera(int numarCamera)
        {
            this.numarCamera = numarCamera;
        }

        public double getPretNoapte()
        {
            return pretNoapte;
        }

        public void setPretNoapte(double pretNoapte)
        {
            this.pretNoapte = pretNoapte;
        }

        public boolean isDisponibil()
        {
            return disponibil;
        }

        public void setDisponibil(boolean disponibil)
        {
            this.disponibil = disponibil;
        }

        public TipCamera getTipCamera()
        {
            return tipCamera;
        }

        public void setTipCamera(TipCamera tipCamera)
        {
            this.tipCamera = tipCamera;
        }

        public Hotel getHotel()
        {
            return hotel;
        }

        public void setHotel(Hotel hotel)
        {
            this.hotel = hotel;
        }

        public String getFacilitati()
        {
            return facilitati;
        }

        public void setFacilitati(String facilitati)
        {
            this.facilitati = facilitati;
        }

        @Override
        public String toString()
        {
            return "Camera{" + "numar=" + numarCamera + ", pret=" + pretNoapte + "}";
        }
    }