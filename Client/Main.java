package Client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/*
Main class that runs for the client devices to connect to the server
 */
public class Main extends Application
        implements Observer<Model, Object> {

    private static final Model cardModel = new Model();
    private final int HEIGHT = 600;
    private final int WIDTH = 1000;
    private Stage stage;
    private BorderPane window;
    private Label diningDollars;
    private Scene beforeDeal;
    private Scene afterDeal;
    private FlowPane beforeDealButtonPanel;
    private BorderPane beforeDealWindow;
    private Background background;
    private Color green;

    /**
     *
     */
    @Override
    public void init(){
        window = new BorderPane();
        beforeDealButtonPanel = new FlowPane();
        beforeDealWindow = new BorderPane();
        green = Color.web("#057520");
        background = new Background(new BackgroundFill(green, CornerRadii.EMPTY, Insets.EMPTY));
        cardModel.addObserver(this);
    }

    /**
     *
     */
    @Override
    public void start(Stage stage){
        this.stage = stage;
        stage.setTitle("RIT BlackJack");
        stage.setScene(sceneCreatorBeforeDeal());
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false);
        stage.show();
        update(cardModel, null);
    }

////////////////////////////////////////////////////////////////////
    public Scene sceneCreatorAfterDeal(){
        Label moneyLabel = playerBalanceLabel();
        window.setTop(moneyLabel);
        window.setAlignment(moneyLabel,Pos.TOP_RIGHT);

        window.setBackground(background);
        afterDeal = new Scene(window);
        return afterDeal;
    }

    public Label playerBalanceLabel(){
        diningDollars = new Label("$3000"); //TODO Change to reflect accurate player balance amount
        diningDollars.setAlignment(Pos.TOP_RIGHT);
        return diningDollars;
    }

    public Scene sceneCreatorBeforeDeal(){
        Button plus = new Button("+");
        Button minus = new Button("-");
        Button bet = new Button("Bet");
        beforeDealButtonPanel.getChildren().addAll(plus, minus, bet);
        beforeDealWindow.setBottom(beforeDealButtonPanel);
        beforeDealWindow.setAlignment(beforeDealButtonPanel, Pos.BOTTOM_CENTER);

        Label betAmount = new Label("Bet amount: $50"); //TODO ADD BET AMOUNT INCREMENTATION

        beforeDeal = new Scene(beforeDealWindow);
        return beforeDeal;
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
