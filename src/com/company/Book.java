package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {

    ArrayList[][] books = new ArrayList[0][0];

    public Book(File libraryFile) {
        try {
        Scanner myReader = new Scanner(libraryFile);
            while (myReader.hasNext()) {
                String data = myReader.nextLine();
                String[] fileLine = data.split(",");
            }
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
    }
}
