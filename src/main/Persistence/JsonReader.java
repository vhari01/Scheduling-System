package persistence;

import model.Patient;
import model.Scheduler;
import model.Specialist;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;

    }

    // EFFECTS: reads scheduler from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Scheduler read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScheduler(jsonObject);

    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: scheduler
    // EFFECTS: parses patients from JSON object and adds them to scheduler
    public Scheduler parseScheduler(JSONObject jsonObject) {
        Scheduler scheduler = new Scheduler();
        addPatients(scheduler, jsonObject);
        return scheduler;
    }

    // MODIFIES: scheduler
    // EFFECTS: parses patients from JSON object and adds them to scheduler
    public void addPatients(Scheduler scheduler, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("patients");
        for (Object json : jsonArray) {
            JSONObject nextPatient = (JSONObject) json;
            addPatient(scheduler, nextPatient);
        }
    }

    // MODIFIES: scheduler
    // EFFECTS: parses patient from JSON object and adds them to scheduler
    public void addPatient(Scheduler scheduler, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        int emergencyLevel = jsonObject.getInt("emergencyLevel");
        boolean hasInsurance = jsonObject.getBoolean("hasInsurance");

        JSONObject specialistObject = jsonObject.getJSONObject("specialist");
        String specialistType = specialistObject.getString("specialistName");
        Specialist specialist = new Specialist(specialistType);

        String appointmentDateStr = jsonObject.getString("appointmentDate");
        LocalDate appointmentDate = LocalDate.parse(appointmentDateStr);

        Patient patient = new Patient(name, age, hasInsurance, emergencyLevel, specialist, appointmentDate);
        scheduler.addPatient(patient);
    }

}
