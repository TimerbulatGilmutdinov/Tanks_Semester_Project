package ru.kpfu.itis;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class ApplicationConfig {
    private static final Lwjgl3ApplicationConfiguration config;

    static {
        config = new Lwjgl3ApplicationConfiguration();
        config.useVsync(false);
        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
    }

    public static Lwjgl3ApplicationConfiguration getConfig() {
        return config;
    }
}
