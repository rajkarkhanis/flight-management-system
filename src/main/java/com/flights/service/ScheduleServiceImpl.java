package com.flights.service;

import com.flights.bean.Booking;
import com.flights.bean.Schedule;
import com.flights.dao.AirportDao;
import com.flights.dao.BookingDao;
import com.flights.dao.ScheduleDao;
import com.flights.dto.ScheduleDto;
import com.flights.exception.RecordNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    AirportDao airportDao;

    @Autowired
    ScheduleDao scheduleDao;
    @Override
    public Schedule addSchedule(ScheduleDto scheduleDto) {
        Schedule newSchedule = new Schedule(
                airportDao.findByAirportCode(scheduleDto.getSourceAirportCode()),
                airportDao.findByAirportCode(scheduleDto.getDestinationAirportCode()),
                scheduleDto.getArrivalTime(),
                scheduleDto.getDepartureTime()
        );
        return newSchedule;
    }

    @Override
    public List<Schedule> viewSchedule() {
        List<Schedule> list = scheduleDao.findAll();
        return list;
    }

    @Override
    public Schedule viewSchedule(int scheduleId) throws RecordNotFound {
        if(scheduleDao.findByScheduleId(scheduleId) == null)
            throw new RecordNotFound("Booking object does not exist");

        Schedule schedule = scheduleDao.findById(scheduleId).orElseThrow();
        return schedule;
    }
}
