package ru.kpfu.itis.api;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderable {
    default void render(SpriteBatch batch, Texture texture) {
        throw new UnsupportedOperationException();
    }

    default void render() {
        throw new UnsupportedOperationException();
    }

    default void render(SpriteBatch batch) {
        throw new UnsupportedOperationException();
    }

    default void renderWithScale(SpriteBatch batch, Texture texture, float x, float y) {
        throw new UnsupportedOperationException();
    }

    default void renderWithScale(SpriteBatch batch, Texture texture, float x, float y, float angle) {
        throw new UnsupportedOperationException();
    }
}
