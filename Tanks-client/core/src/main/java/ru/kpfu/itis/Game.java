package ru.kpfu.itis;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.kpfu.itis.api.Updatable;
import ru.kpfu.itis.constant.Background;
import ru.kpfu.itis.emitter.BulletEmitter;

public abstract class Game extends ApplicationAdapter implements Updatable {
    protected SpriteBatch batch;
    protected BulletEmitter bulletEmitter;

    @Override
    public void create() {
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl20.glClearColor(Background.RED, Background.GREEN, Background.BLUE, Background.ALPHA);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public BulletEmitter getBulletEmitter() {
        return bulletEmitter;
    }
}
