package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestPatient {
    private Patient p1;
    private Patient p2;
    private Patient p3;
    private Patient p4;

    @BeforeEach
    void runBefore(){
        p1 = new Patient("Max",23, true, 3, "Orthopedic", LocalDate.of(2024, 10, 13));
        p2 = new Patient("Jack",50, false, 5, "Cardiologist", LocalDate.of(2024, 10, 13));
        p3 = new Patient("Gil",38, true, 3, "Cardiologist", LocalDate.of(2024, 10, 13));
        p4 = new Patient("cacy",32, true, 3, "Orthopedic", LocalDate.of(2024, 10, 13));
    }


    @Test
    void testConstructor() {
        assertEquals("Max", p1.getPatientName());
        assertEquals(23, p1.getAge());
        assertTrue(p1.getInsuranceStatus());
        assertEquals("Orthopedic", p1.getspecialistRequired());
        assertEquals(3, p1.getLevelOfEmergency());
        assertEquals(LocalDate.of(2024, 10, 13), p1.getAppointementDate());
    }
    @Test
    void testSetters(){
        p1.setPatientName("Hex");
        p1.setAge(19);
        p1.setInsuranceStatus(false);
        p1.setLevelOfEmergency(5);
        p1.setspecialistRequired("Dermatologist");
        p1.setAppointmentDate(LocalDate.of(2024,10,17));

        assertEquals("Hex", p1.getPatientName());
        assertEquals(19, p1.getAge());
        assertFalse(p1.getInsuranceStatus());
        assertEquals(5, p1.getLevelOfEmergency());
        assertEquals("Dermatologist", p1.getspecialistRequired());
        assertEquals(LocalDate.of(2024,10,17), p1.getAppointementDate());
    }
    @Test
    void testToCompareSameEmergencySameSpecialist(){
       assertEquals(0, p1.compareTo(p4)); 
    }

    @Test
    void testToCompareHigherEmergencyFirst(){
       assertTrue(p2.compareTo(p3)<0);
       assertTrue(p3.compareTo(p2)>0);
    }

    @Test 
    void testToCompareForDifferentSpecialist(){
        assertEquals(0,p1.compareTo(p3));
    }


}