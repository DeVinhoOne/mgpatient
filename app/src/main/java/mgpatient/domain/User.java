package mgpatient.domain;

public class User extends Person {

    private String email;
    private String password;

    public User(String name, String surname, String phoneNumber, String email, String password) {
        super(name, surname, phoneNumber);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
}
