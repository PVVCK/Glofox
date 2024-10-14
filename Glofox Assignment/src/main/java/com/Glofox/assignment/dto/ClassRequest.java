package com.Glofox.assignment.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class ClassRequest {
	@NotNull(message = "Name")
    private String name;
	@NotNull(message = "StartDate")
	private LocalDate startDate;
	@NotNull(message = "EndDate")
    private LocalDate endDate;
	@NotNull(message = "Capacity")
	private int capacity;

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getCapacity() {
        return capacity;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
