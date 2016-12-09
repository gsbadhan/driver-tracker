package com.drivertracker.rs.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.drivertracker.common.AppConfig;
import com.drivertracker.data.provider.DataProcessor;
import com.drivertracker.messages.MessageResources;
import com.drivertracker.rs.core.UnprocessableStatusType;
import com.drivertracker.rs.vo.request.DriverLocationInfoRequest;
import com.drivertracker.rs.vo.response.DriverSearchResponse;
import com.google.common.base.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DriverInfoServiceImplTest {

    @Mock
    private DataProcessor dataProcessor;
    @Mock
    private MessageResources messages;
    @Mock
    private AppConfig appConfig;

    private DriverInfoService driverInfoService;

    @Before
    public void setup() {
        driverInfoService = new DriverInfoServiceImpl(dataProcessor, messages, appConfig);
    }

    @Test
    public void testInvalidLatitudeOnSearch() {
        double latitude = 99.89D;
        double longitude = 97.89D;
        Integer radius = 500;
        short limit = 10;
        DriverSearchResponse results = mock(DriverSearchResponse.class);
        when(dataProcessor.searchDriver(latitude, longitude, radius, limit)).thenReturn(Optional.of(results));
        Response response = driverInfoService.search(latitude, longitude, radius, limit);
        assertNotNull(response);
        assertEquals(response.getStatus(), Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void testInvalidLongitudeOnSearch() {
        double latitude = 68.89D;
        double longitude = 600.89D;
        Integer radius = 500;
        short limit = 10;
        DriverSearchResponse results = mock(DriverSearchResponse.class);
        when(dataProcessor.searchDriver(latitude, longitude, radius, limit)).thenReturn(Optional.of(results));
        Response response = driverInfoService.search(latitude, longitude, radius, limit);
        assertNotNull(response);
        assertEquals(response.getStatus(), Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void testInvalidRadiusOnSearch() {
        double latitude = 68.89D;
        double longitude = 89.89D;
        Integer radius = -500;
        short limit = 10;
        DriverSearchResponse results = mock(DriverSearchResponse.class);
        when(dataProcessor.searchDriver(latitude, longitude, radius, limit)).thenReturn(Optional.of(results));
        Response response = driverInfoService.search(latitude, longitude, radius, limit);
        assertNotNull(response);
        assertEquals(response.getStatus(), Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void testInvalidPageLimitOnSearch() {
        double latitude = 68.89D;
        double longitude = 89.89D;
        Integer radius = 500;
        short limit = -10;
        DriverSearchResponse results = mock(DriverSearchResponse.class);
        when(dataProcessor.searchDriver(latitude, longitude, radius, limit)).thenReturn(Optional.of(results));
        Response response = driverInfoService.search(latitude, longitude, radius, limit);
        assertNotNull(response);
        assertEquals(response.getStatus(), Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void testSearch() {
        double latitude = 67.89D;
        double longitude = 97.89D;
        Integer radius = 500;
        short limit = 10;
        DriverSearchResponse results = mock(DriverSearchResponse.class);
        when(dataProcessor.searchDriver(latitude, longitude, radius, limit)).thenReturn(Optional.of(results));
        Response response = driverInfoService.search(latitude, longitude, radius, limit);
        assertNotNull(response);
        assertEquals(response.getStatus(), Status.OK.getStatusCode());
    }

    @Test
    public void testUpdate() {
        Long driverId = 108L;
        DriverLocationInfoRequest locationInfoRequest = new DriverLocationInfoRequest();
        locationInfoRequest.setLatigude(67.89D);
        locationInfoRequest.setLongitude(97.89D);
        locationInfoRequest.setAccuracy(0.7F);
        when(dataProcessor.save(driverId, locationInfoRequest)).thenReturn(Optional.absent());
        Response response = driverInfoService.update(driverId, locationInfoRequest);
        assertNotNull(response);
        assertEquals(response.getStatus(), Status.OK.getStatusCode());
    }

    @Test
    public void testInvalidDrverIdOnUpdate() {
        Long driverId = 5008900L;
        DriverLocationInfoRequest locationInfoRequest = new DriverLocationInfoRequest();
        locationInfoRequest.setLatigude(67.89D);
        locationInfoRequest.setLongitude(97.89D);
        locationInfoRequest.setAccuracy(0.7F);
        when(dataProcessor.save(driverId, locationInfoRequest)).thenReturn(Optional.absent());
        Response response = driverInfoService.update(driverId, locationInfoRequest);
        assertNotNull(response);
        assertEquals(response.getStatus(), Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void testInvalidLatitudeOnUpdate() {
        Long driverId = 108L;
        DriverLocationInfoRequest locationInfoRequest = new DriverLocationInfoRequest();
        locationInfoRequest.setLatigude(99.89D);
        locationInfoRequest.setLongitude(97.89D);
        locationInfoRequest.setAccuracy(0.7F);
        when(dataProcessor.save(driverId, locationInfoRequest)).thenReturn(Optional.absent());
        Response response = driverInfoService.update(driverId, locationInfoRequest);
        assertNotNull(response);
        assertEquals(response.getStatus(), UnprocessableStatusType.STATUS_CODE);
    }

    @Test
    public void testInvalidLongitudeOnUpdate() {
        Long driverId = 108L;
        DriverLocationInfoRequest locationInfoRequest = new DriverLocationInfoRequest();
        locationInfoRequest.setLatigude(68.89D);
        locationInfoRequest.setLongitude(300.89D);
        locationInfoRequest.setAccuracy(0.7F);
        when(dataProcessor.save(driverId, locationInfoRequest)).thenReturn(Optional.absent());
        Response response = driverInfoService.update(driverId, locationInfoRequest);
        assertNotNull(response);
        assertEquals(response.getStatus(), UnprocessableStatusType.STATUS_CODE);
    }

}
