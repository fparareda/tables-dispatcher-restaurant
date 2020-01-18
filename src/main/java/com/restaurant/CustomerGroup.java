package com.restaurant;

public class CustomerGroup {
    private final int size;
    private final String reservationName;

    public CustomerGroup(int size, String reservationName) {
        this.size = size;
        this.reservationName = reservationName;
    }

    public String getReservationName() {
        return reservationName;
    }

    public int getSize() {
        return size;
    }
}
