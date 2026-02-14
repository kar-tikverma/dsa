public class SolvedQuestionsPractice {
    public static void main(String[] args) {
        System.out.println(Book.count);
        // Book b1 = new Book(150);
        // Book b2 = new Book(250);
        Book b3 = new Book();
        System.out.println(Book.count);
        System.out.println(b3.ch);
    }
}

class Book {
    int price;
    static int count;
    Boolean ch;
    Book() {};
    Book(int price) {
        this.price = price;
        count++;
    }
}
