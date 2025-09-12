package org.example.clonezalo.repository;

import org.example.clonezalo.entity.Messages;
import org.example.clonezalo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Integer> {
    @Query("""
        select m from Messages m
        where (m.sender = :currentUser and m.receiver.id = :receiverId)
           or (m.sender.id = :receiverId and m.receiver = :currentUser)
        order by m.createdAt asc
    """)
    List<Messages> findConversation(
            @Param("currentUser") User currentUser,
            @Param("receiverId") Integer receiverId);


}
