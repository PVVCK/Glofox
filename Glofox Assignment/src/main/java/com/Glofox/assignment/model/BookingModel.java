package com.Glofox.assignment.model;

import java.time.LocalDate;

public class BookingModel {
    private String name;
    private LocalDate classDate;
    private String className;

    public BookingModel(String name, LocalDate classDate, String className) {
        this.name = name;
        this.classDate = classDate;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public LocalDate getClassDate() {
        return classDate;
    }

	public String getClassName() {
        return className;
    }
	
	 public void setName(String name) {
			this.name = name;
		}

		public void setClassDate(LocalDate classDate) {
			this.classDate = classDate;
		}

		public void setClassName(String className) {
			this.className = className;
		}
}
