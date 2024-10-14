package com.Glofox.assignment.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Glofox.assignment.dto.BookingRequest;
import com.Glofox.assignment.dto.ClassRequest;
import com.Glofox.assignment.model.BookingModel;
import com.Glofox.assignment.model.ClassModel;
import com.Glofox.assignment.response.APIResponse;
import com.Glofox.assignment.util.ExecutionTimeLogger;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/glofox")
public class StudioController {
	
	public StudioController(List<ClassModel> classes, List<BookingModel> bookings) {
		super();
		this.classes = classes;
		this.bookings = bookings;
	}

	private List<ClassModel> classes = new ArrayList<>();
    private List<BookingModel> bookings = new ArrayList<>();
    
 // Create a new class
    @PostMapping("/class")
    public ResponseEntity<APIResponse> createClass(@Valid @RequestBody ClassRequest request, BindingResult bindingResult) {
    	ExecutionTimeLogger executionTimeLogger = new ExecutionTimeLogger();
    	executionTimeLogger.logMethodStart("StudioController", "createClass");
    	
	        try {
	            // 1. Check for validation errors first
	            if (bindingResult.hasErrors()) {
	                StringBuilder validationErrors = new StringBuilder("Validation errors: ");
	                bindingResult.getFieldErrors().forEach(error -> 
	                    validationErrors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
	                );
	                
	                // Create a failed response with validation errors
	                APIResponse response = new APIResponse("Failed", validationErrors.toString(), request);
	                return ResponseEntity.badRequest().body(response);
	            }
	            
	            // Check if start date is after end date
	            if (request.getStartDate().isAfter(request.getEndDate())) {
	                APIResponse response = new APIResponse("Failed", "Start date cannot be after end date", request);
	                return ResponseEntity.badRequest().body(response);
	            }
	
	            // Generate a list of dates for the class schedule
	            List<LocalDate> schedule = new ArrayList<>();
	            LocalDate current = request.getStartDate();
	            while (!current.isAfter(request.getEndDate())) {
	                schedule.add(current);
	                current = current.plusDays(1);
	            }
	
	            // Create new class
	            ClassModel newClass = new ClassModel(request.getName(), schedule, request.getCapacity());
	            classes.add(newClass);
	            
	            // Create a success response
	            APIResponse response = new APIResponse("Success", null, newClass);
	            return ResponseEntity.ok(response);
	            
	        } 
	        catch (Exception e) {
	            APIResponse response = new APIResponse("Failed", e.getMessage(), request);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	        finally {
				executionTimeLogger.logMethodEnd("StudioController", "createClass");
			}
    }      
        
        
 // Book a class
    @PostMapping("/bookClass")
    public ResponseEntity<APIResponse> bookClass(@Valid @RequestBody BookingRequest request, BindingResult bindingResult) {
    	
    	ExecutionTimeLogger executionTimeLogger = new ExecutionTimeLogger();
    	executionTimeLogger.logMethodStart("StudioController", "bookClass");
        try {
            // Check for validation errors
            if (bindingResult.hasErrors()) {
                StringBuilder validationErrors = new StringBuilder("Validation errors: ");
                bindingResult.getFieldErrors().forEach(error -> 
                    validationErrors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
                );
                // Create a failed response with validation errors
                APIResponse response = new APIResponse("Failed", validationErrors.toString(), request);
                return ResponseEntity.badRequest().body(response);
            }

            // Find the class for the given date
            for (ClassModel booking : classes) {
                if (booking.getName().equalsIgnoreCase(request.getClassName()) &&
                    booking.getSchedule().contains(request.getDate())) {
                    
                    // Successful booking
                    bookings.add(new BookingModel(request.getName(), request.getDate(), request.getClassName()));
                    APIResponse response = new APIResponse("Success", null, request);
                    return ResponseEntity.ok(response);
                }
            }
            
            // Class not found response
            APIResponse response = new APIResponse("Failed", "Class not found for the given date", request);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            
        } catch (Exception e) {
            // Catch any exceptions and return a failed response
            APIResponse response = new APIResponse("Failed", e.getMessage(), request);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        finally {
        	 executionTimeLogger.logMethodEnd("StudioController", "bookClass");
        }
    	
   }
    
    // View all classes
    @GetMapping("/classes")
    public ResponseEntity<List<ClassModel>> getAllClassTypes()
    {
    	return ResponseEntity.ok(classes);
    }
    
    //View all bookings
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingModel>> getAllBookings()
    {
    	return ResponseEntity.ok(bookings);
    }

}
