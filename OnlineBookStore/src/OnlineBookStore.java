import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineBookStore
{
    public static int ISBN_INDEX = 0;
    public static int TITLE_INDEX = 1;
    public static int AUTHOR_INDEX = 2;
    public static int PUBLISHER_INDEX = 3;
    public static int PUBLISHER_YEAR_INDEX = 4;
    public static int QUANTITY_INDEX = 5;
    public static int PRICE_INDEX = 6;

    public static void main(String[] args)
    {
        ArrayList<Book> bookList = new ArrayList<Book>();
        try
        {
            FileReader fileReader = new FileReader("books.txt");// Enter the entire path of the file if needed
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            boolean endOfFile = false;

            while(!endOfFile)
            {
                String bookLine = bufferedReader.readLine();
                if (bookLine != null)
                {
                    String[] bookData = bookLine.split(", ");
                    String isbn = bookData[ISBN_INDEX];
                    String title = bookData[TITLE_INDEX];
                    String author = bookData[AUTHOR_INDEX];
                    String publisher = bookData[PUBLISHER_INDEX];
                    int publishYear = Integer.parseInt (bookData[PUBLISHER_YEAR_INDEX]);
                    int quantity = Integer.parseInt (bookData[QUANTITY_INDEX]);
                    double price = Double.parseDouble (bookData[PRICE_INDEX]);
                    Book book = new Book(isbn, title, author, publisher, publishYear, quantity, price);
                    bookList.add(book);

                }
                else
                {
                    endOfFile = true;
                }
            }
            bufferedReader.close();
            fileReader.close();
        } // End try 

        catch (IOException e)
        {
            e.printStackTrace();
        }

        // Uncomment the following lines once you have implemented the required methods
        printBookDetails(bookList);
        purchaseBook(bookList);
    }
    public static void printBookDetails(ArrayList<Book> bookList){
        if(!bookList.isEmpty()) {
            for (int i = 0; i < bookList.size(); i++) {
                Book tmpBook = bookList.get(i);
                System.out.println("ISBN: " + tmpBook.getIsbn());
                System.out.println("Title: " + tmpBook.getTitle());
                System.out.println("Author: " + tmpBook.getAuthor());
                System.out.println("Publisher: " + tmpBook.getPublisher());
                System.out.println("Publish Year: " + tmpBook.getPublishYear());
                System.out.println("Quantity: " + tmpBook.getQuantity());
                System.out.println("Price: €" + tmpBook.getPrice());
                System.out.println("");
            }
        }
    }

    public static Book getBook(ArrayList<Book> bookList, String title){
        if(!bookList.isEmpty()){
            for(int i =0;i<bookList.size();i++){
                Book tmpBook = bookList.get(i);
                if(tmpBook.getTitle().equalsIgnoreCase(title)){
                    return tmpBook;
                }
            }
        }
        Book fake = new Book("fake", "fake", "fake",
                "fake", 0, 0, 0);
        return fake;
    }

    public static void topUpCard(ChargeCard card, double amount){
        if(amount>=0){
            card.topUpFunds(amount);
            System.out.println("€" +amount + " has been added to your card." );
        }
        else{
            System.out.println("€"+amount+ " couldn't be added to your card as it is an invalid amount.");
        }
    }

    public static void purchaseBook(ArrayList<Book> bookList){
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        ChargeCard card = null;

        while(!exit){
            System.out.print("Enter the amount of money you have on your card: ");
            if(input.hasNextInt()){
                int userInput = input.nextInt();
                if(userInput>0) {
                    card = new ChargeCard();
                    topUpCard(card,userInput);
                    exit = true;
                }
                else{
                    System.out.println("Invalid Input Please Enter A Valid Double.");
                }
            }
            else{
                System.out.println("Invalid Input Please Enter A Valid Double.");
            }
        }

        exit = false;
        while(!exit) {
            System.out.print("What is the title of the book you wish to purchase:");
            String userBookTitle = null;
            if (input.hasNext()){
                String tmp = input.next();
                userBookTitle = input.nextLine();
                tmp += userBookTitle;
                userBookTitle = tmp;
            }
            boolean exit2 = false;
            Book tmpBook = null;
            while(!exit2) {
                tmpBook = getBook(bookList, userBookTitle);
                if(tmpBook.getTitle().equalsIgnoreCase("fake") ){
                    tmpBook = null;
                    exit2 = true;
                }
                if(tmpBook != null){
                    exit2 = true;
                }
            }
            if(tmpBook != null ){
                if(tmpBook.getQuantity()>0){
                    if(tmpBook.getPrice()<= card.getFunds()){
                        tmpBook.setQuantity(tmpBook.getQuantity()-1);
                        card.removeFunds(tmpBook.getPrice());
                        System.out.println("You have successfully purchased "+tmpBook.getTitle()+". €"+
                                tmpBook.getPrice()+ " has been removed from your card.");
                    }
                    else{
                        System.out.println(tmpBook.getTitle()+" is in stock however you have insufficient funds " +
                                "on your card to purchase it. it's price is €"+tmpBook.getPrice());
                    }
                }
                else{
                    System.out.println("There is no more of these books left in stock.");
                }
            }
            else{
                System.out.println("We could not find the title of the book you were looking for in our stock.");
            }
            if(!askToLeave()){
                exit=true;
            }
        }
        input.close();
    }

    public static boolean askToLeave(){
        Scanner input2 = new Scanner(System.in);
        while(true){
            System.out.print("Would you like to try purchasing another book? [YES/NO] ");
            String userInput = input2.next();
            if (userInput.equalsIgnoreCase("yes")) {
                return true;
            } else if (userInput.equalsIgnoreCase("No")) {
                System.out.println("Goodbye");
                return false;
            } else {
                System.out.println("Please enter a valid input.");
            }
        }

    }

}