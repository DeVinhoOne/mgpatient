package mgpatient.domain;

import java.util.Date;

public class Patient {

    private final String name;
    private final String surname;
    private String phoneNumber;
    private final Date dateCreated;

    public Patient(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.dateCreated = new Date();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + "\nPhone number: " + this.phoneNumber;
    }
}
