package mgpatient.util;

public class Validator {

    public static boolean validateStringNotNullInput(String[] args) {
        for (String arg : args) {
            if (arg == null || arg.isBlank() || arg.equalsIgnoreCase("null")) {
                return false;
            }
        }
        return true;
    }
}
