package mgpatient.domain;

public class Doctor extends Person{

    private String specialization;
    private String email;

    public Doctor(String name, String surname, String phoneNumber, String specialization, String email) {
        super(name, surname, phoneNumber);
        this.specialization = specialization;
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getSurname();
    }
}
