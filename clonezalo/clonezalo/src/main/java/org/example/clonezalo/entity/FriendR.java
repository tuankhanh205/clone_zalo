package org.example.clonezalo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clonezalo.base.enumEntity.FriendRStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "friend_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FriendR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private FriendRStatus status;

    @Column(name = "sent_at ")
    private LocalDateTime sentAt;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User userSender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User userReceiver;


}
