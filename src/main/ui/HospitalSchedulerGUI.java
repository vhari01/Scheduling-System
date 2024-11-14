package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import model.Patient;
import model.Scheduler;
import model.Specialist;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HospitalSchedulerGUI extends JFrame {
    private Scheduler schedule;
    private List <Specialist> specialists;

    public HospitalSchedulerGUI(Scheduler schedule) {
        this.schedule = schedule;
        setTitle("Hospital Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        this.specialists = Arrays.asList(
        new Specialist("Cardiologist"),
        new Specialist("Neurologist"),
        new Specialist("Orthopedic"),
        new Specialist("Dermatologist"),
        new Specialist("Pediatrician"),
        new Specialist("Endocrinologist"),
        new Specialist("Gastroenterologist"),
        new Specialist("Infectiologist"),
        new Specialist("Nephrologist"),
        new Specialist("Psychiatrist"),
        new Specialist("Endocrinologist"),
        new Specialist("Rheumatologist"),
        new Specialist("Pulmonologist"),
        new Specialist("Radiologist"),
        new Specialist("Hematologist"),
        new Specialist("Immunologist"),
        new Specialist("Ophthalmologist")
        );
    
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

        addHoverEffect(patientButton);
        addHoverEffect(viewSortedButton);
        addHoverEffect(treatNextButton);
        addHoverEffect(saveButton);
        addHoverEffect(loadButton);
        addHoverEffect(exitButton);
    
        // Add the menuPanel to the main panel and set it as the frame's content pane
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    
    

    // Shows the Patient submenu with options to add, cancel, or reschedule an appointment
    private void showPatientMenu() {
        Color creamColor = new Color(255, 253, 208);
        LineBorder highlightedBorder = new LineBorder(Color.BLUE, 3); // Blue border with thickness 3
    
        // Set up the panel with BoxLayout for vertical stacking of buttons
        JPanel patientMenuPanel = new JPanel();
        patientMenuPanel.setLayout(new BoxLayout(patientMenuPanel, BoxLayout.Y_AXIS)); // Stack components vertically
        patientMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Inner spacing
        patientMenuPanel.setBackground(creamColor);
    
        // Define larger button dimensions (width: 500, height: 150)
        Dimension buttonSize = new Dimension(500, 150);
    
        // Create the Add Patient button
        JButton addPatientButton = new JButton("Add Patient");
        addPatientButton.setFont(new Font("Serif", Font.BOLD, 20));
        addPatientButton.setPreferredSize(buttonSize);
        addPatientButton.setMaximumSize(buttonSize);
        addPatientButton.setBorder(highlightedBorder);
    
        // Create the Cancel Appointment button
        JButton cancelAppointmentButton = new JButton("Cancel Appointment");
        cancelAppointmentButton.setFont(new Font("Serif", Font.BOLD, 20));
        cancelAppointmentButton.setPreferredSize(buttonSize);
        cancelAppointmentButton.setMaximumSize(buttonSize);
        cancelAppointmentButton.setBorder(highlightedBorder);
    
        // Create the Reschedule Appointment button
        JButton rescheduleAppointmentButton = new JButton("Reschedule Appointment");
        rescheduleAppointmentButton.setFont(new Font("Serif", Font.BOLD, 20));
        rescheduleAppointmentButton.setPreferredSize(buttonSize);
        rescheduleAppointmentButton.setMaximumSize(buttonSize);
        rescheduleAppointmentButton.setBorder(highlightedBorder);
    
        // Create the Back button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("Serif", Font.BOLD, 20));
        backButton.setPreferredSize(buttonSize);
        backButton.setMaximumSize(buttonSize);
        backButton.setBorder(highlightedBorder);
    
        // Add mouse hover effects
        addHoverEffect(addPatientButton);
        addHoverEffect(cancelAppointmentButton);
        addHoverEffect(rescheduleAppointmentButton);
        addHoverEffect(backButton);
    
        // Add vertical space between the buttons
        patientMenuPanel.add(addPatientButton);
        patientMenuPanel.add(Box.createVerticalStrut(20));
        patientMenuPanel.add(cancelAppointmentButton);
        patientMenuPanel.add(Box.createVerticalStrut(20));
        patientMenuPanel.add(rescheduleAppointmentButton);
        patientMenuPanel.add(Box.createVerticalStrut(20));
        patientMenuPanel.add(backButton);
    
        // Center the patient menu panel inside a flow layout container
        JPanel centeredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centeredPanel.setBackground(creamColor);
        centeredPanel.add(patientMenuPanel);
    
        // Add action listeners
        addPatientButton.addActionListener(e -> addPatient());
        cancelAppointmentButton.addActionListener(e -> cancelAppointment());
        rescheduleAppointmentButton.addActionListener(e -> rescheduleAppointment());
        backButton.addActionListener(e -> goBackToMainMenu());
    
        // Replace main content with the patient submenu
        getContentPane().removeAll();
        getContentPane().add(centeredPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    // Method to add hover effect to buttons
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(204, 204, 255)); // Light purple on hover
            }
    
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(null); // Revert to original background color
            }
        });
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

    private void addPatient() {
        // Set up the form panel with a nice border and background
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 253, 208)); // Set background color
    
        // Use GridBagConstraints for better control of layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components
        gbc.anchor = GridBagConstraints.WEST;
    
        // Create input fields
        JTextField nameField = new JTextField(20);
        JTextField ageField = new JTextField(20);
        JTextField emergencyLevelField = new JTextField(20);
        JComboBox<Specialist> specialistComboBox = new JComboBox<>(specialists.toArray(new Specialist[0]));
        JCheckBox hasInsuranceCheckbox = new JCheckBox("Has Insurance");
    
        // Date picker for appointment (consider a JDatePicker for better UI, or JTextField for simplicity)
        LocalDate today = LocalDate.now();
        JTextField dateField = new JTextField(today.toString(), 20);
    
        // Add labels and fields to the panel with custom GridBag constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Patient Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Patient Age:"), gbc);
        gbc.gridx = 1;
        panel.add(ageField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Emergency Level (1-5):"), gbc);
        gbc.gridx = 1;
        panel.add(emergencyLevelField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Specialist:"), gbc);
        gbc.gridx = 1;
        panel.add(specialistComboBox, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Appointment Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        panel.add(dateField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(hasInsuranceCheckbox, gbc);
    
        // Create a button panel with OK and Cancel options
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255)); // Button panel background
        JButton okButton = new JButton("Add Patient");
        JButton cancelButton = new JButton("Cancel");
    
        // Customize buttons
        okButton.setBackground(new Color(34, 139, 34)); // Green button color
        okButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(255, 69, 0)); // Red button color
        cancelButton.setForeground(Color.WHITE);
    
        // Add action listeners for buttons
        okButton.addActionListener(e -> processForm(panel, nameField, ageField, emergencyLevelField, specialistComboBox, dateField, hasInsuranceCheckbox, today));
        cancelButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Patient addition cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE));
    
        // Layout the buttons side by side
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
    
        // Wrap everything in a JScrollPane for better layout in case the window is too small
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(400, 350)); // ScrollPane size
    
        // Show input dialog with the form
        int result = JOptionPane.showConfirmDialog(this, scrollPane, "Add Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            processForm(panel, nameField, ageField, emergencyLevelField, specialistComboBox, dateField, hasInsuranceCheckbox, today);
        }
    }
    
    private void processForm(JPanel panel, JTextField nameField, JTextField ageField, JTextField emergencyLevelField, JComboBox<Specialist> specialistComboBox, JTextField dateField, JCheckBox hasInsuranceCheckbox, LocalDate today) {
        try {
            // Gather input values
            String patientName = nameField.getText();
            String ageText = ageField.getText();
            String emergencyLevelText = emergencyLevelField.getText();
            Specialist selectedSpecialist = (Specialist) specialistComboBox.getSelectedItem();
            String appointmentDateText = dateField.getText();
            boolean hasInsurance = hasInsuranceCheckbox.isSelected();
    
            // Validation for empty fields and default selections
            if (patientName.isEmpty() || ageText.isEmpty() || emergencyLevelText.isEmpty() || appointmentDateText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required. Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Parse the inputs
            int patientAge = Integer.parseInt(ageText);
            int emergencyLevel = Integer.parseInt(emergencyLevelText);
            LocalDate appointmentDate = LocalDate.parse(appointmentDateText);
    
            // Validate input values
            if (patientAge <= 0) {
                throw new IllegalArgumentException("Age must be greater than 0.");
            }
            if (emergencyLevel < 1 || emergencyLevel > 5) {
                throw new IllegalArgumentException("Emergency level must be between 1 and 5.");
            }
            if (appointmentDate.isBefore(today)) {
                throw new IllegalArgumentException("Appointment date cannot be before today's date.");
            }
    
            // Create new Patient object
            Patient newPatient = new Patient(patientName, patientAge, hasInsurance, emergencyLevel, selectedSpecialist, appointmentDate);
            schedule.addPatient(newPatient); // Add patient to schedule
    
            JOptionPane.showMessageDialog(this, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            // Show specific error message
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
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
