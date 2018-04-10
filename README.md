# Goat Runner - Shuttle Service Web Application
GoatRunner is a web application to manage university provided shuttle services. This application would enable the students to book cab services at any time of the day.

# Features
- Students can Login/Signup by creating an account using his/her student ID. 
- Once the student logs in, they can make a booking by specifying their ‘TO’ and ‘FROM’ locations along with number of passengers. This will send a request to the server who will assign the available car for the requested number of seats.
- The server will assign the nearest available cab (for the requested no of seats) to the student. A notification will be sent to the user on successful booking. The booking details will contain the driver name, cab number and an estimated arrival time.
- Student can cancel the booking within a specified time limit. If this time limit is exceeded, the cancellation option will be disabled.
- We will be integrating Google Maps API, Directions API and performed geocoding functionality.
- Performed polling request to get up-to-date results for the new rides.

# Environmental Setup
- Front-end: JavaScript, CSS, HTML, JQuery
- Back-end: Java, Eclipse (IDE), JSON data for communication between server and web application, JDBC for application and database communication.
- Database: MySQL
