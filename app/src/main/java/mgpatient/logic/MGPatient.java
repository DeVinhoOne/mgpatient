package mgpatient.logic;

import mgpatient.data.DatabaseConnectivity;
import mgpatient.domain.Doctor;
import mgpatient.domain.Patient;
import mgpatient.domain.Visit;

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

    public void createNewVisit(String pName, String pSurname, String dName, String dSurname, String description, boolean urgent, int room) {
        //Get patient from db
        Patient patient = this.database.selectPatient(pName, pSurname);
        if (patient == null) {
            System.out.println("\nError: Patient " + pName + " " + pSurname + " does not exist.");
            return;
        }
        //Get doctor from db
        Doctor doctor = this.database.selectDoctor(dName, dSurname);
        if (doctor == null) {
            System.out.println("\nError: Doctor " + dName + " " + dSurname + " does not exist.");
            return;
        }
        //Create new visit and save in db
        Visit visit = new Visit(patient.getId(), description, urgent, room, doctor.toString(), doctor.getId());
        if (this.database.insertVisit(visit)) {
            System.out.println("\nNew visit has been created and saved in database.");
        } else {
            System.out.println("\nSomething went wrong. Cannot save visit in database.");
        }
    }
}
