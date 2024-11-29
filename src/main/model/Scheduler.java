package model;

import java.util.*;
import java.time.*;
/*  This class manages the appointmenet schedules the patients
 acoording to their problem. One can Add patinet or treat
  next patient and sorting them on priority */

public class Scheduler {
    private ArrayList<Patient> listOfPatients;
    private ArrayList<String> bookingIds;

    // EFFECTS: Creates a generic arraylist of type patients
    public Scheduler() {
        this.listOfPatients = new ArrayList<Patient>();
        this.bookingIds = new ArrayList<String>();

    }
    // REQUIRES: patient != null
    // MODIFIES: this
    // EFFECTS: adds the given patient to the patientList.

    public String addPatient(Patient p1) {
        String bookingId = generateBookingId();
        p1.setBookingId(bookingId);
        insertPatientInSortedOrder(p1); // Add to sorted position
        EventLog.getInstance().logEvent(new Event("Patient added: " + p1.getPatientName() + ", Booking ID: " + bookingId));
        return bookingId;
        
    }
    
    public void insertPatientInSortedOrder(Patient patient) {
        int position = 0;
        for (; position < listOfPatients.size(); position++) {
            if (patient.compareTo(listOfPatients.get(position)) < 0) {
                break; // Find the right spot
            }
        }
        listOfPatients.add(position, patient); // Insert at correct position
        EventLog.getInstance().logEvent(new Event("Patient reordered: " + patient.getPatientName()));

    }
    

    // EFFECTS : Generates a random unique booking id
    public String generateBookingId() {
        String bookingId;

        do {
            bookingId = String.valueOf((int) (Math.random() * 900000)); // Generate random 6-digit number
        } while (bookingIds.contains(bookingId)); // Ensure the booking ID is unique
        bookingIds.add(bookingId); // Add the new booking ID to the list
        EventLog.getInstance().logEvent(new Event("Booking ID generated: "+ bookingId));

        return bookingId;
    }

    // REQUIRES: patient name, patient age, specialist required should not be null
    // and date >= present date
    // MODIFIES: this
    // EFFECTS: removes the patient from patientList if a patient with the given
    // name, age
    // appointmentDate, and specialistRequired exists. It will return true if
    // canceled,
    // otherwise returns false.

    public boolean cancelAppointment(String bookingId) {
        Iterator<Patient> itr = listOfPatients.iterator();
        while (itr.hasNext()) {
            Patient p1 = itr.next();
            if (p1.getBookingId().equals(bookingId)) {
                itr.remove();
                bookingIds.remove(bookingId);
                EventLog.getInstance().logEvent(new Event("Appointment canceled for Booking ID: " + bookingId));
                return true;
                
            }
        }

        return false;
        
    }

    // REQUIRES: !(new date < current date)
    // MODIFIES: Appointment date
    // EFFECTS: changes the current appointment date to a new appoitment date
    public boolean rescheduleAppointment(String bookingId, LocalDate newAppointmentDate) {
        if (newAppointmentDate.isBefore(LocalDate.now())) {
            return false; // Do not allow past dates
        }
    
        for (int i = 0; i < listOfPatients.size(); i++) {
            Patient patient = listOfPatients.get(i);
            if (patient.getBookingId().equals(bookingId)) {
                listOfPatients.remove(i); 
                patient.setAppointmentDate(newAppointmentDate); 
                insertPatientInSortedOrder(patient); 
                EventLog.getInstance().logEvent(new Event("Appointment rescheduled for Booking ID: " + bookingId +
                " from " + patient.getAppointementDate() + " to " + newAppointmentDate));
            
                return true;
            }
        }
        return false; 
    }
    

    // REQUIRES: a list consisting of patients
    // EFFECTS: returns a new list of patients sorted by emergency level in
    // descending order i.e. higher the emergecy level priority will be given

    public ArrayList<Patient> sortPatientsByPriority() {
        ArrayList<Patient> patientsSorted = new ArrayList<>(listOfPatients); // creates a copy of the original list
        Collections.sort(patientsSorted); // sorts it out using compare to
        EventLog.getInstance().logEvent(new Event("Patients sorted by priority. Total patients: " + patientsSorted.size()));
        return patientsSorted;
        

    }

    // REQUIRES: arraylist of patients should not be empty
    // MODIFIES: this
    // EFFECTS: removes the highest priority patient from the list

    public void treatNextPatient() {
        if (!listOfPatients.isEmpty()) {
            ArrayList<Patient> sortedPatients = sortPatientsByPriority(); // gets the sorted arraylist
            Patient nextPatient = sortedPatients.get(0); // gets the first element in the list and removes it
                                                         // considering as treated
            listOfPatients.remove(nextPatient);
            EventLog.getInstance().logEvent(new Event("Patient treated: " + nextPatient.getPatientName() +
            ", Emergency level: " + nextPatient.getLevelOfEmergency()));
        }
    }

    public ArrayList<Patient> getScheduledPatients() {
        EventLog.getInstance().logEvent(new Event("Scheduled patients list accessed. Total: " + listOfPatients.size()));
        return new ArrayList<>(this.listOfPatients);
        
    }
    
    public void setListOfPatients(ArrayList<Patient> newList) {
        this.listOfPatients = newList;
        EventLog.getInstance().logEvent(new Event("Patient list replaced. New size: " + newList.size()));

    }

}
