package com.vsc;

import java.io.*;
import java.util.Scanner;

public class RegistrationsClass {
    public static String[][] reservationsCopy = new String[0][0];
    public static int rows = 0;
    public static int columns = 0;

    public static void nextIndex() {
        columns++;
        if (columns >= reservationsCopy[0].length) {
            columns = 0;
            rows++;
        }
    }

    public static void getFile() {
        try {
            File myObj = new File("requestBase.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] words = data.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    words[i] = words[i].replaceAll("[^\\w]", "");
                    reservationsCopy[rows][columns] = words[i];
                    nextIndex();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void saveFile() {
        BufferedWriter outputWriter;

        try {
            outputWriter = new BufferedWriter(new FileWriter("requestBase.txt"));
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < reservationsCopy[i].length; j++) {
                    outputWriter.write(reservationsCopy[i][j] + " ");
                }
                outputWriter.newLine();
            }

            outputWriter.flush();
            outputWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}