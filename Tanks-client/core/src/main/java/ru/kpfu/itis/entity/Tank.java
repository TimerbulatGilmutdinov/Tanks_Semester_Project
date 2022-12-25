package ru.kpfu.itis.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.kpfu.itis.Game;
import ru.kpfu.itis.WindowConfig;
import ru.kpfu.itis.api.Renderable;
import ru.kpfu.itis.api.Updatable;
import ru.kpfu.itis.constant.Direction;
import ru.kpfu.itis.util.AngleChanger;

public abstract class Tank implements Updatable, Renderable {

    static final int ROTATION_SPEED = 1_000;
    static final int FIRE_KEY = Input.Buttons.LEFT;
    static final float FIRE_PERIOD = 1;

    Game game;
    Vector2 position;
    int hp;
    float angle;
    float turretAngle;
    float fireTimer;
    Color hpTextureColor;
    Rectangle textureRectangle;
    Direction direction;

    public Tank(Game game, Vector2 position) {
        this.game = game;
        this.position = position;
    }

    @Override
    public void render(SpriteBatch batch, TextureRegion textureRegion, float x, float y, float angle) {
        batch.draw(textureRegion,
                x - (float) textureRegion.getRegionWidth() / 2,
                y - (float) textureRegion.getRegionHeight() / 2,
                (float) textureRegion.getRegionWidth() / 2,
                (float) textureRegion.getRegionHeight() / 2,
                textureRegion.getRegionWidth(),
                textureRegion.getRegionHeight(),
                1, 1, angle);
    }

    @Override
    public void update(float dt) {
        fireTimer += dt;
    }

    void rotateTurretAngle(float x, float y, float dt) {
        float angleTo = AngleChanger.getAngle(position.x, position.y, x, y);
        turretAngle = AngleChanger.makeRotation(turretAngle, angleTo, ROTATION_SPEED, dt);
        turretAngle = AngleChanger.normalize(turretAngle);
    }

    void fire(Tank owner, float bulletDamage) {
        if (fireTimer >= FIRE_PERIOD) {
            float angle = (float)Math.toRadians(turretAngle);
            game.getBulletEmitter().activateBullet(owner, position, angle, bulletDamage);
        }
    }
}
