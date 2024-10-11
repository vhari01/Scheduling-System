package model;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TestScheduler extends TestClass {
    public Scheduler scheduler;

    @BeforeEach
    void runBefore(){
        super.runBefore();
        scheduler= new Scheduler();
    }

    @Test
   public void testAddPatient(){
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
   public void testCancelAppointment(){
        scheduler.addPatient(p1);
        assertTrue(scheduler.cancelAppointment("Max", 23, LocalDate.of(2024, 10, 13), "Orthopedic"));
        assertFalse(scheduler.cancelAppointment("Max", 23, LocalDate.of(2024, 10, 13), "Orthopedic"));

    }

    @Test
   public void testNonExistingAppointmentCancel(){
        assertFalse(scheduler.cancelAppointment("Jenna", 23, LocalDate.of(2024, 10, 13), "Neurology"));

    }

    @Test
   public void testSortPatientsByPriority(){
        

        scheduler.addPatient(p1);
        scheduler.addPatient(p5);
        scheduler.addPatient(p6);
        ArrayList<Patient> sortedPatients = scheduler.sortPatientsByPriority();

        assertEquals(p6, sortedPatients.get(0));
        assertEquals(p5, sortedPatients.get(1));
        assertEquals(p1, sortedPatients.get(2));
    }

    @Test
    public void testTreatNextPatient(){
        scheduler.addPatient(p1);
        scheduler.addPatient(p4);

        scheduler.treatNextPatient();

        assertEquals(1,scheduler.getScheduledPatients().size());
        assertFalse(scheduler.getScheduledPatients().contains(p1));
        assertTrue(scheduler.getScheduledPatients().contains(p4));

        scheduler.treatNextPatient();

        assertEquals(0,scheduler.getScheduledPatients().size());
        assertFalse(scheduler.getScheduledPatients().contains(p4));

    }

    @Test
    public void testTreatPatientWhenListEmpty(){
        scheduler.treatNextPatient();
        assertEquals(0,scheduler.getScheduledPatients().size());
    }
}
