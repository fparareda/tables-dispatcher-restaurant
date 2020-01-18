package com.restaurant;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final int size;
    private List<CustomerGroup> seatedGroup;

    public Table(int size) {
        this.size = size;
        this.seatedGroup = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public void seatGroup(CustomerGroup customerGroup){
        seatedGroup.add(customerGroup);
    }

    public void unSeatGroup(CustomerGroup customerGroup){
        seatedGroup.remove(customerGroup);
    }

    public List<CustomerGroup> getSeatedGroup() {
        return seatedGroup;
    }

    public int getFreeSeats() {
        return (size - seatedGroup.stream().mapToInt(CustomerGroup::getSize).sum());
    }
}
