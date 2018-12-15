package ch.fhnw.projectbois.game;

import java.net.URL;
import java.util.ArrayList;

import ch.fhnw.projectbois._mvc.Controller;
import ch.fhnw.projectbois._mvc.View;
import ch.fhnw.projectbois.components.chat.ChatController;
import ch.fhnw.projectbois.components.chat.ChatModel;
import ch.fhnw.projectbois.components.chat.ChatView;
import ch.fhnw.projectbois.gameobjects.GameState;
import ch.fhnw.projectbois.gameobjects.Player;
import ch.fhnw.projectbois.session.Session;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameView extends View<GameModel> {

	private GamePlayerContainer playerContainer = null;
	private ArrayList<GamePlayerContainer> opponentContainers = null;

	public GameView(GameModel model) {
		super(model);

		this.opponentContainers = new ArrayList<>();
	}

	@Override
	protected URL getFXML() {
		return this.getClass().getResource("GameView.fxml");
	}

	@Override
	public <T extends Controller<GameModel, ? extends View<GameModel>>> void loadRoot(T controller) {
		super.loadRoot(controller);

		// StackPane - new root
		StackPane stackpane = new StackPane();

		// Add game pane
		Parent game = this.root;
		stackpane.getChildren().add(game);

		// Add chat pane
		ChatController chatController = Controller.initMVC(ChatController.class, ChatModel.class, ChatView.class);
		AnchorPane chat = (AnchorPane) chatController.getViewRoot();
		stackpane.getChildren().add(chat);
		StackPane.setAlignment(chat, Pos.TOP_RIGHT);
		chat.setPrefWidth(200);
		chat.setMaxWidth(200);
		chat.setPrefHeight(ChatView.PREF_HEIGHT);
		chat.setMaxHeight(ChatView.PREF_HEIGHT);
		chatController.closeChat();

		// Set gridpane as new root
		this.root = stackpane;
	}

	public void initGamePlayerContainers() {
		GameState gameState = model.getGameState();
		int playerIndex = model.getPlayerIndex();

		ArrayList<Player> players = gameState.getBoard().getPlayers();

		Player currentPlayer = players.stream().filter(f -> f.getUsername().equals(Session.getCurrentUsername()))
				.findFirst().get();

		this.initGamePlayerContainerOpponent(1, currentPlayer, true);

		// turn clockwise -> player to left after current player
		int opponentNr = 2;
		for (int i = playerIndex + 1; i < players.size(); i++) {
			Player player = players.get(i);
			this.initGamePlayerContainerOpponent(opponentNr, player, false);
			opponentNr++;
		}

		for (int i = 0; i < playerIndex; i++) {
			Player player = players.get(i);
			this.initGamePlayerContainerOpponent(opponentNr, player, false);
			opponentNr++;
		}

		Platform.runLater(() -> {
			// Player count
			// removeIf:
			// https://stackoverflow.com/questions/23002532/javafx-2-how-do-i-delete-a-row-or-column-in-gridpane
			int playerCount = gameState.getBoard().getPlayers().size();
			GridPane pnlField = (GridPane) this.root.lookup("#pnlField");

			if (playerCount == 3) {
				// pnlField.getRowConstraints().remove(3);

				pnlField.getRowConstraints().get(3).setPercentHeight(-1);
				pnlField.getRowConstraints().get(2).setPercentHeight(18);
				pnlField.getRowConstraints().get(1).setPercentHeight(18);
				pnlField.getChildren()
						.removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == 3);

			} else if (playerCount == 2) {
				// pnlField.getRowConstraints().remove(3);
				// pnlField.getRowConstraints().remove(2);

				pnlField.getRowConstraints().get(3).setPercentHeight(-1);
				pnlField.getRowConstraints().get(2).setPercentHeight(-1);
				pnlField.getRowConstraints().get(1).setPercentHeight(36);
				pnlField.getChildren()
						.removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == 3);
				pnlField.getChildren()
						.removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == 2);
			}
		});
	}

	public void initGamePlayerContainerOpponent(int opponentNr, Player player, boolean isCurrentPlayer) {
		GamePlayerContainer container = new GamePlayerContainer();
		container.setUsername(player.getUsername());

		Label lblName = (Label) this.root.lookup("#lblName" + opponentNr);
		Label lblPoints = (Label) this.root.lookup("#lblPoints" + opponentNr);
		Label lblMeeples = (Label) this.root.lookup("#lblMeeples" + opponentNr);

		container.setLblUsername(lblName);
		container.setLblPoints(lblPoints);
		container.setLblMeeples(lblMeeples);

		if (isCurrentPlayer) {
			container.setPlayerRow(5);
			this.playerContainer = container;
		} else {
			container.setPlayerRow(opponentNr - 1);
			this.opponentContainers.add(container);
		}

		Platform.runLater(() -> {
			lblName.setText(player.getUsername());
			lblPoints.setText(translator.getTranslation("lbl_Points", player.getPoints()));
			lblMeeples.setText(translator.getTranslation("lbl_Meeples", player.getMeeples()));
		});
	}

	public void initLocations() {
		String cssFileName;

		if (model.getGameState().isCardSideA()) {
			cssFileName = "GameViewSideA.css";
		} else {
			cssFileName = "GameViewSideB.css";
		}

		Platform.runLater(() -> {
			String css = this.getClass().getResource(cssFileName).toExternalForm();
			this.root.getStylesheets().add(css);
		});
	}

	// GETTER AND SETTER

	public GamePlayerContainer getPlayerContainer() {
		return playerContainer;
	}

	public void setPlayerContainer(GamePlayerContainer playerContainer) {
		this.playerContainer = playerContainer;
	}

	public ArrayList<GamePlayerContainer> getOpponentContainers() {
		return opponentContainers;
	}

	public void setOpponentContainers(ArrayList<GamePlayerContainer> opponentContainers) {
		this.opponentContainers = opponentContainers;
	}

}
