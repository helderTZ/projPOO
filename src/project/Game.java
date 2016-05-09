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
	
		/*switch(args[0].charAt(1)) {
		case 'i':
			PlayingAreaInteractive pa = new PlayingAreaInteractive(args);
			break;
		case 'd':
			PlayingAreaDebug pa = new PlayingAreaDebug(args);
			break;
		case 'd':
			PlayingAreaSimulation pa = new PlayingAreaSimulation(args);
			break;
		default: System.exit(-1);
		}*/
		PlayingAreaDebug pa = new PlayingAreaDebug(args);
		Player player = new Player(pa.initialMoney, pa.minBet, pa.maxBet);
		Dealer dealer = new Dealer(pa.initialMoney, pa.minBet, pa.maxBet);
		
		String cmd;
		
		while(true) {
			
			while(player.getNextHand() != null) {	// player's turn
				
				cmd = pa.getCommand();	//get player input
				
				//TODO: isto resolve (temporariamente ou nao) o ciclo infinito
				// ideal serial fazer o getCommand fazer throw a exception NoMoreCmdsExcetion, mas
				// o eclipse queixa-se que o PlayArea nao faz throw, nao sei se uma subclasse ao implementar
				// um metodo abstrato da superclasse pode adicionar throws --> VER ISTO
				if(cmd.equals("")) System.exit(-1);	
				
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
