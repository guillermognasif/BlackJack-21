package blackJack;

import java.util.ArrayList;

/**
 * This class serves as the programs controller.
 * It manages the model classes and will give the view class updated 
 * information from the models.
 * 
 * @author Guillermo Nasif & Tommy George
 */
public class tableUpdate 
{
	private int currentBet;
	public Player player;
	public Deck deck;
	public ArrayList<Card> dealerCards;
	public ArrayList<Card> playerCards;
	public int playerScore = 0;
	public int dealerScore = 0;
	public boolean surrender = false;
	
	
	/**
	 * Constructor for class tableUpdate. Assigns fields with arguments provided.
	 * @param currentBet set to 0 by default
	 * @param player instance of Player
	 * @param deck instance of Deck that contains cards that will be used.
	 * @param dealerCards ArrayList<Card> that stores the dealer's cards used during a hand.
	 * @param playerCards ArrayList<Card> that stores the player's cards used during a hand.
	 */
	public tableUpdate(Player player, Deck deck) {
		this.currentBet = 0;
		this.player = player;
		this.deck = deck;
		dealerCards = new ArrayList<Card>();
		playerCards = new ArrayList<Card>();
	}
	
	/**
	 * @return the dealerCards
	 */
	public ArrayList<Card> getDealerCards() {
		return dealerCards;
	}

	/**
	 * @return the playerCards
	 */
	public ArrayList<Card> getPlayerCards() {
		return playerCards;
	}

	/**
	 * Draws to cards for the player and dealer at the start of the hand. 
	 */
	public void handStart() {
		playerCards.add(deck.draw());
		playerCards.add(deck.draw());
		
		dealerCards.add(deck.draw());
		dealerCards.add(deck.draw());
		setDealerScore();
		
	}
	
	/**
	 * Dealer draws a card, adds it to their deck and updates their score.
	 */
	public String dealerDraw()
	{
		if (getDealerScore() <= 17) 
		{
			dealerCards.add(deck.draw());
			setDealerScore();
			return dealerCards.get(dealerCards.size() - 1).getImagePath();
		}
		else 
		{
			return "end";
		}
	}
	
	/**
	 * Changes the surrender value to True. Used when the surrender button is pressed in the game.
	 */
	public void surrender() {
		this.surrender = true;
	}
	
	/**
	 * Gets the current state of the surrender value.
	 * @return surrender
	 */
	public boolean getSurrender() {
		return surrender;
	}
	
	/**
	 * Subtract the money placed in the bet of the player's wallet
	 * This will only work if the player has enough funds.
	 */
	public void bet(int amountBetted)
	{
		if (player.getWallet() >= amountBetted) {
			player.setWallet(player.getWallet()-amountBetted);
			currentBet = currentBet + amountBetted;
		}
	}
	
	/**
	 * 
	 * @return current bet
	 */
	public int getBet()
	{
		return currentBet;
	}
	
	/**
	 * Player will receive one more card and double their bet.
	 * The new card that is added drawn from the Deck and added to the
	 * player's hand.
	 */
	public String doubleDown()
	{
		if (player.getWallet() > (currentBet) ) {
			player.setWallet(player.getWallet() - currentBet);
			currentBet = currentBet * 2;
			playerCards.add(deck.draw());
			setPlayerScore();
			return playerCards.get(playerCards.size() - 1).getImagePath();
		}
		else
		{
			return "end";
		}
		
	}

	/**
	 * Player draws a card
	 * @return the image path contained in the arrayList of the new card
	 */
	public String hitMe() // calls draw, gets the elements from the arrayList and we select the string containing the image path
	{
		Card card = deck.draw();
		playerCards.add(card);
		setPlayerScore();
		
		return playerCards.get(playerCards.size() - 1).getImagePath();
	}
	
	/**
	 * Sets player score after each draw, checks if the ace can add 10 or if it will just maintain the value of 1
	 */
	public void setPlayerScore() {
		int score = 0;
		for (Card card : playerCards) {
			score += card.getValue();
		}
		if (score<21) {
			for (Card card : playerCards)
			{
				int cardValue = card.getValue();
				if (cardValue==1)
				{
					if (score+10<=21)
					{
						score = score+10;
					}
				}
			}
		}
		playerScore = score;
	}
	
	/**
	 * Sets dealer score after each draw, checks if the ace can add 10 or if it will just maintain the value of 1
	 */
	public void setDealerScore() {
		int score = 0;
		for (Card card : dealerCards) {
			score += card.getValue();
		}
		if (score<21) {
			for (Card card : dealerCards)
			{
				int cardValue = card.getValue();
				if (cardValue==1)
				{
					if (score+10<21)
					{
						score = score+10;
					}
				}
			}
		}
		dealerScore = score;
	}
	
	/**
	 * 
	 * @return player's score
	 */
	public int getPlayerScore()
	{
		return playerScore;
	}
	
	/**
	 * 
	 * @return Dealer's score
	 */
	public int getDealerScore()
	{
		return dealerScore;
	}
	
	/**
	 * checks if the player is under 21 points
	 * @return true if it is under 21, false if its not
	 */
	public boolean under21()
	{
		if (getPlayerScore()<=21)
		{
		return true;
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * Checks if the player or the dealer won
	 * @return true if the player wins, false if the dealer wins
	 */
	public String checkScores()
	{
		if(surrender) {
			return "lose";
		}
		else if (getDealerScore()<=21 && getPlayerScore()<=21)
		{
			if (getDealerScore()==getPlayerScore())
			{
				return "tied";
			}
			if (getDealerScore()<getPlayerScore())
			{
				return "win";
			}
			if (getDealerScore()>getPlayerScore())
			{
				return "lose";
			}
		}
		else 
		{
			if (getPlayerScore()>21)
			{
				return "lose";
			}
			if (getDealerScore()>21)
			{
				return "win";
			}
		}
		return null;
	}
	
	/**
	 * sets the wallet, scores, and resets the decks and arrays
	 */
	public char handEnd()
	{
		String check = checkScores();
		dealerCards.clear();
		playerCards.clear();
		deck.reset();
		if (check=="win") // if method check scores returns true that means the player won
		{
			player.setWins(player.getWins()+1);
			player.setWinPercentage();
			player.setWallet(player.getWallet()+(getBet()*2));
			this.currentBet = 0;
			return 'w';
		}
		else if (check =="tied" )
		{
			player.setWallet(player.getWallet()+getBet());
			this.currentBet = 0;
			return 't';
		}
		else if (check =="lose")
		{
			player.setLosses(player.getLosses()+1);
			player.setWinPercentage();
			this.currentBet = 0;
			this.surrender = false;
			return 'l';
		}
		else 
		{
			this.currentBet = 0;
			return ' ';
		}
	}
}
