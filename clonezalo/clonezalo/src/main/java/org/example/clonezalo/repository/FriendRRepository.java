package org.example.clonezalo.repository;

import jdk.jshell.Snippet;
import org.example.clonezalo.base.enumEntity.FriendRStatus;
import org.example.clonezalo.entity.FriendR;
import org.example.clonezalo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRRepository extends JpaRepository<FriendR,Integer> {
    @Query("SELECT f FROM FriendR f WHERE f.userSender = :sender AND f.userReceiver = :receiver")
    Optional<FriendR> findByUserSenderAndUserReceiver(@Param("sender") User sender, @Param("receiver") User receiver);

    @Query("select f from FriendR  f where f.userReceiver=:userReceiver and f.status=:stattus")
    List<FriendR> findByUserReceiver(@Param("userReceiver") User receiver, @Param("stattus")FriendRStatus friendRStatus);

}
