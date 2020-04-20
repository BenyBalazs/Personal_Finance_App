package org.openjfx;

import Database.DB;
import Database.Loader;
import Modells.Expense;
import Modells.Income;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;


public class PrimaryController {

    private static Logger logger = LoggerFactory.getLogger("PrimaryController.class");
    @FXML
    ChoiceBox expenseOrIncome;
    @FXML
    ComboBox nameField;
    @FXML
    DatePicker dateDatePicker;
    @FXML
    Spinner moneySpinner;
    @FXML
    Button addButton;
    @FXML
    Label expensesSumLabel;
    @FXML
    ListView listOfRecentlyAdded;
    @FXML
    Button editButton;
    @FXML
    Label balanceLabel;
    @FXML
    Button pieChartOpener;
    @FXML
    Label labelOfWarnMessage;
    @FXML
    Label incomesSumLabel;


    public void initialize(){
        Loader.loadExpenseTable();
        Loader.loadIncomeTable();
        update();
    }

    private void update (){

        incomesSumLabel.setText(Loader.storage.getSumOfIncomes().toString() + " Ft");
        expensesSumLabel.setText(Loader.storage.getSumOfExpenses().toString() + " Ft");

        if(Loader.storage.getBalance()>0)
            balanceLabel.setTextFill(Paint.valueOf("#12c548"));
        else
            balanceLabel.setTextFill(Paint.valueOf("#dc143c"));
        balanceLabel.setText(Loader.storage.getBalance().toString() + " Ft");

    }

    private void warnMessage(String s){
        if(!labelOfWarnMessage.isVisible()){
            labelOfWarnMessage.setVisible(true);
        }
        labelOfWarnMessage.setText(s);
    }
    private void createInstanceOfExpense(EntityManager em){

        Expense tmp = new Expense(nameField.getValue().toString(),
                (Integer) moneySpinner.getValue(), dateDatePicker.getValue());
        em.getTransaction().begin();
        em.persist(tmp);
        em.getTransaction().commit();
        Loader.storage.getExpenses().add(tmp);
        listOfRecentlyAdded.getItems().add(Loader.storage.getExpenses().
                get(Loader.storage.getExpenses().size() - 1).toString());
        logger.trace("User added a new Expense to the list");
    }
    private void createInstanceOfIncome(EntityManager em){

        Income tmp = new Income(nameField.getValue().toString(),
                (Integer) moneySpinner.getValue(),dateDatePicker.getValue());
        Loader.storage.getIncomes().add(tmp);
        em.getTransaction().begin();
        em.persist(tmp);
        em.getTransaction().commit();
        listOfRecentlyAdded.getItems().add(Loader.storage.getIncomes().
                get(Loader.storage.getIncomes().size() - 1).toString());
        logger.trace("user added a new Income to the list");
    }

    @FXML
    public void addElementToList() {
        EntityManager em = DB.getEntityManager();
        try{

            if (expenseOrIncome.getValue().toString().equals("Kiadás")){
                createInstanceOfExpense(em);
            }
            else {
                createInstanceOfIncome(em);
            }
            update();
            labelOfWarnMessage.setVisible(false);

        } catch (NullPointerException e) {
            logger.error("Name field was empty ", e);
            warnMessage("A név mező kitöltése kötelező!");
        } catch (VerifyError e){
            logger.error("The Date field was null", e);
            warnMessage("A dátum mező kitöltése kötelező");
        } catch (Exception e){
            logger.error("@Something went wrong {}", e);
            warnMessage("Valami tönkrement hoppá");
        }finally {
            em.close();
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
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    update();
                }
            });
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
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    update();
                }
            });
            logger.trace("Function called  openPieChartWindow");
        }catch (Exception e) {
            logger.error("Error when trying to PieChartWindow: " ,e);
        }
    }
}
