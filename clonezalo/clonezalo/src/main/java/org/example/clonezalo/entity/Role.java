package org.example.clonezalo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clonezalo.base.BaseEntity;
import org.example.clonezalo.base.enumEntity.RoleEnum;

import java.util.List;

//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Role {
//    @Id
//    @GeneratedValue(strategy =  GenerationType.IDENTITY)
//    private Integer id;
//    @Enumerated(EnumType.STRING)
//    private RoleEnum name;
//
//    @ManyToMany(mappedBy = "roles") // mapBy phải trùng tên field ở User
//    private List<User> users;
//}
