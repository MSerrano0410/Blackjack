package test;

import static org.junit.Assert.*;

import org.junit.Test;

import cards.Deck;
import cards.Hand;

public class DeckTest {

	@Test
	public void assembleDeckTest() {
		Deck d = new Deck();
		assertNotNull(d.deck);
	}
	
	@Test
	public void dealFromDeckTest() { //ensures that a card is actually removed from the deck and added to hand.
		Deck d = new Deck();
		Hand h = new Hand();
		d.dealCard(h);
		assertTrue(h.hand.size() > 0);
		assertTrue(d.deck.size() == 51);
	}

}
