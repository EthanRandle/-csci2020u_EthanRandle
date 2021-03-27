package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.File;
import java.time.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.swing.*;

public class Main extends Application {



    @Override
    public void start(Stage stage) {

        Menu menu = new Menu("Menu 1");
        MenuItem newButton = new MenuItem("New");
        MenuItem openButton = new MenuItem("Open");
        MenuItem saveButton = new MenuItem("Save");
        MenuItem saveAsButton = new MenuItem("Save As");
        MenuItem exitButton = new MenuItem("Exit");


        menu.getItems().add(newButton);
        menu.getItems().add(openButton);
        menu.getItems().add(saveButton);
        menu.getItems().add(saveAsButton);
        menu.getItems().add(exitButton);


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);

        VBox vBox = new VBox(menuBar);

        Label SID = new Label("SID:");
        Label Midterm = new Label("Midterm:");
        Label Assignments = new Label("Assignments:");           //Labels
        Label finalExam = new Label("Final Exam:");


        TextField SID_tf = new TextField();
        PasswordField Midterm_tf = new PasswordField();
        TextField Assignments_tf = new TextField();                //tf represents textfield
        TextField finalExam_tf = new TextField();
        TextField phoneNumber_tf = new TextField();

        SID_tf.setPromptText("SID");
        Midterm_tf.setPromptText("Midterm/100");
        Assignments_tf.setPromptText("Assignment/100");                 //prompt texts
        finalExam_tf.setPromptText("Final Exam/100");

        Button Register = new Button("Add");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);                           // Buttons and gridpanes
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(24));



        gridPane.add(SID, 0, 1, 2, 1);
        gridPane.add(SID_tf, 1, 1);
        gridPane.add(Midterm, 0, 2);
        gridPane.add(Midterm_tf, 1, 2);
        gridPane.add(Assignments, 0, 3);
        gridPane.add(Assignments_tf, 1, 3);
        gridPane.add(finalExam, 0, 4);                          //grid pane
        gridPane.add(finalExam_tf, 1, 4);


        gridPane.add(Register, 1, 7);
        gridPane.add(vBox,0,0);


        Register.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                 String SID = SID_tf.getText();
                 String Assignments = Assignments_tf.getText();
                 String Midterm =  Midterm_tf.getText();//outputs
                String FinalExam = finalExam_tf.getText();


            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                System.exit(0);


            }
        });
        TableView table = new TableView();
        BorderPane root = new BorderPane();

        /* Adding the menus as well as the content pane to the root node. */
        root.setTop(vBox);
        root.setCenter(table);
        root.setBottom(gridPane);
       // root.setBottom(gridPane);

        openButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setInitialDirectory(new File("."));
                File currentFilename = directoryChooser.showDialog(stage);


            }
        });



        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Lab 08 Solution");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

    public void load(){

    }
    public void save(){

    }
}