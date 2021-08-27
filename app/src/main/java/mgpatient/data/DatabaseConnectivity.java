package mgpatient.data;

import mgpatient.domain.Doctor;
import mgpatient.domain.Patient;
import mgpatient.domain.Visit;

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
        String createPatients = "CREATE TABLE IF NOT EXISTS patients (" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "surname VARCHAR(50) NOT NULL," +
                "phone_number VARCHAR(15) NOT NULL," +
                "date_created DATE NOT NULL)";
        String createDoctors = "CREATE TABLE IF NOT EXISTS doctors (" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "surname VARCHAR(50) NOT NULL," +
                "specialization VARCHAR(200)," +
                "phone_number VARCHAR(15) NOT NULL," +
                "email VARCHAR(100)," +
                "date_created DATE NOT NULL)";
        String createVisits = "CREATE TABLE IF NOT EXISTS visits (" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "patient_id INT NOT NULL," +
                "description TEXT," +
                "urgent TINYINT(1) NOT NULL," +
                "room INT," +
                "doctor VARCHAR(100) NOT NULL," +
                "doctor_id INT NOT NULL," +
                "FOREIGN KEY (patient_id) REFERENCES patients (id)," +
                "FOREIGN KEY (doctor_id) REFERENCES doctors (id))";
        try (PreparedStatement createPatientsStmt = this.connection.prepareStatement(createPatients);
             PreparedStatement createDoctorsStmt = this.connection.prepareStatement(createDoctors);
             PreparedStatement createVisitsStmt = this.connection.prepareStatement(createVisits)) {
            createPatientsStmt.executeUpdate();
            createDoctorsStmt.executeUpdate();
            createVisitsStmt.executeUpdate();
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
            System.out.println("Cannot add PATIENT to database. " + e.getMessage());
            return false;
        }
    }

    public boolean insertDoctor(Doctor doctor) {
        String insertQuery = "INSERT INTO doctors " +
                "(name, surname, specialization, phone_number, email, date_created)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSurname());
            preparedStatement.setString(3, doctor.getSpecialization());
            preparedStatement.setString(4, doctor.getPhoneNumber());
            preparedStatement.setString(5, doctor.getEmail());
            preparedStatement.setDate(6, new Date(doctor.getDateCreated().getTime()));
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Cannot add DOCTOR to database. " + e.getMessage());
            return false;
        }
    }

    public boolean insertVisit(Visit visit) {
        String insertQuery = "INSERT INTO visits " +
                "(patient_id, description, urgent, room, doctor, doctor_id)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, visit.getPatientId());
            preparedStatement.setString(2, visit.getDescription());
            preparedStatement.setBoolean(3, visit.isUrgent());
            preparedStatement.setInt(4, visit.getRoom());
            preparedStatement.setString(5, visit.getDoctor());
            preparedStatement.setInt(6, visit.getDoctorId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Cannot add VISIT to database. " + e.getMessage());
            return false;
        }
    }

    public Patient selectPatient(String name, String surname) {
        String selectQuery = "SELECT * FROM patients WHERE name = ? AND surname = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Patient(resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getString("phone_number"), resultSet.getInt("id"));
        } catch (SQLException e) {
            System.out.println("Cannot find PATIENT in database. " + e.getMessage());
            return null;
        }
    }

    public Doctor selectDoctor(String name, String surname) {
        String selectQuery = "SELECT * FROM doctors WHERE name = ? AND surname = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Doctor(resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getString("phone_number"), resultSet.getString("specialization"),
                    resultSet.getString("email"), resultSet.getInt("id"));
        } catch (SQLException e) {
            System.out.println("Cannot find DOCTOR in database. " + e.getMessage());
            return null;
        }
    }
}
