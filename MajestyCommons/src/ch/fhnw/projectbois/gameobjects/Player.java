package ch.fhnw.projectbois.gameobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Player {

	private String username;
	private Location[] locations;
	private int meeples;
	private int points;

	public Player() {
		this.locations = new Location[8];
		for (int i = 0; i < this.locations.length; i++) {
			this.locations[i] = new Location();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Location[] getLocations() {
		return locations;
	}

	public int getMeeples() {
		return meeples;
	}

	public void setMeeples(int meeples) {
		this.meeples = meeples;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@JsonIgnore
	public Location getLocationByIndex(int locationIndex) {
		return this.locations[locationIndex];
	}

}
