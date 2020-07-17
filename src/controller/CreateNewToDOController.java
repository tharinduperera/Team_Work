package controller;

import DBConnection.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateNewToDOController {
    public TextField txtTitle;
    public TextArea txtDescription;
    public JFXDatePicker datePicker;
    public JFXButton btnOK;
    public JFXButton btnCancel;
    public AnchorPane root;

    public void btnOKOnAction(ActionEvent actionEvent) {
        if(txtTitle.getText().isEmpty()){
            txtTitle.requestFocus();
        }else if(txtDescription.getText().isEmpty()){
            txtDescription.requestFocus();
        }else if(datePicker.getValue() == null){
            datePicker.requestFocus();
        }else{
            addToDatabase();
        }
    }

    private void addToDatabase() {
        String title = txtTitle.getText();
        String description = txtDescription.getText();
        String date = datePicker.getValue().toString();

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into todolist (title,description,dueDate) values (?,?,?)");
            preparedStatement.setObject(1,title);
            preparedStatement.setObject(2,description);
            preparedStatement.setObject(3,date);

            int i = preparedStatement.executeUpdate();
            if(i != 0){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added").showAndWait();
                txtTitle.clear();
                txtDescription.clear();
                datePicker.setValue(null);
                txtTitle.requestFocus();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/ViewTodo.fxml")));
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("To-Do List");
        primaryStage.centerOnScreen();
        Image image = new Image("/images/icons8-to-do-48.png");
        primaryStage.getIcons().add(image);
    }
}
