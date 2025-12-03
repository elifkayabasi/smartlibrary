package models;

public class Loan {

    private int id;
    private int bookId;
    private int studentId;
    private String dateBorrowed;
    private String dateReturned;

    // boş constructor
    public Loan() {}

    // ödünç verme sırasında kullanılan constructor
    public Loan(int bookId, int studentId, String dateBorrowed) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = null; // teslim edilmedi
    }

    // tüm alanları içeren constructor (getAll, getById için)
    public Loan(int id, int bookId, int studentId, String dateBorrowed, String dateReturned) {
        this.id = id;
        this.bookId = bookId;
        this.studentId = studentId;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    // GETTER – SETTER

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }
    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getDateReturned() {
        return dateReturned;
    }
    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }
}
