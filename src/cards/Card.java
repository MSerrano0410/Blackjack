package cards;

/**
 * Card initialization. The "name" attribute is only needed for kings, queens, and jacks;
 * they're all the same value, so the switch statement used to differentiate them won't work
 * here. Therefore, those need to have explicit names defined.
 *
 */
public class Card {
	public Card (int suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	public Card(int suit, String name, int value) {
		this.suit = suit;
		this.value = value;
		this.name = name;
	}
	
	public int getSuit() {
		return this.suit;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getValueAsString() {
		switch(value){
		case ACE: return "Ace";
		default: return Integer.toString(value);
		}
	}
	
	public String getSuitAsString() {
		switch(suit){
		case CLUBS: return "Clubs";
		case SPADES: return "Spades";
		case DIAMONDS: return "Diamonds";
		case HEARTS: return "Hearts";
		default: return "Joker";
		}
	}
	
	@Override
	public String toString() {
		if(name != null)
			return name + " of " + getSuitAsString();
		return getValueAsString() + " of " + getSuitAsString();
	}
	
	
	public final static int CLUBS = 0;
	public final static int SPADES = 1;
	public final static int DIAMONDS = 2;
	public final static int HEARTS = 3;
	public final static int ACE = 1;
	
	private final int suit;	
	private final int value;
	private String name;
	
}
