package com.flights.service;

import com.flights.bean.Airport;
import com.flights.dao.AirportDao;
import com.flights.dto.AirportDto;
import com.flights.exception.RecordNotFound;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AirportServiceImplementationTest {

    @Autowired
    AirportService airportservice;

    @MockBean
    AirportDao AirportDao;
    @Test
    void testaddAirport()   {
        Airport ap= new Airport();
        ap.setAirportId(1);
        ap.setAirportCode("BOM");
        ap.setAirportName("Maharashtra");
        ap.setAirportLocation("Chhatrapati Shivaji International Airport");

        AirportDto ad= new AirportDto();
        ad.setAirportCode("BOM");
        ad.setAirportName("Maharashtra");
        ad.setAirportLocation("Chhatrapati Shivaji International Airport");

        assertEquals(ap.getAirportCode(),ad.getAirportCode());
        assertEquals(ap.getAirportName(),ad.getAirportName());
        assertEquals(ap.getAirportLocation(),ad.getAirportLocation());
        assertNotNull(ap.getAirportId());
    }
    @Test
    void testviewallAirport() {
        Airport a1 = new Airport();

        a1.setAirportCode("BOM");
        a1.setAirportLocation("Maharashtra");
        a1.setAirportName("Chhatrapati Shivaji International Airport");

        Airport a2 = new Airport();
        a2.setAirportCode("DEL");
        a2.setAirportLocation("Delhi");
        a2.setAirportName("Indira Gandhi International Airport");

        List<Airport> airportList = new ArrayList<>();
        airportList.add(a1);
        airportList.add(a2);

        Mockito.when(AirportDao.findAll()).thenReturn(airportList);
        assertThat(airportservice.viewAirport()).isEqualTo(airportList);

    }
    @Test
    void testViewAirport() throws RecordNotFound {
        Airport a1 = new Airport();
        a1.setAirportCode("CCU");
        a1.setAirportLocation("Kolkata");
        a1.setAirportName("Netaji Subhash Chandra Bose International Aiport");

        Mockito.when(AirportDao.findByAirportCode("CCU")).thenReturn(a1);
        assertThat(airportservice.viewAirport("CCU")).isEqualTo(a1);
    }

    @Test
    void testViewAirportByCode() throws RecordNotFound   {
        Airport a1 = new Airport();
        a1.setAirportCode("CCU");
        a1.setAirportLocation("Kolkata");
        a1.setAirportName("Netaji Subhash Chandra Bose International Aiport");
        Mockito.when(AirportDao.findByAirportCode("CCU")).thenReturn(a1);
        assertThrows(RecordNotFound.class,()->{
            airportservice.viewAirport("ABC");
                }
        );
    }
}