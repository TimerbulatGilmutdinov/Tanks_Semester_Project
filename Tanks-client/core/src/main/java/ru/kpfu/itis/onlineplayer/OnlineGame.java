package ru.kpfu.itis.onlineplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import protocol.Response;
import ru.kpfu.itis.Game;
import ru.kpfu.itis.ResponseParser;
import ru.kpfu.itis.ResponseReceiver;
import ru.kpfu.itis.constant.TankInfo;
import ru.kpfu.itis.emitter.BulletEmitter;
import ru.kpfu.itis.entity.Player;
import ru.kpfu.itis.onlineplayer.entity.Enemy;

import java.io.IOException;
import java.net.Socket;

public class OnlineGame extends Game {

    private Player player;
    private Enemy enemy;
    private ResponseReceiver responseReceiver;
    private Socket socket;
    private Vector2 enemyStartPosition;

    public OnlineGame(Vector2 enemyStartPosition, TankInfo tankInfo, Socket socket, int playerCount) {
        this.enemyStartPosition = enemyStartPosition;
        this.socket = socket;
    }

    @Override
    public void create() {
        super.create();
        player = new Player(this, new Vector2(100, 100), TankInfo.BLUE);
        enemy = new Enemy(this, enemyStartPosition);
        bulletEmitter = new BulletEmitter();
        try {
            responseReceiver = new ResponseReceiver(socket);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void render() {
        super.render();

        float dt = Gdx.graphics.getDeltaTime();
        update(dt);

        batch.begin();
        player.render(batch);
        bulletEmitter.render(batch);
        batch.end();
    }

    @Override
    public void update(float dt) {
        player.update(dt);
        bulletEmitter.update(dt);

        //enemy.update();
    }

    private void sendEnemyData() {

    }
}
