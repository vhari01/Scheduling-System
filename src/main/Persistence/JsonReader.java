package Persistence;

import model.Patient;
import model.Scheduler;
import model.Specialist;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.*;

public class JsonReader {
    private String source;
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source){

    }
    // EFFECTS: reads scheduler from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Scheduler read() throws IOException{

    }
    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException{

    }
    // MODIFIES: scheduler
    // EFFECTS: parses patients from JSON object and adds them to scheduler
    public Scheduler parseScheduler(JSONObject jsonObject){

    }

    // MODIFIES: scheduler
    // EFFECTS: parses patients from JSON object and adds them to scheduler
    public void addPatients(Scheduler scheduler, JSONObject jsonObject){

    }
    // MODIFIES: scheduler
    // EFFECTS: parses patient from JSON object and adds them to scheduler
    public void addPatient(Scheduler scheduler, JSONObject jsonObject){

    }

}
