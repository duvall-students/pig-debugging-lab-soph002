
public class ComputerPlayer extends Player{
	private final int MIN_POINTS = 15;
	
	public ComputerPlayer(){
		super("R2D2");
		// 5. Player goes not set the name so you have to set it in specific one
		myName="R2D2";
	}

	@Override
	/*
	 *Computer will stop rolling if:
	 *	- It doesn't have 15 points yet (or MIN_POINTS)
	 *	- Stopping will win the game.
	 */
	public boolean rollAgain(int totalSoFar) {
		return (myScore + totalSoFar)<100 && totalSoFar < MIN_POINTS;
	}
}
