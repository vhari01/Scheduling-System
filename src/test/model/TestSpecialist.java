package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSpecialist {
    private Specialist specialist;

    @BeforeEach
    void setUp() {
        specialist = new Specialist("Cardiologist");
    }

    @Test
    public void testConstructor() {
        assertEquals("Cardiologist", specialist.getSpecialistName(), "Specialist name should be set correctly.");
    }

    @Test
    public void testGetSpecialistName() {
        assertEquals("Cardiologist", specialist.getSpecialistName(),
                "Get specialist name should return the correct name.");
    }

    @Test
    public void testSetSpecialistName() {
        specialist.setSpecialistName("Neurologist");
        assertEquals("Neurologist", specialist.getSpecialistName(),
                "Set specialist name should change the name correctly.");
        assertEquals("Neurologist", specialist.toString());
    }

}
