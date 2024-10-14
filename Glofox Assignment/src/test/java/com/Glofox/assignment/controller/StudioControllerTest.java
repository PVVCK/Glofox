package com.Glofox.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.Glofox.assignment.dto.BookingRequest;
import com.Glofox.assignment.dto.ClassRequest;
import com.Glofox.assignment.model.BookingModel;
import com.Glofox.assignment.model.ClassModel;
import com.Glofox.assignment.response.APIResponse;

class StudioControllerTest {

    @InjectMocks
    private StudioController studioController;

    @Mock
    private BindingResult bindingResult;

    private List<ClassModel> classes;
    private List<BookingModel> bookings;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        classes = new ArrayList<>();
        bookings = new ArrayList<>();
        studioController = new StudioController(classes, bookings);
    }

    @Test
    void testCreateClassSuccess() {
        ClassRequest request = new ClassRequest();
        request.setName("Yoga Class");
        request.setStartDate(LocalDate.of(2023, 10, 1));
        request.setEndDate(LocalDate.of(2023, 10, 5));
        request.setCapacity(20);

        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<APIResponse> response = studioController.createClass(request, bindingResult);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody().getStatus());
        assertNotNull(response.getBody().getRequestDetails());
    }

    @Test
    void testCreateClassValidationError() {
        ClassRequest request = new ClassRequest();
        request.setStartDate(LocalDate.of(2023, 10, 6));
        request.setEndDate(LocalDate.of(2023, 10, 5));

        when(bindingResult.hasErrors()).thenReturn(false);
//        when(bindingResult.getFieldErrors()).thenReturn(List.of());

        ResponseEntity<APIResponse> response = studioController.createClass(request, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed", response.getBody().getStatus());
        assertTrue(response.getBody().getErrorMessage().contains("Start date cannot be after end date"));
    }

    @Test
    void testBookClassSuccess() {
        ClassModel classModel = new ClassModel("Yoga Class", List.of(LocalDate.of(2023, 10, 1)), 20);
        classes.add(classModel); // Adds to the shared list

        BookingRequest request = new BookingRequest();
        request.setName("John Doe");
        request.setDate(LocalDate.of(2023, 10, 1));
        request.setClassName("Yoga Class");

        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<APIResponse> response = studioController.bookClass(request, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody().getStatus());
    }

    @Test
    void testBookClassNotFound() {
        BookingRequest request = new BookingRequest();
        request.setName("John Doe");
        request.setDate(LocalDate.of(2023, 10, 1));
        request.setClassName("Nonexistent Class");

        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<APIResponse> response = studioController.bookClass(request, bindingResult);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed", response.getBody().getStatus());
        assertTrue(response.getBody().getErrorMessage().contains("Class not found for the given date"));
    }

    @Test
    void testGetAllClassTypes() {
        ClassModel classModel = new ClassModel("Yoga Class", List.of(LocalDate.of(2023, 10, 1)), 20);
        classes.add(classModel);

        ResponseEntity<List<ClassModel>> response = studioController.getAllClassTypes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetAllBookings() {
        BookingModel bookingModel = new BookingModel("John Doe", LocalDate.of(2023, 10, 1), "Yoga Class");
        bookings.add(bookingModel);

        ResponseEntity<List<BookingModel>> response = studioController.getAllBookings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
}
