package textAdventure;

import java.util.*;

/* TO DO LIST --> make into ISSUES on GitHub
 * Create object constructor for rooms (array list or hashmap?)
 * Create object constructor for items 
 * create static values for character (exmp: health, inventory size,)
 * Figure out how to track player progression and movement
 * figure out how to make a window with the Console
 * create setup method and main game loop */

/*To Do:
 * Deal with invalid inputs, code only accepts capitals NSEWUD
 * Puzzles need to be set up, etc. boolean dark (hides true descp while true), when rooms can't be accessed until x action (move bookshelf)
 */
public class AdventureMain {
	
	public static void main(String[]args) {		
		new AdventureMain(); //this means that we don't need to make everything static
	}
	
	HashMap<String,Room> roomList = new HashMap<String, Room>();
	Room currentRoom; 
	ArrayList<Item> items = new ArrayList<Item>();
	Player player;
	//Put global variables here^^^
	
	AdventureMain() {
		setUp();
		gamemain();
	}

	//main game Method
	void gamemain() {
		////Instance variables
		boolean playing = true;
		String command = ""; 

		/***** MAIN GAME LOOP *****/
		while (playing) { 
			command = getCommand(); 
			playing = parseCommand(command);
		}
	}

	void setUp() {
		Item.makeItem(items); //this will make all items and add them to the items arraylist
		Room.setupRooms(roomList);
		currentRoom = roomList.get("Lab1");
		System.out.println("Intro Message");
		System.out.println(currentRoom.toString());
		player = new Player();
	}
	
	String getCommand() { //gets user input
		Scanner sc = new Scanner(System.in);
		System.out.println("Test:NSEWUD");
		String text = sc.nextLine();
		text  = text.trim();
		//sc.close();
		return text;
	}

	void moveToRoom(char c) {
		String nextRoom;
		nextRoom = currentRoom.getExit(c);

		if (!nextRoom.equals("")) {
			currentRoom = roomList.get(nextRoom);
			System.out.println(currentRoom.toString());
		}
	}

	boolean parseCommand(String text) {
		text = text.toUpperCase();
		switch(text) {		
		case "N": case "S": case "W": case "E": case "U": case "D":
			moveToRoom(text.charAt(0));
		}
		return true;
	}
}
	
