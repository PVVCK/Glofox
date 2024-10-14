package com.Glofox.assignment.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {
    private String status;
    private String errorMessage;
    private Object requestDetails;

    public APIResponse(String status, String errorMessage, Object requestDetails) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.requestDetails = requestDetails;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getRequestDetails() {
        return requestDetails;
    }

    public void setRequestDetails(Object requestDetails) {
        this.requestDetails = requestDetails;
    }

	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}
}

