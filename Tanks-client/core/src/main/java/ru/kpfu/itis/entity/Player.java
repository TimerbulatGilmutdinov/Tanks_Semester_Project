package ru.kpfu.itis.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.kpfu.itis.Game;
import ru.kpfu.itis.WindowConfig;
import ru.kpfu.itis.constant.Direction;
import ru.kpfu.itis.constant.GameAtlas;
import ru.kpfu.itis.constant.TankInfo;

public class Player extends Tank {

    private static final int SPEED = 500;
    private static final int MAX_HP = 100;
    private static final float BULLET_DAMAGE = 10;

    private final TextureRegion textureRegion;
    private final TextureRegion turretTextureRegion;


    public Player(Game game, Vector2 position, TankInfo tankInfo) {
        super(game, position, MAX_HP);
        textureRegion = GameAtlas.TEXTURE_ATLAS.findRegion(tankInfo.getRegionName());
        turretTextureRegion = GameAtlas.TEXTURE_ATLAS.findRegion(tankInfo.getTurretRegionName());
        textureRectangle = new Rectangle(position.x, position.y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        hp = MAX_HP;
    }

    @Override
    public void render(SpriteBatch batch) {
        float halfTextureWidth = (float) textureRegion.getRegionWidth() / 2;
        float halfTextureHeight = (float) textureRegion.getRegionHeight() / 2;
        render(
                batch,
                textureRegion,
                position.x - halfTextureWidth,
                position.y - halfTextureHeight,
                angle
        );
        render(
                batch,
                turretTextureRegion,
                position.x - halfTextureWidth,
                position.y - halfTextureHeight,
                turretAngle
        );
        renderHp(batch, textureRegion);
    }

    @Override
    public void update(float dt) {
        checkForScreenExit();
        textureRectangle.setPosition(
                position.x - (float) textureRegion.getRegionWidth() / 2,
                position.y - (float) textureRegion.getRegionHeight() / 2
        );
        float mouseX = Gdx.input.getX();
        float mouseY = WindowConfig.getHeight() - Gdx.input.getY();

        rotateTurretAngle(mouseX, mouseY, dt);
        move(dt);
        fireByKeyPressed();
        super.update(dt);
    }

    @Override
    public void finish() {

    }

    private void checkForScreenExit() {
        float textureWidth = textureRegion.getRegionWidth();
        float textureHeight = textureRegion.getRegionHeight();
        if (position.x - textureWidth / 2 <= 0) {
            position.x = textureWidth / 2;
        }
        if (position.x + textureWidth / 2 >= Gdx.graphics.getWidth()) {
            position.x = Gdx.graphics.getWidth() - textureWidth / 2;
        }
        if (position.y - textureHeight / 2 <= 0) {
            position.y = textureHeight / 2;
        }
        if (position.y + textureHeight / 2 + HP_TEXTURE_GROWTH >= Gdx.graphics.getHeight()) {
            position.y = Gdx.graphics.getHeight() - textureHeight / 2 - HP_TEXTURE_GROWTH;
        }
    }

    public void fireByKeyPressed() {
        if (Gdx.input.isButtonPressed(FIRE_KEY)) {
            fire(this, BULLET_DAMAGE);
        }
    }

    private void move(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            direction = Direction.UP;
            position.y += SPEED * dt;
            angle = direction.getAngle();
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            direction = Direction.DOWN;
            position.y -= SPEED * dt;
            angle = direction.getAngle();
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction = Direction.LEFT;
            position.x -= SPEED * dt;
            angle = direction.getAngle();
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction = Direction.RIGHT;
            position.x += SPEED * dt;
            angle = direction.getAngle();
        }
    }
}
