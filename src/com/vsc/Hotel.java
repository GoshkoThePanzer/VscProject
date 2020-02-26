package com.vsc;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Hotel {

    public static void main(String[] args) throws ParseException {
        //           SCHEME OF HOTEL
        //rooms 1-8: capacity of 1 - floor 1
        //rooms 9-15: capacity of 2 - floor 2
        //rooms 17-19: capacity of 3 - floor 3
        //rooms 16&20: apartments - capacity of 4. - floor 3
        //the last two rooms of each floor have a sight to the sea
        //the other rooms have a sight to the hotel's pool and garden
        //pool card, breakfast, baby bed, etc. are ordered independently for each room.
        int[] roomNumbers = new int[20];
        for (int i = 1; i <= 20; i++) {
            roomNumbers[i - 1] = i;
        }
        String[][] reservations = {};
        Date today = Calendar.getInstance().getTime();
        Scanner input = new Scanner(System.in);


        System.out.println("Welcome to our hotel!");
        System.out.println("Please select one of the following options:");
        System.out.println("1. Make a room reservation            2. Check free rooms today ");
        System.out.println("3. Cancel a reservation/Free a room   4. Get a report of all reservations in a period");
        System.out.println("5. Find a suitable room in a period.  6. Bonus ---");
        byte option = input.nextByte();
        switch (option) {
            case 1:
                System.out.println("Enter room number:");
                String roomNumber = input.next();
                System.out.println("Reservation starting on: (dd/MM/yyyy)");
                String startDate = input.next();
                System.out.println("Reservation ending on: (dd/MM/yyyy)");
                String endDate = input.next();
                System.out.println("Name of the person reserving the room.");
                String[] reservationInfo = {roomNumber, startDate, endDate};
                addElementTo2dArray(reservations, reservationInfo);

                break;
        }
    }

    public static Date convertInputToDate(String input) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        return format1.parse(input);
    }

    public static void checkIfReservationIsValid (Scanner input) throws ParseException {
        ...
    }

    public static boolean checkIfTargetIsBetweenTwoDates(Date min, Date max, Date target) {
        return target.after(min) && target.before(max);
    }

    public static String[][] addElementTo2dArray(String[][] baseArray, String[] elementArray) {
        String[][] newArray = new String[baseArray.length + 1][];
        for(int i = 0; i<baseArray.length; i++){
            newArray[i] = baseArray [i];
        }
        newArray[baseArray.length] = elementArray;
        return newArray;
    }
}
