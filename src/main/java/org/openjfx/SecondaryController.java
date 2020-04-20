package org.openjfx;

import java.io.IOException;
import javafx.fxml.FXML;
import org.openjfx.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}