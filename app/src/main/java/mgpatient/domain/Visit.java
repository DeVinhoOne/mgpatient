package mgpatient.domain;

public class Visit {

    private int patientId;
    private String description;
    private boolean urgent;
    private int room;
    private String doctor;
    private int doctorId;

    public Visit(int patientId, String description, boolean urgent, int room, String doctor, int doctorId) {
        this.patientId = patientId;
        this.description = description;
        this.urgent = urgent;
        this.room = room;
        this.doctor = doctor;
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public int getRoom() {
        return room;
    }

    public String getDoctor() {
        return doctor;
    }

    public int getDoctorId() {
        return doctorId;
    }
}
