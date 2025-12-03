package repository;

import models.Loan;
import java.sql.*;
import java.util.ArrayList;

public class LoanRepository {

    public void add(Loan loan) {
        String sql = "INSERT INTO loans(bookId, studentId, dateBorrowed, dateReturned) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, loan.getBookId());
            ps.setInt(2, loan.getStudentId());
            ps.setString(3, loan.getDateBorrowed());
            ps.setString(4, loan.getDateReturned());  // null olabilir
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Loan Add Error: " + e.getMessage());
        }
    }

    public ArrayList<Loan> getAll() {
        ArrayList<Loan> list = new ArrayList<>();
        String sql = "SELECT * FROM loans";

        try (Connection conn = Database.connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Loan(
                        rs.getInt("id"),
                        rs.getInt("bookId"),
                        rs.getInt("studentId"),
                        rs.getString("dateBorrowed"),
                        rs.getString("dateReturned")
                ));
            }

        } catch (Exception e) {
            System.out.println("Loan GetAll Error: " + e.getMessage());
        }

        return list;
    }

    public Loan getById(int id) {
        String sql = "SELECT * FROM loans WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Loan(
                        rs.getInt("id"),
                        rs.getInt("bookId"),
                        rs.getInt("studentId"),
                        rs.getString("dateBorrowed"),
                        rs.getString("dateReturned")
                );
            }

        } catch (Exception e) {
            System.out.println("Loan GetById Error: " + e.getMessage());
        }

        return null;
    }

    public void update(Loan loan) {
        String sql = "UPDATE loans SET bookId=?, studentId=?, dateBorrowed=?, dateReturned=? WHERE id=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, loan.getBookId());
            ps.setInt(2, loan.getStudentId());
            ps.setString(3, loan.getDateBorrowed());
            ps.setString(4, loan.getDateReturned());
            ps.setInt(5, loan.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Loan Update Error: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM loans WHERE id=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Loan Delete Error: " + e.getMessage());
        }
    }

    // --- özel fonksiyonlar ---

    // kitap ödünçte mi?
    public boolean isBookBorrowed(int bookId) {
        String sql = "SELECT * FROM loans WHERE bookId = ? AND dateReturned IS NULL";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // varsa = ödünçte

        } catch (Exception e) {
            System.out.println("Loan Borrowed Check Error: " + e.getMessage());
            return false;
        }
    }

    // teslim alma — dateReturned güncellemesi
    public void returnBook(int loanId, String dateReturned) {
        String sql = "UPDATE loans SET dateReturned=? WHERE id=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dateReturned);
            ps.setInt(2, loanId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Return Error: " + e.getMessage());
        }
    }
}
