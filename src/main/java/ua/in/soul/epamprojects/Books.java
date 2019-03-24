package ua.in.soul.epamprojects;

public class Books {

    private int size;
    private Book[] books;
    private int nextBookIndex = 0;

    public Books(int size) {
        this.size = size;
        this.books = new Book[size];
    }

    public String addBook(Book book) {
        if (nextBookIndex >= books.length) {
            return "Library is full";
        }
        books[nextBookIndex++] = book;
        return "Book succesfully added";
    }

    public void viewAll() {
        for (int i = 0; i < nextBookIndex; i++) {
            books[i].view();
        }
    }

    public Books changePrice(int percent) {
        Books copyOfCurrent = new Books(nextBookIndex);
        for (int i = 0; i < nextBookIndex; i++) {
            copyOfCurrent.addBook(books[i].clone());
        }

        for (int i = 0; i < nextBookIndex; i++) {
            Book currentBook = copyOfCurrent.books[i];
            double currentPriсe = currentBook.getPrice();
            double newPrice = currentPriсe * (1 + (percent / 100.0));
            currentBook.setPrice(newPrice);
        }
        return copyOfCurrent;
    }

    public Books findAuthor (String author){
        Books booksOfCurrentAuthor = new Books(size);
        for (Book element : books){
            if (element.getAuthor() != null && element.getAuthor().equalsIgnoreCase(author)){
                booksOfCurrentAuthor.addBook(element);
            }
        }
        return booksOfCurrentAuthor;
    }

    public Books findYearOfPublishing (int yearOfPublish){
        Books booksOfCurrentYears = new Books(size);
        for (Book element : books){
            if (element.getYearOfPublishing() != 0 && element.getYearOfPublishing() > yearOfPublish){
                booksOfCurrentYears.addBook(element);
            }
        }
        return booksOfCurrentYears;
    }

    public boolean isEmpty() {
        return nextBookIndex == 0;
    }
}
