import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
        final String MENU_ITEMS = "*** MAIN MENU OF OPTIONS ***\n"
                + "1. Encrypt a File\n"
                + "2. Decrypt a File \n"
                + "3. Exit\n"

                + "Enter Option [1,3]\n";

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
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println("Encryption option chosen");
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        Encrypt();
                        break;

                    case Decrypt_File:
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println("Decryption option chosen");
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        Decrypt();
                        break;

                    case Exit:
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println("Exit Menu option chosen");
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        break;

                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;

                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        }
        while (option != Exit);

        System.out.println("\nExiting Main Menu, goodbye.");
    }


    private void Encrypt() {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a file Name");
        String input = keyboard.nextLine();
        String text = loadFromFile(input);

        System.out.println();

        System.out.println(text);

        System.out.println();

        String password = "Super Secret";

        String salt = Password.generateRandomSalt();
        String masterEncryptionKey = new Password(password, salt).generateHash();

        String ciphertext = Cipher.encryptString(text, masterEncryptionKey);

        System.out.println("This is the Key:\n " + masterEncryptionKey);

        System.out.println("\nCIPHERTEXT:");
        System.out.println(ciphertext);

        saveToFile("ciphertext.txt", ciphertext);
    }


    private void Decrypt() {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a file Name");
        String input = keyboard.nextLine();
        String text = loadFromFile(input);

        System.out.println("CipherText");
        System.out.println(text);

        System.out.println("Please enter the key");
        String masterKey = keyboard.nextLine();

        String decryptedPlaintext = Cipher.decryptString(text, masterKey);
        System.out.println("\nPLAINTEXT:");
        System.out.println(decryptedPlaintext);

        saveToFile("plaintext", decryptedPlaintext);

    }

    public String loadFromFile(String fileName) {
        String plaintext = "";
        try {
            Scanner sc = new Scanner(new File(fileName));

            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                plaintext = sc.next();
            }
        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
        return plaintext;
    }


    public void saveToFile(String fileName, String text) {

        try {
            FileWriter cipherWriter = new FileWriter(fileName);

            cipherWriter.write(text);

            cipherWriter.close();

            System.out.println("The Text has be written to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


//public class MainApp {
//
//    public static void main(String[] args) throws IOException {
//
//        String originalPlaintext = "This is a plaintext\nLine2\t...tabbed";
//        String password = "Super Secret";
//
//        // We need to turn the low-entropy password into a fixed-entropy
//        // key which is 128, 192, or 256 bytes in length. As our Password
//        // object already gives us a way to do this -- we'll use that
//        // (generates a 256-bit key, as a base64-encoded string)
//        String salt = Password.generateRandomSalt();
//        String masterEncryptionKey = new Password(password, salt).generateHash();
//
//        // Encrypt and print ciphertext
//        String ciphertext = Cipher.encryptString(originalPlaintext, masterEncryptionKey);
//        System.out.println("\nCIPHERTEXT:");
//        System.out.println(ciphertext);
//
//        // Decrypt and print plaintext

//    }

//}