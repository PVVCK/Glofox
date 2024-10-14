package com.Glofox.assignment.model;

import java.time.LocalDate;
import java.util.List;

public class ClassModel {
    

	private String name;
    private List<LocalDate> schedule;
    private int capacity;

    public ClassModel(String name, List<LocalDate> schedule, int capacity) {
        this.name = name;
        this.schedule = schedule;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public List<LocalDate> getSchedule() {
        return schedule;
    }

    public int getCapacity() {
        return capacity;
    }
    
    public void setName(String name) {
		this.name = name;
	}

	public void setSchedule(List<LocalDate> schedule) {
		this.schedule = schedule;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
