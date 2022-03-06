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
    private Stage beforeDealStage;
    private Background background;
    private Color green;
    private Button plus;
    private Button minus;
    private Button bet;
    private Label betAmount;
    private Label accountAmount;

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
        stage.setScene(sceneCreatorAfterDeal());
        stage.setTitle("RIT BlackJack");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false);
        stage.show();

        beforeDealStage = new Stage();
        beforeDealStage.setTitle("Enter your bet for the round");
        beforeDealStage.setScene(sceneCreatorBeforeDeal());
        beforeDealStage.show();

        update(cardModel, null);
    }

////////////////////////////////////////////////////////////////////
    public Scene sceneCreatorAfterDeal(){
        Label moneyLabel = playerBalanceLabel();
        window.setTop(moneyLabel);
        BorderPane.setAlignment(moneyLabel,Pos.TOP_RIGHT);

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
        beforeDealWindow = new BorderPane();
        beforeDealWindow.setBottom(createButtonPanel());
        BorderPane.setAlignment(beforeDealButtonPanel, Pos.BOTTOM_CENTER);

        betAmount = new Label("Bet amount: $" + cardModel.getBet()); //TODO ADD BET AMOUNT INCREMENTATION
        betAmount.setAlignment(Pos.CENTER_LEFT);
        accountAmount = new Label("Account Balance: $" + cardModel.getBallance()); //TODO ADD ACCOUNT BALANCE
        accountAmount.setAlignment(Pos.CENTER_RIGHT);
        beforeDealWindow.setLeft(betAmount);
        beforeDealWindow.setRight(accountAmount);

        beforeDeal = new Scene(beforeDealWindow);
        return beforeDeal;
    }

    public FlowPane createButtonPanel(){
        beforeDealButtonPanel = new FlowPane();
        plus = new Button("+");
        plus.setMinSize(25,25);
        plus.setOnAction(actionEvent -> cardModel.incrementBet(true));
        minus = new Button("-");
        minus.setMinSize(25,25);
        minus.setOnAction(actionEvent -> cardModel.incrementBet(false));
        bet = new Button("Bet");
        bet.setMinSize(25,25);
        bet.setOnAction(actionEvent -> beforeDealStage.close());
        beforeDealButtonPanel.getChildren().addAll(minus, plus, bet);
        beforeDealButtonPanel.setAlignment(Pos.CENTER);
        return beforeDealButtonPanel;
    }

////////////////////////////////////////////////////////////////////

    /**
     *
     */
    @Override
    public void update(Model m, Object arg) {
        if ((String) arg == "bet"){
            System.out.println("Bet Changed: "+cardModel.getBet());
            beforeDealStage.setScene(sceneCreatorBeforeDeal());
            beforeDealStage.show();
        }
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
