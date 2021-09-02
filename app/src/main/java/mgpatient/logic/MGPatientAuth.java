package mgpatient.logic;

import mgpatient.data.DatabaseConnectivity;
import mgpatient.domain.User;
import mgpatient.util.Validator;

public class MGPatientAuth {

    private final DatabaseConnectivity database = new DatabaseConnectivity();

    public User signIn(String email, String password) {
        User user = this.database.selectUser(email, password);
        return user;
    }

    public boolean createUser(String name, String surname, String phoneNumber, String email, String password) {
        User user = new User(name, surname, phoneNumber, email, password);
        if (!Validator.valCreateUser(user)) {
            return false;
        }
        this.database.insertUser(user);
        return true;
    }
}
