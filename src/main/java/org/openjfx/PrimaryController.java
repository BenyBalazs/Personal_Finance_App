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

    private static Logger logger = LoggerFactory.getLogger("PrimaryController.class");
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
    @FXML
    Button pieChartOpener;
    @FXML
    Label labelOfWarnMessage;

    void warnMessage(String s){
        if(!labelOfWarnMessage.isVisible()){
            labelOfWarnMessage.setVisible(true);
            labelOfWarnMessage.setText(s);
        }else labelOfWarnMessage.setText(s);
    }

    @FXML
    public void addElementToList() {

        try{

        if (expenseOrIncome.getValue().toString().equals("Kiadás")){
            Expense tmp = new Expense('E',Loader.storage.getPrimaryKeyForExpenses(),
                    idSelector.getValue().toString(),(Integer) moneySpinner.getValue(),dateDatePicker.getValue());
            Loader.storage.getExpenses().add(tmp);
            myList.getItems().add(tmp.toString());
            logger.trace("User added a new Expense to the list");
        }
        else {
            Income tmp = new Income('I', Loader.storage.getPrimaryKeyForIncomes(),
                    idSelector.getValue().toString(),(Integer) moneySpinner.getValue(),dateDatePicker.getValue());
            Loader.storage.getIncomes().add(tmp);
            myList.getItems().add(tmp.toString());
            logger.trace("user added a new Income to the list");
        }
        test.setText("Expenses: " + Loader.storage.getSumOfExpenses() + "Bevételek: " + Loader.storage.getSumOfIncomes() );
        Balance.setText(Loader.storage.getBalance().toString());
        Loader.storage.getDistributionExpenses();
        labelOfWarnMessage.setVisible(false);

        } catch (NullPointerException e) {
            logger.error("No id found ", e);
            warnMessage("A név mező kitöltése kötelező!");
        } catch (VerifyError e){
            logger.error("The Date field was null", e);
            warnMessage("A dátum mező kitöltése kötelező");
        } catch (Exception e){
            logger.error("@Something went wrong {}", e);
            warnMessage("Valami tönkrement hoppá");
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
            logger.trace("User opened the EditWindow");
        }catch (Exception e){
            logger.error("Error when trying to open new EditWindow: " ,e);
        }
    }

    @FXML
    public void openPieChartWindow(){
        try{
            Parent part = FXMLLoader.load(App.class.getResource("piechart.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(part);
            stage.setScene(scene);
            stage.setTitle("Megoszlás");
            stage.setResizable(false);
            stage.sizeToScene();
            stage.show();
            logger.trace("Function called  openPieChartWindow");
        }catch (Exception e) {
            logger.error("Error when trying to PieChartWindow: " ,e);
        }
    }
}
