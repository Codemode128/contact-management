package com.shivam.cms.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    String status;
    public ResourceNotFoundException(String message, String status) {
        super(message);
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResourceNotFoundEXception{" +
                "status='" + status + '\'' +
                '}';
    }
}
