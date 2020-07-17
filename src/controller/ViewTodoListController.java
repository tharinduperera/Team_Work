package controller;

import DBConnection.DBConnection;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewTodoListController {
    public JFXButton btnNewToDO;
    public ListView<String> txtToDoList;
    public TextArea txtDescription;
    public JFXButton btnDelete;
    public Label lblDate;
    public AnchorPane root;
    public JFXButton btnToDayList;

    public void initialize(){
        loadToDoList();

        txtToDoList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Connection connection = DBConnection.getInstance().getConnection();
                String value = txtToDoList.getSelectionModel().getSelectedItem();
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select description,dueDate from todolist where title = '"+value+"'");

                    if(resultSet.next()){
                        txtDescription.setText(resultSet.getString(1));
                    }
                    lblDate.setText(resultSet.getString(2));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    private void loadToDoList() {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select title from todolist");
            ObservableList<String> items = txtToDoList.getItems();
            items.clear();

            while (resultSet.next()){
                String string = resultSet.getString(1);
                items.add(string);
            }

            txtToDoList.refresh();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnNewToDOOnAction(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/CreateNewToDo.fxml")));
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Create New To Do");
        Image image = new Image("/images/icons8-to-do-48.png");
        primaryStage.getIcons().add(image);
        primaryStage.centerOnScreen();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnToDayListOnAction(ActionEvent actionEvent) {
    }
}
