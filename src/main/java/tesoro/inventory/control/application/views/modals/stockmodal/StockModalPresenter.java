/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application.views.modals.stockmodal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.inject.Inject;
import tesoro.inventory.control.business.PersistenceService;
import tesoro.inventory.control.persistence.models.Item;
import tesoro.inventory.control.persistence.models.Stock;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class StockModalPresenter implements Initializable {
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox<Item> itemComboBox;
    @FXML
    private TextField quantityField;

    
    @Inject
    PersistenceService service;
    
    
    Stock stock;
    Stage dialogStage;
    private boolean okClicked = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemComboBox.setItems(service.getItems());
        
        
        
    }    

    @FXML
    private void okButtonAction(ActionEvent event) {
         if(isInputValid()){
            stock.setQuantity(Integer.parseInt(quantityField.textProperty().get()));
            stock.setItem(itemComboBox.getSelectionModel().getSelectedItem());
            okClicked = true;
            dialogStage.close();
        }
        
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        dialogStage.close();
    }
    
    public void setStock(Stock stock){
        this.stock = stock;
        quantityField.textProperty().set(stock.getQuantity().toString());
        if(stock.getItem() != null){
            itemComboBox.getSelectionModel().select(stock.getItem());
        }
        
    }
    
    public void setDialogStage(Stage stage){
        this.dialogStage = stage;
    }
    
    public boolean  isInputValid(){
        String errorMessage = "";
        if(quantityField.textProperty().get().isEmpty()){
            errorMessage += "Namefield is empty";
        }
        if(errorMessage.length() == 0){
            return true;
        }else{
            
            return false;
        }
        
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    
}
