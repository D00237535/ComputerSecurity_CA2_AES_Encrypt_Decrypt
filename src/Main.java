import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    private void start() {
        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void displayMainMenu() throws IOException {
        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Encrypt a File\n"
                + "2. Decrypt a File \n"
                + "3. Exit\n"

                + "Enter Option [1,3]";

        final int Encrypt_File = 1;
        final int Decrypt_File = 2;
        final int Exit = 3;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {

                    case Encrypt_File:
                        System.out.println("Passengers option chosen");
                        Encrypt();
                        break;

                    case Decrypt_File://Runs the Menu in the BookingManager Class
                        System.out.println("Bookings option chosen");
                        Decrypt();
                        break;

                    case Exit:
                        System.out.println("Exit Menu option chosen");
                        break;

                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;

                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != Exit);

        System.out.println("\nExiting Main Menu, goodbye.");
    }



    private void Encrypt() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a file Name");
        String input = keyboard.nextLine();
    }

    private void Decrypt() {
        System.out.println("Decrypt");
    }
}