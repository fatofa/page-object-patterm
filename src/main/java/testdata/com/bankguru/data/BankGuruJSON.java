package testdata.com.bankguru.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class BankGuruJSON {

    public static BankGuruJSON getJsonData(String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(filename), BankGuruJSON .class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("email")
    private String emailAddress;

    @JsonProperty("street")
    private String streetAddress;

    @JsonProperty("city")
    private String city;

    @JsonProperty("phone")
    private String phoneNumber;

    @JsonProperty("bankGuruInfoCard")
    BankGuruInfoCard bankGuruInfoCard;

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getVisa() {
        return bankGuruInfoCard.visa;
    }

    public String getDate() {
        return bankGuruInfoCard.date;
    }

    public String getCvv() {
        return bankGuruInfoCard.cvv;
    }


    public static class BankGuruInfoCard {
        @JsonProperty("visa")
        private String visa;

        @JsonProperty("date")
        private String date;

        @JsonProperty("cvv")
        private String cvv;
    }

}
