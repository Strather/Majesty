package ch.fhnw.projectbois.components.chat;

import ch.fhnw.projectbois._mvc.Controller;
import ch.fhnw.projectbois.dto.MessageDTO;
import ch.fhnw.projectbois.enumerations.ChatMember;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author Leeroy Koller
 *
 */

public class ChatController extends Controller<ChatModel, ChatView> {

	// declaration of things I need need in code
	@FXML
	private TextArea txtChat;

	@FXML
	private TextField txtMessage;

	@FXML
	private Button btnSend;

	@FXML
	private ImageView imgSend;

	@FXML
	private Button btnClose;

	@FXML
	private AnchorPane pnlRoot;
	
	private boolean isClosed = false;

	public ChatController(ChatModel model, ChatView view) {
		super(model, view);
	}

	@Override
	protected void initialize() {
		super.initialize();

		model.getLobbyInfo();

		model.getChatProperty().addListener((observer2, oldValue2, newValue2) -> {
			updateChatView(newValue2);
		});
	}

	private void updateChatView(MessageDTO message) {
		Platform.runLater(() -> {
			// check if author is Player1-4, then post message as globally
			if (message.getAuthor() != ChatMember.System || message.getAuthor() != ChatMember.All
					|| message.getAuthor() != null) {

				String username = model.getUsernameByChatmember(message.getAuthor());
				txtChat.appendText(username + ": " + message.getMessage() + "\n");
			}

		});
	}

	@FXML
	private void btnSend_Click(ActionEvent event) {
		MessageDTO message = new MessageDTO();
		
		if(!txtMessage.getText().isEmpty()) {
			message.setMessage(txtMessage.getText());
			message.setAuthor(model.getCurrentUserChatMember());

			// check if whispering and set receiver
			String messageText = message.getMessage();
			String[] parts = messageText.split(":");
			String part1 = parts[0];

			ChatMember receiver = model.getUsernameMap().get(part1);
			System.out.println(receiver);
			if (receiver != null && receiver != model.getCurrentUserChatMember()) {
				String newMessage = "";
				for (int i = 1; i < parts.length; i++) {
					newMessage += parts[i];

					if (i + 1 < parts.length) {
						newMessage += ":";
					}
				}
				message.setMessage(newMessage);
				message.setReceiver(receiver);
			} else {
				message.setReceiver(ChatMember.All);
			}

			model.sendMessage(message);
		}
		
		txtMessage.clear();
	}

	@FXML
	private void onEnterKey(KeyEvent e) {
		KeyCode key = e.getCode();
		if (key == KeyCode.ENTER) {
			btnSend_Click(new ActionEvent());
		}
	}
	
	
	@FXML
	private void btnMinimize_Click(ActionEvent event) {

		if (isClosed == false) {
			txtChat.setVisible(false);
			txtMessage.setVisible(false);
			btnSend.setVisible(false);
			imgSend.setVisible(false);

			pnlRoot.setMinHeight(20);
			pnlRoot.setMaxHeight(20);
			pnlRoot.setPrefHeight(20);
			isClosed = true;
		} else if (isClosed == true) {
			txtChat.setVisible(true);
			txtMessage.setVisible(true);
			btnSend.setVisible(true);
			imgSend.setVisible(true);

			//match prefHeight to window size
			pnlRoot.setMaxHeight(1000);
			pnlRoot.setPrefHeight(1000);
			isClosed = false;
		}

	}

}
