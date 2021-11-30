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
        int numberOfBooks = Integer.parseInt(getInput("Enter number of books to store: "));
        for (int i = 0; i < numberOfBooks; i++) {
            WriteToFile(getBookInfo());
        }

        System.out.println(searchForBook("among us guide")); // search is done implement properly

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

    public static void CreateFile() {
        try {
            if (libraryFile.createNewFile()) {
                System.out.println("File created: " + libraryFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
                if(fileLine[0].equals(bookTitle)){
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
