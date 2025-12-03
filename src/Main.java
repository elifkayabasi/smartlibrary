import repository.*;
import models.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Database.createTables();
        Scanner scanner = new Scanner(System.in);

        BookRepository bookRepo = new BookRepository();
        StudentRepository studentRepo = new StudentRepository();
        LoanRepository loanRepo = new LoanRepository();

        while (true) {
            System.out.println("""
                    --- SmartLibrary Menü ---
                    1) Kitap Ekle
                    2) Kitapları Listele
                    3) Öğrenci Ekle
                    4) Öğrencileri Listele
                    5) Kitap Ödünç Ver
                    6) Ödünç Listesini Gör
                    7) Kitap Geri Teslim Al
                    0) Çıkış
                    """);

            int sec = scanner.nextInt();
            scanner.nextLine();

            switch (sec) {

                // Kitap ekle
                case 1 -> {
                    System.out.print("Title: ");
                    String t = scanner.nextLine();

                    System.out.print("Author: ");
                    String a = scanner.nextLine();

                    System.out.print("Year: ");
                    int y = scanner.nextInt();
                    scanner.nextLine();

                    bookRepo.add(new Book(t, a, y));
                    System.out.println("Kitap eklendi!\n");
                }

                // Kitapları listele
                case 2 -> {
                    System.out.println("---- Kitap Listesi ----");
                    for (Book b : bookRepo.getAll()) {
                        System.out.println(b.getId() + " | " + b.getTitle() + " | " + b.getAuthor() + " | " + b.getYear());
                    }
                    System.out.println();
                }

                // Öğrenci ekle
                case 3 -> {
                    System.out.print("Name: ");
                    String n = scanner.nextLine();

                    System.out.print("Department: ");
                    String d = scanner.nextLine();

                    studentRepo.add(new Student(n, d));
                    System.out.println("Öğrenci eklendi!\n");
                }

                // Öğrenci listele
                case 4 -> {
                    System.out.println("---- Öğrenci Listesi ----");
                    for (Student s : studentRepo.getAll()) {
                        System.out.println(s.getId() + " | " + s.getName() + " | " + s.getDepartment());
                    }
                    System.out.println();
                }

                // Kitap ödünç ver
                case 5 -> {
                    System.out.print("Öğrenci ID: ");
                    int sid = scanner.nextInt();

                    System.out.print("Kitap ID: ");
                    int bid = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Tarih (örn: 2025-11-29): ");
                    String date = scanner.nextLine();

                    boolean isBorrowed = loanRepo.isBookBorrowed(bid);

                    if (isBorrowed) {
                        System.out.println("Kitap zaten ödünçte!\n");
                    } else {
                        loanRepo.add(new Loan(bid, sid, date));
                        System.out.println("Kitap ödünç verildi!\n");
                    }
                }

                // Ödünç listesi
                case 6 -> {
                    System.out.println("---- Ödünç Listesi ----");
                    for (Loan l : loanRepo.getAll()) {
                        System.out.println(
                                l.getId() + " | KitapID: " + l.getBookId() +
                                " | ÖğrenciID: " + l.getStudentId() +
                                " | Aldığı Tarih: " + l.getDateBorrowed() +
                                " | Teslim Tarihi: " + l.getDateReturned()
                        );
                    }
                    System.out.println();
                }

                // Kitap geri teslim al
                case 7 -> {
                    System.out.print("Loan ID: ");
                    int lid = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Teslim Tarihi: ");
                    String ret = scanner.nextLine();

                    loanRepo.returnBook(lid, ret);
                    System.out.println("Kitap teslim alındı!\n");
                }

                // çıkış
                case 0 -> {
                    System.out.println("Çıkılıyor...");
                    System.exit(0);
                }

                default -> System.out.println("Geçersiz seçim!\n");
            }
        }
    }
}
