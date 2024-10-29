package Persistence;

import model.Patient;
import model.Scheduler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Scheduler scheduler = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyScheduler() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyScheduler.json");
        try {
            Scheduler scheduler = reader.read();
            assertEquals(0, scheduler.getScheduledPatients().size());  
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralScheduler() {
        JsonReader reader = new JsonReader("./data/testReaderPatient.json");
        try {
            Scheduler scheduler = reader.read();
            ArrayList<Patient> patients = scheduler.getScheduledPatients();

            // Check that there are 2 patients
            assertEquals(2, patients.size());

            // Check details of the first patient
            Patient patient1 = patients.get(0);
            assertEquals("Alice", patient1.getPatientName());
            assertEquals(45, patient1.getAge());
            assertTrue(patient1.getInsuranceStatus());
            assertEquals(4, patient1.getLevelOfEmergency());
            assertEquals("Cardiologist", patient1.getspecialistRequired().getSpecialistName());
            assertEquals(LocalDate.of(2024, 12, 13), patient1.getAppointementDate());
            assertNotNull(patient1.getBookingId());

            // Check details of the second patient
            Patient patient2 = patients.get(1);
            assertEquals("Max", patient2.getPatientName());
            assertEquals(24, patient2.getAge());
            assertFalse(patient2.getInsuranceStatus());
            assertEquals(2, patient2.getLevelOfEmergency());
            assertEquals("Dermatologist", patient2.getspecialistRequired().getSpecialistName());
            assertEquals(LocalDate.of(2024, 12, 13), patient2.getAppointementDate());
            assertNotNull(patient2.getBookingId());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
