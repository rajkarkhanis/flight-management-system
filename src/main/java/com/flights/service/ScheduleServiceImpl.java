package com.flights.service;

import com.flights.bean.Schedule;
import com.flights.dao.AirportDao;
import com.flights.dao.ScheduleDao;
import com.flights.dto.ScheduleDto;
import com.flights.exception.RecordNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final AirportDao airportDao;

    private final ScheduleDao scheduleDao;

    @Override
    public Schedule addSchedule(ScheduleDto scheduleDto) {
        Schedule newSchedule = new Schedule(
                airportDao.findByAirportCode(scheduleDto.getSourceAirportCode()),
                airportDao.findByAirportCode(scheduleDto.getDestinationAirportCode()),
                scheduleDto.getArrivalTime(),
                scheduleDto.getDepartureTime()
        );
        scheduleDao.save(newSchedule);
        return newSchedule;
    }

    @Override
    public List<Schedule> viewSchedule() {
       return scheduleDao.findAll();
    }

    @Override
    public Schedule viewSchedule(int scheduleId) throws RecordNotFound {
        if(scheduleDao.findByScheduleId(scheduleId) == null)
            throw new RecordNotFound("Schedule object does not exist");
        return scheduleDao.findById(scheduleId).orElseThrow();
    }

    @Override
    public Schedule modifySchedule(ScheduleDto scheduleDto) throws RecordNotFound {
        Schedule foundSchedule = scheduleDao.findById(scheduleDto.getScheduleId()).orElseThrow(
                () -> new RecordNotFound("Schedule does not exist")
        );

        scheduleDao.findByScheduleId(scheduleDto.getScheduleId());

        foundSchedule.setScheduleId(scheduleDto.getScheduleId());
        foundSchedule.setDestinationAirport(
                airportDao.findByAirportCode(scheduleDto.getDestinationAirportCode())
        );
        foundSchedule.setSourceAirport(
                airportDao.findByAirportCode(scheduleDto.getSourceAirportCode())
        );
        foundSchedule.setDepartureTime(scheduleDto.getDepartureTime());
        foundSchedule.setArrivalTime(scheduleDto.getArrivalTime());

        System.out.println(foundSchedule.toString());
        return scheduleDao.save(foundSchedule);
    }

    @Override
    public void deleteSchedule(int scheduleId) throws RecordNotFound {
        Schedule schedule = scheduleDao.findById(scheduleId).orElseThrow(
                () -> new RecordNotFound("Schedule does not exist")
        );
        scheduleDao.deleteById(schedule.getScheduleId());
    }
}
