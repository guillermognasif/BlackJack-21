package blackJack;

/**
 * This class stores and displays information about a Player in the blackJack game.
 * 
 * @author Tommy George & Guillermo Nasif
 */
/**
 * 
 */
public class Player 
{
	private int wallet;
	private int wins;
	private int losses;
	private double winPercentage;
	
	/**
	 * Initializes and constructs the Player's class
	 * @param wallet initial amount of money to bet
	 * @param wins number of wins
	 * @param losses number of losses
	 * @param chanceToWin based on previous games what are the odds of winning
	 */
	public Player(int wallet, int wins, int losses) 
	{
		this.wallet = wallet;
		this.wins = wins;
		this.losses = losses;
		winPercentage = 0;
	}

	/**
	 * Gets the amount in the player's wallet.
	 * @return wallet
	 */
	public int getWallet() {
		return wallet;
	}

	/**
	 * Updates the amount in the players wallet.
	 * @param wallet
	 */
	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	/**
	 * Gets the amount of the player's wins.
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Updates the amount of wins the player has.
	 * @param wins
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * Gets the amount of the player's losses.
	 * @return
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * Updates the amount of losses the player has.
	 * @param losses
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}

	/**
	 * Updates the percentage of hands the player has won.
	 */
	public void setWinPercentage() {
		if (wins == 0) {
			winPercentage = 0;
		}
		if (losses == 0) {
			winPercentage = 100;
		}
		else {
			winPercentage = (wins * 100) / (wins + losses);
		}
	}
	
	/**
	 * Gets the percentage of hands the player has won.
	 * @return winPercentage
	 */
	public double getWinPercentage() {
		return winPercentage;
	}
}
