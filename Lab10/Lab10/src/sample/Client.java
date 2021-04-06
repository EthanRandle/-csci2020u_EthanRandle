package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;

public class Client {

    public static void buildClientUI(){

    }
    public static void sendMessage(String name, String msg){

        //write name and message to a file for server to open
        new PrintWriter(socket.getOutputStream());
        out.print(name + ": " + msg);
        out.flush();
    }

    public static void main(String[] args) {
        BufferedReader in;
        PrintWriter outs = null;


        Stage clientStage = new Stage();
        clientStage.setTitle("BBS Client v1.0");
        GridPane c = new GridPane();
        c.setHgap(10);
        c.setVgap(5);
        c.setPadding(new Insets(25,25,25, 25));

        TextField msg = new TextField();
        TextField usr = new TextField();
        Button send = new Button("Send");
        Button exit = new Button("Exit");
        Label message = new Label("Message: ");
        Label user = new Label("Username: ");


        c.add(user, 0,0);
        c.add(usr,1,0);
        c.add(message,0,1);
        c.add(msg,1,1);
        c.add(send,0,2);
        c.add(exit,0,3);



        try{
            Socket socket = new Socket(InetAddress.getLocalHost(), 8080);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outs = new PrintWriter(socket.getOutputStream());

            // send the request


            // read and print the response
            System.out.println("Response:");
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // close everything
            in.close();
            outs.close();
            socket.close();

        }catch(IOException e){
            e.printStackTrace();
        }

        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //send the message to server
                sendMessage(outs, usr.getText(), msg.getText());
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //close all sockets
                System.exit(2);
            }
        });

        clientStage.setScene(new Scene(c, 300, 275));
        clientStage.show();
    }
}
