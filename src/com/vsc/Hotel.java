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
        for(int i = 1; i<=20; i++){
            roomNumbers[i-1] = i;
        }
        int[] hotelCapacityOfRooms = {
                1, 1, 1, 1, 1, 1, 1, 1,
                2, 2, 2, 2, 2, 2, 2,
                4, 3, 3, 3, 4
        };
        int[] hotelReservedRooms = new int[20];
        String[] roomComments = new String[20];

        Scanner input = new Scanner (System.in);
        System.out.println("Give me date");
        String date = input.next();
        System.out.println(convertInputToDate(date));

        Calendar c = new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int i, int i1) {

            }

            @Override
            public void roll(int i, boolean b) {

            }

            @Override
            public int getMinimum(int i) {
                return 0;
            }

            @Override
            public int getMaximum(int i) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int i) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int i) {
                return 0;
            }
        };

    }
    public static Date convertInputToDate(String input) throws ParseException {
        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        return format1.parse(input);
    }
    public static boolean checkIfTargetIsBetweenTwoDates (Date min, Date max, Date target){
        return target.after(min) && target.before(max);
    }
}
