package ru.kpfu.itis.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.kpfu.itis.Game;
import ru.kpfu.itis.WindowConfig;
import ru.kpfu.itis.api.Renderable;
import ru.kpfu.itis.api.Updatable;
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

    public Tank(Game game, Vector2 position) {
        this.game = game;
        this.position = position;
    }

    @Override
    public void renderWithScale(SpriteBatch batch, Texture texture, float x, float y) {
        batch.draw(texture, x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void renderWithScale(SpriteBatch batch, Texture texture, float x, float y, float angle) {
        batch.draw(texture, x, y,
                (float) texture.getWidth() / 2,
                (float) texture.getHeight() / 2,
                texture.getWidth(),
                texture.getHeight(),
                1, 1,
                turretAngle,
                0, 0,
                texture.getWidth(),
                texture.getHeight(),
                false, false);
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
