package main;

import java.util.Scanner;

import cards.Deck;

/**
 * Main blackjack class. When initialized, deck is compiled, shuffled, and then each player 
 * is dealt two cards (shown in Player.java). 
 * The main method can be MUCH more coherent. For example, the while loops for the Scanner input can be in
 * their own separate method, since the System.out.println statements are very verbose.
 * @author marvin
 *
 */
public class Blackjack {
	public Blackjack() {
		deck = new Deck();
		player = new Player(deck, false);
		dealer = new Player(deck, true);
	}
	
	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		Scanner userInput = new Scanner(System.in);
		while(blackjack.deck.getDeckSize() > 0) {	
			System.out.println("Welcome! The player, with 2 cards, now has a total of " + blackjack.player.getHandCount()
					+ ", and the dealer, with 2 cards, now has a total of " + blackjack.dealer.getHandCount() + ".");
			String input;
			while(blackjack.player.getHandCount() < 21 && blackjack.dealer.getHandCount() < 21) {
				System.out.println("Would you like to draw? Input 0 to stand, 1 to hit, or 2 to call.");
				
				while (!userInput.hasNext("0|1|2")) {
					System.out.println("Please only enter a 0, 1,or 2.");
					userInput.next();
				}
				
				input = userInput.next();
				int hit = Integer.parseInt(input);
				if(hit <= 1) {//if it's 2, neither player draws, and the game is decided right there.
					try {
						blackjack.hitOrStand(blackjack.player, hit);
						blackjack.hitOrStand(blackjack.dealer, 0);
					}
					
					catch(IllegalArgumentException i) {
						System.out.println("The deck is empty! Both players must now show their hands to see who wins.");
					}
				}
				
				else //player called before reaching 21.
					break;
			}
			
			blackjack.checkResults();
			if(blackjack.player.isWinner)
				System.out.println("The player has won this game!");
			else if(blackjack.dealer.isWinner)
				System.out.println("The dealer has won the game. Check his sleeves for hidden cards!");
			else 
				System.out.println("The game has ended in a draw! Cherish this rare moment.");
			System.out.println("Play again? Yes or No.");
			
			while(!userInput.hasNext("yes|Yes|YES|No|no|NO")) {
				System.out.println("Please enter yes or no.");
				userInput.next();
			}
			
			input = userInput.next();
			if(input.equalsIgnoreCase("yes")) {
				System.out.println("Reshuffling deck and emptying player hands...");
				if(blackjack.deck.getDeckSize() >= 4) {
					blackjack.shuffleAndResetHands();
					continue;
				}
				
				else {
					System.out.println("Not enough cards for another round. Thank you for playing!");
					userInput.close();
					break;
				}
			}
			
			else if(input.equalsIgnoreCase("no")) {
				System.out.println("Thank you for playing!");
				userInput.close();
				break;
			}
		}
		
		if(blackjack.deck.getDeckSize() == 0)
			System.out.println("The deck is completely empty. Thank you for playing!");
	}

	public void shuffleAndResetHands() {
		deck.shuffle();
		player = new Player(deck, false);
		dealer = new Player(deck, true);
	}
	
	public void checkResults() {
		int playerCount = player.getHandCount();
		int dealerCount = dealer.getHandCount();
		if(playerCount == 21 && dealerCount == 21) {//tied
			player.isWinner = true;
			dealer.isWinner = true;
		}
		
		if(playerCount > 21 && dealerCount > 21)//dealer wins if both players bust
			dealer.isWinner = true;
		
		//if player has exactly 21, or if player has a higher count than the dealer, but less
		//than 21 (i.e he/she "folds" before reaching 21), then he/she wins.
		else if(playerCount == 21 || (playerCount > dealerCount && playerCount < 21) || dealerCount > 21)
			player.isWinner = true;
		else if(dealerCount == 21 || (playerCount < dealerCount && dealerCount < 21) || playerCount > 21)
			dealer.isWinner = true;
		
	}
	
	/**
	 * Method to determine if a player hits or stands.
	 * @param player - Player object.
	 * @param input - 0 for stand, and 1 for hit; input by the player.
	 */
	public void hitOrStand(Player player, int input) {
		if(player.isDealer) { //dealer must hit if less than 17. Otherwise, no cards will be dealt to him/her.
			if(player.hand.getTotalInHand() < 17) {
				deck.dealCard(player.hand);
				System.out.println("Dealer hit! Dealer now has a total of " + player.getHandCount() + ".");
			}
			else
				System.out.println("Dealer stands! Dealer now has a total of " + player.getHandCount() + ".");
		}
		
		else {
			if(input == 1) {
				deck.dealCard(player.hand);
				System.out.println("Player hit! Player now has a total of " + player.getHandCount() + ".");
			}
			
			else 
				System.out.println("Player stands! Player now has a total of " + player.getHandCount() + ".");
			
		}
	}
	public Deck deck;
	public Player player;
	public Player dealer;
}
