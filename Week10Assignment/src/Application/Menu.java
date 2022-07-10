package Application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Dao.ConsoleDao;
import Dao.GameDao;
import Entity.Console;
import Entity.Game;

public class Menu {
	
	private ConsoleDao consoleDao = new ConsoleDao();
	private GameDao gameDao = new GameDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display all consoles", 
			"Display one console", 
			"Add a new console", 
			"Remove a console", 
			"Add a game to your library", 
			"Remove a game from your library");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayConsoles();
				} else if (selection.equals("2")) {
					displayConsole();
				} else if (selection.equals("3")) {
					createConsole();
				} else if (selection.equals("4")) {
					deleteConsole();
				} else if (selection.equals("5")) {
					createGame();
				} else if (selection.equals("6")) {
					deleteGame();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
			System.out.println("Press 'Enter' to continue.");
			scanner.nextLine();
			
		} while (!selection.equals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Select an option:\n-------------------------------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayConsoles() throws SQLException {
		List<Console> consoles = consoleDao.getConsoles();
		for (Console console : consoles) {
			System.out.println(console.getConsoleId() + ": " + console.getConsoleName());
		}
	}
	
	private void displayConsole() throws SQLException {
		System.out.print("Enter a console ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Console console = consoleDao.getConsoleById(id);
		System.out.println(console.getConsoleId() + ": " + console.getConsoleName());
		for (Game game : console.getGame()) {
			System.out.println("\tGame Id: " + game.getGameId() + " - Title: " + game.getTitle());
		}
	}
	
	private void createConsole() throws SQLException {
		System.out.print("Enter the new console's name: ");
		String consoleName = scanner.nextLine();
		consoleDao.createNewConsole(consoleName);
	}
	
	private void deleteConsole() throws SQLException {
		System.out.print("Enter the console ID to delete:");
		int id = Integer.parseInt(scanner.nextLine());
		consoleDao.deleteConsoleById(id);
	}
	
	private void createGame() throws SQLException {
		System.out.print("Enter a game's title: ");
		String title = scanner.nextLine();
		System.out.println("Enter the console's ID that the game can be played on: ");
		int consoleId = Integer.parseInt(scanner.nextLine());
		gameDao.createNewGame(title, consoleId);
	}
	
	private void deleteGame() throws SQLException {
		System.out.println("Enter the game's ID to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		gameDao.deleteGameById(id);
		
	}

}
