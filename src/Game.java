import java.util.Random;

public class Game {
	private Player player1;
	private Player player2;
	private Random die;
	private Spinner spinner;
	private final String LOSER_SPIN = "grunt";
	private final int LOSER_ROLL = 1;
	
	public Game(){
		player1 = new GUIPlayer();
		player2 = new ComputerPlayer();
		die = new Random();
		spinner = new Spinner();
	}
	
	/*
	 * Method will play one game between the players.
	 */
	public void playGame(){
		printStartGameMessage();
		Player whoseTurn = player1;
		while(!winner()){
			int roundScore = takeATurn(whoseTurn);
			whoseTurn.addToScore(roundScore);
			// Switch whose turn it is.
			if(whoseTurn == player1){
				whoseTurn = player2;
			}
			else{
				whoseTurn = player1;
			}
		}
		printEndGameMessage();
	}
	
	/*
	 * One player's turn.  Ends because
	 * - roll a 1
	 * - spin a "GRUNT"
	 * - or Player decides to stop
	 * 
	 * Return the number of points to add to the player
	 */
	public int takeATurn(Player whoseTurn){
		int roundScore = 0;
		boolean keepGoing = true;
		printStartRoundMessage(whoseTurn);
		//while(keepGoing){
		// 4. only checks for winner after each round, new code will check after each turn
		while(keepGoing && !winner()){
			//int roll = die.nextInt(7);
			// 3. die.nextInt(7) gives numbers from 0-6, die.nextInt(6) will give numbers from (0+1)-(5+1) so 1-6
			int roll=die.nextInt(6)+1;
			String spin = spinner.spin();
			System.out.println(roll+ " "+ spin);

			//else if(spin == LOSER_SPIN.toUpperCase()){
			// 6. to compare strings you must use equal
			// 7. Reordered so that it will check for GRUNT first and lose all points
			if(spin.equals(LOSER_SPIN.toUpperCase())){
				System.out.println("Too bad!  Lose all your points.");
				whoseTurn.resetScore();
				return 0;
			}
			else if(roll == LOSER_ROLL){
				System.out.println("Lose a turn.");
				return 0;
			}
			else{
				roundScore = roundScore + roll;
				System.out.println("Round total is currently: "+roundScore);
				keepGoing = whoseTurn.rollAgain(roundScore);
			}
		}
		return roundScore;
	}
	
	// True if one of the players has won the game.
	public boolean winner(){
		//return player1.hasWon() && player2.hasWon();
		// 1. there is never an instance where both players will have won
		return player1.hasWon() || player2.hasWon();
	}
	
	/* 
	 * These methods are for printing messages to the console to follow the game.
	 */
	public void printStartRoundMessage(Player whoseTurn){
		System.out.println("New Round!  "+ whoseTurn.getName()+" 's turn."); 
		System.out.println(player1);
		System.out.println(player2);
	}
	
	public void printEndGameMessage(){
		if(player1.hasWon()){
			System.out.println("Winner is "+player1.getName());
		}
		else{
			System.out.println("Winner is "+player2.getName());
		}
	}
	
	public void printStartGameMessage(){
		System.out.println("Let's Play Pig!");
	}
}
