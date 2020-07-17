package controller;

import com.jfoenix.controls.JFXButton;
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

public class ViewTodoListController {
    public JFXButton btnNewToDO;
    public ListView txtToDoList;
    public JFXButton btnToaDayList;
    public TextArea txtDescription;
    public JFXButton btnDelete;
    public Label lblDate;
    public AnchorPane root;

    public void btnNewToDOOnAction(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/CreateNewToDo.fxml")));
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Create New To Do");
        Image image = new Image("/images/icons8-to-do-48.png");
        primaryStage.getIcons().add(image);
        primaryStage.centerOnScreen();
    }

    public void btnToaDayListOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}
