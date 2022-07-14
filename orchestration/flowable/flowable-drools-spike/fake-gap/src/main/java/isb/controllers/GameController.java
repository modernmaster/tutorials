package isb.controllers;

import isb.domain.Player;
import isb.facades.PlayerFacade;
import isb.game.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final PlayerFacade playerFacade;
    private final Game game;

    @PostMapping("/game/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void createGame(@PathVariable(value = "id") Long id) {
        Assert.notNull(id, "Id is null");
        Player player = playerFacade.get(id);
        Assert.notNull(player, "Id does not return a player");
        game.play(player);
    }

}
