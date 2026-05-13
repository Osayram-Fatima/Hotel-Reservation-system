import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

// Room class
class Room {
    String type;
    double price;
    boolean available;

    Room(String type, double price, boolean available) {
        this.type = type;
        this.price = price;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room Type: " + type + ", Price: " + price + ", Available: " + available;
    }
}

// Reservation class
class Reservation {
    String name;
    String cnic;
    String roomType;
    int duration;
    double totalFee;
    double advancePayment;
    String reservationCode;
    String reservationDateTime;

    Reservation(String name, String cnic, String roomType, int duration, double totalFee, double advancePayment) {
        this.name = name;
        this.cnic = cnic;
        this.roomType = roomType;
        this.duration = duration;
        this.totalFee = totalFee;
        this.advancePayment = advancePayment;
        this.reservationCode = generateReservationCode();
        this.reservationDateTime = getCurrentDateTime();
    }

    private String generateReservationCode() {
        return String.format("RES%04d", new Random().nextInt(10000));
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    @Override
    public String toString() {
        return "Reservation Code: " + reservationCode + "\n" +
                "Name: " + name + "\n" +
                "CNIC: " + cnic + "\n" +
                "Room Type: " + roomType + "\n" +
                "Duration: " + duration + " days\n" +
                "Total Fee: " + totalFee + "\n" +
                "Advance Payment: " + advancePayment + "\n" +
                "Reservation Date and Time: " + reservationDateTime;
    }
}

// Base Person class
abstract class Person extends JFrame {
    protected List<Reservation> reservations;
    protected List<Room> rooms;
    private JButton returnToMainMenuButton;

    Person(List<Reservation> reservations, List<Room> rooms) {
        this.reservations = reservations;
        this.rooms = rooms;
        createUI();
    }

    abstract void createUI();

    void addReturnToMainMenuButton(JPanel panel, GridBagConstraints gbc) {
        returnToMainMenuButton = new JButton("Return to Main Menu");
        customizeButton(returnToMainMenuButton);
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(returnToMainMenuButton, gbc);
        returnToMainMenuButton.addActionListener(e -> returnToMainMenu());
    }

    void customizeButton(JButton button) {
        button.setBackground(Color.CYAN);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    void returnToMainMenu() {
        dispose();
        new MainMenu(reservations, rooms).setVisible(true);
    }
}

// User class
class User extends Person {
    private JTextArea textArea;
    private JTextField nameField;
    private JTextField cnicField;
    private JTextField roomTypeField;
    private JTextField durationField;
    private JButton makeReservationButton;
    private JButton checkAvailableRoomsButton;
    private JButton viewReservationDetailsButton;
    private JButton showTermsButton;

    User(List<Reservation> reservations, List<Room> rooms) {
        super(reservations, rooms);
    }

    @Override
    void createUI() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Hotel Reservation System - User");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.setBackground(new Color(227, 4, 97, 199));
        gbc.anchor = GridBagConstraints.WEST;

        nameField = new JTextField(20);
        cnicField = new JTextField(20);
        roomTypeField = new JTextField(20);
        durationField = new JTextField(10);

        makeReservationButton = new JButton("Make Reservation");
        checkAvailableRoomsButton = new JButton("Check Available Rooms");
        viewReservationDetailsButton = new JButton("View Reservation Details");
        showTermsButton = new JButton("Show Terms and Conditions");

        customizeButton(makeReservationButton);
        customizeButton(checkAvailableRoomsButton);
        customizeButton(viewReservationDetailsButton);
        customizeButton(showTermsButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("CNIC:"), gbc);
        gbc.gridx = 1;
        panel.add(cnicField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Room Type (Single/Double/Suite):"), gbc);
        gbc.gridx = 1;
        panel.add(roomTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Duration (days):"), gbc);
        gbc.gridx = 1;
        panel.add(durationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(makeReservationButton, gbc);

        gbc.gridy = 5;
        panel.add(checkAvailableRoomsButton, gbc);

        gbc.gridy = 6;
        panel.add(viewReservationDetailsButton, gbc);

        gbc.gridy = 7;
        panel.add(showTermsButton, gbc);

        addReturnToMainMenuButton(panel, gbc);

        getContentPane().add(panel, BorderLayout.WEST);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        makeReservationButton.addActionListener(e -> makeReservation());
        checkAvailableRoomsButton.addActionListener(e -> checkAvailableRooms());
        viewReservationDetailsButton.addActionListener(e -> viewReservationDetails());
        showTermsButton.addActionListener(e -> showTermsAndConditions());
    }

    private void showTermsAndConditions() {
        textArea.setText("Terms and Conditions:\n" +
                "1. The advance payment is non-refundable.\n" +
                "2. Check-out time is 12:00 PM.\n" +
                "3. Pets are not allowed.\n" +
                "4. Smoking is prohibited inside the rooms.\n" +
                "5. Guests are responsible for any damage caused.");
    }

    private void checkAvailableRooms() {
        textArea.setText("Available Rooms:\n");
        for (Room room : rooms) {
            if (room.available) {
                textArea.append(room.toString() + "\n");
            }
        }
    }

    private void makeReservation() {
        String name = nameField.getText().trim();
        String cnic = cnicField.getText().trim();
        String roomType = roomTypeField.getText().trim();

        if (name.isEmpty() || cnic.isEmpty() || roomType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        int duration;
        try {
            duration = Integer.parseInt(durationField.getText().trim());
            if (duration <= 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid duration entered. Please enter a positive number.");
            return;
        }

        for (Reservation res : reservations) {
            if (res.cnic.equals(cnic)) {
                JOptionPane.showMessageDialog(this, "This CNIC already has a reservation.");
                return;
            }
        }

        boolean roomAvailable = false;
        for (Room room : rooms) {
            if (room.type.equalsIgnoreCase(roomType) && room.available) {
                roomAvailable = true;
                room.available = false;
                break;
            }
        }

        if (!roomAvailable) {
            JOptionPane.showMessageDialog(this, "Room type '" + roomType + "' is not available.");
            return;
        }

        double totalFee = calculateFee(roomType, duration);
        double advancePayment = totalFee * 0.50;

        Reservation newReservation = new Reservation(name, cnic, roomType, duration, totalFee, advancePayment);
        reservations.add(newReservation);

        Admin.addRevenue(totalFee);

        textArea.setText("Reservation made successfully! Details:\n" + newReservation);
    }

    private double calculateFee(String roomType, int duration) {
        double rate;
        switch (roomType.toLowerCase()) {
            case "single":
                rate = 100.0;
                break;
            case "double":
                rate = 150.0;
                break;
            case "suite":
                rate = 200.0;
                break;
            default:
                rate = 0.0;
                break;
        }
        return rate * duration;
    }

    private void viewReservationDetails() {
        String cnic = JOptionPane.showInputDialog(this, "Enter CNIC to view reservation details:");
        if (cnic != null && !cnic.trim().isEmpty()) {
            for (Reservation res : reservations) {
                if (res.cnic.equals(cnic.trim())) {
                    textArea.setText("Reservation Details:\n" + res);
                    return;
                }
            }
            textArea.setText("No reservation found for this CNIC.");
        }
    }
}

// Admin class
class Admin extends Person {
    private JTextArea textArea;
    private JButton showAvailableRoomsButton;
    private JButton showReservedRoomsButton;
    private JButton checkoutButton;
    private JButton addRoomButton;
    private JButton editRoomButton;
    private JButton viewReportsButton;
    private JButton deleteRoomButton;
    private JButton returnToMainMenuButton;

    private List<Reservation> reservations;
    private final List<Room> rooms;

    private static double totalRevenue = 0;

    public static void addRevenue(double amount) {
        totalRevenue += amount;
    }

    Admin(List<Reservation> reservations, List<Room> rooms) {
        super(reservations, rooms);
        this.reservations = reservations;
        this.rooms = rooms;
        createUI();
    }

    @Override
    void createUI() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Hotel Reservation System - Admin");
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.setBackground(new Color(227, 4, 97, 199));
        gbc.anchor = GridBagConstraints.WEST;

        showAvailableRoomsButton = new JButton("Show Available Rooms");
        showReservedRoomsButton = new JButton("Show Reserved Rooms");
        checkoutButton = new JButton("Checkout");
        addRoomButton = new JButton("Add Room");
        editRoomButton = new JButton("Edit Room");
        viewReportsButton = new JButton("View Reports");
        deleteRoomButton = new JButton("Delete Room");
        returnToMainMenuButton = new JButton("Return to Main Menu");

        customizeButton(showAvailableRoomsButton);
        customizeButton(showReservedRoomsButton);
        customizeButton(checkoutButton);
        customizeButton(addRoomButton);
        customizeButton(editRoomButton);
        customizeButton(viewReportsButton);
        customizeButton(deleteRoomButton);
        customizeButton(returnToMainMenuButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(showAvailableRoomsButton, gbc);
        gbc.gridy = 1;
        panel.add(showReservedRoomsButton, gbc);
        gbc.gridy = 2;
        panel.add(checkoutButton, gbc);
        gbc.gridy = 3;
        panel.add(addRoomButton, gbc);
        gbc.gridy = 4;
        panel.add(editRoomButton, gbc);
        gbc.gridy = 5;
        panel.add(viewReportsButton, gbc);
        gbc.gridy = 6;
        panel.add(deleteRoomButton, gbc);
        gbc.gridy = 7;
        panel.add(returnToMainMenuButton, gbc);

        getContentPane().add(panel, BorderLayout.WEST);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        showAvailableRoomsButton.addActionListener(e -> showAvailableRooms());
        showReservedRoomsButton.addActionListener(e -> showReservedRooms());
        checkoutButton.addActionListener(e -> handleCheckout());
        addRoomButton.addActionListener(e -> addRoom());
        editRoomButton.addActionListener(e -> editRoom());
        viewReportsButton.addActionListener(e -> viewReports());
        deleteRoomButton.addActionListener(e -> deleteRoom());
        returnToMainMenuButton.addActionListener(e -> returnToMainMenu());
    }

    private void showAvailableRooms() {
        textArea.setText("Available Rooms:\n");
        for (Room room : rooms) {
            if (room.available)
                textArea.append(room.toString() + "\n");
        }
    }

    private void showReservedRooms() {
        textArea.setText("Reserved Rooms:\n");
        for (Reservation res : reservations) {
            textArea.append(res.toString() + "\n\n");
        }
    }

    private void handleCheckout() {
        String reservationCode = JOptionPane.showInputDialog(this, "Enter reservation code to checkout:");
        if (reservationCode == null || reservationCode.trim().isEmpty())
            return;

        Reservation reservationToRemove = null;
        for (Reservation res : reservations) {
            if (res.reservationCode.equals(reservationCode.trim())) {
                reservationToRemove = res;
                break;
            }
        }

        if (reservationToRemove != null) {
            reservations.remove(reservationToRemove);

            for (Room room : rooms) {
                if (room.type.equalsIgnoreCase(reservationToRemove.roomType) && !room.available) {
                    room.available = true;
                    break;
                }
            }

            totalRevenue -= reservationToRemove.totalFee;

            JOptionPane.showMessageDialog(this, "Checkout completed for: " + reservationCode);
        } else {
            JOptionPane.showMessageDialog(this, "No reservation found for the provided code.");
        }
    }

    private void addRoom() {
        String type = JOptionPane.showInputDialog(this, "Enter room type:");
        if (type == null || type.trim().isEmpty())
            return;
        double price;
        try {
            price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter room price:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price entered.");
            return;
        }
        boolean available = JOptionPane.showConfirmDialog(this, "Is the room available?", "Room Availability",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        rooms.add(new Room(type.trim(), price, available));
        JOptionPane.showMessageDialog(this, "Room added successfully.");
    }

    private void editRoom() {
        String type = JOptionPane.showInputDialog(this, "Enter the type of the room to edit:");
        if (type == null || type.trim().isEmpty())
            return;
        Room roomToEdit = null;
        for (Room room : rooms) {
            if (room.type.equalsIgnoreCase(type.trim())) {
                roomToEdit = room;
                break;
            }
        }
        if (roomToEdit != null) {
            double price;
            try {
                price = Double
                        .parseDouble(JOptionPane.showInputDialog(this, "Enter new room price:", roomToEdit.price));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price entered.");
                return;
            }
            boolean available = JOptionPane.showConfirmDialog(this, "Is the room available?", "Room Availability",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            roomToEdit.price = price;
            roomToEdit.available = available;
            JOptionPane.showMessageDialog(this, "Room updated successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "No room found with the provided type.");
        }
    }

    private void deleteRoom() {
        String type = JOptionPane.showInputDialog(this, "Enter the type of the room to delete:");
        if (type == null || type.trim().isEmpty())
            return;
        Room roomToDelete = null;
        for (Room room : rooms) {
            if (room.type.equalsIgnoreCase(type.trim())) {
                roomToDelete = room;
                break;
            }
        }
        if (roomToDelete != null) {
            rooms.remove(roomToDelete);
            JOptionPane.showMessageDialog(this, "Room deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "No room found with the provided type.");
        }
    }

    private void viewReports() {
        String[] options = { "Occupancy Report", "Reservation Report", "Total Revenue" };
        int choice = JOptionPane.showOptionDialog(this, "Choose a report to view:", "View Reports",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        switch (choice) {
            case 0:
                viewOccupancyReport();
                break;
            case 1:
                viewReservationReport();
                break;
            case 2:
                viewTotalRevenue();
                break;
        }
    }

    private void viewOccupancyReport() {
        int totalRooms = rooms.size();
        int occupiedRooms = 0;
        for (Room room : rooms) {
            if (!room.available)
                occupiedRooms++;
        }
        textArea.setText("Occupancy Report:\n");
        textArea.append("Total Rooms: " + totalRooms + "\n");
        textArea.append("Occupied Rooms: " + occupiedRooms + "\n");
        textArea.append("Available Rooms: " + (totalRooms - occupiedRooms) + "\n");
    }

    private void viewReservationReport() {
        textArea.setText("Reservation Report:\n");
        if (reservations.isEmpty()) {
            textArea.append("No reservations found.\n");
            return;
        }
        for (Reservation res : reservations) {
            textArea.append(res.toString() + "\n\n");
        }
    }

    private void viewTotalRevenue() {
        textArea.setText("Revenue Report:\n");
        textArea.append("Total Revenue: $" + totalRevenue + "\n");
    }
}

// About class
class About extends JDialog {

    private JTextArea textArea;
    private JButton royalStaffButton;
    private JButton singleRoomButton;
    private JButton doubleRoomButton;
    private JButton suiteRoomButton;
    private final Color buttonColor = new Color(227, 4, 97, 199);

    public About() {
        createUI();
    }

    private void createUI() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Hotel Reservation System - About");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setUndecorated(true);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        panel.setBackground(buttonColor);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        royalStaffButton = new JButton("Royal Staff");
        singleRoomButton = new JButton("Single Room");
        doubleRoomButton = new JButton("Double Room");
        suiteRoomButton = new JButton("Suite Room");

        JButton closeButton = new JButton("Close");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.addActionListener(e -> dispose());

        customizeButton(royalStaffButton);
        customizeButton(singleRoomButton);
        customizeButton(doubleRoomButton);
        customizeButton(suiteRoomButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(royalStaffButton, gbc);
        gbc.gridy = 1;
        panel.add(singleRoomButton, gbc);
        gbc.gridy = 2;
        panel.add(doubleRoomButton, gbc);
        gbc.gridy = 3;
        panel.add(suiteRoomButton, gbc);
        gbc.gridy = 4;
        panel.add(closeButton, gbc);

        getContentPane().add(panel, BorderLayout.WEST);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        royalStaffButton.addActionListener(e -> showPopupWithImage(
                "C:\\Users\\DELL\\Downloads\\HOTEL RESERVATION SYSTEM-20260512T141540Z-3-001\\HOTEL RESERVATION SYSTEM\\src\\asset\\staff.jpg",
                "Royal Staff"));
        singleRoomButton.addActionListener(e -> showPopupWithImage(
                "C:\\Users\\DELL\\Downloads\\HOTEL RESERVATION SYSTEM-20260512T141540Z-3-001\\HOTEL RESERVATION SYSTEM\\src\\asset\\single.jpg",
                "Single Type Room"));
        doubleRoomButton.addActionListener(e -> showPopupWithImage(
                "C:\\Users\\DELL\\Downloads\\HOTEL RESERVATION SYSTEM-20260512T141540Z-3-001\\HOTEL RESERVATION SYSTEM\\src\\asset\\double.jpg",
                "Double Type Room"));
        suiteRoomButton.addActionListener(e -> showPopupWithImage(
                "C:\\Users\\DELL\\Downloads\\HOTEL RESERVATION SYSTEM-20260512T141540Z-3-001\\HOTEL RESERVATION SYSTEM\\src\\asset\\Suite.jpg",
                "Suite Type Room"));
    }

    void customizeButton(JButton button) {
        button.setBackground(Color.CYAN);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void showPopupWithImage(String imagePath, String title) {
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);

            if (imageIcon.getIconWidth() == -1) {
                JOptionPane.showMessageDialog(this,
                        "Image not found at:\n" + imagePath,
                        "Image Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Image scaledImage = imageIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaledImage));

            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.add(label, BorderLayout.CENTER);

            JButton closeBtn = new JButton("Close");
            closeBtn.setBackground(Color.RED);
            closeBtn.setForeground(Color.WHITE);
            closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
            imagePanel.add(closeBtn, BorderLayout.SOUTH);

            JDialog dialog = new JDialog(this, title, Dialog.ModalityType.APPLICATION_MODAL);
            dialog.setSize(new Dimension(820, 680));
            dialog.setLocationRelativeTo(this);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setContentPane(imagePanel);

            closeBtn.addActionListener(e -> dialog.dispose());

            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

// MainMenu class
class MainMenu extends JFrame {

    private static final Color BUTTON_COLOR = new Color(227, 4, 97, 199);

    public MainMenu(List<Reservation> reservations, List<Room> rooms) {
        setTitle("Hotel Reservation System - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenW = (int) screenSize.getWidth();
        int screenH = (int) screenSize.getHeight();

        String imagePath = "C:\\Users\\DELL\\Downloads\\HOTEL RESERVATION SYSTEM-20260512T141540Z-3-001\\HOTEL RESERVATION SYSTEM\\src\\asset\\picc.jpg";
        ImageIcon bgIcon = new ImageIcon(imagePath);

        JLabel background;
        if (bgIcon.getIconWidth() > 0) {
            Image resized = bgIcon.getImage().getScaledInstance(screenW, screenH, Image.SCALE_SMOOTH);
            background = new JLabel(new ImageIcon(resized));
        } else {
            background = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    GradientPaint gp = new GradientPaint(0, 0, new Color(30, 10, 60),
                            0, getHeight(), new Color(140, 0, 70));
                    g2d.setPaint(gp);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    super.paintComponent(g);
                }
            };
            background.setOpaque(false);
        }

        setContentPane(background);
        background.setLayout(null);

        // ── CENTERED BUTTONS ──────────────────────────────────────────
        int btnW = 180;
        int btnH = 55;
        int gap = 20; // gap between buttons (tight so group stays compact)

        // Horizontal center
        int btnX = (screenW - btnW) / 2;

        // Vertical center: total block height = 4 buttons + 3 gaps
        int totalH = (4 * btnH) + (3 * gap);
        int startY = (screenH - totalH) / 2 + 100; // +100 = neeche shift, value badao ya ghatao
        // ─────────────────────────────────────────────────────────────

        JButton userButton = createButton("User", e -> {
            dispose();
            new User(reservations, rooms).setVisible(true);
        });
        JButton adminButton = createButton("Admin", e -> {
            String password = JOptionPane.showInputDialog(this, "Enter Admin Password:");
            if ("admin123".equals(password)) {
                dispose();
                new Admin(reservations, rooms).setVisible(true);
            } else
                JOptionPane.showMessageDialog(this, "Invalid Password", "Error", JOptionPane.ERROR_MESSAGE);
        });
        JButton aboutButton = createButton("About", e -> new About().setVisible(true));
        JButton exitButton = createButton("Exit", e -> System.exit(0));

        exitButton.setBackground(new Color(180, 20, 20));

        userButton.setBounds(btnX, startY, btnW, btnH);
        adminButton.setBounds(btnX, startY + (btnH + gap), btnW, btnH);
        aboutButton.setBounds(btnX, startY + (btnH + gap) * 2, btnW, btnH);
        exitButton.setBounds(btnX, startY + (btnH + gap) * 3, btnW, btnH);

        background.add(userButton);
        background.add(adminButton);
        background.add(aboutButton);
        background.add(exitButton);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.BOLD, 24));
        button.addActionListener(actionListener);
        return button;
    }
}

// Main class
public class HotelReservationSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Reservation> reservations = new ArrayList<>();
            List<Room> rooms = new ArrayList<>();

            for (int i = 0; i < 15; i++) {
                if (i < 5)
                    rooms.add(new Room("Single", 100.0, true));
                else if (i < 10)
                    rooms.add(new Room("Double", 150.0, true));
                else
                    rooms.add(new Room("Suite", 200.0, true));
            }

            new MainMenu(reservations, rooms).setVisible(true);
        });
    }
}