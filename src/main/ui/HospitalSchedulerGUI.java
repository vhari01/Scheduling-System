package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;

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

        Color creamColor = new Color(255, 253, 208);
        LineBorder highlightedBorder = new LineBorder(Color.BLUE, 3); // Blue border with thickness 3

        // Main panel to hold everything
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); // Add outer border spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Inner spacing
        mainPanel.setBackground(creamColor);
    
        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Hospital Scheduler", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        
    
        // Panel to hold the menu options with vertical spacing
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Stack buttons vertically
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Space between welcome and buttons
        

    
        // Define button dimensions
        Dimension buttonSize = new Dimension(400, 150);
        Dimension buttonSize2 = new Dimension(820, 150);
    
        // Row 1: Patient and View Patients Sorted
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton patientButton = new JButton("Patient");
        patientButton.setFont(new Font("Serif", Font.BOLD, 20));

        JButton viewSortedButton = new JButton("View Patients Sorted by Emergency Level");
        viewSortedButton.setFont(new Font("Serif", Font.BOLD, 19));

        patientButton.setPreferredSize(buttonSize);
        viewSortedButton.setPreferredSize(buttonSize);
        patientButton.setBorder(highlightedBorder); // Add border to highlight
        viewSortedButton.setBorder(highlightedBorder);
        row1.add(patientButton);
        row1.add(viewSortedButton);
        menuPanel.add(row1);
    
        // Row 2: Treat Next Patient (centered)
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton treatNextButton = new JButton("Treat Next Patient");
        treatNextButton.setFont(new Font("Serif", Font.BOLD, 20));

        treatNextButton.setPreferredSize(buttonSize2);
        treatNextButton.setBorder(highlightedBorder); // Add border to highlight

        row2.add(treatNextButton);
        menuPanel.add(row2);
    
        // Row 3: Save and Load (side by side)
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        saveButton.setFont(new Font("Serif", Font.BOLD, 20));
        loadButton.setFont(new Font("Serif", Font.BOLD, 20));

        saveButton.setPreferredSize(buttonSize);
        loadButton.setPreferredSize(buttonSize);
        saveButton.setBorder(highlightedBorder); // Add border to highlight
        loadButton.setBorder(highlightedBorder);
        row3.add(saveButton);
        row3.add(loadButton);
        menuPanel.add(row3);
    
        // Row 4: Exit button (centered)
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Serif", Font.BOLD, 20));

        exitButton.setPreferredSize(buttonSize2);
        exitButton.setBorder(highlightedBorder); // Add border to highlight

        row4.add(exitButton);
        menuPanel.add(row4);
    
        // Add action listeners for each button
        patientButton.addActionListener(e -> showPatientMenu());
        viewSortedButton.addActionListener(e -> viewPatientsSorted());
        treatNextButton.addActionListener(e -> treatNextPatient());
        saveButton.addActionListener(e -> savePatients());
        loadButton.addActionListener(e -> loadPatients());
        exitButton.addActionListener(e -> System.exit(0));

        menuPanel.setBackground(creamColor);
        row1.setBackground(creamColor);
        row2.setBackground(creamColor);
        row3.setBackground(creamColor);
        row4.setBackground(creamColor);
    
        // Add the menuPanel to the main panel and set it as the frame's content pane
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    
    

    // Shows the Patient submenu with options to add, cancel, or reschedule an appointment
    private void showPatientMenu() {
        JPanel patientMenuPanel = new JPanel(new GridLayout(2, 2, 10, 10));
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
