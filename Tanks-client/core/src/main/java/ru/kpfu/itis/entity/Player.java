package ru.kpfu.itis.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.kpfu.itis.Game;
import ru.kpfu.itis.WindowConfig;
import ru.kpfu.itis.constant.TankInfo;

public class Player extends Tank {

    private static final int SPEED = 500;
    private static final int MAX_HP = 100;
    private static final float BULLET_DAMAGE = 10;

    private final Texture texture;
    private final Texture turretTexture;
    private final Texture hpTexture = null;


    public Player(Game game, Vector2 position, TankInfo tankInfo) {
        super(game, position);
        texture = new Texture(tankInfo.getFileName());
        turretTexture = new Texture(tankInfo.getTurretFileName());
        textureRectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void render(SpriteBatch batch) {
        float halfTextureWidth = (float) texture.getWidth() / 2;
        float halfTextureHeight = (float) texture.getHeight() / 2;
        renderWithScale(
                batch,
                texture,
                position.x - halfTextureWidth,
                position.y - halfTextureHeight
        );
        renderWithScale(
                batch,
                turretTexture,
                position.x - halfTextureWidth,
                position.y - halfTextureHeight,
                turretAngle
        );
    }

    @Override
    public void update(float dt) {
        textureRectangle.setPosition(
                position.x - (float) texture.getWidth() / 2,
                position.y - (float) texture.getHeight() / 2
        );
        float mouseX = Gdx.input.getX();
        float mouseY = WindowConfig.getHeight() - Gdx.input.getY();

        rotateTurretAngle(mouseX, mouseY, dt);
        super.update(dt);
    }

    public void fireByKeyPressed() {
        if (Gdx.input.isButtonPressed(FIRE_KEY)) {
            fire(this, BULLET_DAMAGE);
        }
    }
}
