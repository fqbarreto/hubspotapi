package fqbarreto.hubspotapi.model;
import com.fasterxml.jackson.annotation.JsonProperty;

// Incompleto, já que a API do hubSpot permite fazer associação de contatos com companhias
// porém o desafio não mencionava integração com as companhias, então optei por deixar sem.
public class ContactRequestModel {

    public static class Properties {

        private String email;
        private String firstname;
        private String lastname;
        private String phone;
        private String company;
        private String website;
        private String lifecyclestage;

        public Properties(String email, String firstname, String lastname, String phone, String company, String website, String lifecyclestage) {
            this.email = email;
            this.firstname = firstname;
            this.lastname = lastname;
            this.phone = phone;
            this.company = company;
            this.website = website;
            this.lifecyclestage = lifecyclestage;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getLifecyclestage() {
            return lifecyclestage;
        }

        public void setLifecyclestage(String lifecyclestage) {
            this.lifecyclestage = lifecyclestage;
        }
    }

    @JsonProperty("properties")
    private Properties properties;

    public ContactRequestModel(String email, String firstname, String lastname, String phone, String company, String website, String lifecyclestage) {
        this.properties = new Properties(email, firstname, lastname, phone, company, website, lifecyclestage);
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
