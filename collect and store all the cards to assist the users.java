import java.util.*;

class Card {
    private String symbol;
    private String rank;

    // Constructor
    public Card(String symbol, String rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    // Getter methods
    public String getSymbol() {
        return symbol;
    }

    public String getRank() {
        return rank;
    }

    // Override toString method for easy display
    @Override
    public String toString() {
        return rank + " of " + symbol;
    }
}

public class CardDeck {

    private static ArrayList<Card> deck = new ArrayList<>();
    
    // Initialize the deck of cards
    public static void initializeDeck() {
        String[] symbols = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String symbol : symbols) {
            for (String rank : ranks) {
                deck.add(new Card(symbol, rank));
            }
        }
    }

    // Method to search for cards of a given symbol
    public static void searchCardsBySymbol(String symbol) {
        boolean found = false;
        for (Card card : deck) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                System.out.println(card);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found for symbol: " + symbol);
        }
    }

    // Method to display all cards in the deck
    public static void displayAllCards() {
        if (deck.isEmpty()) {
            System.out.println("The deck is empty.");
        } else {
            for (Card card : deck) {
                System.out.println(card);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the deck
        initializeDeck();
        
        int choice;

        do {
            // Menu to select the operation
            System.out.println("\n--- Card Deck Management ---");
            System.out.println("1. Display All Cards");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("\nDisplaying all cards in the deck:");
                    displayAllCards();
                    break;

                case 2:
                    System.out.print("\nEnter the symbol (Hearts, Diamonds, Clubs, Spades) to search: ");
                    String symbol = scanner.nextLine();
                    System.out.println("\nCards of " + symbol + ":");
                    searchCardsBySymbol(symbol);
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);

        scanner.close();
    }
}
