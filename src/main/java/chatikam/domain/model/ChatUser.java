package chatikam.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatUser {
    @Id
    @GeneratedValue
    @NotNull
    private Long chatUserId;

    @Size(min = 1, max = 255)
    @NotNull
    private String chatUserNickName;

    @Size(min = 1, max = 255)
    @NotNull
    private String chatUserDisplayName;
}
