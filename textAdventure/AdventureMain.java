package textAdventure;

import java.util.*;

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
	ArrayList<Item> invList = new ArrayList<Item>();
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
		System.out.println("Thank you for playing");
	}

	void setUp() {
		Item.makeItem(items); //this will make all items and add them to the items arraylist
		Room.setupRooms(roomList);
		currentRoom = roomList.get("Lab1");
		System.out.println("You just woke up from a deep sleep. "
				+ "\nyou have no idea where you are but you know you need to escape");
		System.out.println(currentRoom.toString());
		player = new Player();
	}

	String getCommand() { //gets user input
		Scanner sc = new Scanner(System.in);
		System.out.println("\nPlease enter command:");
		String text = sc.nextLine();
		text  = text.trim();
		//sc.close();
		return text;
	}

	boolean parseCommand(String text) {
		text = text.toUpperCase();
		switch(text) {		
		case "N": case "S": case "W": case "E": case "U": case "D": 
		case "NORTH": case "SOUTH": case "WEST": case "EAST": case "UP": case "DOWN":
			moveToRoom(text.charAt(0));
			break;
		case "I": case "INVENTORY": case "INV":
			displayInv();
			break;
		case "EXIT":
			return false;
		case "EAT":
			eatItem();
			break;
		case "HELP":
			System.out.println("Here is a list of commands you can use:\nNorth, South, East, West, Up, Down\nEat\nSearch\nInventory\nExit");
		case "SEARCH":
			searchRoom();
			break;
		default:
			System.out.println("Sorry, I don't recognize this command");
			break;
		}
		return true;
	}

	void displayInv(){
		for(Item i: invList) {
			System.out.println("\n"+i.toString());
		}
	}
	
	void moveToRoom(char c) {
		String nextRoom;
		nextRoom = currentRoom.getExit(c);
		
		if(nextRoom.equals("")) {
			System.out.println("You can't go there");
			return;			
		}
		
		currentRoom = roomList.get(nextRoom);
		if (currentRoom.getIsLocked() && !searchInv("Keycard")) {
			System.out.println(currentRoom.getTitle() + "\n" + Room.getLockedMsg());
		}
		
		if (currentRoom.getIsDark() && !searchInv("Torch")) {			
			System.out.println(currentRoom.getTitle() + "\n" + Room.getDarkMsg());
			//Dark room puzzle -- can't see true descp if inv does not contain torch
			
		} else {
		//Standard room message			
			System.out.println(currentRoom.toString());
		}
		
	}
	
	boolean searchInv( String s) {
		for (Item item : invList) {
			if (item.itemName.equals(s))  return true;			
		}
		return false;
	}
	
	//Adds items to inventory list
	void searchRoom() {
		for(Item i: items) {
			if (i.location.equals(currentRoom.getTitle())){
				i.location = "inventory";
				System.out.println("you found "+i);
				invList.add(i);
			}
		}
	}
	
	void eatItem() {
	}
}

