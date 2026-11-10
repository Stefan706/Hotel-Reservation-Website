    import java.time.LocalDate;

    public class Rezervare
    {
        private int id;
        private Client client;
        private Camera camera;
        private String tipCamera;
        private LocalDate dataCheckIn;
        private LocalDate dataCheckOut;
        private double pretTotal;

        public Rezervare()
        {
        }

        public Rezervare(int id, Client client, Camera camera, String tipCamera, LocalDate dataCheckIn, LocalDate dataCheckOut, double pretTotal)
        {
            this.id = id;
            this.client = client;
            this.camera = camera;
            this.tipCamera = tipCamera;
            this.dataCheckIn = dataCheckIn;
            this.dataCheckOut = dataCheckOut;
            this.pretTotal = pretTotal;
        }

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public Client getClient()
        {
            return client;
        }

        public void setClient(Client client)
        {
            this.client = client;
        }

        public Camera getCamera()
        {
            return camera;
        }

        public void setCamera(Camera camera)
        {
            this.camera = camera;
        }

        public String getTipCamera()
        {
            return tipCamera;
        }

        public void setTipCamera(String tipCamera)
        {
            this.tipCamera = tipCamera;
        }

        public LocalDate getDataCheckIn()
        {
            return dataCheckIn;
        }

        public void setDataCheckIn(LocalDate dataCheckIn)
        {
            this.dataCheckIn = dataCheckIn;
        }

        public LocalDate getDataCheckOut()
        {
            return dataCheckOut;
        }

        public void setDataCheckOut(LocalDate dataCheckOut)
        {
            this.dataCheckOut = dataCheckOut;
        }

        public double getPretTotal()
        {
            return pretTotal;
        }

        public void setPretTotal(double pretTotal)
        {
            this.pretTotal = pretTotal;
        }

        @Override
        public String toString()
        {
            return "Rezervare{" +
                    "id=" + id +
                    ", client=" + (client != null ? client.getNume() : "null") +
                    ", camera=" + (camera != null ? camera.getNumarCamera() : "null") +
                    ", pretTotal=" + pretTotal +
                    '}';
        }
    }