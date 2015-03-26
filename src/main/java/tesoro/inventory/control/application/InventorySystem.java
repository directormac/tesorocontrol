/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesoro.inventory.control.application;

import com.airhacks.afterburner.injection.Injector;
import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tesoro.inventory.control.application.views.application.ApplicationView;


public class InventorySystem extends Application {

    
    @Override
    public void start(Stage stage) throws Exception {       

        ApplicationView appView = new ApplicationView();
        Scene scene = new Scene(appView.getView());
        stage.setTitle("Tesoro Inventory");
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