import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.BorderLayout;

/*
 * GymGUI class is made to implement proper interface for The Gym System
 * This class implements a GUI using AWT and Swing components
 * Null layout is used for the frame
 */
public class GymGUI {
    //Main frame
    private JFrame mainFrame;

    //ArrayList to store both RegularMember and PremiumMember objects
    private ArrayList<GymMember> jim;

    //Panels for navigation and content
    private JPanel navPanel;
    private JPanel registrationPanel;
    private JPanel controlPanel;
    private JPanel viewMembersPanel;
    private JPanel currentPanel;

    //Text fields for member information
    private JTextField idField;
    private JTextField nameField;
    private JTextField locationField;
    private JTextField phoneField;
    private JTextField emailField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private ButtonGroup genderGroup;

    //DOB components
    private JComboBox dobDayComboBox;
    private JComboBox dobMonthComboBox;
    private JComboBox dobYearComboBox;

    //Membership Start Date components
    private JComboBox msDayComboBox;
    private JComboBox msMonthComboBox;
    private JComboBox msYearComboBox;

    //Other fields
    private JTextField referralSourceField;
    private JTextField paidAmountField;
    private JTextField removalReasonField;
    private JTextField trainerNameField;

    //Non-editable fields
    private JTextField regularPlanPriceField;
    private JTextField premiumPlanChargeField;
    private JTextField discountAmountField;

    //Membership type selection
    private JRadioButton regularRadio, premiumRadio;
    private ButtonGroup memberTypeGroup;

    //Plan selection for regular members
    private JComboBox planComboBox;

    //Admin panel field for member ID
    private JTextField adminIdField;

    //Member details area
    private JTextArea memberDetailsArea;
    private JTextArea viewMemberDetailsArea;

    //Labels for hiding/showing based on member type
    private JLabel trainerNameLabel;
    private JLabel planLabel;
    private JLabel regularPlanPriceLabel;
    private JLabel premiumPlanChargeLabel;
    private JLabel paidAmountLabel;
    private JLabel discountAmountLabel;

    //Colors for UI
    private Color primaryColor = new Color(66, 133, 244);
    private Color secondaryColor = new Color(234, 237, 237);
    private Color textColor = new Color(51, 51, 51);
    private Color navColor = new Color(44, 62, 80);

    /*
     * Initialize the GUI
     */
    public GymGUI() {
        //Initialize member list
        jim = new ArrayList<>();

        //Create main frame
        mainFrame = new JFrame("Gym Management System");
        mainFrame.setSize(1000, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        //Create navigation panel
        NavigationPanel();

        //Create content panels
        registrationPanel = RegistrationPanel();
        registrationPanel.setBounds(200, 0, 800, 700);

        controlPanel = adminControlPanel();
        controlPanel.setBounds(200, 0, 800, 700);
        controlPanel.setVisible(false);

        viewMembersPanel = ViewMembersPanel();
        viewMembersPanel.setBounds(200, 0, 800, 700);
        viewMembersPanel.setVisible(false);

        //Add panels to frame
        mainFrame.add(registrationPanel);
        mainFrame.add(controlPanel);
        mainFrame.add(viewMembersPanel);

        //Set current panel
        currentPanel = registrationPanel;

        //Set frame to visible
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /*
     * Create the navigation panel (left sidebar)
     */
    private void NavigationPanel() {
        navPanel = new JPanel();
        navPanel.setLayout(null);
        navPanel.setBounds(0, 0, 200, 700);
        navPanel.setBackground(navColor);
        mainFrame.add(navPanel);

        //Add title
        JLabel logoLabel = new JLabel("GYM SYSTEM");
        logoLabel.setBounds(20, 30, 160, 30);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        navPanel.add(logoLabel);

        //Create register button
        JButton registerButton = new JButton("Register Member");
        registerButton.setBounds(10, 100, 180, 40);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(52, 73, 94));
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 14));

        //Create view button
        JButton viewButton = new JButton("View Members");
        viewButton.setBounds(10, 150, 180, 40);
        viewButton.setForeground(Color.WHITE);
        viewButton.setBackground(new Color(52, 73, 94));
        viewButton.setBorderPainted(false);
        viewButton.setFocusPainted(false);
        viewButton.setFont(new Font("Arial", Font.PLAIN, 14));

        //Create admin button
        JButton adminButton = new JButton("Control Panel");
        adminButton.setBounds(10, 200, 180, 40);
        adminButton.setForeground(Color.WHITE);
        adminButton.setBackground(new Color(52, 73, 94));
        adminButton.setBorderPainted(false);
        adminButton.setFocusPainted(false);
        adminButton.setFont(new Font("Arial", Font.PLAIN, 14));

        //Add hover effects
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(52, 73, 94));
            }
        });

        viewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewButton.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewButton.setBackground(new Color(52, 73, 94));
            }
        });

        adminButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminButton.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminButton.setBackground(new Color(52, 73, 94));
            }
        });

        //Add action listeners
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanel(registrationPanel);
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshMemberView();
                switchPanel(viewMembersPanel);
            }
        });

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanel(controlPanel);
            }
        });

        //Add buttons to panel
        navPanel.add(registerButton);
        navPanel.add(viewButton);
        navPanel.add(adminButton);
    }

    /*
     * In order to switch between panels from the bar
     */
    private void switchPanel(JPanel panel) {
        currentPanel.setVisible(false);
        panel.setVisible(true);
        currentPanel = panel;
    }

    /*
     * Creating member registration panel for members to input their information to 
     */
    private JPanel RegistrationPanel() {
        //Create main panel with null layout
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(null);
        registrationPanel.setBackground(Color.WHITE);

        //Add title 
        JLabel titleLabel = new JLabel("Member Registration");
        titleLabel.setBounds(220, 20, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(textColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        registrationPanel.add(titleLabel);

        //Member Type Selection
        JLabel memberTypeLabel = new JLabel("Member Type:");
        memberTypeLabel.setBounds(50, 70, 100, 25);
        registrationPanel.add(memberTypeLabel);

        regularRadio = new JRadioButton("Regular Member");
        regularRadio.setBounds(160, 70, 140, 25);
        regularRadio.setSelected(true);

        premiumRadio = new JRadioButton("Premium Member");
        premiumRadio.setBounds(310, 70, 150, 25);

        memberTypeGroup = new ButtonGroup();
        memberTypeGroup.add(regularRadio);
        memberTypeGroup.add(premiumRadio);

        registrationPanel.add(regularRadio);
        registrationPanel.add(premiumRadio);

        //Create ID field
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 110, 100, 25);
        registrationPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 110, 180, 25);
        registrationPanel.add(idField);

        //Create Name field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 150, 100, 25);
        registrationPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 150, 180, 25);
        registrationPanel.add(nameField);

        //Create Location field
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(50, 190, 100, 25);
        registrationPanel.add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(150, 190, 180, 25);
        registrationPanel.add(locationField);

        //Create Phone field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 230, 100, 25);
        registrationPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 230, 180, 25);
        registrationPanel.add(phoneField);

        //Create Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 270, 100, 25);
        registrationPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 270, 180, 25);
        registrationPanel.add(emailField);

        //Gender Radio Buttons
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 310, 100, 25);
        registrationPanel.add(genderLabel);

        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(160, 310, 60, 25);
        maleRadio.setSelected(true);

        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(230, 310, 80, 25);

        otherRadio = new JRadioButton("Other");
        otherRadio.setBounds(320, 310, 80, 25);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);

        registrationPanel.add(maleRadio);
        registrationPanel.add(femaleRadio);
        registrationPanel.add(otherRadio);

        //Right column
        //DOB field with combo boxes
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(450, 110, 100, 25);
        registrationPanel.add(dobLabel);

        //Day ComboBox
        dobDayComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dobDayComboBox.addItem(String.format("%02d", i));
        }
        dobDayComboBox.setBounds(550, 110, 60, 25);
        registrationPanel.add(dobDayComboBox);

        //Month ComboBox
        dobMonthComboBox = new JComboBox<>();
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        for (String month : months) {
            dobMonthComboBox.addItem(month);
        }
        dobMonthComboBox.setBounds(620, 110, 60, 25);
        registrationPanel.add(dobMonthComboBox);

        //Year ComboBox
        dobYearComboBox = new JComboBox<>();
        for (int i = 2024; i >= 1950; i--) {
            dobYearComboBox.addItem(Integer.toString(i));
        }
        dobYearComboBox.setBounds(690, 110, 80, 25);
        registrationPanel.add(dobYearComboBox);

        //Membership Start Date
        JLabel msDateLabel = new JLabel("Start Date:");
        msDateLabel.setBounds(450, 150, 100, 25);
        registrationPanel.add(msDateLabel);

        //Day ComboBox
        msDayComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            msDayComboBox.addItem(String.format("%02d", i));
        }
        msDayComboBox.setBounds(550, 150, 60, 25);
        registrationPanel.add(msDayComboBox);

        //Month ComboBox
        msMonthComboBox = new JComboBox<>();
        for (String month : months) {
            msMonthComboBox.addItem(month);
        }
        msMonthComboBox.setBounds(620, 150, 60, 25);
        registrationPanel.add(msMonthComboBox);

        //Year ComboBox
        msYearComboBox = new JComboBox<>();
        for (int i = 2024; i >= 2020; i--) {
            msYearComboBox.addItem(Integer.toString(i));
        }
        msYearComboBox.setBounds(690, 150, 80, 25);
        registrationPanel.add(msYearComboBox);

        //Plan selection for regular members
        planLabel = new JLabel("Plan:");
        planLabel.setBounds(450, 190, 100, 25);
        registrationPanel.add(planLabel);

        planComboBox = new JComboBox(new String[]{"Basic", "Standard", "Deluxe"});
        planComboBox.setBounds(550, 190, 180, 25);
        registrationPanel.add(planComboBox);

        //Update plan price based on selection
        planComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updatePlanPrice();
                }
            }
        });

        //Additional fields
        JLabel referralLabel = new JLabel("Referral Source:");
        referralLabel.setBounds(450, 230, 100, 25);
        registrationPanel.add(referralLabel);

        referralSourceField = new JTextField();
        referralSourceField.setBounds(550, 230, 180, 25);
        registrationPanel.add(referralSourceField);

        paidAmountLabel = new JLabel("Paid Amount:");
        paidAmountLabel.setBounds(450, 270, 100, 25);
        registrationPanel.add(paidAmountLabel);

        paidAmountField = new JTextField();
        paidAmountField.setBounds(550, 270, 180, 25);
        registrationPanel.add(paidAmountField);

        trainerNameLabel = new JLabel("Trainer Name:");
        trainerNameLabel.setBounds(450, 190, 100, 25);
        registrationPanel.add(trainerNameLabel);

        trainerNameField = new JTextField();
        trainerNameField.setBounds(550, 190, 180, 25);
        registrationPanel.add(trainerNameField);

        //Non-editable fields in a separate panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBackground(secondaryColor);
        infoPanel.setBounds(50, 350, 650, 100);
        registrationPanel.add(infoPanel);

        JLabel infoTitle = new JLabel("Pricing Information");
        infoTitle.setBounds(10, 10, 200, 25);
        infoTitle.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(infoTitle);

        //Non-editable fields for prices
        regularPlanPriceLabel = new JLabel("Regular Plan Price:");
        regularPlanPriceLabel.setBounds(10, 45, 150, 25);
        infoPanel.add(regularPlanPriceLabel);

        regularPlanPriceField = new JTextField();
        regularPlanPriceField.setBounds(160, 45, 180, 25);
        regularPlanPriceField.setEditable(false);
        infoPanel.add(regularPlanPriceField);

        premiumPlanChargeLabel = new JLabel("Premium Plan Charge:");
        premiumPlanChargeLabel.setBounds(10, 45, 150, 25);
        infoPanel.add(premiumPlanChargeLabel);

        premiumPlanChargeField = new JTextField();
        premiumPlanChargeField.setBounds(160, 45, 180, 25);
        premiumPlanChargeField.setEditable(false);
        infoPanel.add(premiumPlanChargeField);

        discountAmountLabel = new JLabel("Discount Amount:");
        discountAmountLabel.setBounds(350, 45, 150, 25);
        infoPanel.add(discountAmountLabel);

        discountAmountField = new JTextField();
        discountAmountField.setBounds(500, 45, 140, 25);
        discountAmountField.setEditable(false);
        infoPanel.add(discountAmountField);

        //Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(50, 470, 650, 60);
        buttonPanel.setBackground(Color.WHITE);
        registrationPanel.add(buttonPanel);

        //Action buttons
        JButton addButton = hoverButton("Add Member");
        addButton.setBounds(150, 15, 150, 39);
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        buttonPanel.add(addButton);

        JButton clearButton = greyhoverButton("Clear Form");
        clearButton.setBounds(350, 15, 150, 39);
        clearButton.setFocusPainted(false);
        clearButton.setBorderPainted(false);
        buttonPanel.add(clearButton);

        //Register action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMember();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        //Seting visibility of fields based on member type
        regularRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFieldsForMemberType(true);
            }
        });

        premiumRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFieldsForMemberType(false);
            }
        });

        //Initial setup for member type
        setFieldsForMemberType(true);
        updatePlanPrice();

        return registrationPanel;
    }

    /*
     * Creating the admin/control panel where the necessary buttons are swet up
     */
    private JPanel adminControlPanel() {
        //Create admin panel with null layout
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBackground(Color.WHITE);
        
        //Add this initialization (best placed near where you create the control panel)
        memberDetailsArea = new JTextArea();
        memberDetailsArea.setEditable(false);
        memberDetailsArea.setLineWrap(true);
        memberDetailsArea.setWrapStyleWord(true);

        //Add title
        JLabel titleLabel = new JLabel("Control Panel");
        titleLabel.setBounds(220, 20, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(textColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        controlPanel.add(titleLabel);

        //Create admin actions panel
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(null);
        actionsPanel.setBounds(50, 80, 700, 550);
        actionsPanel.setBackground(secondaryColor);
        controlPanel.add(actionsPanel);

        //Admin panel title
        JLabel adminActionTitle = new JLabel("Member Management Actions");
        adminActionTitle.setBounds(165, 20, 400, 25);
        adminActionTitle.setFont(new Font("Arial", Font.BOLD, 16));
        adminActionTitle.setHorizontalAlignment(JLabel.CENTER);
        actionsPanel.add(adminActionTitle);

        //Member ID input field
        JLabel idLabel = new JLabel("Enter Member ID:");
        idLabel.setBounds(50, 70, 120, 30);
        actionsPanel.add(idLabel);

        adminIdField = new JTextField();
        adminIdField.setBounds(180, 70, 200, 35);
        actionsPanel.add(adminIdField);

        //Action buttons
        JButton activateButton = hoverButton("Activate Membership");
        activateButton.setBounds(100, 130, 200, 45);
        activateButton.setForeground(Color.WHITE);
        activateButton.setFocusPainted(false);
        activateButton.setBorderPainted(false);
        actionsPanel.add(activateButton);

        JButton deactivateButton = hoverButton("Deactivate Membership");
        deactivateButton.setBounds(360, 130, 200, 45);
        deactivateButton.setForeground(Color.WHITE);
        deactivateButton.setFocusPainted(false);
        deactivateButton.setBorderPainted(false);
        actionsPanel.add(deactivateButton);

        JButton markAttendanceButton = hoverButton("Mark Attendance");
        markAttendanceButton.setBounds(100, 200, 200, 45);
        markAttendanceButton.setForeground(Color.WHITE);
        markAttendanceButton.setFocusPainted(false);
        markAttendanceButton.setBorderPainted(false);
        actionsPanel.add(markAttendanceButton);

        JButton displayButton = hoverButton("Display Member Info");
        displayButton.setBounds(360, 200, 200, 45);
        displayButton.setForeground(Color.WHITE);
        displayButton.setFocusPainted(false);
        displayButton.setBorderPainted(false);
        actionsPanel.add(displayButton);

        JButton revertRegularButton = redHoverButton("Revert Regular Member");
        revertRegularButton.setBounds(100, 270, 200, 45);
        revertRegularButton.setForeground(Color.WHITE);
        revertRegularButton.setFocusPainted(false);
        revertRegularButton.setBorderPainted(false);
        actionsPanel.add(revertRegularButton);

        JButton revertPremiumButton = redHoverButton("Revert Premium Member");
        revertPremiumButton.setBounds(360, 270, 200, 45);
        revertPremiumButton.setForeground(Color.WHITE);
        revertPremiumButton.setFocusPainted(false);
        revertPremiumButton.setBorderPainted(false);
        actionsPanel.add(revertPremiumButton);

        JButton calculateDiscountButton = hoverButton("Calculate Discount");
        calculateDiscountButton.setBounds(100, 340, 200, 45);
        calculateDiscountButton.setForeground(Color.WHITE);
        calculateDiscountButton.setFocusPainted(false);
        calculateDiscountButton.setBorderPainted(false);
        actionsPanel.add(calculateDiscountButton);

        JButton upgradePlanButton = hoverButton("Upgrade Plan");
        upgradePlanButton.setBounds(360, 340, 200, 45);
        upgradePlanButton.setForeground(Color.WHITE);
        upgradePlanButton.setFocusPainted(false);
        upgradePlanButton.setBorderPainted(false);
        actionsPanel.add(upgradePlanButton);

        JButton saveToFileButton = hoverButton("Save to File");
        saveToFileButton.setBounds(100, 410, 200, 45);
        saveToFileButton.setForeground(Color.WHITE);
        saveToFileButton.setFocusPainted(false);
        saveToFileButton.setBorderPainted(false);
        actionsPanel.add(saveToFileButton);

        JButton readFromFileButton = hoverButton("Read from File");
        readFromFileButton.setBounds(360, 410, 200, 45);
        readFromFileButton.setForeground(Color.WHITE);
        readFromFileButton.setFocusPainted(false);
        readFromFileButton.setBorderPainted(false);
        actionsPanel.add(readFromFileButton);
        
        JButton payDueButton = hoverButton("Pay Due Amount");
        payDueButton.setBounds(230, 480, 200, 45);
        payDueButton.setForeground(Color.WHITE);
        payDueButton.setFocusPainted(false);
        payDueButton.setBorderPainted(false);
        actionsPanel.add(payDueButton);
        
        //Add action listeners to call constructors directly
        payDueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                payDueAmount();
            }
        });

        activateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activateMembership();
            }
        });

        deactivateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deactivateMembership();
            }
        });

        markAttendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                markAttendance();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayInfoDialog();
            }
        });

        revertRegularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revertRegularMember();
            }
        });

        revertPremiumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revertPremiumMember();
            }
        });

        calculateDiscountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateDiscount();
            }
        });

        upgradePlanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                upgradePlan();
            }
        });

        saveToFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveMembersToFile();
            }
        });

        readFromFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readMembersFromFile();
            }
        });
        
        return controlPanel;
    }
    
    private JButton hoverButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(52, 73, 94));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(primaryColor);
            }
    
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 73, 94));
            }
        });
        
        return button;
    }
    
    private JButton redHoverButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(100, 30, 30));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.RED);
            }
    
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 30, 30));
            }
        });
        
        return button;
    }
    
    private JButton greyhoverButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(64, 64, 64));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.GRAY);
            }
    
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(64, 64, 64));
            }
        });
        
        return button;       
    }

    private void displayInfoDialog() {
        GymMember member = checkID();
        if (member != null) {
            showMemberInfoFrame(member);
        }
    }

    private void showMemberInfoFrame(GymMember member) {
        JFrame infoFrame = new JFrame("Member Information - ID: " + member.getId());
        infoFrame.setSize(500, 600);
        infoFrame.setLayout(null);
        infoFrame.setLocationRelativeTo(mainFrame);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));

        StringBuilder details = new StringBuilder();
        details.append("Member ID: ").append(member.getId()).append("\n");
        details.append("Name: ").append(member.getName()).append("\n");
        details.append("Location: ").append(member.getLocation()).append("\n");
        details.append("Phone: ").append(member.getPhone()).append("\n");
        details.append("Email: ").append(member.getEmail()).append("\n");
        details.append("Gender: ").append(member.getGender()).append("\n");
        details.append("Date of Birth: ").append(member.getDOB()).append("\n");
        details.append("Membership Start: ").append(member.getMembershipStartDate()).append("\n");
        details.append("Attendance: ").append(member.getAttendance()).append("\n");
        details.append("Loyalty Points: ").append(member.getLoyaltyPoints()).append("\n");
        details.append("Membership Status: ").append(member.getActiveStatus() ? "Active" : "Inactive").append("\n\n");

        if (member instanceof RegularMember reg) {
            details.append("Member Type: Regular\n");
            details.append("Plan: ").append(reg.getPlan()).append("\n");
            details.append("Price: ").append(reg.getPrice()).append("\n");
            details.append("Referral Source: ").append(reg.getReferralSource()).append("\n");
            details.append("Eligible for Upgrade: ").append(reg.getIsEligibleForUpgrade() ? "Yes" : "No").append("\n");

            if (!reg.getRemovalReason().isEmpty()) {
                details.append("Removal Reason: ").append(reg.getRemovalReason()).append("\n");
            }
        } else if (member instanceof PremiumMember prem) {
            details.append("Member Type: Premium\n");
            details.append("Personal Trainer: ").append(prem.getPersonalTrainer()).append("\n");
            details.append("Premium Charge: ").append(prem.getPremiumCharge()).append("\n");
            details.append("Paid Amount: ").append(prem.getPaidAmount()).append("\n");
            details.append("Payment Status: ").append(prem.getIsFullPayment() ? "Complete" : "Incomplete").append("\n");

            double remaining = prem.getPremiumCharge() - prem.getPaidAmount();
            details.append("Remaining Amount: ").append(remaining).append("\n");

            if (prem.getIsFullPayment()) {
                details.append("Discount Amount: ").append(prem.getDiscountAmount()).append("\n");
            }
        }

        infoArea.setText(details.toString());

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBounds(20, 20, 440, 500);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    /*
     * Create the view members panel
     */
    private JPanel ViewMembersPanel() {
        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(null);
        viewPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("View All Members");
        titleLabel.setBounds(220, 20, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(textColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        viewPanel.add(titleLabel);

        //Container for member buttons
        JPanel memberListPanel = new JPanel();
        memberListPanel.setLayout(null);
        memberListPanel.setBounds(50, 80, 300, 500);
        memberListPanel.setBackground(secondaryColor);
        viewPanel.add(memberListPanel);

        JLabel memberListTitle = new JLabel("Member List");
        memberListTitle.setBounds(20, 20, 260, 25);
        memberListTitle.setFont(new Font("Arial", Font.BOLD, 16));
        memberListTitle.setHorizontalAlignment(JLabel.CENTER);
        memberListPanel.add(memberListTitle);

        //Member details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.setBounds(380, 80, 370, 500);
        detailsPanel.setBackground(secondaryColor);
        viewPanel.add(detailsPanel);

        JLabel detailsTitle = new JLabel("Member Details");
        detailsTitle.setBounds(20, 20, 330, 25);
        detailsTitle.setFont(new Font("Arial", Font.BOLD, 16));
        detailsTitle.setHorizontalAlignment(JLabel.CENTER);
        detailsPanel.add(detailsTitle);

        //Initialize the member details area and store it as a class field for access in showMemberDetails
        viewMemberDetailsArea = new JTextArea();
        viewMemberDetailsArea.setEditable(false);
        viewMemberDetailsArea.setLineWrap(true);
        viewMemberDetailsArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(viewMemberDetailsArea);
        scrollPane.setBounds(20, 50, 330, 430);
        detailsPanel.add(scrollPane);

        return viewPanel;
    }

    /*
     * Updating component visibility based on member type (regular and premium have different required fields to be filled)
     */
    private void setFieldsForMemberType(boolean isRegular) {
        //Update components visibility
        if (planComboBox != null) planComboBox.setVisible(isRegular);
        if (trainerNameField != null) trainerNameField.setVisible(!isRegular);
        if (planLabel != null) planLabel.setVisible(isRegular);
        if (trainerNameLabel != null) trainerNameLabel.setVisible(!isRegular);
        if (regularPlanPriceLabel != null) regularPlanPriceLabel.setVisible(isRegular);
        if (premiumPlanChargeLabel != null) premiumPlanChargeLabel.setVisible(!isRegular);

        //Update labels
        if (regularPlanPriceField != null) regularPlanPriceField.setVisible(isRegular);
        if (premiumPlanChargeField != null) premiumPlanChargeField.setVisible(!isRegular);
        if (discountAmountField != null) discountAmountField.setVisible(!isRegular);
        if (discountAmountLabel != null) discountAmountLabel.setVisible(!isRegular);
        if (paidAmountField != null) paidAmountField.setVisible(!isRegular);
        if (paidAmountLabel != null) paidAmountLabel.setVisible(!isRegular);

        //Update values of price (Used help of AI and youtube)
        if (isRegular) {
            updatePlanPrice();
            regularPlanPriceField.setText(String.valueOf(new RegularMember(0, "", "", "", "", "", "", "", "").getPlanPrice(planComboBox.getSelectedItem().toString())));
        } else {
            PremiumMember fakeyy = new PremiumMember(0, "", "", "", "", "", "", "", "");
            premiumPlanChargeField.setText(String.valueOf(fakeyy.getPremiumCharge()));
        }
    }

    /*
     * Updating the plan price based on selection from combobox
     */
    private void updatePlanPrice() {
        if (regularRadio.isSelected()) {
            String selectedPlan = (String) planComboBox.getSelectedItem();
            RegularMember fakeyy = new RegularMember(0, "", "", "", "", "", "", "", "");
            double price = fakeyy.getPlanPrice(selectedPlan);
            regularPlanPriceField.setText(String.valueOf(price));
        }
    }

    /*
     * Validating the registration form for members for all inputs that needs to be done
     */
    private boolean validateInputs() {
        try {
            //Check all required fields are filled
            if (idField.getText().trim().isEmpty() ||
                nameField.getText().trim().isEmpty() ||
                locationField.getText().trim().isEmpty() ||
                phoneField.getText().trim().isEmpty() ||
                emailField.getText().trim().isEmpty()) {
                throw new Exception("All fields are mandatory");
            }
    
            //Validate ID is numeric
            int id = Integer.parseInt(idField.getText().trim());
            
            //Validate name has at least 3 letters
            if (nameField.getText().trim().length() < 3) {
                throw new Exception("Name must have at least 3 letters");
            }
    
            //Validate phone is exactly 10 digits
            String phone = phoneField.getText().trim();
            if (phone.length() != 10 || !phone.matches("\\d+")) {
                throw new Exception("Phone must be exactly 10 digits");
            }
    
            //Validate email format
            String email = emailField.getText().trim();
            if (!email.contains("@") || !email.contains(".")) {
                throw new Exception("Invalid email format");
            }
    
            //Validate DOB components
            int day = Integer.parseInt((String)dobDayComboBox.getSelectedItem());
            int month = Integer.parseInt((String)dobMonthComboBox.getSelectedItem());
            int year = Integer.parseInt((String)dobYearComboBox.getSelectedItem());
            if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900) {
                throw new Exception("Invalid date of birth");
            }
    
            //Premium member validation
            if (premiumRadio.isSelected() && trainerNameField.getText().trim().isEmpty()) {
                throw new Exception("Trainer name required for premium members");
            }
            
            //If paid amount is provided, validate it's a valid number
            if (!paidAmountField.getText().trim().isEmpty()) {
                try {
                    Double.parseDouble(paidAmountField.getText().trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(mainFrame, "Paid Amount must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, 
                "ID and phone must be numbers", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainFrame, 
                e.getMessage(), 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /*
     * Adding a new member (either regular or premium) from the form
     */
    private void addMember() {
        //Validate inputs
        if (!validateInputs()) {
            return;
        }

        try {
            //Get common member details
            int id = Integer.parseInt(idField.getText());

            //Check for duplicate ID
            for (GymMember member : jim) {
                if (member.getId() == id) {
                    JOptionPane.showMessageDialog(mainFrame, 
                        "A member with this ID already exists.", 
                        "Duplicate ID", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String name = nameField.getText();
            String location = locationField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            //Get gender
            String gender = "";
            if (maleRadio.isSelected()) {
                gender = "Male";
            }
            else if (femaleRadio.isSelected()) {
                gender = "Female";
            }
            else {
                gender = "Other";
            }

            //Get dates
            String dob = dobYearComboBox.getSelectedItem() + "-" + 
                dobMonthComboBox.getSelectedItem() + "-" + 
                dobDayComboBox.getSelectedItem();

            String startDate = msYearComboBox.getSelectedItem() + "-" + 
                msMonthComboBox.getSelectedItem() + "-" + 
                msDayComboBox.getSelectedItem();

            //Create appropriate member object
            GymMember newMember;
            if (regularRadio.isSelected()) {
                String referralSource = referralSourceField.getText();
                newMember = new RegularMember(id, name, location, phone, email, gender, dob, startDate, referralSource);
            } else {
                String trainerName = trainerNameField.getText();
                newMember = new PremiumMember(id, name, location, phone, email, gender, dob, startDate, trainerName);

                //Set paid amount if provided
                if (!paidAmountField.getText().isEmpty()) {
                    double paidAmount = Double.parseDouble(paidAmountField.getText());
                    ((PremiumMember) newMember).payDueAmount(paidAmount);
                    
                    //Explicitly check if payment is complete
                    if ((paidAmount > ((PremiumMember) newMember).getPremiumCharge())) {
                        JOptionPane.showMessageDialog(mainFrame, "Payment amount exceeds premium charge.", "Please try again!", JOptionPane.ERROR_MESSAGE);
                        return;
                    }else if ((paidAmount == ((PremiumMember) newMember).getPremiumCharge())) { 
                        ((PremiumMember) newMember).calculateDiscount(); //Calculate discount if full payment
                    }
                }
            }

            //Add to list and clear form
            jim.add(newMember);
            JOptionPane.showMessageDialog(mainFrame, "Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter valid numeric values for ID and amount fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainFrame, "Error adding member: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
     * Clear the registration form
     */
    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        referralSourceField.setText("");
        paidAmountField.setText("");
        trainerNameField.setText("");
        maleRadio.setSelected(true);
        regularRadio.setSelected(true);
        setFieldsForMemberType(true);
    }

    /*
     * Refresh the member view panel
     */
    private void refreshMemberView() {
        //Get the panel and clear previous content
        JPanel memberListPanel = (JPanel) viewMembersPanel.getComponent(1);
        memberListPanel.removeAll();

        //Re-add title
        JLabel memberListTitle = new JLabel("Member List");
        memberListTitle.setBounds(20, 20, 260, 25);
        memberListTitle.setFont(new Font("Arial", Font.BOLD, 16));
        memberListTitle.setHorizontalAlignment(JLabel.CENTER);
        memberListPanel.add(memberListTitle);

        //Add member buttons
        int y = 60;
        for (GymMember member : jim) {
            String memberType = (member instanceof RegularMember) ? "Regular" : "Premium";
            String buttonText = "ID: " + member.getId() + " - " + member.getName() + " (" + memberType + ")";

            JButton memberButton = new JButton(buttonText);
            memberButton.setBounds(20, y, 260, 30);
            memberButton.setBackground(member.getActiveStatus() ? primaryColor : Color.GRAY);
            memberButton.setForeground(Color.WHITE);
            memberButton.setFocusPainted(false);
            memberButton.setBorderPainted(false);

            //Add action to show member details
            final GymMember currentMember = member;
            memberButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showMemberDetails(currentMember);
                }
            });

            memberListPanel.add(memberButton);
            y += 40;
        }

        memberListPanel.revalidate();
        memberListPanel.repaint();
    }

    /*
     * Show details of a specific member
     */
    private void showMemberDetails(GymMember member) {
        //Use the class field for the member details area
        StringBuilder details = new StringBuilder();
        details.append("Member ID: ").append(member.getId()).append("\n");
        details.append("Name: ").append(member.getName()).append("\n");
        details.append("Location: ").append(member.getLocation()).append("\n");
        details.append("Phone: ").append(member.getPhone()).append("\n");
        details.append("Email: ").append(member.getEmail()).append("\n");
        details.append("Gender: ").append(member.getGender()).append("\n");
        details.append("Date of Birth: ").append(member.getDOB()).append("\n");
        details.append("Membership Start: ").append(member.getMembershipStartDate()).append("\n");
        details.append("Attendance: ").append(member.getAttendance()).append("\n");
        details.append("Loyalty Points: ").append(member.getLoyaltyPoints()).append("\n");
        details.append("Membership Status: ").append(member.getActiveStatus() ? "Active" : "Inactive").append("\n\n");

        if (member instanceof RegularMember) {
            RegularMember regMember = (RegularMember) member;
            details.append("Member Type: Regular\n");
            details.append("Plan: ").append(regMember.getPlan()).append("\n");
            details.append("Price: ").append(regMember.getPrice()).append("\n");
            details.append("Referral Source: ").append(regMember.getReferralSource()).append("\n");
            details.append("Eligible for Upgrade: ").append(regMember.getIsEligibleForUpgrade() ? "Yes" : "No").append("\n");

            if (!regMember.getRemovalReason().isEmpty()) {
                details.append("Removal Reason: ").append(regMember.getRemovalReason()).append("\n");
            }
        } else if (member instanceof PremiumMember) {
            PremiumMember premMember = (PremiumMember) member;
            details.append("Member Type: Premium\n");
            details.append("Personal Trainer: ").append(premMember.getPersonalTrainer()).append("\n");
            details.append("Premium Charge: ").append(premMember.getPremiumCharge()).append("\n");
            details.append("Paid Amount: ").append(premMember.getPaidAmount()).append("\n");
            details.append("Payment Status: ").append(premMember.getIsFullPayment() ? "Complete" : "Incomplete").append("\n");

            double remainingAmount = premMember.getPremiumCharge() - premMember.getPaidAmount();
            details.append("Remaining Amount: ").append(remainingAmount).append("\n");

            if (premMember.getIsFullPayment()) {
                details.append("Discount Amount: ").append(premMember.getDiscountAmount()).append("\n");
            }
        }

        viewMemberDetailsArea.setText(details.toString());
    }

    /*
     * Checking if the ID entered by user in admin panel exists or not
     */
    private GymMember checkID() {
        if (adminIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, 
                "Please enter a member ID.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            int id = Integer.parseInt(adminIdField.getText().trim());

            for (GymMember member : jim) {
                if (member.getId() == id) {
                    return member;
                }
            }

            JOptionPane.showMessageDialog(mainFrame, 
                "No member found with ID " + id, 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return null;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainFrame, 
                "ID must be a valid number.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /*
     * Activate membership (using activateMembership() from GymMember)
     */
    private void activateMembership() {
        GymMember member = checkID();
        if (member != null) {
            member.activateMembership();
            JOptionPane.showMessageDialog(mainFrame, 
                "Membership activated successfully for ID: " + member.getId(), 
                "Activated", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*
     * Deactivate membership (using deactivateMembership() from GymMember)
     */
    private void deactivateMembership() {
        GymMember member = checkID();
        if (member != null) {
            member.deactivateMembership();
            JOptionPane.showMessageDialog(mainFrame, 
                "Membership deactivated successfully for ID: " + member.getId(), 
                "Deactivated", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*
     * Mark attendance method (calling constructors from prev classes and utilising their code)
     */
    private void markAttendance() {
        GymMember member = checkID();
        if (member != null) {
            //Check if membership is active before marking attendance
            if (!member.getActiveStatus()) {
                JOptionPane.showMessageDialog(mainFrame, 
                    "Cannot mark attendance for inactive membership. Please activate the membership first.", 
                    "Inactive Membership", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            member.markAttendance();

            String message = "Attendance marked successfully for ID " + member.getId() + 
                "\nNew attendance count: " + member.getAttendance() +
                "\nLoyalty points: " + member.getLoyaltyPoints();

            if (member instanceof RegularMember) {
                RegularMember regularMember = (RegularMember) member;
                message += "\nEligible for upgrade: " + (regularMember.getIsEligibleForUpgrade() ? "Yes" : "No");
                message += "\nAttendance limit: " + regularMember.getAttendanceLimit();
            }

            JOptionPane.showMessageDialog(mainFrame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*
     * Calculate discount for premium members
     */
    private void calculateDiscount() {
        GymMember member = checkID();
        if (member != null) {
            //Check if membership is active before calculating discount
            if (!member.getActiveStatus()) {
                JOptionPane.showMessageDialog(mainFrame, 
                    "Cannot calculate discount for inactive membership. Please activate the membership first.", 
                    "Inactive Membership", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (member instanceof PremiumMember) {
                PremiumMember premMember = (PremiumMember) member;

                if (!premMember.getIsFullPayment()) {
                    JOptionPane.showMessageDialog(mainFrame,
                        "Cannot calculate discount - payment not completed!\n" +
                        "Paid: " + premMember.getPaidAmount() + "\n" +
                        "Remaining: " + (premMember.getPremiumCharge() - premMember.getPaidAmount()),
                        "Discount Calculation",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    premMember.calculateDiscount();
                    JOptionPane.showMessageDialog(mainFrame,
                        "Discount calculated successfully!\n" +
                        "Discount Amount: " + premMember.getDiscountAmount() + "\n" +
                        "(10% of " + premMember.getPremiumCharge() + ")",
                        "Discount Applied",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame,
                    "This feature is only available for Premium Members",
                    "Invalid Member Type",
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /*
     * Revert a regular member
     * Only allow for reverting "regular" members
     * Check for removal reasoning before reverting
     */
    private void revertRegularMember() {
        GymMember member = checkID();
        if (member != null) {
            if (member instanceof RegularMember) {
                String reason = JOptionPane.showInputDialog(mainFrame,
                        "Enter reason for reverting membership:", "Revert Membership", 
                        JOptionPane.QUESTION_MESSAGE);

                if (reason != null && !reason.trim().isEmpty()) {
                    ((RegularMember) member).revertRegularMember(reason);
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Regular member reverted successfully. ID: " + member.getId(), 
                        "Reverted", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, 
                        "A reason is required to revert membership.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, 
                    "This feature is only available for Regular members.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*
     * Revert a premium member
     * Only allow for reverting "premium" members
     */
    private void revertPremiumMember() {
        GymMember member = checkID();
        if (member != null) {
            if (member instanceof PremiumMember) {
                ((PremiumMember) member).revertPremiumMember();
                JOptionPane.showMessageDialog(mainFrame, 
                    "Premium member reverted successfully. ID: " + member.getId(), 
                    "Reverted", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(mainFrame, 
                    "This feature is only available for Premium members.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*
     * Upgrade plan for regular members
     */
    private void upgradePlan() {
        GymMember member = checkID();
        if (member != null) {
            //Check if membership is active before upgrading plan
            if (!member.getActiveStatus()) {
                JOptionPane.showMessageDialog(mainFrame, 
                    "Cannot upgrade plan for inactive membership. Please activate the membership first.", 
                    "Inactive Membership", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (member instanceof RegularMember) {
                RegularMember regularMember = (RegularMember) member;

                String[] plans = {"Basic", "Standard", "Deluxe"};
                String selectedPlan = (String) JOptionPane.showInputDialog(
                        mainFrame,
                        "Select a plan to upgrade to:",
                        "Upgrade Plan",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        plans,
                        plans[0]
                    );

                if (selectedPlan != null) {
                    String result = regularMember.upgradePlan(selectedPlan);
                    memberDetailsArea.setText(result);
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, 
                    "This feature is only available for Regular members.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*
     * Process payment for premium members
     */
    private void payDueAmount() {
        GymMember member = checkID();
        if (member == null) return;
    
        if (member instanceof PremiumMember) {
            PremiumMember premMember = (PremiumMember) member;
            
            //Get payment amount from user
            String amountStr = JOptionPane.showInputDialog(mainFrame,
                "Enter payment amount (Max: " + (premMember.getPremiumCharge() - premMember.getPaidAmount()) + ")",
                "Pay Due Amount",
                JOptionPane.QUESTION_MESSAGE);
            
            if (amountStr == null || amountStr.trim().isEmpty()) return;
    
            try {
                double amount = Double.parseDouble(amountStr);
                String result;
                
                double newPaidAmount = premMember.getPaidAmount() + amount;
                double remaining = premMember.getPremiumCharge() - newPaidAmount;
                
                //Use the same validation messages as PremiumMember class
                if (premMember.getIsFullPayment()) {
                    result = "Your payment is already completed. Thank You!";
                } 
                else if (newPaidAmount > premMember.getPremiumCharge()) {
                    result = "Payment amount exceeds the premium charge. The total charge is: " 
                           + premMember.getPremiumCharge();
                }
                else {
                    premMember.payDueAmount(amount);
                    
                    if (remaining > 0) {
                        result = "Payment successful! Remaining amount: " + remaining;
                    } else {
                        result = "Payment completed. Thank you for your purchase!\n" + 
                                "Discount of " + (premMember.getPremiumCharge() * 0.10) + 
                                " has been applied.";
                    }
                }
                
                JOptionPane.showMessageDialog(mainFrame, result);
                
                //Refreshing display to update view members panel
                if (currentPanel == viewMembersPanel) {
                    refreshMemberView();
                }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainFrame,
                    "Please enter a valid number for the payment amount.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame,
                "This feature is only available for Premium Members.",
                "Invalid Member Type",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveMembersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("MemberDetails.txt"))) {
            //Write header
            writer.write(String.format("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %-15s %-10s %-15s %-15s %-15s\n", 
                    "ID", "Name", "Location", "Phone", "Email", "Membership Start", "Plan", "Price", 
                    "Attendance", "Loyalty Points", "Active", "Full Payment", "Discount", "Net Paid"));

            //Write member data
            for (GymMember member : jim) {
                String plan = "";
                String price = "";
                String fullPayment = "";
                String discount = "";
                String netPaid = "";

                if (member instanceof RegularMember) {
                    RegularMember reg = (RegularMember) member;
                    plan = reg.getPlan();
                    price = String.valueOf(reg.getPrice());
                    fullPayment = "N/A";
                    discount = "N/A";
                    netPaid = "N/A";
                } else if (member instanceof PremiumMember) {
                    PremiumMember prem = (PremiumMember) member;
                    plan = "Premium";
                    price = String.valueOf(prem.getPremiumCharge());
                    fullPayment = prem.getIsFullPayment() ? "Yes" : "No";
                    discount = String.valueOf(prem.getDiscountAmount());
                    netPaid = String.valueOf(prem.getPaidAmount());
                }

                writer.write(String.format("%-5s %-15s %-15s %-15s %-25s %-20s %-10s %-10s %-10s %-15s %-10s %-15s %-15s %-15s\n", 
                        member.getId(), 
                        member.getName(), 
                        member.getLocation(), 
                        member.getPhone(), 
                        member.getEmail(), 
                        member.getMembershipStartDate(), 
                        plan, 
                        price, 
                        member.getAttendance(), 
                        member.getLoyaltyPoints(), 
                        member.getActiveStatus() ? "Yes" : "No",
                        fullPayment,
                        discount,
                        netPaid));
            }

            JOptionPane.showMessageDialog(mainFrame, 
                "Member details saved to file successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainFrame, 
                "Error saving to file: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void readMembersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("MemberDetails.txt"))) {
            //Create a properly sized frame
            JFrame fileFrame = new JFrame("Member Details from File");
            fileFrame.setSize(1000, 600);
            fileFrame.setLayout(new BorderLayout());
    
            //Use JTextArea with monospaced font for proper alignment
            JTextArea fileContentArea = new JTextArea();
            fileContentArea.setEditable(false);
            fileContentArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            
            //Read and format the file content
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            fileContentArea.setText(content.toString());
    
            //Add to scroll pane with proper sizing
            JScrollPane scrollPane = new JScrollPane(fileContentArea);
            scrollPane.setPreferredSize(new Dimension(980, 550));
            fileFrame.add(scrollPane, BorderLayout.CENTER);
            
            fileFrame.setLocationRelativeTo(mainFrame);
            fileFrame.setVisible(true);
    
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainFrame, 
                "Error reading file: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
     * Main method to start the application
     */
    public static void main() {
        new GymGUI();
    }
}