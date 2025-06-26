package org.example.clonezalo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clonezalo.base.BaseEntity;
import org.example.clonezalo.base.enumEntity.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private String phone;
    private String image;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

//    @ManyToMany
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private List<Role> roles;


    @ManyToMany
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends;

    //chặn
    @ManyToMany
    @JoinTable(name = "blocked_users ",
    joinColumns = @JoinColumn(name = "blocker_id"),
    inverseJoinColumns = @JoinColumn(name = "blocked_id"))
    private List<User> blockedUsers;

    @OneToMany(mappedBy = "user1")
    private List<Friend> friend1;

    @OneToMany(mappedBy = "user2")
    private List<Friend> friend2;


    @OneToMany(mappedBy = "sender")
    private List<Messages> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<Messages> receivedMessages;

    @OneToMany(mappedBy = "user")
    private List<Group> groups;

    @OneToMany(mappedBy = "user")
    private List<GroupMember> groupMembers;

    @OneToMany(mappedBy = "sender")
    private List<GroupMessages> sentGroupMessages;
    @OneToMany(mappedBy = "userSender")
    private List<FriendR> Sender;

    @OneToMany(mappedBy = "userReceiver")
    private List<FriendR> Receiver;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role.name());
    }

    @Override
    public String getUsername() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
