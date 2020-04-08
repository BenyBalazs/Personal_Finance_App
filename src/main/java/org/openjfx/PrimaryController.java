package org.openjfx;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private void setDefaultDateTime(){

        dateDatePicker.setValue(LocalDate.now());
    }
    @FXML
    public void addElementToList(ActionEvent actionEvent) {

        if (expenseOrIncome.getSelectionModel().equals("Kiadás")){
            Storage.getExpenses()
                    .add(new Expense(Storage.getPrimaryKeyForExpenses(),idSelector.getSelectionModel().toString(),(Integer) moneySpinner.getValue(),dateDatePicker.getValue()));
        }
        else {
            Storage.getIncomes()
                    .add(new Income(Storage.getPrimaryKeyForExpenses(),idSelector.getSelectionModel().toString(),(Integer) moneySpinner.getValue(),dateDatePicker.getValue()));
        }

    }
}
