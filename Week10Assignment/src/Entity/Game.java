package Entity;

public class Game {
	
	private int gameId;
	private String title;
	
	public Game(int gameId, String title) {
		this.setGameId(gameId);
		this.setTitle(title);
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
