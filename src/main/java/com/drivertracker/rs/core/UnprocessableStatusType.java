package com.drivertracker.rs.core;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

public class UnprocessableStatusType implements StatusType {
    public static final int STATUS_CODE = 422;
    public static Response.StatusType UN_PROCESSABLE = new UnprocessableStatusType(422, "Unprocessable request");

    private final Family family;
    private final int statusCode;
    private final String reasonPhrase;

    public UnprocessableStatusType(final int statusCode, final String reasonPhrase) {
        super();

        this.family = Family.CLIENT_ERROR;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public Family getFamily() {
        return family;
    }

    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

}
