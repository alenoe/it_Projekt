package Bonuspunkte.Client.LobbyOverview;

import javafx.scene.control.ListCell;


/**
 * 
 * @author Damian
 * This Class makes sure that the ListView recognises when select elements
 * Ideas and problem-solutions from:
 * https://community.oracle.com/thread/2528460?tstart=0
 * http://code.makery.ch/blog/javafx-8-event-handling-examples/
 * 
 */
/**static**/ class MyCustomCell extends ListCell<PseudoLobby> {
	@Override
	public void updateItem(PseudoLobby item, boolean empty) {
		super.updateItem(item, empty);
		if (item == null || empty) {
				setText(null);
			} else {
				setText(item.getLobbyText() + " " + item.getLobbyNumber());
			}
	}
}
