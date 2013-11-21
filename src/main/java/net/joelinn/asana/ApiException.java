package net.joelinn.asana;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Joe Linn
 * 11/17/13
 */
public class ApiException extends RuntimeException{
    protected ClientResponse.Status status;
    protected String message;

    public ApiException(ClientResponse.Status status, String message){
        super(message);
        this.status = status;
        this.message = message;
    }

    public ClientResponse.Status getStatus(){
        return status;
    }

    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return String.format("%s: %s %s", s, status.getStatusCode(), message);
    }
}
