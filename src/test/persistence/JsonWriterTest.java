package persistence;

import model.Patient;
import model.Scheduler;
import model.Specialist;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    
    @Test
    void testWriterInvalidFile() {
        
        try {
            Scheduler scheduler = new Scheduler();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            
        }
        }
        

    @Test
    void testWriterEmptyScheduler() {
        try {
            Scheduler scheduler = new Scheduler();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyScheduler.json");
            writer.open();
            writer.write(scheduler);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyScheduler.json");
            scheduler = reader.read();
            assertEquals(0, scheduler.getScheduledPatients().size()); 
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralScheduler() {
        try {
            Scheduler scheduler = new Scheduler();
            Specialist specialist = new Specialist("Cardiologist");
            LocalDate appointmentDate = LocalDate.of(2024, 12, 13);
            Patient patient = new Patient("John Doe", 30, true, 2, specialist, appointmentDate);
            scheduler.addPatient(patient);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralScheduler.json");
            writer.open();
            writer.write(scheduler);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralScheduler.json");
            scheduler = reader.read();
            assertEquals(1, scheduler.getScheduledPatients().size());

            Patient readPatient = scheduler.getScheduledPatients().get(0);
            assertEquals("John Doe", readPatient.getPatientName());
            assertEquals(30, readPatient.getAge());
            assertTrue(readPatient.getInsuranceStatus());
            assertEquals(2, readPatient.getLevelOfEmergency());
            assertEquals(appointmentDate, readPatient.getAppointementDate());
            assertEquals("Cardiologist", readPatient.getspecialistRequired().getSpecialistName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}


    

