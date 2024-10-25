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
        listOfPatients.add(p1);
        return bookingId;
    }

    // EFFECTS : Generates a random unique booking id
    public String generateBookingId() {
        String bookingId;

        do {
            bookingId = String.valueOf((int) (Math.random() * 900000)); // Generate random 6-digit number
        } while (bookingIds.contains(bookingId)); // Ensure the booking ID is unique
        bookingIds.add(bookingId); // Add the new booking ID to the list
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
            return false;
        }

        for (Patient patient : listOfPatients) {
            if (patient.getBookingId().equals(bookingId)) {
                patient.setAppointmentDate(newAppointmentDate);
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
        }
    }

    public ArrayList<Patient> getScheduledPatients() {
        return new ArrayList<>(this.listOfPatients);
    }

}
