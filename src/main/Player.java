package main;

import cards.Deck;
import cards.Hand;

/**
 * Class to represent a player or the dealer.
 * @author marvin
 *
 */
public class Player {
	
	public Player(Deck deck, boolean isDealer) {
		hand = new Hand();
		this.isDealer = isDealer;
		//deal first two cards when game begins
		deck.dealCard(hand);
		deck.dealCard(hand);
	}
	
	public int getHandCount() {
		return hand.getTotalInHand();
	}
	
	
	boolean isWinner = false;
	boolean isDealer = false;
	Hand hand;
}
