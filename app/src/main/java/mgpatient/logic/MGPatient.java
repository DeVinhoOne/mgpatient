package mgpatient.logic;

import mgpatient.data.DatabaseConnectivity;
import mgpatient.domain.Patient;

public class MGPatient {

    private final DatabaseConnectivity database = new DatabaseConnectivity();

    public void createPatient(String name, String surname, String phoneNumber) {
        Patient patient = new Patient(name, surname, phoneNumber);
        this.database.insertPatient(patient);
    }
}
