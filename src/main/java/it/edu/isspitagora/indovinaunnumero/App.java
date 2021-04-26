package it.edu.isspitagora.indovinaunnumero;

import it.edu.isspitagora.indovinaunnumero.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static PrimaryController controller;

    @Override
    public void start(Stage stage) throws IOException {
        
        //Qui creo l'istanza del modello che poi dovrò passare al Controller
        Model model = new Model();
        scene = new Scene(loadFXML("primary"));
        //...E col reference al controller gli passo il model;
        controller.setModel(model);
        stage.setScene(scene);
        stage.show(); //Apri il sipario
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        Parent risultato;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        risultato = fxmlLoader.load();
        //Da qui tiro fuori il reference al controller della Scena
        controller = fxmlLoader.getController();
        return risultato;
    }

    public static void main(String[] args) {
        launch();
    }

}