package model;

import java.time.LocalDate;
/*A patient class that takes patients data.
 The class has some fields required for patient data, 
there is a constructor and all the attributes have their getters and setters8*/

public class Patient implements Comparable<Patient> {

    // attributes
    private String patientName;     // name of the patient
    private int patientAge;         // age of the patient
    private boolean insuranceStatus;   // if the patient has insuranc or not
    private int levelOfEmergency;      // Seriousness level of patient's problem (1 being the leaset and 5 being the
                                    // highest)
    private Specialist specialistRequired; // Specialist object for type of specialist
    private LocalDate appointmentDate; // Date of the booked appointement
    private String bookingId;  //booking id for the patient

    // REQUIRES: A String name, age > 0, insurance, 1>= emergency level <=5, a
    // specialist type and a date >=current date
    // EFFECTS : Creates a patient object with name, age, insurance, emergency level
    // and specialist type

    public Patient(String name, int age, boolean insurance, int emergencyLevel, Specialist neededSpecialist,
            LocalDate date, String bookingId) {
        this.patientName = name;
        this.patientAge = age;
        this.insuranceStatus = insurance;
        this.levelOfEmergency = emergencyLevel;
        this.specialistRequired = neededSpecialist;
        this.appointmentDate = date;
        this.bookingId = bookingId;
    }

    // Getters and setters for patient's name
    public void setPatientName(String name) {
        this.patientName = name;
    }

    public String getPatientName() {
        return this.patientName;
    }

    // Getters and setters for patient's age
    public void setAge(int age) {
        this.patientAge = age;
    }

    public int getAge() {
        return this.patientAge;
    }

    // Getters and setters for patient's insurance status
    public void setInsuranceStatus(boolean insurance) {
        this.insuranceStatus = insurance;
    }

    public boolean getInsuranceStatus() {
        return this.insuranceStatus;
    }

    // Getters and setters for patient's level of emergency
    public void setLevelOfEmergency(int emergencyLevel) {
        this.levelOfEmergency = emergencyLevel;
    }

    public int getLevelOfEmergency() {
        return this.levelOfEmergency;
    }

    // Getters and setters for specialist required by patient
    public void setspecialistRequired(Specialist newSpecialist) {
        this.specialistRequired = newSpecialist;
    }

    public Specialist getspecialistRequired() {
        return this.specialistRequired;
    }

    // Getters and setters for patient's appointment date
    public void setAppointmentDate(LocalDate date) {
        this.appointmentDate = date;
    }

    public LocalDate getAppointementDate() {
        return this.appointmentDate;
    }
    
    public String getBookingId() {
        return bookingId;
    }

    // REQUIRES: Other patient != null
    // EFFECTS : - Returns -1 if this patient's emergency level is higher than the
    // other patient's emergency level
    // and both share the same appointment date and specialist.
    // - Returns 1 if this patient's emergency level is lower than the other
    // patient's emergency level
    // and both share the same appointment date and specialist.
    // - Returns 0 if the emergency levels are equal and both share the same
    // appointment date and specialist,
    // or if they do not share the same appointment date or specialist.

    @Override
    public int compareTo(Patient other) {
        if (this.appointmentDate.equals(other.appointmentDate)
                &&( this.getspecialistRequired() == other.getspecialistRequired()) ){
            if (this.levelOfEmergency > other.levelOfEmergency) {
                return -1;
            } else if (this.levelOfEmergency < other.levelOfEmergency) {
                return 1;
            } else {
                return 0;
            }

        } else {
            return 0;
        }
    }

}