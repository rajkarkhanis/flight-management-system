package com.flights.service;


import com.flights.bean.Schedule;
import com.flights.dto.ScheduleDto;
import com.flights.exception.RecordNotFound;

import java.util.List;

public interface ScheduleService {

    Schedule addSchedule(ScheduleDto scheduleDto);

    List<Schedule> viewSchedule();

    Schedule viewSchedule(int scheduleId) throws RecordNotFound;

    Schedule modifySchedule(ScheduleDto scheduleDto) throws RecordNotFound;

    void deleteSchedule(int scheduleId) throws RecordNotFound;
}
