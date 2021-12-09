package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    //There are unused methods
    private static final File libraryFile = new File("Library.txt");
    private static final File loginFile = new File("Login.txt");

    public static void main(String[] args) {
        char task;
        String book;
        login();
        while(true) {
            task = getInput("Enter 'a' to add new books, 'b' to search for a book: ").charAt(0);
            switch(task){
                case 'a':
                    WriteToFile(getBookInfo(),true);
                    break;
                case 'b':
                    book = getInput("Enter title of book to find: ").toLowerCase();
                    System.out.println(searchForBook(book));
                    break;
                case 'c':
                    book = getInput("Enter title of the book delete: ").toLowerCase();
                    deleteBook(searchForBook(book));
                    break;
            }
        }

    }

    public static void login(){
        boolean login = false;
        String username;
        String password;
        while(!login){
            username = getInput("Enter your username");
            password = getInput("Enter your password");
            login = true;
        }
    }

    public static String searchForLogin(String login,int index){
        try {
            Scanner myReader = new Scanner(loginFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] fileLine = data.split(",");
                if(fileLine[index].toLowerCase().equals(login)){
                    myReader.close();
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

    public static String getBookInfo(){
        String bookTitle = getInput("Enter book title: ");
        String ISBN = getInput("Enter ISBN number: ");
        String author = getInput("Enter Author: ");
        String genre = getInput("Enter Genre: ");
        return (bookTitle + "," + ISBN + "," + author + "," + genre + "\n");
    }

    public static String getInput(String prompt){
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }

    public static void WriteToFile(String contents,boolean append) {
        try {
            FileWriter myWriter = new FileWriter(libraryFile.getName(), append); //True means append to file contents, False means overwrite
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
                    myReader.close();
                    return data;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "Couldn't find the book";
    }

    public static void deleteBook(String book){
        try {
            Scanner myReader = new Scanner(libraryFile);
            String line = "";
            if (book.equals("Couldn't find the book")) {
                System.out.println(book);
                return;
            }
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (book.equals(data)) {
                    System.out.println(book + " has been deleted");
                } else {
                    line += data;
                }
            }
            WriteToFile(line,false);
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
