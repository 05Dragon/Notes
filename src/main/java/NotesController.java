import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class NotesController {

    public NotesController() {
    }

    // String currentLabelValue;

    /*
     * This string stores the last deleted name of note.
     */
    String currentImageValue;

    /*
     * This string list contains all the availible files that the user has opened
     * during the
     * session they were online for or during their previous sessions with the app.
     */
    List<String> fileNames = new ArrayList<String>();

    /*
     * 
     */
    ArrayList<String> buttonText = new ArrayList<String>();

    /*
     * This integer ID is used to give objects a specific ID.
     */
    int ID = 1;

    /*
     * String array array is used to fetch the text from the textarea.
     */
    String[] array;

    /*
     * The initial button which is deleted afterwards
     */
    @FXML
    Button button;

    /*
     * Stores the latest name of the note the user has clicked.
     */
    String currentLabel;

    /*
     * The initial image which is deleted afterwards
     */
    @FXML
    ImageView image;

    /*
     * The TextArea where all the text is entered by the user.
     */
    @FXML
    TextArea textArea;

    /*
     * Decleration of the buttons in the ToolBar.
     */
    @FXML
    Button clear, home, save, reopen, Note, clear1, openFile;

    /*
     * The ScrollPane on which all the availible notes are visible.
     */
    @FXML
    ScrollPane scroll;

    /*
     * The SplitPane which is the building block of the app and comes after the main
     * VBox, and sed to seperate the TextArea
     * from the list of notes.
     */
    @FXML
    SplitPane splitPane;

    /*
     * The VBox main contains every single element/object that you can visually see
     * in the game.
     * The VBox AllNotes stores the new toolbars made and arranges them.
     */
    @FXML
    VBox AllNotes, main;

    /*
     * Stores the button and image.
     */
    @FXML
    ToolBar toolBar;

    /**
     * The initialize method is called after the constructor is run and
     * any @FXML annotated fields are populated. This is required because the
     * constructor does NOT have access to the @FXML annotated fields. The
     * initialize method
     * is being used to style the app, such as adding colour to the background and
     * enabling effects.
     */
    @FXML
    public void initialize() {
        splitPane.getStylesheets().add(getClass().getResource("splitPane.css").toExternalForm());
        splitPane.getStyleClass().add("split-pane");
        splitPane.setBackground(new Background(
                new BackgroundImage(
                        new Image("notes.jpg"),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));
        textArea.setStyle(
                "-fx-control-inner-background: #fcf7c0; -fx-text-fill: #4f4f4f");
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(AllNotes);
        AllNotes.getChildren().remove(toolBar);
        textArea.setEditable(false);

        Bloom bloom = new Bloom();
        bloom.setThreshold(1.0);

        clear1.setEffect(bloom);
        clear1.setFocusTraversable(true);
        home.setEffect(bloom);
        home.setFocusTraversable(true);
        openFile.setEffect(bloom);
        openFile.setFocusTraversable(true);
    }

    /*
     * When this method is called, the TextArea is cleared.
     */
    @FXML
    public void clearText() {
        textArea.clear();
    }

    /*
     * This method displays the availible files to choose from via a alert pop up
     * display.
     */
    @FXML
    public void openFile() {
        if (buttonText.isEmpty()) {
            return;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.getDialogPane().getButtonTypes().clear();
        for (int i = 0; i < buttonText.size(); i++) {
            ButtonType button = new ButtonType(buttonText.get(i));
            alert.getDialogPane().getButtonTypes().add(button);
        }
        alert.showAndWait();
    }

    /*
     * This is a major method which is used to make new notes when the button is
     * clicked.
     * It is also the same method which allows the user to delete the note, or edit
     * the file name.
     */
    @FXML
    public void newNote() throws IOException {
        // sets effects so all the buttons are now visible
        Bloom bloom = new Bloom();
        bloom.setThreshold(1.0);
        clear.setEffect(bloom);
        clear.setFocusTraversable(true);
        home.setEffect(bloom);
        home.setFocusTraversable(true);
        save.setEffect(bloom);
        save.setFocusTraversable(true);
        reopen.setEffect(bloom);
        reopen.setFocusTraversable(true);

        // sets the TextArea to editable so the user can edit it
        textArea.setEditable(true);

        // process to add new toolbars into the VBox
        ToolBar duplicatedToolbar = new ToolBar();
        duplicatedToolbar.getStylesheets().add(getClass().getResource("toolBar.css").toExternalForm());
        duplicatedToolbar.getStyleClass().add("toolbar");
        duplicatedToolbar.setId("toolbar-" + ID);

        button = new Button("Notes" + ID);
        button.getStylesheets().add(getClass().getResource("button.css").toExternalForm());
        button.getStyleClass().add("button");
        button.setId("button-" + ID);
        duplicatedToolbar.getItems().add(button);

        ImageView duplicatedImageView = new ImageView();
        duplicatedImageView.setId("imageview-" + ID);
        duplicatedImageView.setImage(image.getImage());
        duplicatedImageView.setFitWidth(image.getFitWidth());
        duplicatedImageView.setFitHeight(image.getFitHeight());

        duplicatedToolbar.getItems().add(duplicatedImageView);
        AllNotes.getChildren().add(0, duplicatedToolbar);
        scroll.setVvalue(1);
        ID++;

        // receiving the current note name
        final String currentLabelValue = button.getText();

        button.setOnMouseClicked(event -> {
            // when clicked twice you can edit the note name to whatever you like via a
            // dialog box
            if (event.getClickCount() == 2) {
                TextInputDialog dialog = new TextInputDialog(button.getText());
                dialog.setTitle("Please Edit Note Name");
                dialog.setHeaderText(null);
                dialog.setContentText("Enter new note name:");
                dialog.setGraphic(null);
                dialog.setWidth(100);
                dialog.setHeight(300);

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(newLabel -> {
                    button.setText(newLabel);
                    // Update currentLabel when the button label is changed
                    currentLabel = newLabel;
                });

                // after changing the file name, we replace the original file with the new file
                File file = new File(currentLabel + ".txt");
                File file2 = new File(currentLabel + ".txt");
                boolean success = file.renameTo(file2);

                for (int i = 0; i < fileNames.size(); i++) {
                    if (currentLabelValue == fileNames.get(i)) {
                        fileNames.set(i, currentLabel);
                    }
                }

                if (!success) {
                }

            }
            // if clicked once, the user opens the note corresponding to the note name they
            // clicked
            else {
                textArea.clear();
                currentLabel = button.getText();
                try {
                    fileReader(currentLabel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        currentImageValue = button.getText();

        // is the delete icon is pressed, it deletes the note the user deleted
        duplicatedImageView.setOnMousePressed(event -> {
            AllNotes.getChildren().remove(duplicatedToolbar);
            textArea.clear();
            fileNames.remove(currentLabel);
            currentLabel = currentImageValue;
            ID--;
        });

        List<Node> vboxChildren = AllNotes.getChildren();
        buttonText.clear();
        for (int i = vboxChildren.size() - 1; i >= 0; i--) {
            ToolBar toolbar = (ToolBar) vboxChildren.get(i);
            Button button = (Button) toolbar.getItems().get(0);
            buttonText.add(button.getText());
        }
    }

    /*
     * Enables the user to return to the home screen upon clicking it.
     */
    @FXML
    public void returnHome() throws IOException {
        Taskade.setRoot("Home");
    }

    /*
     * Calls a method which saves the file by writing it into a .txt file on
     * desktop.
     */
    @FXML
    public void saveFile() {
        fileWriter(currentLabel);
    }

    /*
     * Calls a method which receives data from a .txt file and puts it on the
     * TextArea.
     */
    @FXML
    public void reopenFile() throws IOException {
        fileReader(currentLabel);
    }

    /**
     * This method is used to indent the line whenever the user uses a "-" sign at
     * the start
     * of a line, so that it feels similar to a list feature.
     */
    @FXML
    public void inputDetected() {
        // splits all the text as per line number
        String[] lines = textArea.getText().split("\n");
        for (int i = 0; i < lines.length; i++) {
            // checks if the first character is a "-" sign
            if (lines[i].length() != 0 && lines[i].substring(0, 1).equals("-")) {
                // if it is a "-" sign, it replaces it with a indent and adds the "-" sign again
                String y = lines[i].replace("-", "\t" + "- ");
                lines[i] = y;
                textArea.clear();
                for (int g = 0; g < lines.length; g++) {
                    if (g != lines.length - 1) {
                        textArea.appendText(lines[g] + "\r\n");
                    } else {
                        // merges all the lines again and adds the replacement
                        textArea.appendText(lines[g]);
                    }
                }
            } else {
            }
        }
    }

    /**
     * The following method collects the text from the TextArea and stores it in the
     * respective
     * file based on the note the user is currently in. This method also checks
     * whether the file name
     * already exists or not, and if it doesnt, it creates a new file based on the
     * note name.
     * 
     * @param currentLabel The current note name where the user recently inputted
     *                     data
     */
    private void fileWriter(String currentLabel) {
        BufferedWriter bf;
        try {
            // makes a new file
            File file = new File(currentLabel + ".txt");
            // checks whether the file exists or not, if it doesnt, then makes a new one and
            // if it
            // it does exist, then doesnt make a new one
            if (file.createNewFile()) {
                bf = new BufferedWriter(new FileWriter(new File(currentLabel + ".txt").getPath()));
                // writes the text from the TextArea into the respective file
                bf.write(textArea.getText());
                bf.flush();
                bf.close();
            } else {
                bf = new BufferedWriter(new FileWriter(new File(currentLabel + ".txt").getPath()));
                bf.write(textArea.getText());
                bf.flush();
                bf.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get data from a file and display it onto the TextArea.
     * This method also checks whether a file exists or not, and if it doesnt, it
     * makes a new file.
     * 
     * @param currentLabel The current note name where the user recently inputted
     *                     data
     * 
     */
    private void fileReader(String currentLabel) throws IOException {
        textArea.clear();
        // string list to contain everything from the file the user wants to open
        List<String> stngFile = new ArrayList<String>();

        File files = new File(currentLabel + ".txt");
        if (files.createNewFile()) {
            BufferedReader bfredr = new BufferedReader(
                    new FileReader(new File(currentLabel + ".txt").getPath()));
            String text = bfredr.readLine();

            while (text != null) {
                stngFile.add(text);
                text = bfredr.readLine();
            }
            bfredr.close();
            array = stngFile.toArray(new String[0]);
            for (int i = 0; i < array.length; i++) {
                textArea.appendText(array[i] + "\n");
            }
        } else {
            BufferedReader bfredr = new BufferedReader(
                    new FileReader(new File(currentLabel + ".txt").getPath()));
            String text = bfredr.readLine();

            while (text != null) {
                stngFile.add(text);
                text = bfredr.readLine();
            }
            bfredr.close();
            array = stngFile.toArray(new String[0]);
            for (int i = 0; i < array.length; i++) {
                textArea.appendText(array[i] + "\n");
            }
        }
    }
}
