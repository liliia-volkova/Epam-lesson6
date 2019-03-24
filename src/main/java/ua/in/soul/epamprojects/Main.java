package ua.in.soul.epamprojects;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = askIntValue("Enter count of books: ", scanner);
        Books books = new Books(size);

        if (books.isEmpty()){
            System.out.println("Library is empty");
        }

        for (int i = 0; i < size; i++) {
            System.out.println(books.addBook(enterBook(scanner)));
        }

        boolean shouldContinue = true;
        while (shouldContinue) {
            int choice = askIntValue("\nВыберите нужное действие: \n1 - посмотреть все книги; \n2 - изменить цену; " +
                    "\n3 - найти книги автора; \n4 - найти книги по году издания; \n5 - завершить работу", scanner);
            shouldContinue = chooseAction(choice, books, scanner);
        }
    }

    private static int askIntValue(String question, Scanner scanner) {
        while (true) {
            try {
                System.out.println(question);
                String value = scanner.nextLine();
                return Integer.valueOf(value);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid data please re-enter");
            }
        }
    }

    private static double askDoubleValue(String question, Scanner scanner) {
        while (true) {
            try {
                System.out.println(question);
                String value = scanner.nextLine();
                return Double.valueOf(value);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid data please re-enter");
            }
        }
    }

    private static String askStringValue(String question, Scanner scanner) {
        while (true) {
            try {
                System.out.println(question);
                return scanner.nextLine();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid data please re-enter");
            }
        }
    }

    public static Book enterBook(Scanner scanner) {
        int id = askIntValue("Enter ID: ", scanner);
        String title = askStringValue("Enter title: ", scanner);
        String author = askStringValue("Enter author: ", scanner);
        String publishingHouse = askStringValue("Enter Publishing House: ", scanner);
        int yearOfPublishing = askIntValue("Enter year of publishing: ", scanner);
        int numberOfPages = askIntValue("Enter number of pages: ", scanner);
        double price = askDoubleValue("Enter price: ", scanner);

        return new Book(id, title, author, publishingHouse, yearOfPublishing, numberOfPages, price);
    }

    public static boolean chooseAction(int choice, Books books, Scanner scanner) {
        switch (choice) {
            case 1: {
                books.viewAll();
                break;
            }
            case 2: {
                int percent = askIntValue("Enter percent:", scanner);
                Books booksChangePrice = books.changePrice(percent);
                booksChangePrice.viewAll();
                break;
            }
            case 3: {
                String author = askStringValue("Enter author: ", scanner);
                Books booksOfAuthor = books.findAuthor(author);
                if (booksOfAuthor.isEmpty()) {
                   System.out.println("Author not found");
                } else {
                   booksOfAuthor.viewAll();
                }
                break;
            }
            case 4: {
                int yearOfPub = askIntValue("Enter year of publishing: ", scanner);
                Books booksBeforeYear = books.findYearOfPublishing(yearOfPub);
                if (booksBeforeYear.isEmpty()) {
                    System.out.println("Books not found");
                } else {
                    booksBeforeYear.viewAll();
                }
                    break;
            }
            case 5: {
                System.out.println("The work is done");
                return false;
            }
            default: {
                System.out.println("Invalid input");
            }
        }
        return true;
    }
}