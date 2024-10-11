package ui;

import model.Patient;
import model.Scheduler;

import java.util.*;
import java.time.LocalDate;

public class HospitalScheduler {

    private Scheduler schedule;
    Scanner sc;

    public HospitalScheduler() {
        schedule = new Scheduler();
        sc = new Scanner(System.in);
        runHospital();
    }

    // EFFECTS: Runs the hospital scheduler app
    public void runHospital() {
        boolean run = true;
        String command;

        while (run) {
            showMenu();
            command = sc.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                run = false;
            } else {
                checkCommand(command);
            }

        }
        System.out.println("\n Thank you. Take Care!!!");

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void showMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a patient");
        System.out.println("\tc -> Cancel an appointment");
        System.out.println("\ts -> View patients sorted by emergency level");
        System.out.println("\tn -> Treat the next patient");
        System.out.println("\tq -> Quit");
    }

    // EFFECTS: displays menu of options to user with keys
    public void checkCommand(String command) {
        if (command.equals("a")) {
            patientAddition();
        } else if (command.equals("c")) {
            patientAppointmentCancel();
        } else if (command.equals("s")) {
            sortingOutPatients();
        } else if (command.equals("n")) {
            treatingPatient();
        } else {
            System.out.println("Invalid selection...");
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void patientAddition() {
        sc.nextLine();

        System.out.println("Enter patient's name:");
        String name = sc.nextLine();
        System.out.println(" ");

        System.out.println("Enter patient's age:");
        int age = sc.nextInt();

        System.out.println(" ");

        System.out.println("Does the patient have insurance? (true/false)");
        boolean insurance = sc.nextBoolean();

        System.out.println(" ");

        System.out.println("Enter level of emergency (1 to 5):");
        int emergencyLevel = sc.nextInt();

        System.out.println(" ");

        sc.nextLine(); // consume newline
        System.out.println("Enter specialist required:");
        String specialist = sc.nextLine();

        System.out.println(" ");

        System.out.println("Enter appointment date (YYYY-MM-DD):");
        String dateInput = sc.nextLine();
        LocalDate appointmentDate = LocalDate.parse(dateInput);

        Patient newPatient = new Patient(name, age, insurance, emergencyLevel, specialist, appointmentDate);
        schedule.addPatient(newPatient);
        System.out.println("Patient added successfully!");
    }

    // MODIFIES: this
    // EFFECTS: adds a new patient to the scheduler
    public void patientAppointmentCancel() {
        sc.nextLine(); // consume newline
        System.out.println("Enter patient's name:");
        String name = sc.nextLine();

        System.out.println("Enter patient's age:");
        int age = sc.nextInt();

        sc.nextLine(); // consume newline
        System.out.println("Enter specialist required:");
        String specialist = sc.nextLine();

        System.out.println("Enter appointment date (YYYY-MM-DD):");
        String dateInput = sc.nextLine();
        LocalDate appointmentDate = LocalDate.parse(dateInput);

        boolean cancel = schedule.cancelAppointment(name, age, appointmentDate, specialist);
        if (cancel) {
            System.out.println("Appointment canceled successfully!!!.");
        } else {
            System.out.println("No matching appointment found.");
        }

    }

    // MODIFIES: this
    // EFFECTS: cancels a patient appointment if it exists
    public void sortingOutPatients() {
        System.out.println("\nPatients sorted by priority:");
        for (Patient patient : schedule.sortPatientsByPriority()) {
            System.out.println(patient.getPatientName() + " - Emergency Level: " + patient.getLevelOfEmergency());
        }

    }

    // EFFECTS: displays patients sorted by emergency level
    public void treatingPatient() {
        if (schedule.getScheduledPatients().isEmpty()) {
            System.out.println("No patients to treat.");
        } else {
            schedule.treatNextPatient();
            System.out.println("Next patient treated.");
        }

    }
}
