package Bonuspunkte.Client.LobbyOverview;

public class PseudoLobby {
	
	int LobbyCounter = 0;
	int LobbyNumber;
	String LobbyStandardText = "Pseudolobby Nr.";
	String LobbyText;
	
	public PseudoLobby(){
		this.LobbyNumber = LobbyCounter;
		LobbyCounter++;
		LobbyText = LobbyStandardText;
	}

	public int getLobbyCounter() {
		return LobbyCounter;
	}

	public int getLobbyNumber() {
		return LobbyNumber;
	}

	public String getLobbyStandardText() {
		return LobbyStandardText;
	}

	public String getLobbyText() {
		return LobbyText;
	}

	public void setLobbyCounter(int lobbyCounter) {
		LobbyCounter = lobbyCounter;
	}

	public void setLobbyNumber(int lobbyNumber) {
		LobbyNumber = lobbyNumber;
	}

	public void setLobbyStandardText(String lobbyStandardText) {
		LobbyStandardText = lobbyStandardText;
	}

	public void setLobbyText(String lobbyText) {
		LobbyText = lobbyText;
	}
	
	public String toString(){
		return LobbyText + " " + LobbyNumber;
	}

}
