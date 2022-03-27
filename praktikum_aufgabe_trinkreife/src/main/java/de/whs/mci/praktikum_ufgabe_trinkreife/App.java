package de.whs.mci.praktikum_ufgabe_trinkreife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.application.Platform;

/**
 * JavaFX App 
 * Abidi Abdelbaset 
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent root = fxmlLoader.load();

        try {
            var controller = (Controller) fxmlLoader.getController();

            controller.trinkwein_diagramm(2021, 2021, 0);//1 
//            controller.trinkwein_diagramm(2021, 2021, 1);//2  
//            controller.trinkwein_diagramm(2021, 2021, 2);//3 
//            controller.trinkwein_diagramm(2021, 2021, 4);//4 
//            controller.trinkwein_diagramm(2021, 2021, 7);//5           
//            controller.trinkwein_diagramm(2019, 2021, 7);//6  
//            controller.trinkwein_diagramm(2017, 2021, 7);//7  
//            controller.trinkwein_diagramm(2014, 2021, 7);//8 
//            controller.trinkwein_diagramm(2013, 2021, 7);//9  
//            controller.trinkwein_diagramm(2010, 2021, 7);//10 
//            controller.trinkwein_diagramm(2008, 2021, 7);//11 
//            controller.trinkwein_diagramm(2007, 2021, 7);//12 
//            controller.trinkwein_diagramm(2006, 2021, 7);//13
//            controller.trinkwein_diagramm(2007, 2021, 20);//14  
            scene = new Scene(root, 650, 150);
            stage.setScene(scene);
            stage.setTitle("Trinkreife");
            stage.show();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            Platform.exit();
        }

    }

    public static void main(String[] args) {
        launch();
    }

}
