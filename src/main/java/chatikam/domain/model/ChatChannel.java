package chatikam.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatChannel {
    @Id
    @Size(min = 1, max = 255)
    @NotNull
    private String chatChannelName;
    
    private boolean visible;
    
    @Column( nullable = true)
    @Size(min = 1, max = 255)
    @JsonIgnore
    private String chatChannelHashedPassword;
}
