package ch.fhnw.projectbois.game;

import javafx.scene.control.Label;

public class GamePlayerContainer {

	private String username = null;
	private int playerRow = -1;

	private Label lblUsername = null;
	private Label lblPoints = null;
	private Label lblMeeples = null;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPlayerRow() {
		return playerRow;
	}

	public void setPlayerRow(int playerRow) {
		this.playerRow = playerRow;
	}

	public Label getLblUsername() {
		return lblUsername;
	}

	public void setLblUsername(Label lblUsername) {
		this.lblUsername = lblUsername;
	}

	public Label getLblPoints() {
		return lblPoints;
	}

	public void setLblPoints(Label lblPoints) {
		this.lblPoints = lblPoints;
	}

	public Label getLblMeeples() {
		return lblMeeples;
	}

	public void setLblMeeples(Label lblMeeples) {
		this.lblMeeples = lblMeeples;
	}

}
