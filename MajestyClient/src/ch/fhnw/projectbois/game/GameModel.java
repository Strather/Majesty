package ch.fhnw.projectbois.game;

import ch.fhnw.projectbois._mvc.Model;
import ch.fhnw.projectbois.communication.Request;
import ch.fhnw.projectbois.communication.RequestId;
import ch.fhnw.projectbois.communication.Response;
import ch.fhnw.projectbois.communication.ResponseId;
import ch.fhnw.projectbois.dto.ReportDTO;
import ch.fhnw.projectbois.enumerations.ReportSeverity;
import ch.fhnw.projectbois.gameobjects.GameMove;
import ch.fhnw.projectbois.gameobjects.GameState;
import ch.fhnw.projectbois.gameobjects.Player;
import ch.fhnw.projectbois.json.JsonUtils;
import ch.fhnw.projectbois.network.Network;
import ch.fhnw.projectbois.session.Session;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class GameModel extends Model {

	private SimpleObjectProperty<GameState> gameStateProperty = null;
	private SimpleBooleanProperty gameEndProperty;

	public GameModel() {
		this.gameStateProperty = new SimpleObjectProperty<>();
		this.gameEndProperty = new SimpleBooleanProperty();
		this.initResponseListener();
	}

	public SimpleObjectProperty<GameState> getGameStateProperty() {
		return this.gameStateProperty;
	}
	
	public SimpleBooleanProperty getGameEndProperty() {
		return this.gameEndProperty;
	}

	public void getGameState() {
		Request request = new Request(Session.getCurrentUserToken(), RequestId.GET_GAMESTATE, null);
		Network.getInstance().sendRequest(request);
	}

	public void sendMove(GameMove move) {
		String json = JsonUtils.Serialize(move);
		Request request = new Request(Session.getCurrentUserToken(), RequestId.DO_MOVE, json);

		Network.getInstance().sendRequest(request);
	}
	
	public void leaveGame() {
		Request request = new Request(Session.getCurrentUserToken(), RequestId.lEAVE_GAME, null);
		Network.getInstance().sendRequest(request);
	}

	@Override
	protected ChangeListener<Response> getChangeListener() {
		return new ChangeListener<Response>() {
			@Override
			public void changed(ObservableValue<? extends Response> observable, Response oldValue, Response newValue) {

				if (newValue.getResponseId() == ResponseId.UPDATE_GAMESTATE) {
					String json = newValue.getJsonDataObject();
					GameState gameState = JsonUtils.Deserialize(json, GameState.class);
					
					gameStateProperty.setValue(gameState);

				} else if (newValue.getResponseId() == ResponseId.GAME_ENDED) {
					String json = newValue.getJsonDataObject();
					GameState gameState = JsonUtils.Deserialize(json, GameState.class);
					gameStateProperty.setValue(gameState);
					gameEndProperty.setValue(true);
					
					//ReportDTO report = new ReportDTO(ReportSeverity.INFO, "inf_Game_Endend");
					//getReportProperty().setValue(report);
					for (Player player : gameState.getBoard().getPlayers()) {
						System.out.println("Username: " + player.getUsername());
						System.out.println("GameCount: " + player.getFinalCalculation().getGameCount());
						System.out.println("InfirmaryCount: " + player.getFinalCalculation().getInfirmaryCount());
						System.out.println("LocationCount: " + player.getFinalCalculation().getLocationCount());
						System.out.println("MajorityCount: " + player.getFinalCalculation().getMajorityCount());
						System.out.println("TotalCount: " + player.getFinalCalculation().getTotalCount());
					}
					
				} else if(newValue.getResponseId() == ResponseId.GAME_PLAYER_LEFT) {
					String json = newValue.getJsonDataObject();
					GameState gameState = JsonUtils.Deserialize(json, GameState.class);
					gameStateProperty.setValue(gameState);
					
					ReportDTO report = new ReportDTO(ReportSeverity.INFO, "inf_Player_Left_Game");
					getReportProperty().setValue(report);
					
				} else if(newValue.getResponseId() == ResponseId.GAME_TURN_TIME_OVER) {
					String json = newValue.getJsonDataObject();
					GameState  gameState = JsonUtils.Deserialize(json, GameState.class);
					gameStateProperty.setValue(gameState);
					
					ReportDTO report = new ReportDTO(ReportSeverity.WARNING, "wrn_Game_Turn_Time_Over");
					getReportProperty().setValue(report);
					
				} else if (newValue.getResponseId() == ResponseId.GAME_ERROR) {
					String json = newValue.getJsonDataObject();
					ReportDTO report = JsonUtils.Deserialize(json, ReportDTO.class);

					getReportProperty().setValue(report);
				}
			}
		};
	}

}
