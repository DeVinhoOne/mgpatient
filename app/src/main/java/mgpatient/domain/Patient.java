package mgpatient.domain;

public class Patient extends Person{

    private int id;

    public Patient(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
    }

    public Patient(String name, String surname, String phoneNumber, int id) {
        super(name, surname, phoneNumber);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
