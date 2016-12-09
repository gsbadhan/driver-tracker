package com.drivertracker.rs.services;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.drivertracker.rs.vo.request.DriverLocationInfoRequest;

@Path("/drivers")
@WebService(name = "driverService")
public interface DriverInfoService {

	/**
	 * search drivers with in give radius.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @param limit
	 * @return
	 */
    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/")
    Response search(@QueryParam(value = "latitude") Double latitude, @QueryParam(value = "longitude") Double longitude,
                    @QueryParam(value = "radius") Integer radius, @QueryParam(value = "limit") Short limit);

    
    /**
     * save/update driver's location.
     *  
     * @param driverId
     * @param locationInfoRequest
     * @return
     */
    @PUT
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/{id}/location")
    Response update(@PathParam(value = "id") Long driverId, DriverLocationInfoRequest locationInfoRequest);

}
