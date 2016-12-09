package com.drivertracker.rs.core;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import com.google.common.base.Optional;

public class JAXRSUtil {

    public static <T> Response returnIffound(final Optional<T> object) {
        return returnIffound(object.orNull());
    }

    public static <T> Response returnIffound(final T object) {
        if (object == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok().entity(object).build();
    }

    public static <T> Response response(Status status, final T object) {
        return Response.status(status).entity(object).build();
    }

    public static <T> Response response(StatusType status, final T object) {
        return Response.status(status).entity(object).build();
    }

    public static <T> Response responseWithStatus(Status status) {
        return Response.status(status).build();
    }

}
