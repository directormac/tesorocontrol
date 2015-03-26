/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application.views.modals.purchasemodal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import tesoro.inventory.control.persistence.models.Purchase;
import tesoro.inventory.control.persistence.models.Stock;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class PurchaseModalPresenter implements Initializable {
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox<Item> itemComboBox;
    @FXML
    private TextField quantityField;

    Purchase purchase;
    Stage dialogStage;
    private boolean okClicked = false;
    
    @Inject
    PersistenceService service;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemComboBox.setItems(service.getItems());
        
    }    

    @FXML
    private void okButtonAction(ActionEvent event) {
         if(isInputValid()){
            purchase.setQuantity(Integer.parseInt(quantityField.textProperty().get()));
            purchase.setItem(itemComboBox.getSelectionModel().getSelectedItem());
            okClicked = true;
            dialogStage.close();
        }
        
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        dialogStage.close();
    }
    
    public void setPurchase(Purchase purchase){
        this.purchase = purchase;
        quantityField.textProperty().set(purchase.getQuantity().toString());
        if(purchase.getItem() != null){
            itemComboBox.getSelectionModel().select(purchase.getItem());
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
            List<Stock> currentStocks = service.getStocks();
            int currentQuantity = Integer.parseInt(quantityField.textProperty().get());
            int stockQuantity = 0;
            List<Stock> subList = new ArrayList<>();
            for(Stock stock: currentStocks){
                if(stock.getItem().equals(itemComboBox.getSelectionModel().getSelectedItem())){
                    if(currentQuantity <= stock.getQuantity() ){
                        int larger = stock.getQuantity();
                        stock.setQuantity(larger-currentQuantity);
                        service.updateStock(stock);
                        break;
                    }
                    if(currentQuantity >= stock.getQuantity()){
                        stockQuantity += stock.getQuantity();
                        
                    }
                }
            }
            
            
            
            return true;
        }else{
            
            return false;
        }
        
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    
}
