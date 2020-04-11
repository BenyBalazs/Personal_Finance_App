package org.openjfx;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.stage.Modality;
import javafx.stage.StageStyle;
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
    Label Balance;

    void warnMessage(String s){
        idSelector.setStyle("-fx-border-color: firebrick ");
        try {
            wait(100);
        }catch (Exception e){logger.error("Oops");}
        idSelector.setStyle("");
    }

    @FXML
    public void addElementToList() {

        try{

        if (expenseOrIncome.getValue().toString().equals("Kiadás")){
            Expense tmp = new Expense(Loader.storage.getPrimaryKeyForExpenses(),
                    idSelector.getValue().toString(),(Integer) moneySpinner.getValue(),dateDatePicker.getValue());
            Loader.storage.getExpenses().add(tmp);
            myList.getItems().add(tmp.toString());

        }
        else {
            Income tmp = new Income(Loader.storage.getPrimaryKeyForIncomes(),
                    idSelector.getValue().toString(),(Integer) moneySpinner.getValue(),dateDatePicker.getValue());
            Loader.storage.getIncomes().add(tmp);
            myList.getItems().add(tmp.toString());
        }
        test.setText("Expenses: " + Loader.storage.getSumOfExpenses() + "Bevételek: " + Loader.storage.getSumOfIncomes() );
        Balance.setText(Loader.storage.getBalance().toString());
        Loader.storage.getDistributionExpenses();


        } catch (NullPointerException e) {
            logger.error("No id found ", e);
        } catch (Exception e){
            logger.error("@Something went wrong {}", e);
        }
    }

    @FXML
    public void openEditWindow(){
        logger.trace("Function called openEditWindow");
        try {
            Parent part = FXMLLoader.load(App.class.getResource("EditWindow.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.setTitle("Szerkesztés");
            stage.show();
        }catch (Exception e){
            logger.error("Error when trying to open new window: " ,e);
        }
    }
}
