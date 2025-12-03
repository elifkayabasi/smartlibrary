package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:../smartlibrary.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
            return null;
        }
    }

    public static void createTables() {
        String books = """
            CREATE TABLE IF NOT EXISTS books(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                author TEXT,
                year INTEGER
            );
        """;

        String students = """
            CREATE TABLE IF NOT EXISTS students(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                department TEXT
            );
        """;

        String loans = """
            CREATE TABLE IF NOT EXISTS loans(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                bookId INTEGER,
                studentId INTEGER,
                dateBorrowed TEXT,
                dateReturned TEXT
            );
        """;

        try (Connection conn = connect(); Statement st = conn.createStatement()) {
            st.execute(books);
            st.execute(students);
            st.execute(loans);
            System.out.println("Tablolar oluşturuldu!");
        } catch (Exception e) {
            System.out.println("Table Error: " + e.getMessage());
        }
    }
}
