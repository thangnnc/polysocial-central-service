package com.polysocial.repo;

import com.polysocial.entity.Users;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByEmailAndIsActive(String email, boolean isActive);

    Users findByUserIdAndIsActive(Long userId, boolean isActive);

    Users findByUserId(Long userId);

    @Query("SELECT o FROM Users o WHERE o.email Like %?1%")
    List<Users> findByEmail(String email);

    @Query("SELECT o FROM Users o WHERE o.email =?1")
    Users findOneByEmail(String email);

    @Query("SELECT o FROM Users o WHERE o.fullName Like %?1%")
    List<Users> findByFullName(String fullName);

    @Query("SELECT o FROM Users o WHERE o.studentCode = ?1")
    Users findByStudentCode(String studentCode);

    @Query("SELECT o FROM Users o WHERE o.fullName Like %?1% OR o.email Like %?1% or o.studentCode Like %?1%")
    List<Users> searchByKeyWord(String keyword);

    @Query("SELECT o FROM Users o WHERE o.role.roleId not like 2")
    List<Users> findUserByRoleNotStudent();


}
