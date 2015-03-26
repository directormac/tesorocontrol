/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application.views.modals.itemmodal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tesoro.inventory.control.persistence.models.Item;
import tesoro.inventory.control.persistence.models.Purchase;
import tesoro.inventory.control.persistence.models.Supplier;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ItemModalPresenter implements Initializable {
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField bottleSizeField;
    @FXML
    private TextField prizeField;
    @FXML
    private ComboBox<Supplier> supplierComboBox;
    
    
    private Stage dialogStage;
    private boolean okClicked = false;
    private Item item;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void okButtonAction(ActionEvent event) {
         if(isInputValid()){
            item.setName(nameField.textProperty().get());
            item.setPrice(Double.parseDouble(prizeField.textProperty().get()));
            item.setSupplier(supplierComboBox.getSelectionModel().getSelectedItem());
            okClicked = true;
            dialogStage.close();
        }
        
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        dialogStage.close();
    }
    
    public void setItem(Item item){
        this.item = item;
        nameField.textProperty().set(item.getName());
        bottleSizeField.textProperty().set(item.getBottleSize());
        if(item.getSupplier()!= null){
            supplierComboBox.getSelectionModel().select(item.getSupplier());
        }
        
    }
    
    public void setDialogStage(Stage stage){
        this.dialogStage = stage;
    }
    
    public boolean  isInputValid(){
        String errorMessage = "";
        if(nameField.textProperty().get().isEmpty()){
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
