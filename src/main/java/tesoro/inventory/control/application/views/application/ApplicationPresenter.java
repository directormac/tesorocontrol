/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application.views.application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;
import tesoro.inventory.control.application.views.item.ItemView;
import tesoro.inventory.control.business.PersistenceService;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class ApplicationPresenter implements Initializable {
    @FXML
    private Button itemButton;
    @FXML
    private Button stocksButton;
    @FXML
    private Button purchaseButton;

    @Inject
    PersistenceService service;
    @FXML
    private AnchorPane mainPane;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void itemButtonAction(ActionEvent event) {
        mainPane.getChildren().clear();
        mainPane.getChildren().addAll(new ItemView().getView());
    }

    @FXML
    private void stocksButtonAction(ActionEvent event) {
    }

    @FXML
    private void purchaseButtonAction(ActionEvent event) {
    }
    
}
