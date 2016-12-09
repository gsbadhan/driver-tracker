package com.drivertracker.data.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.drivertracker.messages.MessageResources;
import com.drivertracker.persistence.dao.DriverLocationDao;
import com.drivertracker.persistence.entity.DriverSearchResults;
import com.drivertracker.rs.vo.request.DriverLocationInfoRequest;
import com.drivertracker.rs.vo.response.DriverSearchResponse;
import com.drivertracker.rs.vo.response.ErrorStatus;
import com.google.common.base.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DataProcessorTest {
    @Mock
    private DriverLocationDao driverLocationDao;
    @Mock
    private MessageResources messages;

    private DataProcessor dataProcessor;

    @Before
    public void setup() {
        dataProcessor = new DataProcessor(driverLocationDao, messages);
    }

    @Test
    public void testSuccessSave() {
        Long driverId = 108L;
        DriverLocationInfoRequest locationInfoRequest = mock(DriverLocationInfoRequest.class);
        when(driverLocationDao.saveOrUpdate(Mockito.any())).thenReturn(true);
        Optional<ErrorStatus> error = dataProcessor.save(driverId, locationInfoRequest);
        assertNotNull(error);
        assertEquals(error.isPresent(), false);
    }

    @Test
    public void testFailSave() {
        Long driverId = 108L;
        DriverLocationInfoRequest locationInfoRequest = mock(DriverLocationInfoRequest.class);
        when(driverLocationDao.saveOrUpdate(Mockito.any())).thenReturn(false);
        Optional<ErrorStatus> error = dataProcessor.save(driverId, locationInfoRequest);
        assertNotNull(error);
        assertEquals(error.isPresent(), true);
    }

    @Test
    public void testSucessSearchDriver() {
        double latitude = 67.89D;
        double longitude = 97.89D;
        Integer radius = 500;
        short limit = 10;
        List<DriverSearchResults> dbRows = new LinkedList<>();
        dbRows.add(new DriverSearchResults(108L, 69.99D, 97.90D, 100.0D));
        when(driverLocationDao.search(latitude, longitude, radius, (short) 0, limit)).thenReturn(dbRows);
        Optional<DriverSearchResponse> result = dataProcessor.searchDriver(latitude, longitude, radius, limit);
        assertNotNull(result);
        assertEquals(result.isPresent(), true);
    }

    @Test
    public void testFailSearchDriver() {
        double latitude = 67.89D;
        double longitude = 97.89D;
        Integer radius = 500;
        short limit = 10;
        when(driverLocationDao.search(latitude, longitude, radius, (short) 0, limit)).thenReturn(null);
        Optional<DriverSearchResponse> result = dataProcessor.searchDriver(latitude, longitude, radius, limit);
        assertNotNull(result);
        assertEquals(result.isPresent(), false);
    }

}
