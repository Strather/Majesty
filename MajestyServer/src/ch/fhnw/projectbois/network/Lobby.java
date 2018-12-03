package ch.fhnw.projectbois.network;

import java.util.ArrayList;
import ch.fhnw.projectbois.game.GameStateServer;
import ch.fhnw.projectbois.gameobjects.GameState;
import ch.fhnw.projectbois.general.IdFactory;

/**
 * 
 * @author Rosario Brancato
 *
 */
public class Lobby {

	private int id = -1;
	private boolean cardSideA = true;

	private ArrayList<ServerClient> clients = new ArrayList<>();

	private boolean gameStarted = false;
	private GameState gameState;
	private GameStateServer gameStateServer;

	public Lobby() {
		this.id = IdFactory.getInstance().getNewId(this.getClass().getName());
	}

	public synchronized boolean addClient(ServerClient client) {
		boolean success = false;

		if (this.isNotFull()) {
			client.setLobby(this);
			this.clients.add(client);
			success = true;
		}

		return success;
	}

	public boolean removeClient(ServerClient client) {
		return this.clients.removeIf(f -> f.getUser().getId() == client.getUser().getId());
	}

	public boolean isNotFull() {
		return this.clients.size() < 4;
	}

	public boolean isEmpty() {
		return this.clients.size() <= 0;
	}

	public int getId() {
		return this.id;
	}

	public ArrayList<ServerClient> getClients() {
		return this.clients;
	}

	public boolean isCardSideA() {
		return cardSideA;
	}

	public void setCardSideA(boolean cardSideA) {
		this.cardSideA = cardSideA;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public GameStateServer getGameStateServer() {
		return gameStateServer;
	}

	public void setGameStateServer(GameStateServer gameStateServer) {
		this.gameStateServer = gameStateServer;
	}

	public boolean isGameStarted() {
		return this.gameStarted;
	}

	public void setGameStarted(boolean started) {
		this.gameStarted = started;
	}

}
