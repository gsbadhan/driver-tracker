package com.drivertracker.data.provider;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.drivertracker.messages.MessageConstant;
import com.drivertracker.messages.MessageResources;
import com.drivertracker.persistence.dao.DriverLocationDao;
import com.drivertracker.persistence.entity.DriverLocation;
import com.drivertracker.persistence.entity.DriverSearchResults;
import com.drivertracker.rs.vo.request.DriverLocationInfoRequest;
import com.drivertracker.rs.vo.response.DriverSearchResponse;
import com.drivertracker.rs.vo.response.DriverSearchRow;
import com.drivertracker.rs.vo.response.ErrorStatus;
import com.google.common.base.Optional;

@Component
public class DataProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataProcessor.class);

    private final DriverLocationDao driverLocationDao;
    private final MessageResources messages;

    @Inject
    public DataProcessor(DriverLocationDao driverLocationDao, MessageResources messages) {
        super();
        this.driverLocationDao = checkNotNull(driverLocationDao);
        this.messages = checkNotNull(messages);
    }

    public Optional<ErrorStatus> save(Long driverId, DriverLocationInfoRequest locationInfoRequest) {
        DriverLocation driverLocation = new DriverLocation(driverId, locationInfoRequest.getLatigude(),
                locationInfoRequest.getLongitude(), locationInfoRequest.getAccuracy());
        boolean isSaved = false;
        try {
            isSaved = driverLocationDao.saveOrUpdate(driverLocation);
        } catch (Exception e) {
            LOGGER.error("error occurred while saving/updaing driver location:{}", driverLocation, e);
        }
        if (!isSaved) {
            return Optional.of(new ErrorStatus(messages.getMsg(MessageConstant.APP_ERROR)));
        }
        return Optional.absent();
    }

    public Optional<DriverSearchResponse> searchDriver(Double latitude, Double longitude, Integer radius, Short limit) {
        List<DriverSearchResults> dbRows = null;

        try {
            dbRows = driverLocationDao.search(latitude, longitude, radius, (short) 0, limit);
        } catch (Exception e) {
            LOGGER.error("error occurred while searching drivers,latitude:{},longitude:{},radius:{},limit:{}", latitude,
                    longitude, radius, limit, e);
        }

        if (dbRows == null)
            return Optional.absent();

        List<DriverSearchRow> searchRows = new LinkedList<>();
        for (DriverSearchResults row : dbRows) {
            searchRows.add(new DriverSearchRow(row.getId(), row.getLatigude(), row.getLongitude(),
                    row.getDistance().toString()));
        }

        if (!searchRows.isEmpty())
            return Optional.of(new DriverSearchResponse(searchRows));

        return Optional.absent();
    }

}
