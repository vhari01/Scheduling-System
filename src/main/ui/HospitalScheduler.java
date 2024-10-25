package ui;

import model.Patient;
import model.Scheduler;
import model.Specialist;

import java.util.*;

import Persistence.JsonReader;
import Persistence.JsonWriter;

import java.time.LocalDate;

public class HospitalScheduler {

    private Scheduler schedule;
    private List<Specialist> specialistAvailable;
    private Scanner sc;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/patients.json";

    public HospitalScheduler() {
        schedule = new Scheduler();
        specialistAvailable = createSpecialists();
        sc = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadPatients();
        runHospital();
    }

    public List<Specialist> createSpecialists() {
        List<Specialist> specialists = new ArrayList<>();
        specialists.add(new Specialist("Cardiologist"));
        specialists.add(new Specialist("Dermatologist"));
        specialists.add(new Specialist("Endocrinologist"));
        specialists.add(new Specialist("Gastroenterologist"));
        specialists.add(new Specialist("Infectiologist"));
        specialists.add(new Specialist("Nephrologist"));
        specialists.add(new Specialist("Neurologist"));
        specialists.add(new Specialist("Orthopedist"));
        specialists.add(new Specialist("Psychiatrist"));
        specialists.add(new Specialist("Endocrinologist"));
        specialists.add(new Specialist("Rheumatologist"));
        specialists.add(new Specialist("Pulmonologist"));
        specialists.add(new Specialist("Radiologist"));
        specialists.add(new Specialist("Hematologist"));
        specialists.add(new Specialist("Immunologist"));
        specialists.add(new Specialist("Ophthalmologist"));
        specialists.add(new Specialist("Pediatrician"));

        return specialists;

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
                savePatients(); // Save patients before quitting
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
        System.out.println("\tr -> Reschedule an appointment");
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
        } else if (command.equals("r")) {
            reschedulePatient();
        } else {
            System.out.println("Invalid selection...");
        }

    }

    // MODIFIES: this
    // EFFECTS: creates and adds a new patient
    @SuppressWarnings("methodlength")
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

        System.out.println("Select a specialist by entering the corresponding letter:");
        char specialistLetter = 'a';

        for (int i = 0; i < specialistAvailable.size(); i++) {
            System.out.println(specialistLetter + "->" + specialistAvailable.get(i).getSpecialistName());
            specialistLetter++;
        }
        String specialistChoice = sc.next().toLowerCase();
        int specialistIndex = specialistChoice.charAt(0) - 'a';

        if (specialistIndex < 0 || specialistIndex >= specialistAvailable.size()) {
            System.out.println("Invalid specialist selection. Please try again.");
            return;
        }

        Specialist specialist = specialistAvailable.get(specialistIndex);

        sc.nextLine();

        System.out.println("Enter appointment date (YYYY-MM-DD):");
        String dateInput = sc.nextLine();

        if (dateInput.isEmpty()) {
            System.out.println("Appointment date cannot be empty. Please enter a valid date.");
            return;
        }

        LocalDate appointmentDate;
        try {
            appointmentDate = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            return;
        }

        Patient newPatient = new Patient(name, age, insurance, emergencyLevel, specialist, appointmentDate);
        String bookingId = schedule.addPatient(newPatient);
        System.out.println("Patient added successfully!Booking ID: " + bookingId);
    }
    

    // MODIFIES: this
    // EFFECTS: cancels a patients appointment if it exists
    public void patientAppointmentCancel() {
        sc.nextLine();
        System.out.println("Enter booking id");
        String bookingId = sc.nextLine();

        boolean cancelAp = schedule.cancelAppointment(bookingId);

        if (cancelAp) {
            System.out.println("Appointment canceled successfully!!!");
        } else {
            System.out.println("No matching appointments found");
        }
    }

    public void reschedulePatient() {
        sc.nextLine();
        System.out.println("Enter booking ID:");
        String bookingId = sc.nextLine();

        System.out.println("Enter the new appointment date (YYYY-MM-DD):");
        String dateInput = sc.nextLine();
        LocalDate newAppointmentDate;

        try {
            newAppointmentDate = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            return;
        }

        boolean rescheduled = schedule.rescheduleAppointment(bookingId, newAppointmentDate);
        if (rescheduled) {
            System.out.println("Appointment rescheduled to " + dateInput);
        } else {
            System.out.println("Invalid Id, Please check your Id");
        }

    }

    // MODIFIES: this
    // EFFECTS: compare patients and sorts them out
    public void sortingOutPatients() {
        System.out.println("\nPatients sorted by priority:");
        for (Patient patient : schedule.sortPatientsByPriority()) {
            System.out.println(patient.getPatientName() + " - Emergency Level: " + patient.getLevelOfEmergency());
        }

    }

    // EFFECTS: treats patients and removes them from the list
    public void treatingPatient() {
        if (schedule.getScheduledPatients().isEmpty()) {
            System.out.println("No patients to treat.");
        } else {
            schedule.treatNextPatient();
            System.out.println("Next patient treated.");
        }

    }

    // EFFECTS: loads patient data from JSON
    private void loadPatients() {
        try {
            schedule = jsonReader.read(); // Load patients into the schedule
            System.out.println("Loaded patients from " + JSON_STORE);
        } catch (Exception e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves patient data to JSON
    private void savePatients() {
        try {
            jsonWriter.open();
            jsonWriter.write(schedule);
            jsonWriter.close();
            System.out.println("Saved patients to " + JSON_STORE);
        } catch (Exception e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}
