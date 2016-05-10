package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;

public class Game {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PlayingArea pa = null;
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		if(args.length < 1){ // minimum arg size
			System.out.println("Not enough arguments");
			System.exit(-1);
		}
		
		switch(args[0].charAt(1)) {
		case 'i':
			pa = new PlayingAreaInteractive(args);
			break;
		case 'd':
			pa = new PlayingAreaDebug(args);
			break;
		case 's':
			pa = new PlayingAreaSimulation(args);
			break;
		default: System.exit(-1);
		}

		//PlayingAreaDebug pa = new PlayingAreaDebug(args);
		Player player = new Player(pa.initialMoney, pa.minBet, pa.maxBet);
		Dealer dealer = new Dealer(pa.initialMoney, pa.minBet, pa.maxBet);
		
		String cmd;
		
		//System.out.println(pa.shoe);
		
		while(true) {
			
			while(player.getNextHand() != null) {	// player's turn
				cmd = "";
				while(cmd.equals("")){
					try{
						cmd = pa.getCommand();	//get player input
					}catch(NoMoreCmdsException e){
						System.out.println("Game Over");
						System.exit(0);
					}
				}
				
				try {
					pa.executePlayerAction(cmd, player, dealer);
				} catch (IllegalCmdException e) {
					System.out.println(e.getMessage());
				}
			}//end_player_turn
			
			//dealer turn
			pa.dealerTurn(dealer);
			
			//payout time
			pa.payOut(player, dealer);
			
			//reset hands
			pa.prepareNextRound(player,dealer);
			
		}//end_rounds
			
	}

	/**
	 * Create the application.
	 */
	public Game() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	}
	


}
