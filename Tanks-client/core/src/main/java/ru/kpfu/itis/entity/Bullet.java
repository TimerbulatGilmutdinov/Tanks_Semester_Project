package ru.kpfu.itis.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.kpfu.itis.api.Renderable;
import ru.kpfu.itis.api.Updatable;
import ru.kpfu.itis.constant.GameAtlas;

public class Bullet implements Updatable, Renderable {
    private static final TextureRegion TEXTURE_REGION = GameAtlas.TEXTURE_ATLAS.findRegion("bullet");
    private static final int SPEED = 1_500;

    private final Vector2 position;
    private final Vector2 direction;
    private boolean activated;
    private Object owner;
    private float damage;

    public Bullet() {
        this.position = new Vector2();
        this.direction = new Vector2();
        this.activated = false;
    }

    public Object getOwner() {
        return owner;
    }

    public void activate(Object owner, float x, float y, float angle, float damage) {
        this.owner = owner;
        activated = true;
        position.set(x, y);
        direction.set((float) Math.cos(angle), (float) Math.sin(angle));
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isActive() {
        return activated;
    }

    public void deactivate() {
        activated = false;
    }

    @Override
    public void update(float dt) {
        position.mulAdd(direction, SPEED * dt);
        if (position.x < 0
                || position.x > Gdx.graphics.getWidth()
                || position.y < 0
                || position.y > Gdx.graphics.getHeight()) {
            deactivate();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(TEXTURE_REGION,
                position.x,
                position.y);
    }

}
