package Server;

public class CardsConfig {
	
	private int value;
	private int[][] cards = new int[4][13];
	private int[][] inCards = new int[4][13];
	
	public CardsConfig() {
		//Creates each card element within the array
		for(int row = 0; row < cards.length; row++) {
			for(int col = 0; col < cards[row].length; col++) {
				cards[row][col] = col;
			}
		}
		
		value = 0;
	}
	
	public String currentCard(int row, int col) {
		switch (cards[row][col]) {
			case 0: return "Ace";
			case 1: return "Two";
			case 2: return "Three";
			case 3: return "Four";
			case 4: return "Five";
			case 5: return "Six";
			case 6: return "Seven";
			case 7: return "Eight";
			case 8: return "Nine";
			case 9: return "Ten";
			case 10: return "Jack";
			case 11: return "Queen";
			case 12: return "King";
			default: return "Default"; 
		}
	}
	
	public void valCheck() {
		
		
	}
	
	
	public void gameRun() {
		int row1 = randomFrom(0,3);
		int col1 = randomFrom(0,12);
		
		if (inCards[row1][col1] != 1 && inCards[row2][col2] != 1) {
			//get values of each card picked or use valcheck prob because why get string when get value numbnuts
			String value1 = currentCard(row1, col1);
			String value2 = currentCard(row2, col2);
			
			//add values
			value = value1 + value2;
			//ask if user wants to draw again
			//graphics request here
			if yes
			value += value3
			//repeat
			//check for 21
		
			
		} else {
			gameRun();
		}
		
		
	}
	
	public int randomFrom(int low, int high) {
		int randNum = 0;
		randNum = (int) (Math.random()*(high-low) + low);
		return randNum;
	}
	
	
	
	
	
}