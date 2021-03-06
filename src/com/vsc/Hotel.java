package com.vsc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Hotel {

    static String[][] reservations = RegistrationsClass.reservationsCopy;

    public static void main(String[] args) throws ParseException {
        //           SCHEME OF HOTEL
        //rooms 1-8: capacity of 1 - floor 1
        //rooms 9-15: capacity of 2 - floor 2
        //rooms 17-19: capacity of 3 - floor 3
        //rooms 16&20: apartments - capacity of 4. - floor 3
        RegistrationsClass.getFile();
        int[] roomNumbers = new int[20];
        for (int i = 1; i <= 20; i++) {
            roomNumbers[i - 1] = i;
        }

        Date today = Calendar.getInstance().getTime();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to our hotel!");
        System.out.println("Please select one of the following options:");
        while (true) {
            System.out.println("\n" + "1. Make a room reservation            2. Check free rooms today ");
            System.out.println("3. Cancel a reservation/Free a room   4. Get a report of all reservations in a period");
            System.out.println("5. Find a suitable room in a date     6. Exit (please use this button to close the program)");

            byte option = input.nextByte();

            switch (option) {
                case 1:
                    System.out.println("Enter room number:");
                    String roomNumber = input.next();
                    System.out.println("Reservation starting on: (dd/MM/yyyy)");
                    String startDate = input.next();
                    System.out.println("Reservation ending on: (dd/MM/yyyy)");
                    String endDate = input.next();
                    System.out.println("Name of the person reserving the room: (only first name)");
                    String customer = input.next();
                    String[] reservationInfo = {roomNumber, startDate, endDate, customer};
                    if (!checkIfReservationIsValid(reservations, reservationInfo, today)) {
                        System.out.println("Invalid registration info. Please try again.");
                    } else {
                        reservations = addElementTo2dArray(reservations, reservationInfo);
                        System.out.println("Reservation successful!");
                    }
                    break;

                case 2:
                    int[] availableRooms = findAvailableRooms(roomNumbers, today);
                    System.out.print("Available rooms today: ");
                    for (int i = 0; i < availableRooms.length - 1; i++) {
                        System.out.print(availableRooms[i] + ", ");
                    }
                    System.out.println(availableRooms[availableRooms.length - 1] + ".");
                    break;

                case 3:
                    System.out.println("Enter your name:");
                    customer = input.next();
                    System.out.println("Enter number of the room:");
                    roomNumber = input.next();
                    System.out.println("Start date of the reservation:");
                    startDate = input.next();
                    int flag = 0;
                    for (String[] reservation : reservations) {
                        if (roomNumber.equals(reservation[0]) && customer.equals(reservation[3]) && startDate.equals(reservation[1])) {
                            flag++;
                            reservations = deleteElementOf2dArray(reservations, reservation);
                        }
                    }
                    if (flag == 0) {
                        System.out.println("Error. Such reservation was not found.");
                    }
                    break;

                case 4:
                    System.out.println("Enter the first date of the period:");
                    Date firstDate = convertInputToDate(input.next());
                    System.out.println("Enter the last date of the period:");
                    Date lastDate = convertInputToDate(input.next());
                    for (String[] reservation : reservations) {
                        if (checkIfTargetIsBetweenTwoDates(firstDate, lastDate, convertInputToDate(reservation[1])) ||
                                checkIfTargetIsBetweenTwoDates(firstDate, lastDate, convertInputToDate(reservation[2]))) {
                            System.out.println(String.format("Room: %s, Reservation days: %s - %s, on the name: %s",
                                    reservation[0], reservation[1], reservation[2], reservation[3]));
                        }
                    }
                    break;

                case 5:
                    int[] matchingRooms = new int[0];
                    System.out.println("Enter date for the search:");
                    Date date = convertInputToDate(input.next());
                    System.out.println("Enter needed capacity of the room (max capacity: 4)");
                    int requiredCapacity = input.nextInt();
                    for (int room : findAvailableRooms(roomNumbers, date)) {
                        if (findCapacityOfARoom(room) >= requiredCapacity) {
                            matchingRooms = addElementToArray(matchingRooms, room);
                        }
                    }
                    if (matchingRooms.length == 0) {
                        System.out.println("No such rooms available.");
                    } else {
                        System.out.println("Matching rooms: " + printsAllElementsOfArray(matchingRooms));
                    }
                    break;
            }
            RegistrationsClass.reservationsCopy = reservations;
            for (String[] reservation : RegistrationsClass.reservationsCopy) {
                System.out.println(Arrays.toString(reservation));
            }
            if (option == 6){
                break;
            }
        }
        RegistrationsClass.saveFile();
        System.exit(0);
    }

    public static boolean checkIfReservationIsValid(String[][] allReservations, String[] checkedInfo, Date today) throws ParseException {
        Date minDate = convertInputToDate(checkedInfo[1]);
        Date maxDate = convertInputToDate(checkedInfo[2]);
        int flag = 0;
        if (minDate.before(today) || maxDate.before(today) || minDate.after(maxDate)) {
            System.out.println("Error: Invalid duration of stay.");
            flag = 1;
        }
        if (Byte.parseByte(checkedInfo[0]) > 20 || Byte.parseByte(checkedInfo[0]) < 0) {
            System.out.println("Error 404: Room not found.");
            flag = 1;
        } else {
            for (String[] reservation : allReservations) {
                if (reservation[0].equals(checkedInfo[0]) &&
                        (checkIfTargetIsBetweenTwoDates(minDate, maxDate, convertInputToDate(reservation[1])) ||
                                checkIfTargetIsBetweenTwoDates(minDate, maxDate, convertInputToDate(reservation[2])) ||
                                checkIfTargetIsBetweenTwoDates(convertInputToDate(reservation[1]), convertInputToDate(reservation[2]), minDate) ||
                                checkIfTargetIsBetweenTwoDates(convertInputToDate(reservation[1]), convertInputToDate(reservation[2]), maxDate))
                ) {
                    System.out.println("Error: room already registered.");
                    flag = 1;
                }
            }
        }
        return flag == 0;
    }

    public static Date convertInputToDate(String input) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        return format1.parse(input);
    }

    public static boolean checkIfTargetIsBetweenTwoDates(Date min, Date max, Date target) {
        return target.after(min) && target.before(max);
    }

    public static int[] addElementToArray(int[] baseArray, int element) {
        int[] newArray = new int[baseArray.length + 1];
        System.arraycopy(baseArray, 0, newArray, 0, baseArray.length);
        newArray[baseArray.length] = element;
        return newArray;
    }

    public static String[][] addElementTo2dArray(String[][] baseArray, String[] elementArray) {
        String[][] newArray = new String[baseArray.length + 1][];
        System.arraycopy(baseArray, 0, newArray, 0, baseArray.length);
        newArray[baseArray.length] = elementArray;
        return newArray;
    }

    public static int[] findAvailableRooms(int[] startingArray, Date date) throws ParseException {
        int[] array = startingArray;
        for (String[] reservation : reservations) {
            int roomOfReservation = Integer.parseInt(reservation[0]);
            if (checkIfTargetIsBetweenTwoDates(convertInputToDate(reservation[1]), convertInputToDate(reservation[2]), date)) {
                array = deleteElementOfArray(array, roomOfReservation);
            }
        }
        return array;
    }

    public static int[] deleteElementOfArray(int[] baseArray, int toDelete) {
        int[] newArray = new int[baseArray.length - 1];
        int index = 0;
        for (int i = 0; i < baseArray.length; i++) {
            if (baseArray[i] == toDelete) {
                index = i;
            }
        }
        System.arraycopy(baseArray, 0, newArray, 0, index);
        if (newArray.length - index >= 0)
            System.arraycopy(baseArray, index + 1, newArray, index, newArray.length - index);
        return newArray;
    }

    public static String[][] deleteElementOf2dArray(String[][] baseArray, String[] toDelete) {
        String[][] newArray = new String[baseArray.length - 1][];
        int index = 0;
        for (int i = 0; i < baseArray.length; i++) {
            if (baseArray[i] == toDelete) {
                index = i;
            }
        }
        System.arraycopy(baseArray, 0, newArray, 0, index);
        if (newArray.length - index >= 0)
            System.arraycopy(baseArray, index + 1, newArray, index, newArray.length - index);
        return newArray;
    }

    public static String printsAllElementsOfArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length - 1; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.append(array[array.length - 1]).append(".");
        return sb.toString();
    }

    public static int findCapacityOfARoom(int roomNumber) {
        int capacity = 0;
        if (roomNumber < 9) {
            capacity = 1;
        }
        if (roomNumber >= 9 && roomNumber < 16) {
            capacity = 2;
        }
        if (roomNumber >= 17 && roomNumber <= 19) {
            capacity = 3;
        }
        if (roomNumber == 16 || roomNumber == 20) {
            capacity = 4;
        }
        return capacity;
    }
}
