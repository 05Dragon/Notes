import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    public PrimaryController() {
        System.out.println("Constructing the controller - and injecting all @FXML annotated fields");
    }

    /**
     * The initialize method is called after the constructor is run and
     * any @FXML annotated fields are populated.  This is required because the 
     * constructor does NOT have access to the @FXML annotated fields.
     */
    @FXML
    public void initialize() {
        System.out.println("The controller is loaded and ready to use");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
