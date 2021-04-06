package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class LabServer {
    private ServerSocket serverSocket = null;
    private int port;

    //server maybe should create source file here for historical message storing.
    public LabServer(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        this.port = port;
        buildServerUI();
    }

    public void handleRequests(){
        while(true){
            try {                   //creating a thread to handle each client
                Socket clientSocket = serverSocket.accept();
                LabServerHandler handler = new LabServerHandler(clientSocket);
                Thread handlerThread = new Thread(handler);                         //make thread runnable UI
                handlerThread.start();                                              //start running the thread
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void buildServerUI(){
        Stage serverStage = new Stage();
        serverStage.setTitle("BBS Server v1.0");
        GridPane c = new GridPane();                            //btn
        BorderPane otr = new BorderPane();                      //textarea + grid
        c.setHgap(10);
        c.setVgap(5);
        c.setPadding(new Insets(25,25,25, 25));

        TextArea messages = new TextArea();
        Button exit = new Button("Exit");

        otr.setCenter(messages);
        c.add(exit,0,0);
        otr.setBottom(c);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //close all sockets
                try {
                    serverSocket.close();
                }catch(IOException e){e.printStackTrace();}
                System.exit(2);
            }
        });

        serverStage.setScene(new Scene(otr, 300, 300));
        serverStage.show();
    }

}
