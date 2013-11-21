package net.joelinn.asana;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Joe Linn
 * 11/17/13
 */
public class ForbiddenException extends ApiException {
    public ForbiddenException(String message) {
        super(ClientResponse.Status.FORBIDDEN, message);
    }
}
