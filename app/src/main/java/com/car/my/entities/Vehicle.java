package com.car.my.entities;

public class Vehicle {
    private long id;
    private String name;
    private long initialRun;
    private long currentRun;

    public Vehicle(long id, String name, long initialRun, long currentRun){
        this.id = id;
        this.name = name;
        this.initialRun = initialRun;

        this.currentRun = currentRun;
    }

    public String getName() {
        return name;
    }

    public long getCurrentRun() {
        return currentRun;
    }
}
