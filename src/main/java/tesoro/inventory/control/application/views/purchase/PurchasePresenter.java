/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application.views.purchase;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.inject.Inject;
import tesoro.inventory.control.application.views.modals.purchasemodal.PurchaseModalPresenter;
import tesoro.inventory.control.application.views.modals.purchasemodal.PurchaseModalView;
import tesoro.inventory.control.business.FXImaging;
import tesoro.inventory.control.business.PersistenceService;
import tesoro.inventory.control.persistence.models.Purchase;


public class PurchasePresenter implements Initializable {
    @FXML
    private TextField searchField;
    @FXML
    private Button removeStockButton,saveTable;
    
    @FXML
    private Button addStockButton;
    @FXML
    private TableView<Purchase> stockTableView;
    @FXML
    private TableColumn<Purchase, Date> datePurchasedColumn;
    
    @FXML
    private TableColumn<Purchase, String> nameColumn;
    
    @FXML
    private TableColumn<Purchase, String> quantityColumn;
    
    @FXML
    private AnchorPane contentPane;

    @Inject
    PersistenceService service;
   
    FilteredList<Purchase> filteredList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datePurchasedColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDatePurchased()));
        nameColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getItem().getName()));
        quantityColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getQuantity().toString()));
        refreshList();
    }    

    @FXML
    private void removeStockButtonAction(ActionEvent event) {
        service.getPurchases().remove(stockTableView.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void saveTableAction(ActionEvent event){
        FXImaging imaging = new FXImaging();
        imaging.nodeToImage(stockTableView, service.getPurchases(), new File("c:/tesoro/raport.png"));
        

    }

    @FXML
    private void addStockButtonAction(ActionEvent event) {
        Purchase tempPurchase = new Purchase();
        boolean isOkClicked = showPurchaseModal(tempPurchase);
        if(isOkClicked){
            service.getPurchases().add(tempPurchase);
        }
    }
    
    private boolean showPurchaseModal(Purchase purchase){
        PurchaseModalView pmv = new PurchaseModalView();
        PurchaseModalPresenter pmp = (PurchaseModalPresenter)pmv.getPresenter();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Category");
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner((Stage)contentPane.getScene().getWindow());
        Scene scene = new Scene(pmv.getView());
        dialogStage.setScene(scene);
        pmp.setDialogStage(dialogStage);
        pmp.setPurchase(purchase);
        dialogStage.showAndWait();
        return pmp.isOkClicked();
    }

    private void refreshList() {
        filteredList = new FilteredList<>(service.getPurchases(), p -> true);
        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        filteredList.setPredicate(purchase ->{
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (purchase.getItem().getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (purchase.getItem().getSupplier().getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false; 
            });
        });
        stockTableView.setItems(filteredList);
    }
}
