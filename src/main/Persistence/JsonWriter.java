package Persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Patient;
import model.Scheduler;

public class JsonWriter {

    // EFFECTS: constructs writer to write to the destination file
    public JsonWriter(String destination) {
    }
    // REQUIRES: scheduler is not null
    // MODIFIES: this
    // EFFECTS: writes JSON representation of scheduler to file
    public void write(Scheduler scheduler)  {
    }

    // EFFECTS: converts scheduler to JSON object
    private JSONObject schedulerToJson(Scheduler scheduler) {
    }

    // EFFECTS: converts list of patients to JSON array
    private JSONArray patientsToJson(Scheduler scheduler) {
    }
    // REQUIRES: patient is not null
    // EFFECTS: converts a patient to JSON object
    private JSONObject patientToJson(Patient patient) {
    }
}
