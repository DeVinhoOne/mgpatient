package mgpatient.logic;

import mgpatient.data.DatabaseConnectivity;
import mgpatient.domain.Doctor;
import mgpatient.domain.Patient;

public class MGPatient {

    private final DatabaseConnectivity database = new DatabaseConnectivity();

    public void createPatient(String name, String surname, String phoneNumber) {
        Patient patient = new Patient(name, surname, phoneNumber);
        this.database.insertPatient(patient);
    }

    public void createDoctor(String name, String surname, String phoneNumber, String specialization, String email) {
        Doctor doctor = new Doctor(name, surname, phoneNumber, specialization, email);
        this.database.insertDoctor(doctor);
    }
}
