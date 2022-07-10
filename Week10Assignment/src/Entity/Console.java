package Entity;

import java.util.List;

public class Console {
	
	private int consoleId;
	private String consoleName;
	private List<Game> game;
	
	public Console(int consoleId, String consoleName, List<Game> game) {
		this.consoleId = consoleId;
		this.consoleName = consoleName;
		this.game = game;
	}

	public int getConsoleId() {
		return consoleId;
	}

	public void setConsoleId(int consoleId) {
		this.consoleId = consoleId;
	}

	public String getConsoleName() {
		return consoleName;
	}

	public void setConsoleName(String consoleName) {
		this.consoleName = consoleName;
	}

	public List<Game> getGame() {
		return game;
	}

	public void setGame(List<Game> game) {
		this.game = game;
	}

}
