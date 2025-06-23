//package org.example.clonezalo.repository;
//
//import org.example.clonezalo.entity.Role;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface RoleRepository extends JpaRepository<Role, Integer> {
//    @Query("select r from Role r where r.name=:name")
//    List<Role> findByName(@Param("name") String name);
//}
