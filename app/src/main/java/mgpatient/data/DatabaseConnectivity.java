package mgpatient.data;

import mgpatient.domain.Patient;

import java.sql.*;

public class DatabaseConnectivity {

    private Connection connection;

    public DatabaseConnectivity() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mgpatient", "root", "admin");
            this.createTables();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    private void createTables() {
        try (Statement stmt = this.connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS patients (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "surname TEXT NOT NULL," +
                    "phone_number TEXT NOT NULL," +
                    "date_created DATE)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS visits (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "patient_id INT NOT NULL," +
                    "urgent BOOLEAN NOT NULL," +
                    "room INT," +
                    "doctor TEXT NOT NULL," +
                    "FOREIGN KEY (patient_id) REFERENCES patients (id))");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public boolean insertPatient(Patient patient) {
        String insertQuery = "INSERT INTO patients (name, surname, phone_number, date_created) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getSurname());
            preparedStatement.setString(3, patient.getPhoneNumber());
            preparedStatement.setDate(4, new Date(patient.getDateCreated().getTime()));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Cannot add patient to database. " + e.getMessage());
            return false;
        }
    }
}
