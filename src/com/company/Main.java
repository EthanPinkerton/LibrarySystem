package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    //There are unused methods
    private static File libraryFile = new File("Library.txt");

    public static void main(String[] args) {
        char task;
        String book;
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

    public static void WriteToFile(String contents) {
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
}
