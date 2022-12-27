package ru.kpfu.itis;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.math.Vector2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.kpfu.itis.constant.TankInfo;
import ru.kpfu.itis.onlineplayer.OnlineGame;

import java.net.Socket;

public class DesktopLauncher extends Application {

	public static void main (String[] args) {
		launch();
	}

	public static void startGame(Vector2 startPosition, Socket socket, TankInfo tankInfo, int playerCount) {
		new Lwjgl3Application(new GameLauncher(new OnlineGame(startPosition, tankInfo, socket, playerCount)),
				ApplicationConfig.getConfig());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
		Scene scene = new Scene(fxmlLoader.load());

		primaryStage.setScene(scene);
		primaryStage.sizeToScene();

		primaryStage.setTitle("TANKS");
		primaryStage.show();

	}
}
