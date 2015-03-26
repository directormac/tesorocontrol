/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application;

import com.airhacks.afterburner.injection.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.inject.Inject;
import tesoro.inventory.control.application.views.application.ApplicationView;
import tesoro.inventory.control.business.PersistenceService;


public class InventorySystem extends Application {


    
    @Override
    public void start(Stage stage) throws Exception {       

        ApplicationView appView = new ApplicationView();
        Scene scene = new Scene(appView.getView());
        stage.setTitle("followme.fx");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}