package sample;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;


public class Main extends Application {

    @Override
    public void start(Stage stage) {

        TableColumn SID = new TableColumn("SID");
        TableColumn Assignments = new TableColumn("Assignments");
        TableColumn Midterm = new TableColumn("Midterm");
        TableColumn finalExam = new TableColumn("Final Exam");
        TableColumn finalMark = new TableColumn("Final Mark");
        TableColumn letterGrade = new TableColumn("Letter Grade");

        TableView Table = new TableView();
        Table.setEditable(true);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);


        Table.setItems(DataSource.getAllMarks());
        
        SID.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("SID"));
        Assignments.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("Assignments"));
        Midterm.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("Midterm"));
        finalExam.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("FinalExam"));
        finalMark.setCellValueFactory(new PropertyValueFactory<StudentRecord, Double>("FinalMark"));
        letterGrade.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("LetterGrade"));


        Table.getColumns().addAll(SID, Assignments, Midterm, finalExam, finalMark, letterGrade);
        gridPane.add(Table,0,0);


        Scene scene = new Scene(gridPane, 600, 300);
        stage.setTitle("Lab 05 Solutions");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}