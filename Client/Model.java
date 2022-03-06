package Client;

import java.util.LinkedList;
import java.util.List;

public class Model {

    private final List<Observer< Model, Object >> observers;
	private int accountBal, betAmount;

    public Model(){
        this.observers= new LinkedList<>();
		betAmount = 50;
		accountBal = 3000;
    }
	
    //TODO EVERYTHINGGGGGG
    public void addObserver( Observer< Model, Object > obs ) {
        this.observers.add( obs );
    }

    private void announce( String arg ) {
        for ( var obs : this.observers ) {
            obs.update( this, arg );
        }
    }
	
	public void lossBet() {
		//if bet is lost, bet is subtracted from bal
		accountBal = accountBal - betAmount;
		if(accountBal < 0) {
			accountBal = 0;
		}
	}
	
	public void gainBet() {
		//if bet is won, bet is added to bal
		accountBal = accountBal + betAmount;
		if(accountBal < 0) {
			accountBal = 0;
		}
	}
	
	public void incrementBet(boolean Direct) {
		//to increment bet, false decrements by 50, true does oposite
		if(Direct == true) {
			betAmount += 50;
		} else {
			betAmount -= 50;
		}		
		checkBetvBal();
		announce("bet");
	}
	
	public void checkBetvBal() {
		if(betAmount > accountBal) {
			betAmount -= 50;
		} else if(betAmount == 0) {
			betAmount += 50;
		}
	}
	
	///---Setters&Getters---///
	public int getBallance() {
		return accountBal;
	}
	
	public void setBallance(int temp) {
		accountBal = temp;
	}
	
	public int getBet() {
		return betAmount;
	}
	
	public void setBet(int temp) {
		betAmount = temp;
	}
		
}
