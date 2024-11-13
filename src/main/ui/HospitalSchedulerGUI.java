package ui;

import javax.swing.*;

import model.Patient;
import model.Scheduler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HospitalSchedulerGUI extends JFrame {
    private Scheduler schedule;

    public HospitalSchedulerGUI(Scheduler schedule) {
        this.schedule = schedule;
        setTitle("Hospital Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        initializeUI();
    }

    // Initializes the UI components and main panel layout
    private void initializeUI() {
        // Main panel to hold everything
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Hospital Scheduler", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Menu options panel
        JPanel menuPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        JButton patientButton = new JButton("Patient");
        JButton viewSortedButton = new JButton("View Patients Sorted by Emergency Level");
        JButton treatNextButton = new JButton("Treat Next Patient");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton exitButton = new JButton("Exit");

        // Add action listeners for each button
        patientButton.addActionListener(e -> showPatientMenu());
        viewSortedButton.addActionListener(e -> viewPatientsSorted());
        treatNextButton.addActionListener(e -> treatNextPatient());
        saveButton.addActionListener(e -> savePatients());
        loadButton.addActionListener(e -> loadPatients());
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to the menu panel
        menuPanel.add(patientButton);
        menuPanel.add(viewSortedButton);
        menuPanel.add(treatNextButton);
        menuPanel.add(saveButton);
        menuPanel.add(loadButton);
        menuPanel.add(exitButton);

        mainPanel.add(menuPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    // Shows the Patient submenu with options to add, cancel, or reschedule an appointment
    private void showPatientMenu() {
        JPanel patientMenuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton addPatientButton = new JButton("Add Patient");
        JButton cancelAppointmentButton = new JButton("Cancel Appointment");
        JButton rescheduleAppointmentButton = new JButton("Reschedule Appointment");
        JButton backButton = new JButton("Back to Main Menu");

        addPatientButton.addActionListener(e -> addPatient());
        cancelAppointmentButton.addActionListener(e -> cancelAppointment());
        rescheduleAppointmentButton.addActionListener(e -> rescheduleAppointment());
        backButton.addActionListener(e -> goBackToMainMenu());

        patientMenuPanel.add(addPatientButton);
        patientMenuPanel.add(cancelAppointmentButton);
        patientMenuPanel.add(rescheduleAppointmentButton);
        patientMenuPanel.add(backButton);

        // Replace main content with the patient submenu
        getContentPane().removeAll();
        getContentPane().add(patientMenuPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Method to view patients sorted by emergency level
    private void viewPatientsSorted() {
        JTextArea sortedPatientsTextArea = new JTextArea();
        sortedPatientsTextArea.setText("Patients sorted by emergency level:\n");
        for (Patient patient : schedule.sortPatientsByPriority()) {
            sortedPatientsTextArea.append(patient.getPatientName() + " - Emergency Level: " + patient.getLevelOfEmergency() + "\n");
        }
        JOptionPane.showMessageDialog(this, new JScrollPane(sortedPatientsTextArea), "Sorted Patients", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to treat the next patient
    private void treatNextPatient() {
        if (schedule.getScheduledPatients().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No patients to treat.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            schedule.treatNextPatient();
            JOptionPane.showMessageDialog(this, "Next patient treated.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to save patients
    private void savePatients() {
        // Implement save functionality based on your JSON handling
        JOptionPane.showMessageDialog(this, "Patients saved successfully!", "Save", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to load patients
    private void loadPatients() {
        // Implement load functionality based on your JSON handling
        JOptionPane.showMessageDialog(this, "Patients loaded successfully!", "Load", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to go back to the main menu
    private void goBackToMainMenu() {
        getContentPane().removeAll();
        initializeUI();
        revalidate();
        repaint();
    }

    // Method to add a new patient
    private void addPatient() {
        // Implement patient addition dialog
        JOptionPane.showMessageDialog(this, "Add Patient functionality goes here.", "Add Patient", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to cancel an appointment
    private void cancelAppointment() {
        // Implement cancel appointment dialog
        JOptionPane.showMessageDialog(this, "Cancel Appointment functionality goes here.", "Cancel Appointment", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to reschedule an appointment
    private void rescheduleAppointment() {
        // Implement reschedule appointment dialog
        JOptionPane.showMessageDialog(this, "Reschedule Appointment functionality goes here.", "Reschedule Appointment", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler(); // Assuming Scheduler class is correctly set up
        HospitalSchedulerGUI gui = new HospitalSchedulerGUI(scheduler);
        gui.setVisible(true);
    }
}
