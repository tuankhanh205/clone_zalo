package org.example.clonezalo.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class GroupMemberId implements Serializable {
    private Integer groupId;
    private Integer userId;
}
