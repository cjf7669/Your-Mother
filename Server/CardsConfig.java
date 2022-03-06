
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
			case 0: return "Ace"; break;
			case 1: return "Two"; break;
			case 2: return "Three"; break;
			case 3: return "Four"; break;
			case 4: return "Five"; break;
			case 5: return "Six"; break;
			case 6: return "Seven"; break;
			case 7: return "Eight"; break;
			case 8: return "Nine"; break;
			case 9: return "Ten"; break;
			case 10: return "Jack"; break;
			case 11: return "Queen"; break;
			case 12: return "King"; break;
			default: return "Default"; 
		}
	}
	
	public void valCheck() {
		
		
	}
	
	
	public void gameRun() {
		row1 = randomFrom(0,3);
		col1 = randomFrom(0,12);
		
		if(inCards[row][col] doesntequal 1 && row2col2 same) {
			//get values of each card picked or use valcheck prob because why get string when get value numbnuts
			value1 = currentCard(row1, col1)
			value2 = currentCard(row2, col2)
			
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