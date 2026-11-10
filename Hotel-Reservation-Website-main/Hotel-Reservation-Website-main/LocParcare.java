    public class LocParcare
    {
        private int id;
        private int numarLoc;
        private boolean ocupat;
        private Client client;

        public LocParcare() {};

        public LocParcare(int id, int numarLoc, boolean ocupat, Client client) {
            this.id = id;
            this.numarLoc = numarLoc;
            this.ocupat = ocupat;
            this.client = client;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumarLoc() {
            return numarLoc;
        }

        public void setNumarLoc(int numarLoc) {
            this.numarLoc = numarLoc;
        }

        public boolean isOcupat() {
            return ocupat;
        }

        public void setOcupat(boolean ocupat) {
            this.ocupat = ocupat;
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }
        @Override
        public String toString() {
            return "LocParcare{" +
                    "id=" + id +
                    ", numarLoc=" + numarLoc +
                    ", ocupat=" + ocupat +
                    ", client=" + client +
                    '}';
        }
    }
