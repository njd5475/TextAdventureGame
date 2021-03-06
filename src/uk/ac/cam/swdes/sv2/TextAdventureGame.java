package uk.ac.cam.swdes.sv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * A demo entrypoint with a silly example
 * @author timgoh0
 *
 */
public class TextAdventureGame {

	/**
	 * Starts the game
	 * @param args
	 */
	public static void main(String[] args) {
		//A new buffered reader to read user input
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		
		
		//Creates a sample room
		Room r = new Room("Test room", "The long description of the test room");
		r.addItem(new Item("widget", "A widget!"));
		GameState s = GameState.getInstance();
		s.setCurrentRoom(r);
		//And another room adjacent to it
		Room n = new Room("The north room", "It's a room to the north!");
		r.addAdjacentRoom(n, Direction.NORTH);
		n.addAdjacentRoom(r, Direction.SOUTH);
		
		s.look();
		//Sit in a loop and wait for user input
		while(true){
			try {
				System.out.print("> ");
				GameGrammar.ParseString(b.readLine());
			} catch (ParseException e) {
				System.out.println("You want me to do what?");
			} catch (TokenMgrError e){
				System.out.println("I don't understand what you mean.");
			} catch (IOException e){
				System.err.println("Oops, something happened to stdin");
				e.printStackTrace();
			}
		}
	}

}
