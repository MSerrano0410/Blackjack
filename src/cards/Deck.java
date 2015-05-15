package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Constructs a deck of cards. We use ArrayList here for convenience, since it comes with a pre-built
 * "shuffle" method.
 * @author marvin
 *
 */
public class Deck {
	public Deck() {
		deck = new ArrayList<Card>(52);
		for(int suit = 0; suit <= 3; suit++) {
			for(int value = 1; value <= 13; value++) {
				Card card;
				switch(value){ //makes King/Queen/Jack equal 10.
				case 1: card = new Card(suit, "Ace", value); break;
				case 11: card = new Card(suit, "King", value - 1); break;
				case 12: card = new Card(suit, "Queen", value - 2); break;
				case 13: card = new Card(suit, "Jack", value - 3); break;
				default: card = new Card(suit, value); break;
				}
				deck.add(card);
			}
		}
	shuffle();
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	/**
	 * Generates a random number between 1 and the deck size, then adds that index
	 * @param h - hand to add card to
	 */
	public void dealCard(Hand h) {
		Random cardIndex = new Random();
		int cardToPick = cardIndex.nextInt(deck.size());//ArrayList indexes are zero-indexed.
		h.addCard(deck.get(cardToPick));
		System.out.println("Card " + deck.get(cardToPick) + " removed from deck.");
		deck.remove(cardToPick);
	}
	
	public int getDeckSize() {
		return deck.size();
	}
	
	public ArrayList<Card> deck;
}
