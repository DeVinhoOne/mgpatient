package mgpatient.util;

import mgpatient.domain.Doctor;
import mgpatient.domain.Patient;

public class Validator {

    public static boolean valCreatePatient(Patient patient) {
        // Validate not null input
        if (!validateNotNullInput(new String[] {patient.getName(), patient.getSurname(), patient.getPhoneNumber()})) {
            return false;
        }
        return true;
    }

    public static boolean valCreateDoctor(Doctor doctor) {
        // Validate not null input
        if (!validateNotNullInput(new String[] {doctor.getName(), doctor.getSurname(), doctor.getPhoneNumber()})) {
            return false;
        }
        return true;
    }

    private static boolean validateNotNullInput(String[] args) {
        for (String arg : args) {
            if (arg == null || arg.isBlank() || arg.equalsIgnoreCase("null")) {
                return false;
            }
        }
        return true;
    }
}
