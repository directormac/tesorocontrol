/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application.views.stock;

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
import tesoro.inventory.control.application.views.modals.stockmodal.StockModalPresenter;
import tesoro.inventory.control.application.views.modals.stockmodal.StockModalView;
import tesoro.inventory.control.business.PersistenceService;
import tesoro.inventory.control.persistence.models.Stock;


public class StockPresenter implements Initializable {
    
    @FXML
    private Button addStockButton;
    @FXML
    private Button removeStockButton;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Stock> stockTableView;
    @FXML
    private TableColumn<Stock, Date> dateArrivedColumn;
    @FXML
    private TableColumn<Stock, String> nameColumn;
    @FXML
    private TableColumn<Stock, String> quantityColumn;

    @FXML
    private AnchorPane contentPane;

    @Inject
    PersistenceService service;
   
    FilteredList<Stock> filteredList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dateArrivedColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDateArrived()));
        nameColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getItem().getName()));
        quantityColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getQuantity().toString()));
        
        refreshList();
    }    

    @FXML
    private void removeStockButtonAction(ActionEvent event) {
        service.getStocks().remove(stockTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void addStockButtonAction(ActionEvent event) {
        Stock tempStock = new Stock();
        boolean isOkClicked = showStockModal(tempStock);
        if(isOkClicked){
            service.getStocks().add(tempStock);
        }
    }
    
    private boolean showStockModal(Stock stock){
        StockModalView smv = new StockModalView();
        StockModalPresenter smp = (StockModalPresenter)smv.getPresenter();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Stock");
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner((Stage)contentPane.getScene().getWindow());
        Scene scene = new Scene(smv.getView());
        dialogStage.setScene(scene);
        smp.setDialogStage(dialogStage);
        smp.setStock(stock);
        dialogStage.showAndWait();
        return smp.isOkClicked();
    }

    private void refreshList() {
        filteredList = new FilteredList<>(service.getStocks(), p -> true);
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
