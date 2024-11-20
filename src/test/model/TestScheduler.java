package model;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestScheduler extends TestClass {
    private Scheduler scheduler;

    @BeforeEach
    void runBefore() {
        super.runBefore();
        scheduler = new Scheduler();
    }

    @Test
    public void testAddPatient() {
        scheduler.addPatient(p1);
        scheduler.addPatient(p2);
        scheduler.addPatient(p3);
        scheduler.addPatient(p4);
        assertEquals(4, scheduler.getScheduledPatients().size());
        assertEquals(p1, scheduler.getScheduledPatients().get(0));
        assertEquals(p2, scheduler.getScheduledPatients().get(1));
        assertEquals(p3, scheduler.getScheduledPatients().get(2));
        assertEquals(p4, scheduler.getScheduledPatients().get(3));
    }
    @Test
    public void testCancelAppointment() {
        String bookingId = scheduler.addPatient(p1); // Add p1 and get the booking ID
        assertTrue(scheduler.cancelAppointment(bookingId)); // Cancel using the booking ID
        assertFalse(scheduler.cancelAppointment(bookingId)); // Canceling again should return false
    }

    @Test
    public void testCancelAppointmentNameMismatch() {
        assertFalse(scheduler.cancelAppointment("InvalidBookingId")); // Pass an invalid booking ID
    }

    @Test
    public void testCancelAppointmentAgeMismatch() {
        assertFalse(scheduler.cancelAppointment("InvalidBookingId")); // Pass an invalid booking ID
    }

    @Test
    public void testCancelAppointmentDateMismatch() {
        assertFalse(scheduler.cancelAppointment("InvalidBookingId")); // Pass an invalid booking ID
    }

    @Test
    public void testCancelAppointmentSpecialistMismatch() {
        String bookingId = scheduler.addPatient(p1); // Assuming p1 requires "Orthopedic"
        assertFalse(scheduler.cancelAppointment("InvalidBookingId")); // Pass an invalid booking ID
    }

    @Test
    public void testNonExistingAppointmentCancel() {
        assertFalse(scheduler.cancelAppointment("NonExistingBookingId")); // Use a booking ID that doesn't exist
    }

    @Test
    public void testSortPatientsByPriority() {

        scheduler.addPatient(p1);
        scheduler.addPatient(p5);
        scheduler.addPatient(p6);
        ArrayList<Patient> sortedPatients = scheduler.sortPatientsByPriority();

        assertEquals(p6, sortedPatients.get(0));
        assertEquals(p5, sortedPatients.get(1));
        assertEquals(p1, sortedPatients.get(2));
    }

    @Test
    public void testTreatNextPatient() {
        scheduler.addPatient(p1);
        scheduler.addPatient(p4);

        scheduler.treatNextPatient();

        assertEquals(1, scheduler.getScheduledPatients().size());
        assertFalse(scheduler.getScheduledPatients().contains(p1));
        assertTrue(scheduler.getScheduledPatients().contains(p4));

        scheduler.treatNextPatient();

        assertEquals(0, scheduler.getScheduledPatients().size());
        assertFalse(scheduler.getScheduledPatients().contains(p4));

    }

    @Test
    public void testTreatPatientWhenListEmpty() {
        scheduler.treatNextPatient();
        assertEquals(0, scheduler.getScheduledPatients().size());
    }

    @Test
    public void testRescheduleAppointmentPastDate() {
        String bookingId = scheduler.addPatient(p1);
        LocalDate pastDate = LocalDate.of(2023, 12, 13);
        assertFalse(scheduler.rescheduleAppointment(bookingId, pastDate)); 
    }

    @Test
    public void testRescheduleAppointment() {
        String bookingId = scheduler.addPatient(p1);
        LocalDate newDate = LocalDate.of(2024, 12, 21);
        assertTrue(scheduler.rescheduleAppointment(bookingId, newDate)); 
        assertEquals(newDate, p1.getAppointementDate());
    }
    @Test
    public void testGenerateBookingIdWithDuplicate() {
        
        String existingId = scheduler.generateBookingId(); 
        scheduler.addPatient(p1); 
        String newId = scheduler.generateBookingId();
        assertNotEquals(existingId, newId);
    }

    @Test
    public void testRescheduleAppointmentNonExistingBookingId() {
        LocalDate newDate = LocalDate.of(2024, 12, 14); 
        boolean result = scheduler.rescheduleAppointment("non-existing-id", newDate);
        
        assertFalse(result); 
    }

    @Test
    public void testRescheduleAppointmentThatIsValid() {
        String bookingId = scheduler.addPatient(p1); 
        assertTrue(scheduler.getScheduledPatients().contains(p1)); 
    
        LocalDate newDate = LocalDate.of(2024, 12, 14); 
        boolean result = scheduler.rescheduleAppointment(bookingId, newDate); 
        
        assertTrue(result);
        assertEquals(newDate, p1.getAppointementDate()); 
        assertTrue(p1.getBookingId().equals(bookingId)); 
    }
    


   
}
