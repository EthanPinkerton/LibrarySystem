package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    //There are unused methods
    private static File libraryFile = new File("Library.txt");
    private static final File loginFile = new File("Login.txt");
    private static Book[] books = getBooks();

    public static void main(String[] args) {
        login();

        String action = "0";
        while(!action.equals("3")) {
            action = getInput("1 - borrow a book\n2 - return a book\n3 - log out\nEnter action: ");
            switch (action){
                case "1":
                    borrow();
                case "2":

                case "3":
                    System.out.println("Logging out");
                    break;
                default:
                    System.out.println("Invalid");
            }
        }
    }

    public static void borrow(){
        String book = getInput("Enter book you would like to borrow: ");
        searchForBook(book);
    }

    public static void bookObjects() throws FileNotFoundException {
        Scanner myReader = new Scanner(libraryFile);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] fileLine = data.split(",");
        }
    }

    public static void searchForBook(String bookTitle){
        for (int i = 0; i < books.length; i++) {
            return;
        }
    }

    public static void login(){
        while(true){
            String action = getInput("1 - login\n2 - register\nEnter action: ");
            switch (action) {
                case "1":
                    String details = getInput("Enter your username: ");
                    if (Objects.equals(details, searchForLogin(details, 0))) {
                        for (int i = 0; i < 5; i++) {
                            details = getInput("Enter your password: ");
                            if (Objects.equals(details, searchForLogin(details, 1))) {
                                System.out.println("Successfully logged in");
                                return;
                            }
                        }
                        System.out.println("Incorrect password");
                    } else {
                        System.out.println("Couldn't find that username");
                    }
                    break;
                case "2":
                    String username = getInput("Enter a username: ");
                    if (!username.equals(searchForLogin(username, 0))) {
                        String password = getInput("Enter a password: ");
                        while (password.length() < 8) {
                            password = getInput("Enter a password at least 8 characters: ");
                        }
                        WriteToFile(username + "," + password + "\n", true);
                    } else {
                        System.out.println("Username has already been taken");
                    }
                    break;
                default:
                    System.out.println("Invalid action");
            }

        }
    }

    public static void WriteToFile(String contents,boolean append) {
        try {
            FileWriter myWriter = new FileWriter(loginFile.getName(), append); //True means append to file contents, False means overwrite
            myWriter.write(contents);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String searchForLogin(String login,int index){
        try {
            Scanner myReader = new Scanner(loginFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] fileLine = data.split(",");
                if(fileLine[index].equals(login)){
                    myReader.close();
                    return fileLine[index];
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "null";
    }

    public static String getInput(String prompt){
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }

    public static Book[] getBooks(){
        try {
            Scanner myReader = new Scanner(libraryFile);
            Book[] books = new Book[(int) libraryFile.length()];
            for (int i = 0; i < libraryFile.length(); i++) {
                String data = myReader.nextLine();
                String[] fileLine = data.split(",");
                books[i] = new Book(fileLine[0],fileLine[1],fileLine[2],fileLine[3]);
                return books;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return getBooks();
    }
}
