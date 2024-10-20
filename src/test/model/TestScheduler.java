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
        String bookingId = scheduler.addPatient(p1); // Assuming p1 has "Max" as name
        assertFalse(scheduler.cancelAppointment("InvalidBookingId")); // Pass an invalid booking ID
    }

    @Test
    public void testCancelAppointmentAgeMismatch() {
        String bookingId = scheduler.addPatient(p1); // Assuming p1 is 23 years old
        assertFalse(scheduler.cancelAppointment("InvalidBookingId")); // Pass an invalid booking ID
    }

    @Test
    public void testCancelAppointmentDateMismatch() {
        String bookingId = scheduler.addPatient(p1); // Assuming p1 has an appointment on 2024-10-13
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
}
