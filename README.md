# 🏨 Hotel Reservation System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-GUI-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

A fully functional **Hotel Reservation Management System** built with **Java Swing** — featuring a beautiful fullscreen UI, role-based access (User & Admin), room management, reservations, checkout, and revenue reporting.

---

## 📸 Screenshots

> **Main Menu**
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/60264f27-ed6e-4d99-9ce4-bc2808479799" />




> **User Panel — Make Reservation**
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/c48278a2-4589-47f9-9e0a-de2678b884d0" />

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/19ca0a2a-6cfe-4cf7-9da5-c289acc5104d" />


 

> **Admin Panel — Reports & Management**
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/c088c192-f8f3-414c-82f1-aaf54cc26d1c" />

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/273df713-6c3e-432b-b96f-96693d5b9ee4" />

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/d5f870aa-32d7-4944-8437-ffd89f0bbf70" />


> **About Section — Room Gallery**
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/e2826ffa-4d31-4dad-a322-efb5c1756ede" />

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/2031f9d3-2c12-4586-bf82-d899c4dd573a" />


 

##  Features

## User Panel
- ✅ Make a new room reservation (Single / Double / Suite)
- ✅ Check available rooms in real-time
- ✅ View reservation details by CNIC
- ✅ View Terms & Conditions
- ✅ Automatic reservation code generation
- ✅ Advance payment calculation (50% of total fee)

###  Admin Panel *(Password Protected)*
- ✅ View all available and reserved rooms
- ✅ Add, Edit, and Delete rooms
- ✅ Checkout guests by reservation code
- ✅ Occupancy Report
- ✅ Reservation Report
- ✅ Total Revenue Report
- ✅ Room availability auto-updates on reservation/checkout

### About Section
- ✅ View Royal Staff image
- ✅ View Single, Double, and Suite room photos
- ✅ Interactive image gallery with popup viewer

###  UI & Design
- ✅ Fullscreen maximized window
- ✅ Custom background image on main menu
- ✅ Gradient fallback if image not found
- ✅ Nimbus Look & Feel
- ✅ Buttons centered perfectly on screen (horizontal + vertical)

---

##  Project Structure

```
HotelReservationSystem/
│
├── src/
│   ├── HotelReservationSystem.java   # Main entry point
│   ├── Room.java                     # Room model class
│   ├── Reservation.java              # Reservation model class
│   ├── Person.java                   # Abstract base class
│   ├── User.java                     # User panel & logic
│   ├── Admin.java                    # Admin panel & logic
│   ├── About.java                    # About/gallery dialog
│   └── MainMenu.java                 # Main menu with navigation
│
├── asset/
│   ├── picc.jpg                      # Main menu background
│   ├── staff.jpg                     # Royal staff photo
│   ├── single.jpg                    # Single room photo
│   ├── double.jpg                    # Double room photo
│   └── Suite.jpg                     # Suite room photo
│
├── screenshots/
│   ├── main_menu.png
│   ├── user_panel.png
│   ├── admin_panel.png
│   └── about_panel.png
│
└── README.md
```

---

##  Getting Started

### Prerequisites

- Java JDK **8 or higher**
- Any Java IDE (IntelliJ IDEA / Eclipse / NetBeans) or terminal

### Installation & Run

**Option 1 — Using an IDE:**
1. Clone the repository
   ```bash
   git clone https://github.com/your-username/hotel-reservation-system.git
   ```
2. Open the project in your IDE
3. Run `HotelReservationSystem.java`

**Option 2 — Using Terminal:**
```bash
# Clone the repo
git clone https://github.com/your-username/hotel-reservation-system.git

# Navigate to src folder
cd hotel-reservation-system/src

# Compile
javac HotelReservationSystem.java

# Run
java HotelReservationSystem
```

 **Note:** Update the image paths in `About.java` and `MainMenu.java` to match your local asset folder location before running.

---

Login Credentials

| Role  | Password   |
|-------|------------|
| Admin | `admin123` |
| User  | No password required |

---

 Room Pricing

| Room Type | Price Per Night |
|-----------|----------------|
| Single    | $100.00         |
| Double    | $150.00         |
| Suite     | $200.00         |

> Advance payment = **50%** of total fee at time of reservation.

 

   Class Design

```
Person (Abstract - extends JFrame)
├── User      → handles reservations from guest side
└── Admin     → manages rooms, checkouts, reports

MainMenu      → entry point UI with navigation buttons
About         → JDialog for image gallery
Room          → data model for room info
Reservation   → data model for booking info
```

---

  Built With

| Technology | Purpose |
|------------|---------|
| Java SE    | Core programming language |
| Java Swing | GUI framework |
| Nimbus L&F | Modern look and feel |
| AWT        | Layout & graphics |
| SimpleDateFormat | Reservation timestamps |
 
 

---

  Future Improvements

- [ ] Database integration (MySQL / SQLite) for persistent data
- [ ] Login system for multiple users
- [ ] Email confirmation on reservation
- [ ] Date-based check-in / check-out calendar picker
- [ ] Invoice / receipt PDF generation
- [ ] Dark mode support

---

 
 License

This project is licensed under the **MIT License** — feel free to use, modify, and distribute.

---

> ⭐ If you found this project helpful, please give it a **star** on GitHub!
