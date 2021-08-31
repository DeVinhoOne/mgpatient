package mgpatient.util;

import mgpatient.domain.Doctor;
import mgpatient.domain.Patient;

public class Validator {

    public static boolean valCreatePatient(Patient patient) {
        // Validate not null input
        if (!validateNotNullInput(new String[] {patient.getName(), patient.getSurname(), patient.getPhoneNumber()})) {
            return false;
        }
        if (!validatePhoneNumber(patient.getPhoneNumber())) {
            System.out.println("\nInvalid phone number: " + patient.getPhoneNumber());
            return false;
        }
        if (!validateName(patient.getName()) || !validateName(patient.getSurname())) {
            System.out.println("\nInvalid name/surname: " + patient.getName() + " " + patient.getSurname());
            return false;
        }
        return true;
    }

    public static boolean valCreateDoctor(Doctor doctor) {
        // Validate not null input
        if (!validateNotNullInput(new String[] {doctor.getName(), doctor.getSurname(), doctor.getPhoneNumber(), doctor.getSpecialization(), doctor.getEmail()})) {
            return false;
        }
        if (!validatePhoneNumber(doctor.getPhoneNumber())) {
            System.out.println("\nInvalid phone number." + doctor.getPhoneNumber());
            return false;
        }
        if (!validateName(doctor.getName()) || !validateName(doctor.getSurname())) {
            System.out.println("\nInvalid name/surname: " + doctor.getName() + " " + doctor.getSurname());
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

    private static boolean validateName(String name) {
        if (name.length() < 2 || name.contains(" ")) {
            return false;
        }
        for (char el : name.toCharArray()) {
            if (!Character.isAlphabetic(el)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        //accepted format: +48123456789
        if (phoneNumber.length() != 12 ||
            phoneNumber.charAt(0) != '+' ||
            phoneNumber.contains(" ")) {
            return false;
        }
        //false if char is not a digit or plus if first
        for (int i = 1; i < phoneNumber.length(); i++) {
            char digit = phoneNumber.charAt(i);
            if (!Character.isDigit(digit)) {
                return false;
            }
        }
        return true;
    }
}
