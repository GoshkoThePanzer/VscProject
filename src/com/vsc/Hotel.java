package com.vsc;

import java.time.LocalDate;

public class Hotel {

    public static void main(String[] args) {
        //             ПЛАН-СХЕМА:
        //rooms 1-8: capacity of 1 - floor 1
        //rooms 9-15: capacity of 2 - floor 2
        //rooms 17-19: capacity of 3 - floor 3
        //rooms 16&20: apartments - capacity of 4. - floor 3
        //the last two rooms of each floor have a sight to the sea
        //the other rooms have a sight to the hotel's pool and garden
        //pool card, breakfast, baby bed, etc. are ordered independently for each room.
        int[][] RoomNumbers = {
                {1,2,3,4,5,6,7,8},
                {9,10,11,12,13,14,15},
                {16,17,18,19,20}
        };
        int[][] hotelCapacityOfRooms = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2},
                {4, 3, 3, 3, 4}
        };
        int[][] hotelReservedRooms = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
    }
}
