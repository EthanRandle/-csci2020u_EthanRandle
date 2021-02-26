
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.time.*;
import java.time.chrono.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Register extends Application {

    @Override
    public void start(Stage stage) {
        //declaring variables
        Label usr = new Label("Username:");                     //Labels
        Label pass = new Label("Password:");
        Label fullName = new Label("Full Name:");
        Label eml = new Label("Email:");
        Label fone = new Label("Phone #:");
        Label dob = new Label("Date of Birth");

        TextField usrtxt = new TextField("Username");                     //User input fields
        PasswordField passtxt = new PasswordField();
        TextField fullNametxt = new TextField();
        TextField emltxt = new TextField();
        TextField fonetxt = new TextField();
        DatePicker dobtxt = new DatePicker();

        Button register = new Button("Register");

        //setting up gridpane
        GridPane myGrid = new GridPane();
        myGrid.setHgap(10);
        myGrid.setVgap(10);
        myGrid.setPadding(new Insets(25,25,25, 25));
        

        //adding to the grid
        myGrid.add(usr, 0, 0, 2, 1);
        myGrid.add(usrtxt, 1, 0);
        myGrid.add(pass, 0, 1);
        myGrid.add(passtxt, 1, 1);
        myGrid.add(fullName, 0, 2);
        myGrid.add(fullNametxt, 1, 2);
        myGrid.add(eml, 0, 3);
        myGrid.add(emltxt, 1, 3);
        myGrid.add(fone, 0, 4);
        myGrid.add(fonetxt, 1, 4);
        myGrid.add(dob, 0, 5);
        myGrid.add(dobtxt, 1, 5);
        myGrid.add(register, 1, 6);

        register.setOnAction(new EventHandler<ActionEvent>(){                   //If Button is pressed
            @Override
            public void handle(ActionEvent actionEvent){
                final String name = fullNametxt.getText();
                final String email = emltxt.getText();
                final String phone = fonetxt.getText();
                final LocalDate birthDay = dobtxt.getValue();

                System.out.println(name + " " + email + " " + phone + " " + birthDay);
            }
        });

        //adding to the scene
        Scene scene = new Scene(myGrid, 640, 480);
        stage.setTitle("Lab 04 Solution");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}