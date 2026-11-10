    public class Client
    {
        private int id;
        private String nume;
        private String email;
        private String telefon;
        private int discount;
        public Client() {}

        public Client(int id, String nume, String email, String telefon, int discount) {
            this.id = id;
            this.nume = nume;
            this.email = email;
            this.telefon = telefon;
            this.discount = discount;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefon() {
            return telefon;
        }

        public void setTelefon(String telefon) {
            this.telefon = telefon;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }
        @Override
        public String toString() {
            return "Client{" +
                    "id=" + id +
                    ", nume='" + nume + '\'' +
                    ", email='" + email + '\'' +
                    ", telefon='" + telefon + '\'' +
                    ", discount=" + discount +
                    '}';
        }
    }
