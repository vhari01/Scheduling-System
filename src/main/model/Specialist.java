package model;

public class Specialist {
    private String specialistName;

    //REQUIRES: specialistName to not be null
    //EFFECTS: Creates a specialist object with a String name
    public Specialist(String specialistName){
        this.specialistName = specialistName;
        
    }

    public void setSpecialist(String specialistName){
        this.specialistName = specialistName;
    }

    public String getSpecialist(){
        return  this.specialistName;
    }

}
