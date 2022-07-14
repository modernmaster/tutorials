package isb.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(targetEntity=RoundEvent.class, fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    private List<RoundEvent> roundEvent;
}
