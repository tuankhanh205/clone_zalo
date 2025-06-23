package org.example.clonezalo.repository;

import org.example.clonezalo.entity.FriendR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRRepository extends JpaRepository<FriendR,Integer> {
}
