package com.flights.dao;

import com.flights.bean.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface ScheduleDao extends JpaRepository<Schedule,Integer> {
    Schedule findByScheduleId(int scheduleId);
}
