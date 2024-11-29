package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import model.Event;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class TestEvent {
    private Event event;
    private Date date;

    @BeforeEach
    public void runBefore() {
        date = Calendar.getInstance().getTime(); // Get current time
        event = new Event("Sensor open at door"); // Create Event
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", event.getDescription());

        // Compare times within an acceptable range (e.g., 100ms)
        long diffInMillis = Math.abs(date.getTime() - event.getDate().getTime());
        assertTrue(diffInMillis < 100, "Event date is not within expected time range");
    }

    @Test
    public void testToString() {
        // Ensure the toString() format matches expectations
        String expectedString = event.getDate().toString() + "\n" + "Sensor open at door";
        assertEquals(expectedString, event.toString());
    }
}
