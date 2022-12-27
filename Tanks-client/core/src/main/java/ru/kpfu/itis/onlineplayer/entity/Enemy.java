package ru.kpfu.itis.onlineplayer.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.kpfu.itis.Game;
import ru.kpfu.itis.api.Renderable;
import ru.kpfu.itis.entity.Tank;

public class Enemy implements Renderable {
    private Vector2 position;
    private TextureRegion textureRegion;
    private TextureRegion turretTextureRegion;
    private TextureRegion hpTextureRegion;
    private float angle;
    private float turretAngle;
    private Game game;
    private static float BULLET_DAMAGE = 10;

    public Enemy(Game game, Vector2 position) {
        this.game = game;
        this.position = position;
    }

    public void update(Vector2 position, float angle, float textureAngle, boolean attack) {
        this.position = position;
        this.angle = angle;
        this.turretAngle = textureAngle;
        if (attack) {
            float angleRad = (float) Math.toRadians(turretAngle);
            game.getBulletEmitter().activateBullet(this, position, angleRad, BULLET_DAMAGE);
        }
    }

}
