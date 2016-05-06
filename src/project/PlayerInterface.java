package project;

/** PlayerInterface interface
 * Interface to the basic movements a BlackJack player can execute
 * 
 * @author Filipe Correia
 * @author Hélder Duarte
 * @author João Vieira
 */
public interface PlayerInterface {
	
	//TODO: make this a superclass
	
	/**
	 * This method is used to ask for one card that will be randomly picked from shoe
	 * 
	 * @param Shoe that contains all the decks
	 * @return card picked randomly
	 * @see Hit
	 */
	public void hit(Shoe s);
	
	/**
	 * This method is used to keep the player's hand as it is.
	 * 
	 * @see Stand
	 */
	public int stand(int curHand);
	
}
