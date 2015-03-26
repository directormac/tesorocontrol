/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application.views.item;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
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
import javafx.stage.StageStyle;
import javax.inject.Inject;
import javax.persistence.Persistence;
import tesoro.inventory.control.application.views.modals.itemmodal.ItemModalPresenter;
import tesoro.inventory.control.application.views.modals.itemmodal.ItemModalView;
import tesoro.inventory.control.application.views.modals.suppliermodal.SupplierModalPresenter;
import tesoro.inventory.control.application.views.modals.suppliermodal.SupplierModalView;
import tesoro.inventory.control.business.PersistenceService;
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
    private Button addSupplierButton,removesupplierButton,removeItemButton;
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
    private TableColumn<Item, Double> itemPrizeColumn;
    @FXML
    private TableColumn<Item, String> supplierColumn;
    
    @FXML
    private AnchorPane contentPane;
    
    @Inject
    PersistenceService service;

    FilteredList<Item> filteredItems;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       supplierComboBox.setItems(service.getSuppliers());
       itemNameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
       itemSizeColumn.setCellValueFactory(data -> data.getValue().bottleSizeProperty());
       itemPrizeColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrice()));
       supplierColumn.setCellValueFactory(data -> data.getValue().getSupplier().nameProperty());
       
       removeItemButton.disableProperty().bind(itemTableView.getSelectionModel().selectedItemProperty().isNull());
       editItem.disableProperty().bind(itemTableView.getSelectionModel().selectedItemProperty().isNull());
       
       editSupplierButton.disableProperty().bind(supplierComboBox.getSelectionModel().selectedItemProperty().isNull());
       removesupplierButton.disableProperty().bind(supplierComboBox.getSelectionModel().selectedItemProperty().isNull());
       refreshList();
    }    

    @FXML
    private void addSupplierButtonAction(ActionEvent event) {
        Supplier tempSupplier = new Supplier();
        boolean okClicked = showSupplierModal(tempSupplier);
        if(okClicked){
           service.getSuppliers().add(tempSupplier);
        }
    }

    @FXML
    private void editSupplierButtonAction(ActionEvent event) {
        Supplier tempSupplier = supplierComboBox.getSelectionModel().getSelectedItem();
        boolean okClicked = showSupplierModal(tempSupplier);
        if(okClicked){
           service.updateSupplier(tempSupplier);
           refreshList();
           supplierComboBox.getSelectionModel().select(tempSupplier);
        }
    }

    @FXML
    private void addNewItemButton(ActionEvent event) {
        Item tempItem = new Item();
        boolean okClicked = showItemModal(tempItem);
        if(okClicked){
            service.getItems().add(tempItem);
        }
    }

    @FXML
    private void editItemButton(ActionEvent event) {
        Item tempItem = itemTableView.getSelectionModel().getSelectedItem();
        boolean okClicked = showItemModal(tempItem);
        if(okClicked){
           service.updateItem(tempItem);
           refreshList();
           itemTableView.getSelectionModel().select(tempItem);
        }
    }
    @FXML
    private void removeItemButtonAction(ActionEvent event) {
        service.getItems().remove(itemTableView.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void removesupplierButtonAction(ActionEvent event) {
        service.getSuppliers().remove(supplierComboBox.getSelectionModel().getSelectedItem());
    }
    
    
    private boolean showItemModal(Item item){
        ItemModalView imv = new ItemModalView();
        ItemModalPresenter imp = (ItemModalPresenter) imv.getPresenter();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Item");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initOwner((Stage)contentPane.getScene().getWindow());
        Scene scene = new Scene(imv.getView());
        dialogStage.setScene(scene);
        imp.setDialogStage(dialogStage);
        imp.setItem(item);
        dialogStage.showAndWait();
        return imp.isOkClicked();
    }
    
    private boolean showSupplierModal(Supplier supplier){
        SupplierModalView smv = new SupplierModalView();
        SupplierModalPresenter smp = (SupplierModalPresenter)smv.getPresenter();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Supplier");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initOwner((Stage)contentPane.getScene().getWindow());
        Scene scene = new Scene(smv.getView());
        dialogStage.setScene(scene);
        smp.setDialogStage(dialogStage);
        smp.setSupplier(supplier);
        dialogStage.showAndWait();
        return smp.isOkClicked();
    }
    
    private void refreshList(){
        filteredItems = new FilteredList<>(service.getItems(), p -> true);
        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        filteredItems.setPredicate(item ->{
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (item.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (item.getSupplier().getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false; 
            });
        });
        itemTableView.setItems(filteredItems);
        
    }
    
}
