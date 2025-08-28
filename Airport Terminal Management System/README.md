# Airport Terminal Management System

## Overview

This is a Java-based Airport Terminal Management System that manages flights, passengers, aircraft, and reservations. It supports different types of aircraft (commercial and private jets) using inheritance and stores reservations in a CSV file for easy access and editing.

> **Note:** This version does **not** include the optional Loyalty Program feature.

---

## Features

- Manage flights and passenger reservations
- Aircraft types using inheritance (`CommercialAircraft`, `PrivateJet`)
- File I/O to read/write reservations in CSV format
- Uses `HashMap` and `ArrayList` for data structures
- Clean architecture with packages for model, data, and view layers
- Unit tested with JUnit 5

---

## Project Structure
```
AirportTerminalManagementSystem/
├── src/
│ ├── main/
│ │ └── com/
│ │ └── airport/
│ │ ├── data/
│ │ │ └── CSVUtil.java
│ │ ├── domain/
│ │ │ ├── model/
│ │ │ │ ├── Aircraft.java
│ │ │ │ ├── CommercialAircraft.java
│ │ │ │ ├── PrivateJet.java
│ │ │ │ ├── Passenger.java
│ │ │ │ └── Flight.java
│ │ │ └── reservation/
│ │ │ └── ReservationSystem.java
│ │ └── view/
│ │ └── AirportTerminalApp.java
│ └── test/
│ └── com/
│ └── airport/
│ └── AirportTerminalTest.java
├── data/
│ └── reservations.csv
├── README.md
└── pom.xml (if using Maven)
```


---

## Technologies Used

- Java 17+
- JUnit 5
- Maven
- LocalDate & BigDecimal for time and currency
- File I/O with CSV

---

## How to Run

1. **Clone or open the project in IntelliJ IDEA**.
2. Make sure your project structure includes:
    - The source code under `src/main/java`
    - A `data/` folder with a `reservations.csv` file (you can create it empty)
3. **Run the main class**:
    - Go to `AirportTerminalApp.java`
    - Right-click and select `Run AirportTerminalApp.main()`

### Example Output
Reservations saved to CSV.

Flight AA101:

Alice Smith (P12345)

Flight PJ001:

Bob VIP (P67890)

Reservations loaded from CSV.

---

## Running Tests

To run the JUnit tests:

1. Right-click on the `AirportTerminalTest.java` file in IntelliJ.
2. Choose **Run 'AirportTerminalTest'**.
3. Ensure all test cases pass (green check marks).

---

## CSV Format

Each line in `reservations.csv` is formatted like:

flightNumber,departureDate,ticketPrice,passengerName,passportNumber,aircraftModel,aircraftType


Example:
```
AA101,2024-05-10,299.99,Alice Smith,P12345,Boeing 737,Commercial
PJ001,2024-07-20,5000.00,Bob VIP,P67890,Gulfstream G650,PrivateJet
```

---

## Notes

- You may need to manually create the `data/reservations.csv` file or folder if it doesn’t exist.
- File path is set to `"data/reservations.csv"`. Update it if you store the file elsewhere.

---

## Author

Marquita Scotton  
Java Developer Apprentice

---
