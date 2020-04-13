package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class EditWindowController {


    @FXML
    private TableView<Expense> expTableInfo;

    @FXML
    private TableView<Income> incTableInfo;
    @FXML
    private TableColumn<Income, Character> colIncId;

    @FXML
    private TableColumn<Income, Integer> colIncAmount;

    @FXML
    private TableColumn<Expense, LocalDate> colExpDate;

    @FXML
    private TableColumn<Expense, Integer> colExpAmount;

    @FXML
    private TableColumn<Expense, Button> colExpEdit;

    @FXML
    private TableColumn<Income, Button> colIncEdit;

    @FXML
    private TableColumn<Income, String> colIncName;

    @FXML
    private TableColumn<Expense, String> colExpName;

    @FXML
    private TableColumn<Income, LocalDate> colIncDate;

    @FXML
    private TableColumn<Expense, Character> colExpId;

    @FXML
    Button listUpdaterButton;

    public void initialize(){
        updateList();
    }

    private void initExpColumns(){
        colExpId.setCellValueFactory(new PropertyValueFactory<Expense,Character>("Id"));
        colExpName.setCellValueFactory(new PropertyValueFactory<Expense,String>("Név"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<Expense,LocalDate>("Dátum"));
        colExpAmount.setCellValueFactory(new PropertyValueFactory<Expense,Integer>("Összeg"));
        colExpEdit.setCellValueFactory(new PropertyValueFactory<>("Szerkeszt"));

        editableExpCols();
    }

    private void initIncColumns(){
        colIncId.setCellValueFactory(new PropertyValueFactory<Income,Character>("Id"));
        colIncName.setCellValueFactory(new PropertyValueFactory<Income,String >("Név"));
        colIncDate.setCellValueFactory(new PropertyValueFactory<Income,LocalDate>("Dátum"));
        colIncAmount.setCellValueFactory(new PropertyValueFactory<Income,Integer>("Összeg"));
        colIncEdit.setCellValueFactory(new PropertyValueFactory<>("Update"));
    }


    private void editableExpCols(){
        colExpName.setCellFactory(TextFieldTableCell.forTableColumn());
        colExpDate.setCellFactory(column -> {
            TableCell<Expense, LocalDate> cell = new TableCell<Expense, LocalDate>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });
        colExpAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        colExpName.setOnEditCommit(e -> e.getTableView().getItems().
                get(e.getTablePosition().getRow()).setName(e.getNewValue()));

    }

    private void editableIncCols(){
        colIncName.setCellFactory(TextFieldTableCell.forTableColumn());
        colIncDate.setCellFactory(column -> {
            TableCell<Income, LocalDate> cell = new TableCell<Income, LocalDate>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });
        colIncAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        colIncName.setOnEditCommit(e -> e.getTableView().getItems().
                get(e.getTablePosition().getRow()).setName(e.getNewValue()));

    }
    @FXML
    void updateList(){

    }
}
