package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestPatient {
    private Patient p1;
    private Patient p2;
    private Patient p3;

    @BeforeEach
    void runBefore(){
        p1 = new Patient("Max",23, true, 3, "Orthopedic", LocalDate.of(2024, 10, 13));
        p2 = new Patient("Jack",50, false, 5, "Cardiologist", LocalDate.of(2024, 10, 13));
        p3 = new Patient("Gil",38, true, 1, "Dentist", LocalDate.of(2024, 10, 13));
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
    

}