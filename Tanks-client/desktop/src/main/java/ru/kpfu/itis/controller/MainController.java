package ru.kpfu.itis.controller;

import com.badlogic.gdx.math.Vector2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import ru.kpfu.itis.Client;
import ru.kpfu.itis.DesktopLauncher;
import ru.kpfu.itis.constant.TankInfo;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class MainController {

    @FXML
    private Button searchButton;

    @FXML
    private Button exitButton;

    @FXML
    private Text searchText;

    public void initialize() {
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchText.setVisible(true);
                Random random = new Random();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Socket socket = new Client().searchMatch();
                            searchText.setVisible(false);
                            DesktopLauncher.startGame(
                                    new Vector2(random.nextInt(1000),
                                            random.nextInt(800)),
                                    socket,
                                    TankInfo.RED, 2
                            );
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException();
                        }
                    }
                }).start();
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }
}
