package isb.repositories;

import isb.domain.Player;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerRepository extends PagingAndSortingRepository <Player, Long> {
}
