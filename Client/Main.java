package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/*

 */
public class Main extends Application
        implements Observer<Model, Object> {

    private static final Model cardModel = new Model();
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
        stage.setScene(new Scene(window));
        stage.show();
        update(cardModel, null);
    }

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
