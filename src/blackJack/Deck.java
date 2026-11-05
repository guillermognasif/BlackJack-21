package blackJack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class stores and manages ArrayLists<Card>
 * 
 * @author Tommy George & Guillermo Nasif
 */
public class Deck {
	private ArrayList<Card> CardsInPlay = new ArrayList<Card>();
	private ArrayList<Card> DiscardedCards = new ArrayList<Card>();
	private final Random rand = new Random();
	
	/**
	 * Constructor for Class Deck. Reads information in from a text file and uses 
	 * that to populate each instance of Card with the Deck.
	 */
	public Deck() {
		try(Scanner scnr = new Scanner(Deck.class.getResourceAsStream("Cards"))) {
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine();
				getCard(line);
				
			}
		}
	}
	
	/**
	 * Reads information in from each line in the text file.
	 * @param line
	 */
	private void getCard(String line) {
			String[] info = line.split(",");
			int value = Integer.parseInt(info[0]);
			String suite = info[1];
			String imagePath = info[2];
			boolean isAce = Boolean.parseBoolean(info[3]);
			Card card = new Card(value, suite, imagePath, isAce);
			CardsInPlay.add(card);

	}

	/**
	 * Returns all of the discarded cards used in the hand back into the
	 * CardsInPlay ArrayList, resets all Ace cards to having a value of 1.
	 */
	public void reset() {
		for (int i = 0; i < DiscardedCards.size(); i++) {
			if (DiscardedCards.get(i).checkAce() == true) {
				DiscardedCards.get(i).setValue(1);
			}
			CardsInPlay.add(DiscardedCards.get(i));
			DiscardedCards.remove(i);
		}
	}
	
	/**
	 * Draws a random card from the CardsInPlay ArrayList that will be used
	 * in the blackjack hand. Removes that Card from CardsInPlay ArrayList
	 * so it cannot be used again and places it in the Discarded Cards
	 * ArrayList so it can be used again in the next hand when the cards are reset. 
	 * @return a Random Card
	 */
	public Card draw() {
		int cardIndex = rand.nextInt(CardsInPlay.size());
		Card card = CardsInPlay.get(cardIndex);
		DiscardedCards.add(card);
		CardsInPlay.remove(cardIndex);
		//PlayersCards.add(card);
		return card;
	}

	/**
	 * @return the cardsInPlay
	 */
	public ArrayList<Card> getCardsInPlay() {
		return CardsInPlay;
	}

	/**
	 * @return the discardedCards
	 */
	public ArrayList<Card> getDiscardedCards() {
		return DiscardedCards;
	}
	
}
