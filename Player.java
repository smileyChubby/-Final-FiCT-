// Thanapon Jarukasetphon 5888057 Sec1

public class Player {

	public enum PlayerType {
		Healer, Tank, Samurai, BlackMage, Phoenix
	};

	private PlayerType type; // Type of this player. Can be one of either
								// Healer, Tank, Samurai, BlackMage, or Phoenix
	private double maxHP; // Max HP of this player
	private double currentHP; // Current HP of this player
	private double atk; // Attack power of this player

	// AddMore: Count special turns
	private int numSpecialTurns;
	// AddMore: Show team
	public Arena.Team team;
	// AddMore Cursed
	private boolean cursed;
	// AddMore Taunting
	private boolean taunting;
	// AddMore for count special turns
	private int countSpecialTurns;
	// AddMore for check output
	private int numRow;
	// AddMore for check output
	private int numPlayer;

	/**
	 * Constructor of class Player, which initializes this player's type, maxHP,
	 * atk, numSpecialTurns, as specified in the given table. It also reset the
	 * internal turn count of this player.
	 * 
	 * @param _type
	 */
	public Player(PlayerType _type) {
		// COMPLETE: INSERT YOUR CODE HERE
		this.type = _type;
		switch (this.type) {
		case Healer:
			this.maxHP = 4790;
			this.currentHP = this.maxHP;
			this.atk = 238;
			this.numSpecialTurns = 4;
			this.countSpecialTurns = 1;
			this.numRow=100;
			this.numPlayer=100;
			break;
		case Tank:
			this.maxHP = 6240;
			this.currentHP = this.maxHP;
			this.atk = 315;
			this.numSpecialTurns = 4;
			this.countSpecialTurns = 1;
			this.numRow=100;
			this.numPlayer=100;
			break;
		case Samurai:
			this.maxHP = 4905;
			this.currentHP = this.maxHP;
			this.atk = 368;
			this.numSpecialTurns = 3;
			this.countSpecialTurns = 1;
			this.numRow=100;
			this.numPlayer=100;
			break;
		case BlackMage:
			this.maxHP = 4175;
			this.currentHP = this.maxHP;
			this.atk = 339;
			this.numSpecialTurns = 4;
			this.countSpecialTurns = 1;
			this.numRow=100;
			this.numPlayer=100;
			break;
		case Phoenix:
			this.maxHP = 5175;
			this.currentHP = this.maxHP;
			this.atk = 209;
			this.numSpecialTurns = 8;
			this.countSpecialTurns = 1;
			this.numRow=100;
			this.numPlayer=100;
			break;
		}
	}

	/**
	 * Returns the current HP of this player
	 * 
	 * @return
	 */
	public double getCurrentHP() {
		// COMPLETE: INSERT YOUR CODE HERE
		return this.currentHP;
	}

	/**
	 * Returns type of this player
	 * 
	 * @return
	 */
	public Player.PlayerType getType() {
		// COMPLETE: INSERT YOUR CODE HERE
		return this.type;
	}

	/**
	 * Returns max HP of this player.
	 * 
	 * @return
	 */
	public double getMaxHP() {
		// COMPLETE: INSERT YOUR CODE HERE
		return this.maxHP;
	}

	// AddMore: for set Team
	/**
	 * Set team of this player
	 * @param team
	 */
	public void setTeam(Arena.Team team) {
		this.team = team;
	}

	// AddMore: for get Team
	/**
	 * Returns team of this player
	 * @return
	 */
	public Arena.Team getTeam() {
		return this.team;
	}

	// AddMore
	/**
	 * Return counter of specialTurns
	 * @return
	 */
	public int getCountSpecialTurns() {
		return countSpecialTurns;
	}
	
	// AddMore
	/**
	 * Receive the data from Arena at startBattle() method
	 * For count the specialTurns of this player
	 * @param countSpecialTurns
	 */
	public void setCountSpecialTurns(int countSpecialTurns) {
		this.countSpecialTurns = this.countSpecialTurns + countSpecialTurns;
	}

	/**
	 * Returns whether this player is being cursed.
	 * 
	 * @return
	 */
	public boolean isCursed() {
		// COMPLETE: INSERT YOUR CODE HERE
		if (this.cursed == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns whether this player is alive (i.e. current HP > 0).
	 * 
	 * @return
	 */
	public boolean isAlive() {
		// COMPLETE: INSERT YOUR CODE HERE
		return this.currentHP > 0;
	}

	/**
	 * Returns whether this player is taunting the other team.
	 * 
	 * @return
	 */
	public boolean isTaunting() {
		// COMPLETE: INSERT YOUR CODE HERE
		return this.taunting;
	}

	public void attack(Player target) {
		// COMPLETE: INSERT YOUR CODE HERE
		target.currentHP -= atk;
		if (target.currentHP < 0)
			target.currentHP = 0;
		System.out.println(this.getTeam() + "["+this.getnumRow() + "][" +this.numPlayer+ "]" + " " + "{" + this.getType() + "}" + " Attacks "
				+ target.getTeam() + "[" +target.getnumRow()+ "]" + "[" +target.numPlayer+"]" + " " + "{" + target.getType() + "}");

	}

	public void useSpecialAbility(Player[][] myTeam, Player[][] theirTeam) {
		// COMPLETE: INSERT YOUR CODE HERE
		Player a;
		switch (this.type) {
		case Healer:
			a = selectHeal(myTeam);
			if (a != null) {
				if (a.cursed)
					break;
				a.currentHP += (0.3 * a.maxHP);
				if (a.currentHP > a.maxHP) {
					a.currentHP = a.maxHP;
				}
				System.out.println(this.getTeam() + "["+this.getnumRow()+ "]["+this.getnumPlayer()+"]" + " " + "{" + this.getType() + "}" + " Heals "
						+ a.getTeam() + "["+a.getnumRow()+ "]" + "["+a.getnumPlayer()+ "]" + " " + "{" + a.getType() + "}");
			}
			break;
		case Tank:
			this.taunting = true;
			System.out.println(this.getTeam() + "["+this.getnumRow()+"]["+this.getnumPlayer()+"]" + " " + "{" + this.getType() + "}" + " is Taunting ");
			break;
		case Samurai:
			a = selectTarget(theirTeam);
			if (a != null) {
				a.currentHP -= 2 * atk;
				if (a.currentHP < 0) {
					a.currentHP = 0;
				}
				System.out.println(this.getTeam() + "["+this.getnumRow()+"]["+this.getnumPlayer()+"]" + " " + "{" + this.getType() + "}"
						+ " Double-Slashes " + a.getTeam() + "["+a.getnumRow()+ "]" + "["+a.getnumPlayer()+"]" + " " + "{" + a.getType() + "}");
			}
			break;
		case BlackMage:
			a = selectTarget(theirTeam);
			if (a != null) {
				a.cursed = true;
				System.out.println(this.getTeam() + "["+this.getnumRow()+"]["+this.getnumRow()+"]" + " " + "{" + this.getType() + "}" + " Curses "
						+ a.getTeam() + "["+a.getnumRow()+"]" + "["+a.getnumPlayer()+"]" + " " + "{" + a.getType() + "}");
			}
			break;
		case Phoenix:
			a = selectRevive(myTeam);
			if (a != null) {
				a.currentHP = 0.3 * a.maxHP;
				a.countSpecialTurns = 1;
				a.cursed = a.taunting = false;
				System.out.println(this.getTeam() + "[" + "][" + "]" + " " + "{" + this.getType() + "}" + " Revives "
						+ a.getTeam() + "["+a.getnumRow()+"]" + "["+a.getnumPlayer()+"]" + " " + "{" + a.getType() + "}");
			}
			break;
		}
	}

	/**
	 * This method is called by Arena when it is this player's turn to take an
	 * action. By default, the player simply just "attack(target)". However,
	 * once this player has fought for "numSpecialTurns" rounds, this player
	 * must perform "useSpecialAbility(myTeam, theirTeam)" where each player
	 * type performs his own special move.
	 * 
	 * @param arena
	 */

	public void takeAction(Arena arena) {

		// COMPLETE: INSERT YOUR CODE HERE

		// this player is team A or B
		if (arena.isMemberOf(this, Arena.Team.A)) {
			if (countSpecialTurns % numSpecialTurns == 0) {
				useSpecialAbility(arena.getTeamA(), arena.getTeamB());
			} else {
				taunting = cursed = false;
				attack(selectTarget(arena.getTeamB()));
			}
		} else {
			if (countSpecialTurns % numSpecialTurns == 0) {
				useSpecialAbility(arena.getTeamB(), arena.getTeamA());
			} else {
				taunting = cursed = false;
				attack(selectTarget(arena.getTeamA()));
			}
		}
	}

	// AddMore: select for Heal
	/**
	 * This method called by Healer for search target (Heal).
	 * Find the lowest percentage HP.
	 * First, define double lowestPercentHP equal to 1.
	 * Second, build object p and set it to null. 
	 * Using for loop, if the percentage HP of team[i][j](target) is lower than lowestPercentHP
	 * then set percentage of team[i][j] to lowestPercentHP and set team[i][j] to p instead. 
	 * Returns p.
	 * @param team
	 * @return
	 */
	
	public Player selectHeal(Player[][] team) {
		double lowestPercentHP = 1;
		Player p = null;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < team[0].length; j++) {
				if (team[i][j].getCurrentHP() / team[i][j].getMaxHP() < lowestPercentHP && team[i][j].isAlive()) {
					lowestPercentHP = team[i][j].getCurrentHP() / team[i][j].getMaxHP();
					p = team[i][j];
				}
			}
		}
		return p;
	}

	// AddMore: select for Revive
	/**
	 * This method called by Phoenix for search target (Revive).
	 * Using loop to find a player that die, if found then return that player.
	 * If not return null (no target). 
	 * @param team
	 * @return
	 */
	public Player selectRevive(Player[][] team) {

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < team[0].length; j++) {
				if (team[i][j].isAlive() == false) {
					return team[i][j];
				}

			}
		}
		return null;
	}

	// AddMore: select target
	/**
	 * This method is called by this player for select the target to attack.
	 * Moreover, it can use for Cursed(Samurai) and Double-Slashes.
	 * The target must be the lowestHP.
	 * First, define lowesrHP = 5000, build int positionOfRow and positionOfPlayer and initialize them to 0.
	 * Second, using loop for find Taunt(it must be alive). If found return Taunt. 
	 * If not, find the target in the next loop. 
	 * Find the front row first. (Using boolean f and initialize it equal to false) 
	 * If found the target that has lowestHP in the first row then set boolean f to true (for break loop)
	 * and the position of the target for return.
	 * If all player in the first row die then find target in the second row.
	 * If found the target then return the position of that target.
	 * @param team
	 * @return
	 */
	public Player selectTarget(Player[][] team) {
		double lowestHP = 50000;
		int positionOfRow = 0;
		int positionOfPlayer = 0;

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < team[0].length; j++) {
				if (team[i][j].isTaunting() && team[i][j].isAlive()) {
					return team[i][j];
				}
			}
		}

		boolean f = false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < team[0].length; j++) {
				if (i == 0) {
					if (team[i][j].getCurrentHP() < lowestHP && team[i][j].isAlive()) {
						f = true;
						lowestHP = team[i][j].getCurrentHP();
						positionOfRow = i;
						positionOfPlayer = j;
					}
				} else {
					if (team[i][j].getCurrentHP() < lowestHP && team[i][j].isAlive()) {
						lowestHP = team[i][j].getCurrentHP();
						positionOfRow = i;
						positionOfPlayer = j;
					}
				}
			}
			if (f) {
				break;
			}
		}
		return team[positionOfRow][positionOfPlayer];
	}

	//AddMore for check output
	/**
	 * set the row of this player in Arena (startBattle method).
	 * Rows can be 0 or 1.
	 * @param position
	 */
	public void setnumRow(int row){
		this.numRow = row;	
		}
	
	//AddMore for check output
	/**
	 * numRow == 0 mean Front row
	 * else numRow must be 1 (Back row) 
	 * Return String "Front" or "Back"(depend on this.numRow).
	 * @return
	 */
	public String getnumRow(){
		if(this.numRow==0) return "Front";
		else return "Back";
	}
	
	//AddMore for check output
	/**
	 * set the position of this player in Arena (startBattle method).
	 * @param position
	 */
	public void setnumPlayer(int position){
		this.numPlayer = position;	
	}
	
	//AddMore for check output
	/**
	 * Return position of this player.
	 * @return
	 */
	public int getnumPlayer(){
		 return this.numPlayer;	
	}
	

	/**
	 * This method overrides the default Object's toString() and is already
	 * implemented for you.
	 */
	@Override
	public String toString() {
		return "[" + this.type.toString() + " HP:" + this.currentHP + "/" + this.maxHP + " ATK:" + this.atk + "]";
	}
}
