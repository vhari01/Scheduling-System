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
        assertEquals("Orthopedic", p1.getspecialistRequired());
        assertEquals(3, p1.getLevelOfEmergency());
        assertEquals(LocalDate.of(2024, 10, 13), p1.getAppointementDate());
    }

    @Test
    void testSetters() {
        p1.setPatientName("Hex");
        p1.setAge(19);
        p1.setInsuranceStatus(false);
        p1.setLevelOfEmergency(5);
        p1.setspecialistRequired("Dermatologist");
        p1.setAppointmentDate(LocalDate.of(2024, 10, 17));

        assertEquals("Hex", p1.getPatientName());
        assertEquals(19, p1.getAge());
        assertFalse(p1.getInsuranceStatus());
        assertEquals(5, p1.getLevelOfEmergency());
        assertEquals("Dermatologist", p1.getspecialistRequired());
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

}