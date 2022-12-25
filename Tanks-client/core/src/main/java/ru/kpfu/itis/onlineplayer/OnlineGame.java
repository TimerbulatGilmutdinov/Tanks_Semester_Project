package ru.kpfu.itis.onlineplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import ru.kpfu.itis.Game;
import ru.kpfu.itis.constant.TankInfo;
import ru.kpfu.itis.entity.Player;
import ru.kpfu.itis.onlineplayer.entity.Enemy;

public class OnlineGame extends Game {

    private Player player;
    private Enemy enemy;

    @Override
    public void create() {
        super.create();
        player = new Player(this, new Vector2(100, 100), TankInfo.BLUE);
    }

    @Override
    public void render() {
        super.render();

        float dt = Gdx.graphics.getDeltaTime();
        update(dt);

        batch.begin();
        player.render(batch);
        batch.end();
    }

    @Override
    public void update(float dt) {
        player.update(dt);
        //enemy.update(dt);
    }
}
