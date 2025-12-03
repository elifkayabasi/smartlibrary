package repository;

import models.Book;
import java.sql.*;
import java.util.ArrayList;

public class BookRepository {

    public void add(Book book) {
        String sql = "INSERT INTO books(title, author, year) VALUES (?, ?, ?)";
        try(Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.executeUpdate();

        } catch(Exception e){
            System.out.println("Add Error: " + e.getMessage());
        }
    }

    public ArrayList<Book> getAll() {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try(Connection conn = Database.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while(rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year")));
            }

        } catch(Exception e) {
            System.out.println("GetAll Err: " + e.getMessage());
        }

        return list;
    }

    public Book getById(int id){
        String sql = "SELECT * FROM books WHERE id = ?";
        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"));
            }
        } catch(Exception e){
            System.out.println("Get Err: " + e.getMessage());
        }
        return null;
    }

    public void update(Book book){
        String sql = "UPDATE books SET title=?, author=?, year=? WHERE id=?";
        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.setInt(4, book.getId());
            ps.executeUpdate();

        } catch(Exception e){
            System.out.println("Update Err: " + e.getMessage());
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM books WHERE id=?";
        try(Connection conn = Database.connect();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

       } catch(Exception e){
    System.out.println("Update Err: " + e.getMessage());
        }

    }
}
