    public class Hotel
    {
        private int id;
        private String nume;
        private String adresa;

        public Hotel(int id, String nume, String adresa) {
            this.id = id;
            this.nume = nume;
            this.adresa = adresa;
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

        public String getAdresa() {
            return adresa;
        }

        public void setAdresa(String adresa) {
            this.adresa = adresa;
        }
        @Override
        public String toString()
        {
            return "Hotel{" + "id=" + id + ", nume='" + nume + '\'' + ", adresa='" + adresa + '\'' + '}';
        }
    }
