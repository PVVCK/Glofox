package com.Glofox.assignment.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class BookingRequest {
	@NotNull(message = "Name")
    private String name;
    @NotNull(message = "Date")
	private LocalDate date;
    @NotNull(message = "ClassName")
    private String className;

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getClassName() {
        return className;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}

