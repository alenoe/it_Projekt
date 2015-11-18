package gameLogic;

import java.util.ArrayList;

import interfaces.Observer;
import interfaces.Subject;

public class GameM implements Observer{
	
	public static final int MAX_VP = 20;
	public static final int MAX_HP = 10;
	
	//Klassenvariablen
	private DiceM dice = new DiceM(6);
	private boolean gameState;
	private ArrayList<PlayerInGameM> players = new ArrayList<PlayerInGameM>();
	private int rounds;
	private boolean endOfGame = false;
	
	public GameM(PlayerInGameM player1, PlayerInGameM player2){
		this.gameState = true;
		this.players.add(player1);
		this.players.add(player2);
		this.rounds = 0;
		
		while(endOfGame = false){
			this.addRound();
		}
	}
	
	public GameM(PlayerInGameM player1, PlayerInGameM player2, PlayerInGameM player3){
		this.gameState = true;
		this.players.add(player1);
		this.players.add(player2);
		this.players.add(player3);
		this.rounds = 0;
	}
	
	public GameM(PlayerInGameM player1, PlayerInGameM player2, PlayerInGameM player3, PlayerInGameM player4){
		this.gameState = true;
		this.players.add(player1);
		this.players.add(player2);
		this.players.add(player3);
		this.players.add(player4);
		this.rounds = 0;
	}
	
//	public PlayerInGameM getCurrentPlayer(){
//		return 
//	}
	
	public void addRound(){
		if(endOfGame = false){
			this.rounds = rounds++;
			GameRoundM nextRound = new GameRoundM(players, dice);
		} else {
			endOfGame = true;
		}		
	}

	@Override
	public void update(Subject o, Object arg) {
		for(PlayerInGameM p : players){
			PlayerInGameM pNew = (PlayerInGameM) p.getUpdate(this);
			p.setVictoryPoints(pNew.getVictoryPoints());
			p.setHealthPoints(pNew.getHealthPoints());
			p.setPositionTokyo();
	}
	
	}

	@Override
	public void setSubject(Subject sub) {
	this.players = (ArrayList<PlayerInGameM>) sub;
	
	}

}
