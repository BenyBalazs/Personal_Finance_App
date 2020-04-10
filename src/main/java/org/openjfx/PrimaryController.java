package org.openjfx;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;

public class PrimaryController {

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
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditWindow"));
        }catch (Exception e){ }
    }
}
