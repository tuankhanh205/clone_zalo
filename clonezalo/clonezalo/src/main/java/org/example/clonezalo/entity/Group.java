package org.example.clonezalo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clonezalo.base.BaseEntity;

import java.util.List;

@Entity
@Table(name = "chat_groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Group extends BaseEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User user;

    @OneToMany(mappedBy = "group")
    private List<GroupMember> members;


    @OneToMany(mappedBy = "group")
    private List<GroupMessages> messages;

}
