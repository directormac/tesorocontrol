/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application.views.item;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ItemPresenter implements Initializable {
    @FXML
    private TableView<?> itemTableView;
    @FXML
    private TableColumn<?, ?> itemNameColumn;
    @FXML
    private TableColumn<?, ?> itemSizeColumn;
    @FXML
    private TableColumn<?, ?> itemPrizeColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
