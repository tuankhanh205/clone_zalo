package org.example.clonezalo.repository;

import org.example.clonezalo.entity.Friend;
import org.example.clonezalo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
    // danh sách bạn bè
    @Query("select f from Friend f where f.user1=:user1")
    List<Friend> findByUser1(@Param("user1") User user1);
}