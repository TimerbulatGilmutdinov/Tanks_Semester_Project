package ru.kpfu.itis.handler;

import ru.kpfu.itis.emitter.BulletEmitter;
import ru.kpfu.itis.entity.Bullet;
import ru.kpfu.itis.entity.Player;

public class CollisionHandler {

    private final BulletEmitter bulletEmitter;
    private final Player player;

    public CollisionHandler(BulletEmitter bulletEmitter, Player player) {
        this.bulletEmitter = bulletEmitter;
        this.player = player;
    }

    public void checkBulletAndPlayerCollision() {
        for (int i = 0; i < bulletEmitter.getBullets().length; i++) {
            Bullet bullet = bulletEmitter.getBullets()[i];
            if (bullet.isActive()
                    && bullet.getOwner() != player
                    && player.getTextureRectangle().contains(bullet.getPosition())) {
                bullet.deactivate();
                player.takeDamage(bullet.getDamage());
            }
        }
    }
}
