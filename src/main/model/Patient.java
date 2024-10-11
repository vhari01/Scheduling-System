// A patient class that takes patients data. The class has some fields required for patient data, there is a constructor and all the attributes have their getters and setters
package model;

import java.time.LocalDate;

public class Patient {

    //attributes
    private String patientName;
    private int patientAge;
    private boolean insuranceStatus;
    private int levelOfEmergency;
    private String specialistRequired;
    private LocalDate appointmentDate;

    //REQUIRES: A String name, age > 0, insurance, 1>= emergency level <=5, a specialist type and a date >=current date
    //EFFECTS : Creates a patient object with name, age, insurance, emergency level and specialist type
    public Patient (String name, int age, boolean insurance, int emergencyLevel, String neededSpecialist, LocalDate date){
        this.patientName = name;
        this.patientAge = age;
        this.insuranceStatus = insurance;
        this.levelOfEmergency = emergencyLevel;
        this.specialistRequired = neededSpecialist;
        this.appointmentDate = date;
    }

     //Getters and setters for patient's name
     public void setPatientName(String name){
        this.patientName = name;
    }
    public String getPatientName(){
        return this.patientName;
    }

    //Getters and setters for patient's age
    public void setAge(int age){
        this.patientAge = age;
    }
    public int getAge(){
        return this.patientAge;
    }


    //Getters and setters for patient's insurance status
    public void setInsuranceStatus(boolean insurance){
        this.insuranceStatus = insurance;
    }
    public boolean getInsuranceStatus(){
        return this.insuranceStatus;
    }


    //Getters and setters for patient's level of emergency
    public void setLevelOfEmergency(int emergencyLevel){
        this.levelOfEmergency = emergencyLevel;
    }
    public int getLevelOfEmergency(){
        return this.levelOfEmergency;
    }


    //Getters and setters for specialist required by patient 
    public void setspecialistRequired(String neededSpecialist){
        this.specialistRequired = neededSpecialist;   
    }
    public String getspecialistRequired(){
        return this.specialistRequired;
    }


    //Getters and setters for patient's appointment date
    public void setAppointmentDate(LocalDate date){
        this.appointmentDate = date;   
    }
    public LocalDate getAppointementDate(){
        return this.appointmentDate;
    }


}