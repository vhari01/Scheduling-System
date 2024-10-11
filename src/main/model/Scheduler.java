// This class manages the appointmenet schedules the patients acoording to their problem. One can Add patinet or treat next patient and sorting them on priority
package model;
import java.util.*;
import java.time.*;


public class Scheduler {
    private ArrayList <Patient> listOfPatients;

    //EFFECTS: Creates a generic arraylist of type patients 
    public Scheduler(){
        this.listOfPatients = new ArrayList();

    }
    //REQUIRES: patient != null
    //MODIFIES: this
    //EFFECTS:  adds the given patient to the patientList.

    public void addPatient(Patient p1){
        listOfPatients.add(p1);
    }

    //REQUIRES: patient name, patient age, specialistrequired should not be null and date >= present date
    //MODIFIES: this
    //EFFECTS: removes the patient from patientList if a patient with the given name, age
          // appointmentDate, and specialistRequired exists. It will return true if canceled,
          // otherwise returns false.

    public boolean cancelAppointment(String name, int age, LocalDate appointmentDate, String specialistRequired){
        for(Patient patient : listOfPatients ){
            if(patient.getPatientName().equals(name) && patient.getAge()== age && patient.getAppointementDate().equals(appointmentDate)&& patient.getspecialistRequired().equals(specialistRequired)){
                listOfPatients.remove(patient);
                return true;
            }
            
        }
        return false;

    }
    
    //REQUIRES: a list consisting of patients
    //EFFECTS: returns a new list of patients sorted by emergency level in descending order i.e. higher the emergecy level priority will be given

    public ArrayList<Patient> sortPatientsByPriority(){
        ArrayList <Patient> patientsSorted = new ArrayList<>(listOfPatients);    //creates a copy of the original list
        Collections.sort(patientsSorted);           //sorts it out using compare to 
        return patientsSorted;
         
    }

    //REQUIRES: arraylist of patients should not be empty
    //MODIFIES: this
    //EFFECTS: removes the highest priority patient from the list

    public void treatNextPatient(){
        if(!listOfPatients.isEmpty()){
        ArrayList <Patient> sortedPatients = sortPatientsByPriority();  //gets the sorted arraylist
        Patient nextPatient = sortedPatients.get(0);              // gets the first element in the list and removes it considering as treated
        listOfPatients.remove(nextPatient);
        }             
    }
    
    public ArrayList<Patient> getScheduledPatients() {
        return new ArrayList<>(this.listOfPatients); 
    }
    
    
    
}

