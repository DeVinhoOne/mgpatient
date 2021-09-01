package mgpatient.domain;

public class User extends Person {

    private String email;

    public User(String name, String surname, String phoneNumber, String email) {
        super(name, surname, phoneNumber);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
