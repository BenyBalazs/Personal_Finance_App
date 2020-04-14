package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.openjfx.Expense;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class EditWindowController  {

    private static Logger logger = LoggerFactory.getLogger("EditWindowController.class");

    @FXML
    private TableView<Expense> expTableInfo;

    @FXML
    private TableView<Income> incTableInfo;
    @FXML
    private TableColumn<Income, Integer> colIncId;

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
    private TableColumn<Expense, Integer> colExpId;
    @FXML
    private TableColumn<Income,Character> iType;
    @FXML
    private TableColumn<Expense,Character> eType;

    @FXML
    Button listUpdaterButton;

    public void initialize(){
        updateExpList();
        initExpColumns();
    }

    private void initExpColumns(){
        eType.setCellValueFactory(new PropertyValueFactory<Expense,Character>("Type"));
        colExpId.setCellValueFactory(new PropertyValueFactory<Expense,Integer>("PrimaryKey"));
        colExpName.setCellValueFactory(new PropertyValueFactory<Expense,String>("Name"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<Expense,LocalDate>("Amount"));
        colExpAmount.setCellValueFactory(new PropertyValueFactory<Expense,Integer>("DayOfAdd"));
       // colExpEdit.setCellValueFactory(new PropertyValueFactory<>("Szerkeszt"));
    }

    private void initIncColumns(){
        colIncId.setCellValueFactory(new PropertyValueFactory<Income,Integer>("PrimaryKey"));
        colIncName.setCellValueFactory(new PropertyValueFactory<Income,String >("Name"));
        colIncDate.setCellValueFactory(new PropertyValueFactory<Income,LocalDate>("Amount"));
        colIncAmount.setCellValueFactory(new PropertyValueFactory<Income,Integer>("DayOfAdd"));
        //colIncEdit.setCellValueFactory(new PropertyValueFactory<>("Update"));
    }


    private void editableExpCols(){
        colExpName.setCellFactory(TextFieldTableCell.forTableColumn());
        colExpDate.setCellFactory(column -> {
            TableCell<Expense, LocalDate> cell = new TableCell<Expense, LocalDate>() {
                private SimpleDateFormat format = new SimpleDateFormat("YYYY.MM.DD");

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
                private SimpleDateFormat format = new SimpleDateFormat("YYYY.MM.DD");

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

    }

    void updateExpList(){
        ObservableList<Expense> expTableData = FXCollections.observableArrayList(Loader.storage.getExpenses());

        try{

            for(int i = 0; i < Loader.storage.getExpenses().size(); i++) {

                expTableInfo.setItems(expTableData);
                logger.trace("New element was added to the Exp list");

            }
        }catch (Exception e){logger.error("Unknown error: ", e);}
    }

    @FXML
    void updateList(){
        updateExpList();
    }
}
