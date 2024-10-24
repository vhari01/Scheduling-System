package Persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Patient;
import model.Scheduler;

public class JsonWriter {

    // EFFECTS: constructs writer to write to the destination file
    public JsonWriter(String destination) {
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() {
    }

    // REQUIRES: scheduler is not null
    // MODIFIES: this
    // EFFECTS: writes JSON representation of scheduler to file
    public void write(Scheduler scheduler)  {
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
    }

    // EFFECTS: converts scheduler to JSON object
    private JSONObject schedulerToJson(Scheduler scheduler) {
    }

    // EFFECTS: converts list of patients to JSON array
    private JSONArray patientsToJson(Scheduler scheduler) {
    }
    // REQUIRES: patient is not null
    // EFFECTS: converts a patient object to JSON object
    private JSONObject patientToJson(Patient patient) {
    }
}
