package org.example.clonezalo.repository;

import org.example.clonezalo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select count(u) from User u where u.phone=:phone")
     int countByPhone(@Param("phone") String phone);

    @Query("select u from User u where u.phone=:phone")
    Optional<User> findByPhone(@Param("phone") String phone);
}
