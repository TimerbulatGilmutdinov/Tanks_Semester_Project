package ru.kpfu.itis;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ru.kpfu.itis.api.Updatable;
import ru.kpfu.itis.constant.Background;
import ru.kpfu.itis.emitter.BulletEmitter;

public abstract class Game extends ApplicationAdapter implements Updatable {
    protected SpriteBatch batch;
    protected BulletEmitter bulletEmitter;
    private Button exitButton;
    private BitmapFont font;
    private Stage stage;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font24.fnt"));
        stage = new Stage();
        Skin skin = new Skin();
        skin.add("exitButton", new Texture("simple_button.png"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("exitButton");
        textButtonStyle.font = font;
        exitButton = new TextButton("Exit", textButtonStyle);
        exitButton.setPosition(0, Gdx.graphics.getHeight() - 30);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        Gdx.gl20.glClearColor(Background.RED, Background.GREEN, Background.BLUE, Background.ALPHA);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public BulletEmitter getBulletEmitter() {
        return bulletEmitter;
    }
}
