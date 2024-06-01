
# Order Management System

https://github.com/maciekstrach01/Quiz_API/assets/146733279/fd1596e5-4276-456e-a2b0-9ff5ff1721a5

The design of the ticket reservation system follows SOLID principles, which contributes to its flexibility, extensibility and ease of maintenance. The structure of the classes and their responsibilities are well defined, allowing the system to be easily extended without modifying existing code.

The project effectively handles the complex real-world problem of booking tickets for various modes of transportation. It supports different types of users, carriers, connections and discounts, making it a versatile tool for end users.

The project uses a number of design and architectural patterns, such as Factory Method, Strategy, Template Method, Decorator, MVC and Layered Architecture. These patterns contribute to the modularity, flexibility and scalability of the system, making it easier to develop and maintain. The implementation of the Facade pattern in the ReservationSystem class and the MVC pattern in the system structure further improves code readability and organization.
## Unit tests

I performed unit tests to all 28 project files using the JUnit library and the Maven tool in Java. The test results showed a success rate close to 100%, except for 2 tests in the AppTest file for: testSystemInitialization() and testPlaneLineCreationAndAddition(), and 2 tests in the ConsoleReservationSystemTest file for testLoginSuccess() and testLoginFailure(). When running the tests, I made changes to the source code for the TrainTicket and PlaneTicket files so that the tests could pass for these files.




## Screenshots

![Obraz1](https://github.com/maciekstrach01/Portfolio/assets/146733279/0b161911-3ada-4e76-8a35-ea8b3d4740c0)

![Obraz4](https://github.com/maciekstrach01/Portfolio/assets/146733279/f578fa7c-4168-4b92-bceb-cb0af6b38d62)


![Obraz5](https://github.com/maciekstrach01/Portfolio/assets/146733279/049c6199-391d-4c30-b0b7-0b2ca6469161)


![Obraz6](https://github.com/maciekstrach01/Portfolio/assets/146733279/516eaace-a836-4c9e-84bf-1e08811ab549)


















## SOLID
Let me explain here, using the example of a few selected files from the project, the principle of solid

| File Group                              | SRP | OCP | LSP | ISP | DIP |
|------------------------------------------|-----|-----|-----|-----|-----|
| TrainLine, BusLine, PlaneLine, Line      | Yes | Yes | Yes | Yes | Yes |
| **Explanation:**                         | Each class represents a transport line. | New types of lines can be added without modifying existing classes. | Classes can be used interchangeably with Line. | Each class is specialized. | Line is abstract, separating high-level modules from low-level modules. |


| File Group                               | SRP | OCP | LSP | ISP | DIP |
|------------------------------------------|-----|-----|-----|-----|-----|
| TrainTicket, BusTicket, PlaneTicket, Ticket | Yes | Yes | Yes | Yes | Yes |
| **Explanation:**                         | Each class represents a specific type of ticket. | New functionalities can be added through inheritance. | Classes can be used interchangeably with Ticket. | Each class is specialized. | Ticket is abstract, separating high-level modules from low-level modules. |

| File Group         | SRP       | OCP | LSP | ISP       | DIP       |
|--------------------|-----------|-----|-----|-----------|-----------|
| ReservationSystem  | Partially | Yes | -   | Partially | Partially |
| **Explanation:**   | The class manages multiple responsibilities. | New functionalities can be added without modifying existing classes. | - | The class could be divided into smaller interfaces. | The class depends on specific implementations. |

| File Group        | SRP | OCP | LSP | ISP | DIP |
|-------------------|-----|-----|-----|-----|-----|
| SystemVisualiser  | Yes | Yes | -   | Yes | Yes |
| **Explanation:**  | The class handles formatting and visualization. | New formatting methods can be added without modifying existing methods. | - | The class is specialized. | The class does not depend on high-level modules. |


SRP (Single Responsibility Principle): Each class should have only one reason to change, meaning it should have only one job or responsibility.

OCP (Open/Closed Principle): Software entities should be open for extension but closed for modification.

LSP (Liskov Substitution Principle): Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.

ISP (Interface Segregation Principle): Clients should not be forced to depend on interfaces they do not use; instead, they should have minimal interfaces specific to their needs.

DIP (Dependency Inversion Principle): High-level modules should not depend on low-level modules; both should depend on abstractions, and abstractions should not depend on details.
## Realization of the real-world problem

The design of the ticket order management system is well designed to realize the real-world problem. 
The design structure is modular and follows SOLID principles, which facilitates maintenance and development. 
The system effectively handles different types of transportation, manages users and reservations, and takes into account discounts and data security. 
Visualization and reporting functions further enhance its usability. 
Overall, the design meets the requirements of a real ticket order management system, offering flexibility and scalability.
## Design patterns used in the project
Factory Method pattern
- Description: the Factory Method pattern is to define a method that creates objects, but allows subclasses to change the type of the created object.
- Example in the project: Classes such as TrainTicket, BusTicket, PlaneTicket can be created with factory methods depending on the type of transportation, which provides flexibility and makes it easy to extend the system with new ticket types.

Singleton pattern
- Description: The Singleton pattern ensures that an object has exactly one instance and provides global access to that instance.
- Example in a project: If there is a class in the project responsible for managing the system configuration or connection to the database, it could be implemented as a Singleton. In the files shown, the direct use of Singleton is not noted, but this could be applied to other parts of the system.

Strategy pattern
- Description: the Strategy pattern allows you to define a family of algorithms, put them in separate classes and swap their objects when the program runs.
- Example in the project: The Discount class and its various implementations can be examples of the Strategy pattern, where different strategies for calculating discounts can be applied depending on the type of discount.

Template Method pattern
- Description: the Template Method pattern defines the skeleton of an algorithm in a method, passing some steps to subclasses.
- Example in the project: The Ticket class can be an example of the Template Method pattern, where the skeleton of ticket price calculation is defined, and the individual steps are implemented in the subclasses TrainTicket, BusTicket, PlaneTicket.

Decorator pattern
- Description: the Decorator pattern allows to dynamically add new functions to objects by placing these objects in wrapper objects (decorators).
- Example in the project: The application of different ticket discounts can be implemented as decorators that dynamically add additional discounts to the basic ticket price.
## Architectural patterns used in the project
MVC (Model-View-Controller).
- Description: the MVC pattern divides the application into three main components: Model (business logic and data), View (user interface) and Controller (controls the application flow).
- Example in the project: In the design, you can see the separation of business logic (e.g. Reservation, Ticket, User classes) from the potential presentation layer, which could be classes such as SystemVisualiser. The ConsoleReservationSystem class could act as a controller.

Layered Architecture.
- Description: Layered architecture divides the system into layers, where each layer is responsible for a specific aspect of the system's functionality.
- Example in a project: The project is divided into different layers, such as a model layer (User, Reservation, Ticket), a business logic layer (ReservationSystem, ConsoleReservationSystem) and a support layer (Hashing, Dates, EnumVisualiser).

Dependency Injection (DI).
- Description: Dependency Injection is a pattern that allows you to inject dependencies into an object instead of creating them directly in the object.
- Example in the project: There are no direct examples of DI in the project, but using this pattern could improve the flexibility and testability of the system, especially in classes such as ReservationSystem.


The design of the ticket order management system makes effective use of multiple design and architectural patterns, which increases its flexibility, extensibility and maintainability. Introducing additional patterns, such as Dependency Injection, could further improve the quality of the code. Overall, the design and architectural patterns used make the system well-designed, making it easier to develop and maintain in the long term.
## 🛠 Skills
Maven,Junit,Java


## Running Tests



Maven Setup

Make sure you have Maven installed on your system. You can download it from Maven's official website.

The file structure in the src folder must be appropriately divided into main and test folders.

Project Structure: Your project should have the following structure:

 ![obraz](https://github.com/maciekstrach01/Quiz/assets/146733279/22a6c86c-4f9d-45d3-a7a8-202c0921813b) 

pom.xml Configuration: Make sure your pom.xml includes the JUnit dependency. Here's an example configuration:

![obraz](https://github.com/maciekstrach01/Quiz/assets/146733279/b3e7988a-6c95-4d8f-98f6-a7dfd44d8528)

Creating Tests in IntelliJ

Navigate to the Source File: Open the class you want to test in IntelliJ.

Create a Test Class: Right-click on the class name, choose Show Context Actions, then select Create Test.

Select JUnit Library: In the dialog that appears, select the JUnit library (JUnit 4 or 5, depending on your preference and the setup in pom.xml).

Generate Test Methods: Choose the methods you want to test and IntelliJ will generate the corresponding test methods in the test class.