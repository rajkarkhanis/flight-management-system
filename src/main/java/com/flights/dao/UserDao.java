package com.flights.dao;

import com.flights.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUserName(String name);

    User findByUserId(int userId);
}
