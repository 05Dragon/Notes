
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * JavaFX FXML Application Loader
 * 
 * Loads an FXML based JavaFX ui and displays it.
 */
public class Taskade extends Application {

    private static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        //Change primary to whatever FXML file you wish to load

        scene = new Scene(loadFXML("Home"), 1100, 700);
        stage.setTitle("Taskade");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Taskade.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}