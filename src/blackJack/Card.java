package blackJack;

/**
 * This class stores and displays information about playing cards.
 * 
 * @author Tommy George & Guillermo Nasif
 */
public class Card {
	private int value;
	private String suite;
	private String imagePath;
	private boolean isAce;
	
	/**
	 * Constructor for Class Card. Sets fields based on provided values
	 * @param value Card's value
	 * @param suite	Card's suite
	 * @param imagePath Card image's file path
	 * @param isAce is the card an Ace (then it can be worth 1 or 11)
	 */
	public Card(int value, String suite, String imagePath, boolean isAce) {
		super();
		this.value = value;
		this.suite = suite;
		this.imagePath = imagePath;
		this.isAce = isAce;
	}

	/**
	 * @return the Card's numeric value.
	 */
	public int getValue() {
		return value;
	}
	
	public void setValue(int val) {
		this.value = val;
	}

	/**
	 * @return the Card's suite represented as a String.
	 */
	public String getSuite() {
		return suite;
	}

	/**
	 * @return the Card's image file path represented as a String.
	 */
	public String getImagePath() {
		return imagePath;
	}
	
	/**
	 * Checks if the card is an Ace. This is important when determining points values.
	 * @return isAce
	 */
	public boolean checkAce() {
		return isAce;
	}
	
	/**
	 * Updates the card's imagePath if it was changed prior.
	 * @param s the cards imagePath represented as a String value.
	 */
	public void setImagePath(String s) {
		this.imagePath = s;
	}

}
