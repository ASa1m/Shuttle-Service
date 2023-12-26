# Shuttle Tracking System

The Shuttle Tracking System is a Java application that allows users to track the location of shuttles on a map. It provides separate functionality for both drivers and users. Drivers can view and update their current location, while users can view the locations of available shuttles and request a shuttle pickup.

## Features

- **DriverMap**: This module provides a graphical user interface for drivers to view and update their current location on a map.

- **UserMap**: This module allows users to view the locations of available shuttles on a map and request a shuttle pickup.

- **DbConnection**: This module establishes a connection to a MySQL database using JDBC and provides a method to retrieve the database connection object.

- **DriverController**: This module contains the logic for the DriverMap GUI. It handles the display of shuttles and users on the map and updates their locations periodically.

- **LoginController**: This module handles the login functionality for both drivers and users. It validates the entered credentials against the database and grants access to the corresponding map module.

## Dependencies

- JavaFX: The application uses JavaFX for the graphical user interface.

![JavaFX Logo!](https://repository-images.githubusercontent.com/400161932/257a8be2-bbf2-4218-a55b-219d819578b2)
- MySQL Connector/J: The application requires the MySQL Connector/J library to connect to the MySQL database.
![MySQL Logo!](https://www.learnhindituts.com/storage/uploads/images/mysql_1664108774.png)

## Getting Started

1. Clone the repository or download the source code.
2. Import the project into your preferred Java IDE.
3. Make sure the required dependencies are added to the project.
4. Configure the MySQL database connection details in the `DbConnection` module.
5. Compile and run the application.

## Usage

1. Launch the application.
2. Login as a driver or user using the provided credentials.
3. If logged in as a driver, update your current location on the map.
4. If logged in as a user, view the available shuttles on the map and request a pickup.
5. The map will automatically update to reflect the current locations of shuttles and users using multithreaded UI-engagement and Model Updation Model.

 ![MultiThreading!](https://www.simplilearn.com/ice9/free_resources_article_thumb/Multithreading%20in%20Java/Multithreading-in-Java-Mutlitasking.png)

## Team

- Mehran Wahid - [Github](https://github.com/MehranSangrasi)
- Abdullah Saim 
- Areeba Tanveer - [Github](https://github.com/areeba-tanveer).
- Aniqa Tufail - [Github](https://github.com/AniqaTufail)
- Onkar - [Github](https://github.com/onkarrai06) 
- Muneeb Ahmed
- Muhammad Abubakar

## Contributing

Contributions to the Shuttle Tracking System are welcome. If you find any issues or have suggestions for improvements, please submit a pull request or open an issue.

## License

This project is licensed under the [MIT License](LICENSE).
