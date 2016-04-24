package project;

import java.util.Scanner;

/** 
 * This class controls all game logic.
 * 
 * @author Filipe Correia
 * @author Helder Duarte
 * @author Joao Vieira
 *
 */
public class PlayingArea {
	
	Player player;
	Dealer dealer;
	Shoe shoe;
	
	char gameMode;
	int maxBet;
	int minBet;
	int balance;
	int nbDecksInShoe;
	int shufflePercentage;
	String shoeFile;
	String cmdFile;
	int nbShuffles;
	int strategy;
	
	int previousBet;
	public static int minimumBet;
	
	public PlayingArea(String[] args) {
		
		this.gameMode = args[0].charAt(1);
		
		if(gameMode == 'i') {
			if(args.length != 6) {
				System.out.println("Invalid number of arguments for interactive mode.");
				System.out.println("max-bet min-bet balance shoe shuffle");
				System.exit(-1);
			}
			this.maxBet = Integer.parseInt(args[1]);
			this.minBet = Integer.parseInt(args[2]);
			this.balance = Integer.parseInt(args[3]);
			this.nbDecksInShoe = Integer.parseInt(args[4]);
			this.shufflePercentage = Integer.parseInt(args[5]);		
		}
		
		if(gameMode == 'd') {
			if(args.length != 6) {
				System.out.println("Invalid number of arguments for debug mode.");
				System.out.println("max-bet min-bet balance shoe-file shuffle-file");
				System.exit(-1);
			}
			this.maxBet = Integer.parseInt(args[1]);
			this.minBet = Integer.parseInt(args[2]);
			this.balance = Integer.parseInt(args[3]);
			this.shoeFile = args[4];
			this.cmdFile = args[5];
		}
		
		if(gameMode == 's') {
			if(args.length != 8) {
				System.out.println("Invalid number of arguments for simulation mode.");
				System.exit(-1);
			}
			this.maxBet = Integer.parseInt(args[1]);
			this.minBet = Integer.parseInt(args[2]);
			this.balance = Integer.parseInt(args[3]);
			this.shoeFile = args[4];
			this.cmdFile = args[5];		
		}
		
		previousBet = minBet;
			
		}
		
	

	private void bet(int bet) {
		
		
		
	}
	
	
	public static void main(String[] args) {
		
		PlayingArea pa = new PlayingArea(args);
		Player player = new Player(pa.balance, pa.minBet);
		Dealer dealer = new Dealer();
		Scanner reader = new Scanner(System.in);
		StringBuffer userArgs = new StringBuffer();
		Command cmd;
		
		
		switch(pa.gameMode) {
		
		case 'i':
			pa.shoe = new Shoe(pa.nbDecksInShoe);
			//dar cartas
			//player's turn
			userArgs.replace(0, userArgs.length(), reader.nextLine());	//player input
			cmd = new Command(userArgs);
			switch(cmd.command) {
			case 'b':
				
			case '$':
				System.out.println("Current balance: " + player.getPlayerMoney());
				break;
			case 'd':
				break;
			case 'h':
				player.hit(pa.shoe);
				break;
			case 's':
				player.stand();
				break;
			case 'i':
				player.insurance(null, null);
				break;
			case 'u':
				player.surrender();
				break;
			case 'p':
				player.split(null, pa.shoe);
				break;
			case '2':
				break;
			//case "ad":	isto sao strings, ha que mudar o command para devolver string em vez de char para estes casos
			//case "st":
			}
			break;
			
		case 'd':
			break;
			
		case 's':
			break;
			
		}
		
		
		
	}

}
