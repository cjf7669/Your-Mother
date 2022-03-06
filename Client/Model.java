package Client;

import java.util.LinkedList;
import java.util.List;

public class Model {

    private final List<Observer< Model, Object >> observers;
	private int accountBal, betAmount;

    public Model(){
        this.observers= new LinkedList<>();
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
		//if bet is lost 
		
		
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
