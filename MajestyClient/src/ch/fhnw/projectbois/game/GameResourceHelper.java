package ch.fhnw.projectbois.game;

import ch.fhnw.projectbois.gameobjects.Board;
import ch.fhnw.projectbois.gameobjects.Card;
import ch.fhnw.projectbois.gameobjects.CardType;

public class GameResourceHelper {

	private final String PATH_TO_CARD = "game/cards/character%20cards/";
	private final String PATH_TO_SPLIT_CARD = "game/cards/split%20cards/";
	//private final String PATH_TO_LOCATION_A = "game/locations/Side%20A/";
	//private final String PATH_TO_LOCATION_B = "game/locations/Side%20B/";
	private final String PATH_TO_CARDBACKS = "game/cards/backs/";

	public String getUrlByCard(Card card) {
		String url = "";

		if (card != null) {
			if (card.isSplitCard()) {
				if (card.getCardType1() == CardType.Guard && card.getCardType2() == CardType.Knight) {
					url = PATH_TO_SPLIT_CARD + "Blue%20Red.jpg";

				} else if (card.getCardType1() == CardType.Guard && card.getCardType2() == CardType.Noble) {
					url = PATH_TO_SPLIT_CARD + "Blue%20Violet.jpg";

				} else if (card.getCardType1() == CardType.Guard && card.getCardType2() == CardType.Innkeeper) {
					url = PATH_TO_SPLIT_CARD + "Blue%20Yellow.jpg";

				} else if (card.getCardType1() == CardType.Brewer && card.getCardType2() == CardType.Witch) {
					url = PATH_TO_SPLIT_CARD + "Brown%20Green.jpg";

				} else if (card.getCardType1() == CardType.Brewer && card.getCardType2() == CardType.Knight) {
					url = PATH_TO_SPLIT_CARD + "Brown%20Red.jpg";

				} else if (card.getCardType1() == CardType.Witch && card.getCardType2() == CardType.Guard) {
					url = PATH_TO_SPLIT_CARD + "Green%20Blue.jpg";

				} else if (card.getCardType1() == CardType.Witch && card.getCardType2() == CardType.Noble) {
					url = PATH_TO_SPLIT_CARD + "Green%20Violet.jpg";

				} else if (card.getCardType1() == CardType.Witch && card.getCardType2() == CardType.Innkeeper) {
					url = PATH_TO_SPLIT_CARD + "Green%20Yellow.jpg";

				} else if (card.getCardType1() == CardType.Miller && card.getCardType2() == CardType.Brewer) {
					url = PATH_TO_SPLIT_CARD + "Orange%20Brown.jpg";

				} else if (card.getCardType1() == CardType.Miller && card.getCardType2() == CardType.Knight) {
					url = PATH_TO_SPLIT_CARD + "Orange%20Red.jpg";

				} else if (card.getCardType1() == CardType.Knight && card.getCardType2() == CardType.Innkeeper) {
					url = PATH_TO_SPLIT_CARD + "Red%20Yellow.jpg";

				} else if (card.getCardType1() == CardType.Innkeeper && card.getCardType2() == CardType.Noble) {
					url = PATH_TO_SPLIT_CARD + "Yellow%20Violet.jpg";
				}

			} else {
				switch (card.getCardTypeActive()) {
				case Miller:
					url = PATH_TO_CARD + "Orange.jpg";
					break;
				case Brewer:
					url = PATH_TO_CARD + "Brown.jpg";
					break;
				case Guard:
					url = PATH_TO_CARD + "Blue.jpg";
					break;
				case Innkeeper:
					url = PATH_TO_CARD + "Yellow.jpg";
					break;
				case Knight:
					url = PATH_TO_CARD + "Red.jpg";
					break;
				case Noble:
					url = PATH_TO_CARD + "Violet.jpg";
					break;
				case Witch:
					url = PATH_TO_CARD + "Green.jpg";
					break;
				}
			}
		}

		return url;
	}

	public String getUrlGetBackImage(int deckBack) {
		String url = "";

		switch (deckBack) {
		case Board.DECKBACK_TIER1:
			url = PATH_TO_CARDBACKS + "Back%201.jpg";
			break;
		case Board.DECKBACK_TIER2:
			url = PATH_TO_CARDBACKS + "Back%202.jpg";
			break;
		case Board.DECKBACK_EMPTY:
			// image = null;
			break;
		}

		return url;
	}
	
	
//	public Image getCardImage(Card card) {
//		Image image = null;
//
//		if (card != null) {
//			if (card.isSplitCard()) {
//				if (card.getCardType1() == CardType.Guard && card.getCardType2() == CardType.Knight) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Blue Red.jpg");
//
//				} else if (card.getCardType1() == CardType.Guard && card.getCardType2() == CardType.Noble) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Blue Violet.jpg");
//
//				} else if (card.getCardType1() == CardType.Guard && card.getCardType2() == CardType.Innkeeper) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Blue Yellow.jpg");
//
//				} else if (card.getCardType1() == CardType.Brewer && card.getCardType2() == CardType.Witch) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Brown Green.jpg");
//
//				} else if (card.getCardType1() == CardType.Brewer && card.getCardType2() == CardType.Knight) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Brown Red.jpg");
//
//				} else if (card.getCardType1() == CardType.Witch && card.getCardType2() == CardType.Guard) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Green Blue.jpg");
//
//				} else if (card.getCardType1() == CardType.Witch && card.getCardType2() == CardType.Noble) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Green Violet.jpg");
//
//				} else if (card.getCardType1() == CardType.Witch && card.getCardType2() == CardType.Innkeeper) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Green Yellow.jpg");
//
//				} else if (card.getCardType1() == CardType.Miller && card.getCardType2() == CardType.Brewer) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Orange Brown.jpg");
//
//				} else if (card.getCardType1() == CardType.Miller && card.getCardType2() == CardType.Knight) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Orange Red.jpg");
//
//				} else if (card.getCardType1() == CardType.Knight && card.getCardType2() == CardType.Innkeeper) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Red Yellow.jpg");
//
//				} else if (card.getCardType1() == CardType.Innkeeper && card.getCardType2() == CardType.Noble) {
//					image = new Image(PATH_TO_SPLIT_CARD + "Yellow Violet.jpg");
//				}
//
//			} else {
//				switch (card.getCardTypeActive()) {
//				case Miller:
//					image = new Image(PATH_TO_CARD + "Orange.jpg");
//					break;
//				case Brewer:
//					image = new Image(PATH_TO_CARD + "Brown.jpg");
//					break;
//				case Guard:
//					image = new Image(PATH_TO_CARD + "Blue.jpg");
//					break;
//				case Innkeeper:
//					image = new Image(PATH_TO_CARD + "Yellow.jpg");
//					break;
//				case Knight:
//					image = new Image(PATH_TO_CARD + "Red.jpg");
//					break;
//				case Noble:
//					image = new Image(PATH_TO_CARD + "Violet.jpg");
//					break;
//				case Witch:
//					image = new Image(PATH_TO_CARD + "Green.jpg");
//					break;
//				}
//			}
//		}
//
//		return image;
//	}
//
//	public Image getDeckBackImage(int deckBack) {
//		Image image = null;
//
//		switch (deckBack) {
//		case Board.DECKBACK_TIER1:
//			image = new Image(PATH_TO_CARDBACKS + "Back 1.jpg");
//			break;
//		case Board.DECKBACK_TIER2:
//			image = new Image(PATH_TO_CARDBACKS + "Back 2.jpg");
//			break;
//		case Board.DECKBACK_EMPTY:
//			// image = null;
//			break;
//		}
//
//		return image;
//	}
//
//	public Image getLocationImage(int location) {
//		Image image = null;
//
//		switch (location) {
//		case Location.A1:
//			image = new Image(PATH_TO_LOCATION_A + "Side A1.jpg");
//			break;
//		case Location.A2:
//			image = new Image(PATH_TO_LOCATION_A + "Side A2.jpg");
//			break;
//		case Location.A3:
//			image = new Image(PATH_TO_LOCATION_A + "Side A3.jpg");
//			break;
//		case Location.A4:
//			image = new Image(PATH_TO_LOCATION_A + "Side A4.jpg");
//			break;
//		case Location.A5:
//			image = new Image(PATH_TO_LOCATION_A + "Side A5.jpg");
//			break;
//		case Location.A6:
//			image = new Image(PATH_TO_LOCATION_A + "Side A6.jpg");
//			break;
//		case Location.A7:
//			image = new Image(PATH_TO_LOCATION_A + "Side A7.jpg");
//			break;
//		case Location.A8:
//			image = new Image(PATH_TO_LOCATION_A + "Side A8.jpg");
//			break;
//
//		case Location.B1:
//			image = new Image(PATH_TO_LOCATION_B + "Side B1.jpg");
//			break;
//		case Location.B2:
//			image = new Image(PATH_TO_LOCATION_B + "Side B2.jpg");
//			break;
//		case Location.B3:
//			image = new Image(PATH_TO_LOCATION_B + "Side B3.jpg");
//			break;
//		case Location.B4:
//			image = new Image(PATH_TO_LOCATION_B + "Side B4.jpg");
//			break;
//		case Location.B5:
//			image = new Image(PATH_TO_LOCATION_B + "Side B5.jpg");
//			break;
//		case Location.B6:
//			image = new Image(PATH_TO_LOCATION_B + "Side B6.jpg");
//			break;
//		case Location.B7:
//			image = new Image(PATH_TO_LOCATION_B + "Side B7.jpg");
//			break;
//		case Location.B8:
//			image = new Image(PATH_TO_LOCATION_B + "Side B8.jpg");
//			break;
//		}
//
//		return image;
//	}

}
