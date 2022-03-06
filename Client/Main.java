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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
Main class that runs for the client devices to connect to the server
 */
public class Main extends Application
        implements Observer<Model, Object> {

    static Socket clientSocket;
    static PrintWriter out;
    static BufferedReader in;
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
    private BorderPane topBorder;
    private FlowPane topLeftButtons;
    private BorderPane bottomBorder;


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

        //update(cardModel, null);
    }

////////////////////////////////////////////////////////////////////
    public Scene sceneCreatorAfterDeal(){
        window = new BorderPane();
        BorderPane top = topBorder();
        window.setTop(top);
        BorderPane bottom = bottomBorder();
        window.setBottom(bottom);

        window.setBackground(background);
        afterDeal = new Scene(window);
        return afterDeal;
    }

    public Label playerBalanceLabel(){
        diningDollars = new Label("Account Balance: $" + cardModel.getBallance());
        diningDollars.setAlignment(Pos.TOP_RIGHT);
        return diningDollars;
    }

    public BorderPane topBorder(){
        topBorder = new BorderPane();

        Label moneyLabel = playerBalanceLabel();
        moneyLabel.setAlignment(Pos.TOP_RIGHT);
        topBorder.setRight(moneyLabel);

        Button quit = new Button("Quit");
        quit.setOnAction(actionEvent -> {
            out.println("QUIT");
            out.flush();
            stage.close();
            beforeDealStage.close();
        });//TODO ADD SO THAT WHEN CLICKED SENDS QUIT TO SERVER
        Button surrender = new Button("Surrender");
        topLeftButtons = new FlowPane();
        topLeftButtons.getChildren().addAll(quit, surrender);
        topLeftButtons.setAlignment(Pos.TOP_LEFT);
        topBorder.setLeft(topLeftButtons);

        Label currentBet = new Label("Current Bet: $" + cardModel.getBet());
        currentBet.setAlignment(Pos.TOP_LEFT);
        topBorder.setCenter(currentBet);

        return topBorder;
    }

    public BorderPane bottomBorder(){
        bottomBorder = new BorderPane();

        BorderPane leftStuff = new BorderPane();
        Label handValue = new Label("Current Hand Value: "); //TODO ADD FUNCTION IN MODEL
        Label tillTO = new Label("Number till 21: "); //TODO ADD FUNCTION IN MODEL
        handValue.setAlignment(Pos.CENTER);
        tillTO.setAlignment(Pos.CENTER);
        leftStuff.setBottom(tillTO);
        leftStuff.setTop(handValue);
        bottomBorder.setLeft(leftStuff);

        BorderPane rightStuff = new BorderPane();
        FlowPane buttonLayer1 = new FlowPane();
        FlowPane buttonLayer2 = new FlowPane();
        Button hit = new Button("Hit");
        Button stand = new Button("Stand");
        Button dd = new Button("Double Down");
        Button split = new Button("Split");
        buttonLayer1.getChildren().addAll(hit, stand);
        buttonLayer1.setAlignment(Pos.CENTER);
        buttonLayer2.getChildren().addAll(dd, split);
        buttonLayer2.setAlignment(Pos.CENTER);
        rightStuff.setTop(buttonLayer1);
        rightStuff.setBottom(buttonLayer2);
        bottomBorder.setRight(rightStuff);

        //TODO CENTER CARDS

        return bottomBorder;
    }

    public Scene sceneCreatorBeforeDeal(){
        beforeDealWindow = new BorderPane();
        beforeDealWindow.setBottom(createButtonPanel());
        BorderPane.setAlignment(beforeDealButtonPanel, Pos.BOTTOM_CENTER);

        betAmount = new Label("Bet amount: $" + cardModel.getBet());
        betAmount.setAlignment(Pos.CENTER_LEFT);
        accountAmount = new Label("Account Balance: $" + cardModel.getBallance());
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
        bet.setOnAction(actionEvent -> {
            beforeDealStage.close();
            update(cardModel, null);
        });
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
        else {
            System.out.println("Main Stage Updated");
            stage.setScene(sceneCreatorAfterDeal());
            stage.show();
        }
    }

    /**
     * Main class that starts everything
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException {
        //TODO create connection to server here
        clientSocket = new Socket("192.168.2.178",6666);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        Application.launch(args); //Starts GUI
    }
}
