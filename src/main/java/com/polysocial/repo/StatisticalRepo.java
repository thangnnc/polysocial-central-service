package com.polysocial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.polysocial.entity.Users;

@Repository
public interface StatisticalRepo extends JpaRepository<Users, Long>{
    
    //select count * users by role.roleid=2
    @Query(value = "SELECT COUNT(o) FROM Users o WHERE o.role.roleId = 2")
    Long countStudent();

    @Query(value = "SELECT COUNT(o) FROM Groups o WHERE  o.totalMember not like null and o.status = 1")
    Long countGroups();

    @Query(value = "SELECT COUNT(o) FROM Users o WHERE o.role.roleId = 1")
    Long countTeacher();

}
