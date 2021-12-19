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
// Start of the main menu by using final ints and cases you input a value, and it will bring
// you do the case you have selected 1 will bring you to the encryption, 2 will bring you to the
// decryption and 3 will allow you to exit the program
    private void displayMainMenu() throws IOException {
        final String MENU_ITEMS = "*** MAIN MENU OF OPTIONS ***\n"
                + "1. Encrypt a File (Task 2)\n"
                + "2. Decrypt a File (Task 3)\n"
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
                        // Case one is the option to encrypt a file and acquire the key that will be needed to decrypt the code.
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println("Encryption option chosen");
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        Encrypt();
                        break;

                    case Decrypt_File:
                        // Case two is the option to decrypt a file using the key that was gotten from the encrypt class.
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println("Decryption option chosen");
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        Decrypt();
                        break;

                    case Exit:
                        // case three is the option to exit the program
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println("Exit Menu option chosen");
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+");
                        break;

                    default:
                        // this section if for if someone were to enter a value that is not in the selection
                        // and when done will loop back to the menu and allow you to enter another option
                        System.out.print("Invalid option - please enter number in range");
                        break;

                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        }
        while (option != Exit);

        System.out.println("\nExiting Main Menu, Goodbye.");
    }

    private void Encrypt() {

        // The Encrypt method was created for Task 2 of the Ca and works by allowing you to enter the name of a file
        // allowing the program to read the file

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a file Name");
        String input = keyboard.nextLine();
        String text = loadFromFile(input);

        System.out.println();

        // The program will then display the content of the file you have entered and will use the password that has been
        // set and will run all the processes to make encrypt the file using AES

        System.out.println("Content of the File\n " + text);

        System.out.println();

        String password = "Super Secret";

        String salt = Password.generateRandomSalt();
        String masterEncryptionKey = new Password(password, salt).generateHash();

        String ciphertext = Cipher.encryptString(text, masterEncryptionKey);

        // Once the encryption is completed the key will be printed for the user to copy

        System.out.println("This is the Key:\n " + masterEncryptionKey);

        // The final part of the encrypt method is to print the ciphertext along with writing and saving it to a file

        System.out.println("\nCIPHERTEXT:");
        System.out.println(ciphertext);

        saveToFile("ciphertext.txt", ciphertext);
    }

    private void Decrypt() {

        // The Decrypt method was created for Task 3 of the Ca and works by entering the name of the file you stored the
        // cipher in for the last part

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a file Name");
        String input = keyboard.nextLine();
        String text = loadFromFile(input);

        System.out.println("CipherText");
        System.out.println(text);
        System.out.println();

        // After you enter the file name the program will print the context of the file and will prompt you to enter the
        // key you got in the Encrypt section of the program

        System.out.println("Please enter the key");
        String masterKey = keyboard.nextLine();
        System.out.println();

        // After you enter the key as long as it is correct the encrypted AES will Decrypt and will print the plain text
        // that was in the file you encrypt and then will save to the file of your choosing

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
