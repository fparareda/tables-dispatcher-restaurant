package com.restaurant;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SeatingManager {

    private List<Table> tables;
    private List<CustomerGroup> waitingQueue;

    /* Constructor */
    public SeatingManager(List<Table> tables, List<CustomerGroup> waitingQueue){
        this.tables = tables.stream()
                            .sorted(Comparator.comparingInt(Table::getSize))
                            .collect(Collectors.toList());
        this.waitingQueue = waitingQueue;
    }

    /* Group arrives and wants to be seated. */
    public void arrives(CustomerGroup group) {
        assert group != null;
        if(!waitingQueue.contains(group)) {
            waitingQueue.add(group);
        }
    }

    private Optional<Table> getPossibleTableToSeat(CustomerGroup group) {
        return tables.stream()
                    .filter(table -> table.getFreeSeats() >= group.getSize())
                    .findFirst();
    }

    /* Whether seated or not, the group leaves the restaurant. */
    public void leaves(CustomerGroup group){
        assert group != null;
        if(waitingQueue.contains(group)) {
            waitingQueue.remove(group);
        } else {
            unseatGroup(group);
            reassignGroupsInQueue();
        }
    }

    private void reassignGroupsInQueue() {
        waitingQueue.forEach(this::locate);
    }

    private void unseatGroup(CustomerGroup group) {
        Optional<Table> possibleTable = tables.stream()
                .filter(table -> table.getSeatedGroup().contains(group))
                .findFirst();
        if(possibleTable.isPresent()) {
            Table table = possibleTable.get();
            table.unSeatGroup(group);
        }
    }

    /* Return the table at which the group is seated, or null if
    they are not seated (whether they're waiting or already left). */
    public Table locate(CustomerGroup group){
        assert group != null;
        Optional<Table> possibleTable = getPossibleTableToSeat(group);
        if(possibleTable.isPresent()) {
            waitingQueue.remove(group);
            Table table = possibleTable.get();
            table.seatGroup(group);
            return table;
        }

        if(!waitingQueue.contains(group)){
            waitingQueue.add(group);
        }
        return null;
    }
}