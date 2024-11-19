import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Author: %s, ISBN: %s, Issued: %s", title, author, isbn, isIssued);
    }
}

class Member {
    private String name;
    private String memberId;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return String.format("Member Name: %s, Member ID: %s", name, memberId);
    }
}

class Library {
    private List<Book> books;
    private Map<Book, Member> issuedBooks;

    public Library() {
        books = new ArrayList<>();
        issuedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void issueBook(String isbn, Member member) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isIssued()) {
                    book.issue();
                    issuedBooks.put(book, member);
                    System.out.println("Book issued: " + book + " to " + member);
                    return;
                } else {
                    System.out.println("Error: Book is already issued.");
                    return;
                }
            }
        }
        System.out.println("Error: Book not found.");
    }

    public void returnBook(String isbn) {
        for (Map.Entry<Book, Member> entry : issuedBooks.entrySet()) {
            if (entry.getKey().getIsbn().equals(isbn)) {
                Book book = entry.getKey();
                book.returnBook();
                issuedBooks.remove(book);
                System.out.println("Book returned: " + book);
                return;
            }
        }
        System.out.println("Error: Book not found or was not issued.");
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!book.isIssued()) {
                System.out.println(book);
            }
        }
    }

    public void displayIssuedBooks() {
        System.out.println("Issued Books:");
        for (Map.Entry<Book, Member> entry : issuedBooks.entrySet()) {
            System.out.println(entry.getKey() + " issued to " + entry.getValue());
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Display Available Books");
            System.out.println("5. Display Issued Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;
                case "2":
                    System.out.print("Enter book ISBN to issue: ");
                    String isbnToIssue = scanner.nextLine();
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    String memberId = scanner.nextLine();
                    library.issueBook(isbnToIssue, new Member(memberName, memberId));
                    break;
                case "3":
                    System.out.print("Enter book ISBN to return: ");
                    String isbnToReturn = scanner.nextLine();
                    library.returnBook(isbnToReturn);
                    break;
                case "4":
                    library.displayAvailableBooks();
                    break;
                case "5":
                    library.displayIssuedBooks();
                    break;
                case "6":
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
