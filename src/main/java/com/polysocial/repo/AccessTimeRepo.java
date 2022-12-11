package com.polysocial.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.AccessTime;

@Repository
public interface AccessTimeRepo extends JpaRepository<AccessTime, Long> {
    
    @Query("SELECT o FROM AccessTime o WHERE year(o.createdDate) BETWEEN ?1 and ?2 ")
    List<AccessTime> findByYear(int year, int yearLast);

    @Query("SELECT o FROM AccessTime o WHERE year(o.createdDate) = ?1 AND month(o.createdDate) = ?2 ")
    List<AccessTime> findByYearAndMonth(int year, int month);

    @Query("SELECT o FROM AccessTime o WHERE year(o.createdDate) = ?1 AND month(o.createdDate) BETWEEN ?2 AND ?3")
    List<AccessTime> findByYearAndBetweenMonth(int year, int monthFirst, int monthLast);

}
