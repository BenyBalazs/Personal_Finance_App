package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.LocalDateStringConverter;
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
    Button listUpdaterButton;

    public void initialize(){
        updateLists();
    }

    private void initExpColumns(){
        //eType.setCellValueFactory(new PropertyValueFactory<Expense,Character>("Type"));
        colExpId.setCellValueFactory(new PropertyValueFactory<Expense,Integer>("PrimaryKey"));
        colExpName.setCellValueFactory(new PropertyValueFactory<Expense,String>("Name"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<Expense,LocalDate>("DayOfAdd"));
        colExpAmount.setCellValueFactory(new PropertyValueFactory<Expense,Integer>("Amount"));
       // colExpEdit.setCellValueFactory(new PropertyValueFactory<>("Szerkeszt"));

        editableExpCols();
    }

    private void initIncColumns(){
        colIncId.setCellValueFactory(new PropertyValueFactory<Income,Integer>("PrimaryKey"));
        colIncName.setCellValueFactory(new PropertyValueFactory<Income,String>("Name"));
        colIncDate.setCellValueFactory(new PropertyValueFactory<Income,LocalDate>("DayOfAdd"));
        colIncAmount.setCellValueFactory(new PropertyValueFactory<Income,Integer>("Amount"));
        //colIncEdit.setCellValueFactory(new PropertyValueFactory<>("Update"));

        editableIncCols();
    }


    private void editableExpCols(){
        colExpName.setCellFactory(TextFieldTableCell.forTableColumn());
        colExpDate.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        colExpAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        expTableInfo.setEditable(true);

        /*colExpName.setOnEditCommit(e -> e.getTableView().getItems().
                get(e.getTablePosition().getRow()).setName(e.getNewValue()));*/

    }

    private void editableIncCols(){
        colIncName.setCellFactory(TextFieldTableCell.forTableColumn());
        colIncDate.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        colIncAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        incTableInfo.setEditable(true);
    }

    void updateExpList(){
        ObservableList<Expense> expTableData = FXCollections.observableArrayList(Loader.storage.getExpenses());

        try{
                expTableInfo.setItems(expTableData);
                logger.trace("New element was added to the Exp list", expTableInfo);

        }catch (Exception e){logger.error("Unknown error: ", e);}
    }

    void updateIncList(){
        ObservableList<Income> incTableData = FXCollections.observableArrayList(Loader.storage.getIncomes());

        try{
                incTableInfo.setItems(incTableData);
                logger.trace("New element was added to the Inc list", expTableInfo);

        }catch (Exception e){logger.error("Unknown error: ", e);}
    }

    @FXML
    public void updateLists(){
        try {
            updateExpList();
            initExpColumns();
            updateIncList();
            initIncColumns();
        }catch(Exception e){
            logger.error("Something went wrong during the update of Lists", e);
        }
    }
}
