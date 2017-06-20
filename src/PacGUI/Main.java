package PacGUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Pᗣᗧ··•··MᗣN");
        primaryStage.setHeight(700);
        primaryStage.setWidth(600);
        primaryStage.setResizable(false);


        Controller controller = (Controller) loader.getController();

        Scene scene = new Scene(root, 560,620);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyPressed(event.getCode());
            }
        });
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("PacGUI/stylesheet.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
