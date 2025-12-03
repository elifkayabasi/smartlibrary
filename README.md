# SmartLibrary

SmartLibrary is a simple console-based library management system built with Java OOP, JDBC, and SQLite.  
This project was developed as a university assignment and includes basic operations for managing books, students, and loan records.

## Features
- Add / List Books
- Add / List Students
- Borrow Books (Loan Creation)
- Return Books
- View All Loan Records
- Automatic SQLite database and table creation
- Full JDBC CRUD operations (add, update, delete, list, getById)
- Repository-based OOP structure

## Project Structure
src/
 ├── models/
 ├── repository/
 ├── Main.java
lib/
smartlibrary.db

## How to Run

Compile:
javac -cp "lib/sqlite-jdbc-3.36.0.3.jar" src/models/*.java src/repository/*.java src/Main.java

Run:
java -cp ".:lib/sqlite-jdbc-3.36.0.3.jar:src" Main

---

This project was developed as part of the SmartLibrary OOP + JDBC + SQLite assignment.
