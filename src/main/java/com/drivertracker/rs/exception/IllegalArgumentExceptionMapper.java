package com.drivertracker.rs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


@Provider
public class IllegalArgumentExceptionMapper extends AbstractExceptionMapper<IllegalArgumentException> {

    @Override
    public Response.Status getStatus() {
        return Response.Status.BAD_REQUEST;
    }
}
