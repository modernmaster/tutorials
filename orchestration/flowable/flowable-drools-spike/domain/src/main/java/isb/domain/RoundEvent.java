package isb.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class RoundEvent {
    @Id
    @GeneratedValue
    private Long id;
    private RoundEventType roundEventType;
    private Double value;
}
