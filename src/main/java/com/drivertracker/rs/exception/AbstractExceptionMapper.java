package com.drivertracker.rs.exception;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractExceptionMapper<E extends Exception> implements ExceptionMapper<E> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractExceptionMapper.class);

    @Override
    public Response toResponse(final E exception) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Error processing HTTP request: " + exception.getMessage(), exception);
        }
        return Response.status(getStatus()).entity(exception.getMessage())
                .header(HttpHeaders.CONTENT_TYPE, "application/text").build();
    }

    protected abstract Response.Status getStatus();
}
