/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application.views.item;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tesoro.inventory.control.application.views.modals.itemmodal.ItemModalPresenter;
import tesoro.inventory.control.application.views.modals.itemmodal.ItemModalView;
import tesoro.inventory.control.persistence.models.Item;
import tesoro.inventory.control.persistence.models.Supplier;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ItemPresenter implements Initializable {
    @FXML
    private ComboBox<Supplier> supplierComboBox;
    @FXML
    private Button addSupplierButton;
    @FXML
    private Button editSupplierButton;
    @FXML
    private Button addNewItem;
    @FXML
    private Button editItem;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Item> itemTableView;
    @FXML
    private TableColumn<Item, String> itemNameColumn;
    @FXML
    private TableColumn<Item, String> itemSizeColumn;
    @FXML
    private TableColumn<Item, String> itemPrizeColumn;

    @FXML
    private AnchorPane contentPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void addSupplierButtonAction(ActionEvent event) {
        
    }

    @FXML
    private void editSupplierButtonAction(ActionEvent event) {
        
    }

    @FXML
    private void addNewItemButton(ActionEvent event) {
        Item tempItem = new Item();
        boolean okClicked = showItemModal(tempItem);
        if(okClicked){
            
        }
    }

    @FXML
    private void editItemButton(ActionEvent event) {
        
        
    }
    
    private boolean showItemModal(Item item){
        ItemModalView imv = new ItemModalView();
        ItemModalPresenter imp = (ItemModalPresenter) imv.getPresenter();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Category");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner((Stage)contentPane.getScene().getWindow());
        Scene scene = new Scene(imv.getView());
        dialogStage.setScene(scene);
        imp.setDialogStage(dialogStage);
        imp.setItem(item);
        dialogStage.showAndWait();
        return imp.isOkClicked();
    }
    
    private void showSupplierModal(Supplier supplier){
        
    }
    
    
    
    
}
