package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.time.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label userName = new Label("Username:");
        Label password = new Label("Password:");
        Label fullName = new Label("Full Name:");           //Labels
        Label email = new Label("Email:");
        Label phoneNumber = new Label("Phone #:");
        Label DateofBirth = new Label("Date of Birth");

        TextField userName_tf = new TextField();
        PasswordField password_tf = new PasswordField();
        TextField fullName_tf = new TextField();                //tf represents textfield
        TextField email_tf = new TextField();
        TextField phoneNumber_tf = new TextField();
        DatePicker DateofBirth_tf = new DatePicker();

        userName_tf.setPromptText("Username");
        password_tf.setPromptText("Password");
        fullName_tf.setPromptText("Full Name");                 //prompt texts
        phoneNumber_tf.setPromptText("Phone #");
        email_tf.setPromptText("Email");

        Button Register = new Button("Register");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);                           // Buttons and gridpanes
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(24));



        gridPane.add(userName, 0, 0, 2, 1);
        gridPane.add(userName_tf, 1, 0);
        gridPane.add(password, 0, 1);
        gridPane.add(password_tf, 1, 1);
        gridPane.add(fullName, 0, 2);
        gridPane.add(fullName_tf, 1, 2);
        gridPane.add(email, 0, 3);                          //grid pane
        gridPane.add(email_tf, 1, 3);
        gridPane.add(phoneNumber, 0, 4);
        gridPane.add(phoneNumber_tf, 1, 4);
        gridPane.add(DateofBirth, 0, 5);
        gridPane.add(DateofBirth_tf, 1, 5);
        gridPane.add(Register, 1, 6);

        Register.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                final String name = fullName_tf.getText();
                final String email = email_tf.getText();                        //outputs
                String phone = phoneNumber_tf.getText();
                final LocalDate birthDay = DateofBirth_tf.getValue();
                //regex for phone output
                phone = String.valueOf(phone).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");

                System.out.println(name + " " + email + " " + phone + " " + birthDay);
            }
        });


        Scene scene = new Scene(gridPane, 800, 400);
        stage.setTitle("Lab 04 Solution");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}