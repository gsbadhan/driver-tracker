package com.drivertracker.rs.services;

import static com.drivertracker.common.Validation.isValidDriverId;
import static com.drivertracker.common.Validation.isValidLatitude;
import static com.drivertracker.common.Validation.isValidLimit;
import static com.drivertracker.common.Validation.isValidLongitude;
import static com.drivertracker.common.Validation.isValidRadius;
import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.perf4j.aop.Profiled;
import org.springframework.stereotype.Service;

import com.drivertracker.common.AppConfig;
import com.drivertracker.data.provider.DataProcessor;
import com.drivertracker.messages.MessageConstant;
import com.drivertracker.messages.MessageResources;
import com.drivertracker.messages.PerfLabels;
import com.drivertracker.rs.core.JAXRSUtil;
import com.drivertracker.rs.core.UnprocessableStatusType;
import com.drivertracker.rs.vo.request.DriverLocationInfoRequest;
import com.drivertracker.rs.vo.response.DriverSearchResponse;
import com.drivertracker.rs.vo.response.ErrorStatus;
import com.google.common.base.Optional;

@Service("driverInfoServiceImpl")
public class DriverInfoServiceImpl implements DriverInfoService {

    private final DataProcessor dataProcessor;
    private final MessageResources messages;
    private final AppConfig appConfig;

    @Inject
    public DriverInfoServiceImpl(DataProcessor dataProcessor, MessageResources messages, AppConfig appConfig) {
        this.dataProcessor = checkNotNull(dataProcessor);
        this.messages = checkNotNull(messages);
        this.appConfig = checkNotNull(appConfig);
    }

    @Override
    @Profiled(tag = PerfLabels.HTTP_SEARCH_DRIVER, logFailuresSeparately = true)
    public Response search(Double latitude, Double longitude, Integer radius, Short limit) {
        if (!isValidLatitude(latitude)) {
            return JAXRSUtil.response(Status.BAD_REQUEST,
                    new ErrorStatus(messages.getMsg(MessageConstant.LATITUDE_ERROR)));
        }
        if (!isValidLongitude(longitude)) {
            return JAXRSUtil.response(Status.BAD_REQUEST,
                    new ErrorStatus(messages.getMsg(MessageConstant.LONGITUDE_ERROR)));
        }
        if (radius == null) {
            radius = appConfig.getRadius();
        } else if (!isValidRadius(radius)) {
            return JAXRSUtil.response(Status.BAD_REQUEST,
                    new ErrorStatus(messages.getMsg(MessageConstant.RADIUS_ERROR)));
        }
        if (limit == null) {
            limit = appConfig.getPageLimit();
        } else if (!isValidLimit(limit)) {
            return JAXRSUtil.response(Status.BAD_REQUEST,
                    new ErrorStatus(messages.getMsg(MessageConstant.LIMIT_ERROR)));
        }

        Optional<DriverSearchResponse> data = dataProcessor.searchDriver(latitude, longitude, radius, limit);

        return JAXRSUtil.returnIffound(data);
    }

    @Override
    @Profiled(tag = PerfLabels.HTTP_SAVE_DRIVER_LOCATION, logFailuresSeparately = true)
    public Response update(Long driverId, DriverLocationInfoRequest locationInfoRequest) {
        if (!isValidDriverId(driverId)) {
            return JAXRSUtil.responseWithStatus(Status.NOT_FOUND);
        }

        if (!isValidLatitude(locationInfoRequest.getLatigude())) {
            return JAXRSUtil.response(UnprocessableStatusType.UN_PROCESSABLE,
                    new ErrorStatus(messages.getMsg(MessageConstant.LATITUDE_ERROR)));
        }

        if (!isValidLongitude(locationInfoRequest.getLongitude())) {
            return JAXRSUtil.response(UnprocessableStatusType.UN_PROCESSABLE,
                    new ErrorStatus(messages.getMsg(MessageConstant.LATITUDE_ERROR)));
        }

        Optional<ErrorStatus> status = dataProcessor.save(driverId, locationInfoRequest);

        if (status.isPresent()) {
            return JAXRSUtil.response(UnprocessableStatusType.UN_PROCESSABLE, status);
        }
        return JAXRSUtil.responseWithStatus(Status.OK);
    }

    @Context
    public void setHttpHeaders(final HttpHeaders httpHeaders) {
    }

}
