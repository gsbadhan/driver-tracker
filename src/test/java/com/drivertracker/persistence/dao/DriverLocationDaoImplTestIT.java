package com.drivertracker.persistence.dao;

import java.util.LinkedList;
import static org.junit.Assert.*;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.drivertracker.persistence.entity.DriverLocation;
import com.drivertracker.persistence.entity.DriverSearchResults;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
public class DriverLocationDaoImplTestIT {

	@Inject
	private DriverLocationDaoImpl driverLocationDaoImpl;
	private List<DriverLocation> testData = new LinkedList<>();

	@Before
	public void setup() {
		testData.add(new DriverLocation(101L, 28.57294, 77.32601, 0.7F));
		testData.add(new DriverLocation(110L, 28.57115, 77.32580, 0.7F));
		testData.add(new DriverLocation(111L, 28.57121, 77.32524, 0.7F));
		testData.add(new DriverLocation(131L, 48.87363, 2.35491, 0.7F));
		testData.add(new DriverLocation(141L, 48.87454, 2.35590, 0.7F));
		testData.add(new DriverLocation(151L, 48.87530, 2.35646, 0.7F));
		testData.add(new DriverLocation(191L, 48.87640, 2.35676, 0.7F));
	}

	@Test
	public void saveOrUpdate() {
		for (DriverLocation location : testData) {
			boolean st = driverLocationDaoImpl.saveOrUpdate(location);
			assertTrue(st);
		}
	}

	@Test
	public void search() {
		List<DriverSearchResults> list = driverLocationDaoImpl.search(28.57294, 77.32601, 10000, (short) 0, (short) 10);
		assertNotNull(list);
		assertTrue(list.size() > 0);
		System.out.println("search results:" + list);
	}
}
