
# Order Management System

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


## 🛠 Skills
Java


## Running Tests

To run the tests, you must have Intellij or another preferred IDE for Java installed. Configure the IDE for Java, translate the code into machine language i.e. compile it, and then run the program.