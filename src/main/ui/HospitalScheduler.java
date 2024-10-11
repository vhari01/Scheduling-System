package ui;
import model.Patient;
import model.Scheduler;

import java.util.*;
import java.time.LocalDate;

public class HospitalScheduler {

    private Scheduler schedule;
    Scanner sc;

    public HospitalScheduler(){
        schedule = new Scheduler();
        sc = new Scanner(System.in);
    }
    //EFFECTS: Runs the hospital scheduler app
    public void runHospital(){

    }
    //MODIFIES: this
    //EFFECTS: processes user input
    public void showMenu(){

    }
    //EFFECTS: displays menu of options to user with keys
    public void checkCommand(){

    }
    //MODIFIES: this
    //EFFECTS: processes user command
    public void patientAddition(){

    }
    //MODIFIES: this
    //EFFECTS: adds a new patient to the scheduler
    public void patientAppointmentCancel(){

    }
    //MODIFIES: this
    //EFFECTS: cancels a patient appointment if it exists
    public void sortingOutPatients(){

    }
    //EFFECTS: displays patients sorted by emergency level
    public void treatingPatient(){

    }
}
