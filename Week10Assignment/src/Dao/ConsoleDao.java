package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Console;

public class ConsoleDao {
	
	private Connection connection;
	private GameDao gameDao;
	private final String GET_CONSOLES_QUERY = "SELECT * FROM consoles";
	private final String GET_CONSOLE_BY_ID_QUERY = "SELECT * FROM consoles WHERE console_id = ?";
	private final String CREATE_NEW_CONSOLE_QUERY = "INSERT INTO consoles(console_name) VALUES(?)";
	private final String DELETE_CONSOLE_BY_ID_QUERY = "DELETE FROM consoles WHERE console_id = ?";
	
	public ConsoleDao() {
		connection = DBConnection.getConnection();
		gameDao = new GameDao();
	}
	
	public List<Console> getConsoles() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CONSOLES_QUERY).executeQuery();
		List<Console> consoles = new ArrayList<Console>();
		
		while (rs.next()) {
			consoles.add(populateConsole(rs.getInt(1), rs.getString(2)));
		}
		
		return consoles;
	}
	
	public Console getConsoleById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CONSOLE_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateConsole(rs.getInt(1), rs.getString(2));
	}
	
	public void createNewConsole(String consoleName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CONSOLE_QUERY);
		ps.setString(1, consoleName);
		ps.executeUpdate();
	}
	
	public void deleteConsoleById (int id) throws SQLException {
		gameDao.deleteGamesByTeamId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_CONSOLE_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	private Console populateConsole(int id, String name) throws SQLException {
		return new Console(id, name, gameDao.getGamesByConsoleId(id));
	}

}
