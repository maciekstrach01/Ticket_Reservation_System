## Screenshots

![class_diagram](https://github.com/maciekstrach01/Library_project_cpp/assets/146733279/cd284447-0683-41dd-917c-fd4e30eadb27)
# Ticket Reservation System

The aim of the project was to model and program, using an inheritance mechanism and polymorphism, one of the systems presented. Our choice became a ticket booking system for buses, trains and planes.  
  
A preliminary analysis of similar ticket booking systems already existing on the market justified the need to implement user registration and login functionality. In our system, we have a separation into two types of users - passengers and administrators (UserType enum). Administrators have access to all bookings, connections and user information, while passengers can book a ticket and view a list of valid tickets.  
  
In the designed system, we have a separation between reservations (Reservation class) and tickets (Ticket class). The reason for this is that reservations can be made for several different seats (implicitly persons) within a single connection.

Connections (class Line), tickets (class Ticket) and seats (class Seat) are abstract classes from which the classes PlaneLine, TrainLine, BusLine, PlaneTicket, TrainTicket, BusTicket, PlaneSeat, TrainSeat and BusSeat respectively inherit. Each connection can have places in both class one and class two (enum ClassType), but class two is not mandatory.  
  
Declarations of values denoting carriers are found in the following enumeration types: PlaneCarrier, TrainCarrier and BusCarrier. In the same way, cities (enum City) and types of tolls (enum Discount) are stored.  
  
The application implements the facade pattern by separating a main class (ReservationSystem) responsible for storing information about reservations, connections and users, and a class ConsoleReservationSystem, whose task is to display a graphical interface in the system console.

The ConsoleReservationSystem class is strongly related to the SystemVisualiser class, which provides methods to display the objects used in the system. The enumeration types, on the other hand, implement the EnumVisualiser interface, which provides us with a method that returns a friendly name.  
  
The source files of the classes are divided into matching modules, the use of which helps to increase the clarity of the project.

## 🛠 Skills
Java


## Running Tests

To run the tests, you must have IntelliJ or another preferred IDE for Java installed. Configure the IDE for Java, translate the code into machine language i.e. compile it, and then run the program.

```

