/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application.views.modals.suppliermodal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tesoro.inventory.control.persistence.models.Supplier;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class SupplierModalPresenter implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    private Stage dialogStage;
    private boolean okClicked = false;
    private Supplier supplier;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void okButtonAction(ActionEvent event) {
        if(isInputValid()){
            
        }
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        
    }
    
    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
        nameField.textProperty().set(supplier.getName());
                
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
