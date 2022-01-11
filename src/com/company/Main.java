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

    public static void main(String[] args) {
        char task;
        String book;
        login();
        while(true) {
            task = getInput("Enter 'a' to add new books, 'b' to search for a book: ").charAt(0);
            switch(task){
                case 'a':
                    WriteToFile(getBookInfo());
                    break;
                case 'b':
                    book = getInput("Enter title of book to find: ").toLowerCase();
                    System.out.println(searchForBook(book));
            }
        }

    }

    public static String getBookInfo(){
        String bookTitle = getInput("Enter book title: ");
        String ISBN = getInput("Enter ISBN number: ");
        String author = getInput("Enter Author: ");
        String genre = getInput("Enter Genre: ");
        return (bookTitle + "," + ISBN + "," + author + "," + genre + "\n");
    }

    public static String getInput(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }

    public static void WriteToFileLibrary(String contents) {
        try {
            FileWriter myWriter = new FileWriter(libraryFile.getName(), true); //True means append to file contents, False means overwrite
            myWriter.write(contents); // Overwrites everything in the file
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String searchForBook(String bookTitle){
        try {
            Scanner myReader = new Scanner(libraryFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] fileLine = data.split(",");
                if(fileLine[0].toLowerCase().equals(bookTitle)){
                    return data;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "null";
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
                    break;
            }
        }
    }

    public static void WriteToFile(String contents,boolean append) {
        try {
            FileWriter myWriter = new FileWriter(loginFile.getName(), append); //True means append to file contents, False means overwrite
            myWriter.write(contents); // Overwrites everything in the file
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
}
