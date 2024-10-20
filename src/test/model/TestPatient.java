package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;


import org.junit.jupiter.api.Test;

public class TestPatient extends TestClass {

    @Test
    void testConstructor() {
        assertEquals("Max", p1.getPatientName());
        assertEquals(23, p1.getAge());
        assertTrue(p1.getInsuranceStatus());
        assertEquals(Orthopedic, p1.getspecialistRequired());
        assertEquals(3, p1.getLevelOfEmergency());
        assertEquals(LocalDate.of(2024, 12, 13), p1.getAppointementDate());
    }

    @Test
    void testSetters() {
        Specialist newSpecialist = new Specialist("Dermatologist");
        p1.setPatientName("Hex");
        p1.setAge(19);
        p1.setInsuranceStatus(false);
        p1.setLevelOfEmergency(5);
        p1.setspecialistRequired(newSpecialist);
        p1.setAppointmentDate(LocalDate.of(2024, 10, 17));

        assertEquals("Hex", p1.getPatientName());
        assertEquals(19, p1.getAge());
        assertFalse(p1.getInsuranceStatus());
        assertEquals(5, p1.getLevelOfEmergency());
        assertEquals(newSpecialist, p1.getspecialistRequired());
        assertEquals(LocalDate.of(2024, 10, 17), p1.getAppointementDate());
    }

    @Test
    void testToCompareSameEmergencySameSpecialist() {
        assertEquals(0, p1.compareTo(p4));
    }

    @Test
    void testToCompareHigherEmergencyFirst() {
        assertEquals(-1, p2.compareTo(p3) ); 
        assertEquals(1, p3.compareTo(p2) ); 
    }   

    @Test
    void testToCompareSameAppointmentDate() {
        p2.setAppointmentDate(LocalDate.of(2024, 10, 13));
        p3.setAppointmentDate(LocalDate.of(2024, 10, 13));
    
        assertEquals(-1, p2.compareTo(p3)); 
}



    @Test
    void testToCompareForDifferentSpecialist() {
        assertEquals(0, p1.compareTo(p3));
    }

    @Test
    public void testCompareToDifferentDates() {
        Patient p1 = new Patient("Alice", 25, true, 5, new Specialist("Cardiologist"), LocalDate.of(2024, 12, 14));
        Patient p2 = new Patient("Bob", 30, true, 5, new Specialist("Cardiologist"), LocalDate.of(2024, 12, 15));
        
        assertEquals(0, p1.compareTo(p2)); 
    }
    
    @Test
    public void testCompareToDifferentSpecialists() {
        Patient p1 = new Patient("Alice", 25, true, 5, new Specialist("Cardiologist"), LocalDate.of(2024, 12, 14));
        Patient p2 = new Patient("Bob", 30, true, 5, new Specialist("Orthopedic"), LocalDate.of(2024, 12, 14));
        
        assertEquals(0, p1.compareTo(p2)); 
    }
}