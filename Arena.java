// Thanapon Jarukasetphon 5888057 Sec1

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Arena {

	public enum Row {Front, Back}; // enum for specifying the front or back row

	public enum Team {A, B}; // enum for specifying team A or B

	private Player[][] teamA = null; // two dimensional array representing the
									 // players of Team A
	private Player[][] teamB = null; // two dimensional array representing the
									 // players of Team B
	public static final int MAXROUNDS = 100; // Max number of turn
	
	public static final int MAXEACHTYPE = 3; // Max number of players of each
											 // type, in each team.
	private final Path logFile = Paths.get("battle_log.txt");

	private int numRounds = 0; // keep track of the number of rounds so far
	
	// AddMore: number of row
	public int _numRowPlayers;	
	
	// AddMore: get teamA
	public Player[][] getTeamA(){
		return this.teamA;
	}
	// AddMore: get teamB
	public Player[][] getTeamB(){
		return this.teamB;
	}

	/**
	 * Constructor.
	 * 
	 * @param _numRows
	 *            is the number of row in each team.
	 * @param _numRowPlayers
	 *            is the number of player in each row.
	 */
	public Arena(int _numRowPlayers) {
		// COMPLETE:  INSERT YOUR CODE HERE
		this._numRowPlayers = _numRowPlayers;
		this.teamA = new Player[2][_numRowPlayers];
		this.teamB = new Player[2][_numRowPlayers];
		
		//// Keep this block of code. You need it for initialize the log file.
		//// (You will learn how to deal with files later)
		try {
			Files.deleteIfExists(logFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/////////////////////////////////////////

	}

	/**
	 * Returns true if "player" is a member of "team", false otherwise.
	 * Assumption: team can be either Team.A or Team.B
	 * 
	 * @param player
	 * @param team
	 * @return
	 */
	public boolean isMemberOf(Player player, Team team) {
		// COMPLETE:  INSERT YOUR CODE HERE
		if(player.getTeam().equals(team)){
			return true;
		}
		else{
			return false;
		} 
	}

	/**
	 * This methods receives a player configuration (i.e., team, type, row, and position), 
	 * creates a new player instance, and places him at the specified position.
	 * @param team is either Team.A or Team.B
	 * @param pType is one of the Player.Type  {Healer, Tank, Samurai, BlackMage, Phoenix}
	 * @param row	either Row.Front or Row.Back
	 * @param position is the position of the player in the row. Note that position starts from 1, 2, 3....
	 */
	public void addPlayer(Team team, Player.PlayerType pType, Row row, int position)
	{	
		// COMPLETE: INSERT YOUR CODE HERE
		Player play = new Player(pType);
		play.setTeam(team);
		
		if(team == Team.A){
			if(row== Row.Front){
				teamA[0][position-1] = play;
				play.setnumRow(0);
				play.setnumPlayer(position);
				}
			else{
				teamA[1][position-1] = play;
				play.setnumRow(1);
				play.setnumPlayer(position);
			}
		}
		else {
			if(row== Row.Front){
				teamB[0][position-1] = play;
				play.setnumRow(0);
				play.setnumPlayer(position);
				}
			else{
				teamB[1][position-1] = play;
				play.setnumRow(1);
				play.setnumPlayer(position);
			}
		}
	}

	/**
	 * Validate the players in both Team A and B. Returns true if all of the
	 * following conditions hold:
	 * 
	 * 1. All the positions are filled. That is, there each team must have
	 * exactly numRow*numRowPlayers players. 2. There can be at most MAXEACHTYPE
	 * players of each type in each team. For example, if MAXEACHTYPE = 3 then
	 * each team can have at most 3 Healers, 3 Tanks, 3 Samurais, 3 BlackMages,
	 * and 3 Phoenixes.
	 * 
	 * Returns true if all the conditions above are satisfied, false otherwise.
	 * 
	 * @return
	 */
	public boolean validatePlayers() {
		// COMPLETE: INSERT YOUR CODE HERE
		int[] countA = {0,0,0,0,0};
		int[] countB = {0,0,0,0,0}; 
		// 0 = Healer, 1 = Tank, 2 = Samurai, 3 = BlackMage, 4 = Phoenix
				
		for(int i = 0; i<2; i++){
			for(int j = 0; j<_numRowPlayers; j++){
				if (teamA[i][j] == null || teamB[i][j] == null){
					return false;
				}
				else{
					switch(teamA[i][j].getType()){
						case Healer:
							countA[0]++;
							if(countA[0]>MAXEACHTYPE){
								return false;
							}
							break;
						case Tank: 
							countA[1]++; 
							if(countA[1]>MAXEACHTYPE){
								return false;
							}
							break;
						case Samurai:
							countA[2]++; 
							if(countA[2]>MAXEACHTYPE){
								return false;
							}
							break; 
						case BlackMage: 
							countA[3]++;
							if(countA[3]>MAXEACHTYPE){
								return false;
							}
							break;
						case Phoenix: 
							countA[4]++;
							if(countA[4]>MAXEACHTYPE){
								return false;
							}
							break;
					}
					switch(teamB[i][j].getType()){
						case Healer:
							countB[0]++; 
							if(countB[0]>MAXEACHTYPE){
								return false;
							}
							break;
						case Tank:							
							countB[1]++; 
							if(countB[1]>MAXEACHTYPE){
								return false;
							}
							break;
						case Samurai:
							countB[2]++; 
							if(countB[2]>MAXEACHTYPE){
								return false;
							}
							break;
						case BlackMage: 
							countB[3]++; 
							if(countB[3]>MAXEACHTYPE){
								return false;
							}
							break;
						case Phoenix: 
							countB[4]++; 
							if(countB[4]>MAXEACHTYPE){
								return false;
							}
							break;
					}
				}
			}	
		}
		return true;
	}
		
	
	/**
	 * Returns the sum of HP of all the players in the given "team"
	 * 
	 * @param team
	 * @return
	 */
	public static double getSumHP(Player[][] team) {
		// COMPLETE: INSERT YOUR CODE HERE
		double sumHP = 0.0;
			for(int i = 0; i<2 ;i++){
				for(int j = 0; j< team[0].length; j++){
					sumHP = sumHP + team[i][j].getCurrentHP();
				}
		}
		return sumHP;
	}
	

	/**
	 * Return the team (either teamA or teamB) whose number of alive players is
	 * higher than the other.
	 * 
	 * If the two teams have an equal number of alive players, then the team
	 * whose sum of HP of all the players is higher is returned.
	 * 
	 * If the sums of HP of all the players of both teams are equal, return
	 * teamA.
	 * 
	 * @return
	 */
	public Player[][] getWinningTeam() {
		// COMPLETE INSERT YOUR CODE HERE
		int countAliveTeamA = 0;
		int countAliveTeamB = 0;
		
		for(int i=0; i<2; i++){
			for(int j=0; j<_numRowPlayers; j++){
					if(teamA[i][j].isAlive()==true){
						countAliveTeamA++;
					}
					if(teamB[i][j].isAlive()==true){
						countAliveTeamB++;
					}
			}
		}
		
		if(countAliveTeamA > countAliveTeamB){
			return teamA;
		}
		else if(countAliveTeamA < countAliveTeamB){
			return teamB;
		}
		else{
			if(getSumHP(teamA) >= getSumHP(teamB)){
				return teamA;
			}
			else{
				return teamB;
			}
		}
	}

	/**
	 * This method simulates the battle between teamA and teamB. The method
	 * should have a loop that signifies a round of the battle. In each round,
	 * each player in teamA invokes the method takeAction(). The players' turns
	 * are ordered by its position in the team. Once all the players in teamA
	 * have invoked takeAction(), not it is teamB's turn to do the same.
	 * 
	 * The battle terminates if one of the following two conditions is met:
	 * 
	 * 1. All the players in a team has been eliminated. 2. The number of rounds
	 * exceeds MAXROUNDS
	 * 
	 * After the battle terminates, report the winning team, which is determined
	 * by getWinningTeam().
	 */
	public void startBattle() {
		// COMPLETE: INSERT YOUR CODE HERE
		
		while(this.numRounds!=MAXROUNDS && getSumHP(teamA)>0 && getSumHP(teamB)>0){
			this.numRounds++;
			System.out.println("@ Round" + " " + numRounds );
			for(int i=0; i<2; i++){
				for(int j=0; j<_numRowPlayers; j++){
					if(teamA[i][j].isAlive() && getSumHP(teamB)!=0){
						teamA[i][j].takeAction(this);
						teamA[i][j].setCountSpecialTurns(1);
					}
				}	
			}
			for(int i=0; i<2; i++){
				for(int j=0; j<_numRowPlayers; j++){
					if(teamB[i][j].isAlive() && getSumHP(teamA)!=0){
						teamB[i][j].takeAction(this);
						teamB[i][j].setCountSpecialTurns(1);
					}
				}
			}
			
			displayArea(this,true);
			
			logAfterEachRound();
			
			if(getSumHP(teamA)==0 || getSumHP(teamB)==0){
				if(getWinningTeam() == teamA)
					System.out.println("@@@ Team A won.");
				else
					System.out.println("@@@ Team B won.");
				return;
			}
		}		
			if(getWinningTeam() == teamA)
				System.out.println("@@@ Team A won.");
			else
				System.out.println("@@@ Team B won.");
	}

	/**
	 * This method displays the current area state, and is already implemented
	 * for you. In startBattle(), you should call this method once before the
	 * battle starts, and after each round ends.
	 * 
	 * @param arena
	 * @param verbose
	 */
	public static void displayArea(Arena arena, boolean verbose) {
		if (arena == null)
			return;

		StringBuilder str = new StringBuilder();
		if (verbose) {
			str.append(String.format("%38s   %35s", "Team A", "") + "\t\t" + String.format("%-33s%-35s", "", "Team B")
					+ "\n");
			str.append(String.format("%38s", "BACK ROW") + String.format("%38s", "FRONT ROW") + "  |  "
					+ String.format("%-38s", "FRONT ROW") + "\t" + String.format("%-38s", "BACK ROW") + "\n");
			for (int i = 0; i < arena.teamA[0].length; i++) {
				str.append(String.format("%38s", arena.teamA[1][i]) + String.format("%38s", arena.teamA[0][i]) + "  |  "
						+ String.format("%-38s", arena.teamB[0][i]) + String.format("%-38s", arena.teamB[1][i]) + "\n");
			}
		}

		str.append("@ Total HP of Team A = " + getSumHP(arena.teamA) + ". @ Total HP of Team B = "
				+ getSumHP(arena.teamB) + "\n\n");
		System.out.print(str.toString());

	}

	/**
	 * This method writes a log (as round number, sum of HP of teamA, and sum of
	 * HP of teamB) into the log file. You are not to modify this method,
	 * however, this method must be call by startBattle() after each round.
	 * 
	 * The output file will be tested against the auto-grader, so make sure the
	 * output look something like:
	 * 
	 * 1 47415.0 49923.0 2 44977.0 46990.0 3 42092.0 43525.0 4 44408.0 43210.0
	 * 
	 * Where the numbers of the first, second, and third columns specify round
	 * numbers, sum of HP of teamA, and sum of HP of teamB respectively.
	 */
	private void logAfterEachRound() {
		try {
			Files.write(logFile,
					Arrays.asList(new String[] { numRounds + "\t" + getSumHP(teamA) + "\t" + getSumHP(teamB) }),
					StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}