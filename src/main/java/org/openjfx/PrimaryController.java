package org.openjfx;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

public class PrimaryController {

    private static Logger logger = LoggerFactory.getLogger("App.class");
    @FXML
    ChoiceBox expenseOrIncome;
    @FXML
    ComboBox idSelector;
    @FXML
    DatePicker dateDatePicker;
    @FXML
    Spinner moneySpinner;
    @FXML
    Button addButton;
    @FXML
    Label test;
    @FXML
    ListView myList;

    @FXML
    Button editButton;


    @FXML
    public void addElementToList(ActionEvent actionEvent) {

        if (expenseOrIncome.getValue().toString().equals("Kiadás")){
            Expense tmp = new Expense(Storage.getPrimaryKeyForExpenses(),idSelector.getValue().toString(),(Integer) moneySpinner.getValue(),dateDatePicker.getValue());
            Storage.getExpenses().add(tmp);
            myList.getItems().add(tmp.toString());

        }
        else {
            Income tmp = new Income(Storage.getPrimaryKeyForIncomes(),idSelector.getValue().toString(),(Integer) moneySpinner.getValue(),dateDatePicker.getValue());
            Storage.getIncomes().add(tmp);
            myList.getItems().add(tmp.toString());
        }
        test.setText("Kiadások " + Storage.primaryKeyForExpenses + " asdasd " + Storage.primaryKeyForIncomes );

    }
    @FXML
    public void openEditWindow(){
        logger.trace("Function called openEditWindow");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditWindow"));
            Scene scene2 = new Scene(loader.load(), 600,400);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();

        }catch (Exception e){
            logger.error("Error when trying to open new window: " ,e);
        }
    }
}
