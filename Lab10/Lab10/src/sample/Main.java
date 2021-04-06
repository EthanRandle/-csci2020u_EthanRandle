package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Button startServer = new Button("Start Server");
        Button addClient = new Button("Launch a client");
        Label desc = new Label("Welcome to Lab 10!");
        Label d1 = new Label(" Simply press Start Server before launching a client.");
        GridPane btns = new GridPane();
        btns.add(startServer,0,0);
        btns.add(addClient,1,0);
        BorderPane otr = new BorderPane();
        otr.setCenter(btns);
        otr.setTop(desc);

        addClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               // try {
                Client server = new Client();
               // } //catch (IOException e) {
               //     e.printStackTrace();
               // }
            }
        });

        startServer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    LabServer server = new LabServer(8080);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        primaryStage.setTitle("Lab 10");
        primaryStage.setScene(new Scene(otr, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
