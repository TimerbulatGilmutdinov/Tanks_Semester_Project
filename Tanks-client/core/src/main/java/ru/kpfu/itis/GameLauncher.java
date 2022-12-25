package ru.kpfu.itis;

import com.badlogic.gdx.ApplicationAdapter;
import ru.kpfu.itis.onlineplayer.OnlineGame;

public class GameLauncher extends ApplicationAdapter {
    private final Game game;

    public GameLauncher() {
        this.game = new OnlineGame();
    }

    @Override
    public void create() {
        game.create();
    }

    @Override
    public void render() {
        game.render();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void pause() {
        game.pause();
    }

    @Override
    public void resume() {
        game.resume();
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
