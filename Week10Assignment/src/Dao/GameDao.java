package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Game;

public class GameDao {
	
	private Connection connection;
	private final String GET_GAMES_BY_CONSOLE_ID_QUERY = "SELECT * FROM games WHERE console_id = ?";
	private final String DELETE_GAMES_BY_CONSOLE_ID_QUERY = "DELETE FROM games WHERE console_id = ?";
	private final String CREATE_NEW_GAME_QUERY = "INSERT INTO games(title, console_id) VALUES(?,?)";
	private final String DELETE_GAME_BY_ID_QUERY = "DELETE FROM games WHERE game_id = ?";
	
	public GameDao() {
		connection = DBConnection.getConnection();
	}

	public List<Game> getGamesByConsoleId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_GAMES_BY_CONSOLE_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		List<Game> games = new ArrayList<Game>();
		
		while (rs.next()) {
			games.add(new Game(rs.getInt(1), rs.getString(2)));
		}
		
		return games;
	}
	
	public void createNewGame(String title, int consoleId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_GAME_QUERY);
		ps.setString(1, title);
		ps.setInt(2, consoleId);
		ps.executeUpdate();
	}
	
	public void deleteGamesByTeamId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_GAMES_BY_CONSOLE_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void deleteGameById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_GAME_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

}
