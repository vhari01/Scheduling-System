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
    }
    public String getPatientName(){
        return null;
    }

    //Getters and setters for patient's age
    public void setAge(int age){
        
    }
    public int getAge(){
        return 0;
    }

    //Getters and setters for patient's insurance status
    public void setInsuranceStatus(boolean insurance){
    
    }
    public boolean getInsuranceStatus(){
        return false;
    }

    //Getters and setters for patient's level of emergency
    public void setLevelOfEmergency(int emergencyLevel){
        
    }
    public int getLevelOfEmergency(){
        return 0;
    }

    //Getters and setters for specialist required by patient 
    public void setspecialistRequired(String neededSpecialist){
           
    }
    public String getspecialistRequired(){
        return null;

    }

    //Getters and setters for patient's appointment date
    public void setAppointmentDate(LocalDate date){
          
    }
    public LocalDate getAppointementDate(){
        return null;
    }

}