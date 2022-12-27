package ru.kpfu.itis.emitter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.kpfu.itis.api.Renderable;
import ru.kpfu.itis.api.Updatable;
import ru.kpfu.itis.entity.Bullet;
import ru.kpfu.itis.entity.Tank;

public class BulletEmitter implements Updatable, Renderable {

    private static final int MAX_BULLET_COUNT = 100;
    private final Bullet[] bullets;

    public BulletEmitter() {
        this.bullets = new Bullet[MAX_BULLET_COUNT];
        for (int i = 0; i < MAX_BULLET_COUNT; i++) {
            this.bullets[i] = new Bullet();
        }
    }

    public void activateBullet(Object owner, Vector2 position, float angle, float bulletDamage) {
        for (int i = 0; i < MAX_BULLET_COUNT; i++) {
            if (!bullets[i].isActive()) {
                bullets[i].activate(owner, position.x, position.y, angle, bulletDamage);
                break;
            }
        }
    }

    public Bullet[] getBullets() {
        return bullets;
    }

    @Override
    public void render(SpriteBatch batch) {
        for (int i = 0; i < MAX_BULLET_COUNT; i++) {
            if (bullets[i].isActive()) {
                bullets[i].render(batch);
            }
        }
    }

    @Override
    public void update(float dt) {
        for (int i = 0; i < MAX_BULLET_COUNT; i++) {
            if (bullets[i].isActive()) {
                bullets[i].update(dt);
            }
        }
    }

}
