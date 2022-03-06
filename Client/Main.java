package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
Main class that runs for the client devices to connect to the server
 */
public class Main extends Application
        implements Observer<Model, Object> {

    private static final Model cardModel = new Model();
    private final int HEIGHT = 750;
    private final int WIDTH = 1500;
    private Stage stage;
    private BorderPane window;

    /**
     *
     */
    @Override
    public void init(){
        window = new BorderPane();
        //update(cardModel, null);
        cardModel.addObserver(this);
    }

    /**
     *
     */
    @Override
    public void start(Stage stage){
        this.stage = stage;
        stage.setTitle("RIT BlackJack");
        stage.setScene(sceneCreator());
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false);
        stage.show();
        update(cardModel, null);
    }

////////////////////////////////////////////////////////////////////
    public Scene sceneCreator(){
        Scene scene = new Scene(window);
        scene.setFill(Color.web("#057520"));
        return scene;
    }



////////////////////////////////////////////////////////////////////

    /**
     *
     */
    @Override
    public void update(Model m, Object arg) {
        stage.show();
    }

    /**
     * Main class that starts everything
     * @param args command line arguments
     */
    public static void main(String[] args){
        //TODO create connection to server here
        Application.launch(args); //Starts GUI
    }
}
