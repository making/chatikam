package chatikam.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatUser {
    @Id
    @GeneratedValue
    private Long chatUserId;

    private String chatUserNickName;
    
    private String chatUserDisplayName;
}
