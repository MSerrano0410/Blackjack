package cards;

import java.util.ArrayList;

/**
 * Class to represent a player's hand.
 * Has remove card method for cosmetic purposes, since no cards are removed from a Hand in Blackjack.
 * @author marvin
 *
 */
public class Hand {
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void addCard(Card c) {
		hand.add(c);
	}
	
	public void removeCard(Card c) {
		hand.remove(c);
	}
	
	public int getTotalInHand() {
		int total = 0;
		for(int i = 0; i < hand.size(); i++) {
			//if it's an ace, it'll be 11 if it won't make the hand go above 21.
			int value = hand.get(i).getValue();
			if(value == 1) {//ace
				if((total + 11) <= 21)
					value = 11;
			}
			total += value;
		}
		return total;
	}
	
	public ArrayList<Card> hand;
}
