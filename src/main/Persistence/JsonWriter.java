package persistence;

import model.Patient;
import model.Scheduler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to the destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    // cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // REQUIRES: scheduler is not null
    // MODIFIES: this
    // EFFECTS: writes JSON representation of scheduler to file
    public void write(Scheduler scheduler) {
        JSONObject json = schedulerToJson(scheduler);
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // EFFECTS: converts scheduler to JSON object
    private JSONObject schedulerToJson(Scheduler scheduler) {
        JSONObject json = new JSONObject();
        json.put("patients", patientsToJson(scheduler));
        return json;
    }

    // EFFECTS: converts list of patients to JSON array
    private JSONArray patientsToJson(Scheduler scheduler) {
        JSONArray jsonArray = new JSONArray();
        for (Patient patient : scheduler.getScheduledPatients()) {
            jsonArray.put(patientToJson(patient));
        }
        return jsonArray;
    }

    // REQUIRES: patient is not null
    // EFFECTS: converts a patient object to JSON object
    private JSONObject patientToJson(Patient patient) {
        JSONObject json = new JSONObject();
        json.put("name", patient.getPatientName());
        json.put("age", patient.getAge());
        json.put("emergencyLevel", patient.getLevelOfEmergency());
        json.put("hasInsurance", patient.getInsuranceStatus());
        json.put("appointmentDate", patient.getAppointementDate().toString());

        // Convert specialist to JSON object
        JSONObject specialistJson = new JSONObject();
        specialistJson.put("specialistName", patient.getspecialistRequired().getSpecialistName());
        json.put("specialist", specialistJson);

        return json;
    }
}
