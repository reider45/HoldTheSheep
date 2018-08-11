package me.reider45.HoldTheSheep.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class Game {
	
	// Game States
	public enum State {
		LOBBY, INGAME, RESTARTING
	}
	
	private static Game game;
	
	public static Game getInstance(){
		return game;
	}
	
	private Integer lobbyTimer;
	private Integer gameTimer;
	private Integer playersNeeded;
	private List<UUID> players;
	private List<Location> spawns;
	private State state;
	
	private Sheep sheep;
	private Player sheepHolder;
	
	private HashMap<UUID, Integer> score = new HashMap<UUID, Integer>();
	
	public Game(Integer lobbyTimer, Integer gameTimer, Integer playersNeeded, List<Location> spawns) {
		this.lobbyTimer = lobbyTimer;
		this.gameTimer = gameTimer;
		this.playersNeeded = playersNeeded;
		this.players = new ArrayList<UUID>();
		this.spawns = spawns;
		this.state = State.LOBBY;
		
		game = this;
	}
	
	// Score
	public HashMap<UUID, Integer> getScores(){
		return this.score;
	}
	
	public void setScore(Player p, Integer score){
		this.score.remove(p.getUniqueId());
		this.score.put(p.getUniqueId(), score);
	}
	
	// Sheep
	public Sheep getSheep(){
		return this.sheep;
	}
	
	public void setSheep(Sheep s){
		this.sheep = s;
	}
	
	public Player getSheepHolder(){
		return this.sheepHolder;
	}
	
	public void setSheepHolder(Player holder){
		this.sheepHolder = holder;
	}
	
	// Lobby
	
	public Integer getLobbyTimer(){
		return this.lobbyTimer;
	}
	
	public void setLobbyTimer(int time){
		this.lobbyTimer = time;
	}
	
	// Game
	
	public Integer getGameTimer(){
		return this.gameTimer;
	}
	
	public void setGameTimer(int time){
		this.gameTimer = time;
	}
	
	// Players
	
	public List<UUID> getPlayers(){
		return this.players;
	}
	
	public Integer getPlayersNeeded(){
		return this.playersNeeded;
	}
	
	// Spawns
	
	public List<Location> getSpawns(){
		return this.spawns;
	}

	// State
	
	public State getState(){
		return this.state;
	}
	
	public void setState(State s){
		this.state = s;
	}

}
