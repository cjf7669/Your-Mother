
public class CardsConfig {
	
	
	private int[][] cards = new int[13][13];
	
	public void CardsConfig() {
		//Creates each card element within the array
		for(int row = 0; row < cards.length; row++) {
			for(int col = 0; col < cards[row].length; col++) {
				cards[row][col] = col;
			}
		}
				
	}
	
	
	
	
	
	
	
	
	
	
	
}