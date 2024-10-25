package model;
import org.json.JSONObject;


public class Specialist {
    private String specialistName;

    //REQUIRES: specialistName to not be null
    //EFFECTS: Creates a specialist object with a String name
    public Specialist(String specialistName){
        this.specialistName = specialistName;
        
    }

    public void setSpecialistName(String specialistName){
        this.specialistName = specialistName;
    }

    public String getSpecialistName(){
        return  this.specialistName;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("specialistName", getSpecialistName());
        return json;
    }

}
