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
	boolean bookshelf = true; // Secret room puzzle
	boolean playing = true;
	boolean dead = false;
	boolean won = false;
	//Put global variables here^^^

	AdventureMain() {
		setUp();
		gamemain();
	}

	//main game Method
	void gamemain() {
		////Instance variables
		
		String command = ""; 

		/***** MAIN GAME LOOP *****/
		while (playing) { 
			command = getCommand(); 
			playing = parseCommand(command);
			if(player.health<=0) {
				dead = true;
				playing = false;
				break;
			}
			if(won) playing = false;
		}
		
		if(dead) {
			System.out.println("You have done the ded, nerd");
		} else {
			System.out.println("You have successfuly escaped the facility!");
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
		
		String [] command = text.split(" ");
		ArrayList<String> wordlist = new ArrayList<String>(Arrays.asList(text));		//array list of words
		for(int i=0; i< wordlist.size(); i++) {
			if (wordlist.get(i).equals("the")) wordlist.remove(i--);
			if (wordlist.get(i).equals("to")) wordlist.remove(i--);
		}

		switch(command[0]) {		
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
			eatItem(command[1]);
			break;
		case "MOVE":
			if(command[1].equals("BOOKSHELF")) {
				bookshelf = false;
				System.out.println("You have moved the bookshelf. Where it used to be, a door is now visible.");
			} else {
				System.out.println("You can't move that");
			}

			break;
		case "HELP":
			System.out.println("Here is a list of commands you can use:\nNorth, South, East, West, Up, Down\nEat\nSearch\nInventory\nExit\nEat <ItemName>\nMove<ObjectName>");
			break;
		case "SEARCH":
			searchRoom();
			break;
		case "LEAVE":
			System.out.println("Using your dormant power of planar manipulation you successfully travel behind the universal curtain.\n"
					+ "you end up on a sunny beach somewhere in what looks like Hawaii. GG I guess?");
			won = true;
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

		//Airlock puzzle -- can't access airlock while you don't have keycard
		if (currentRoom.getIsLocked() && !searchInv("keycard")) {
			System.out.println(currentRoom.getTitle() + "\n" + Room.getLockedMsg());
			currentRoom = roomList.get("Hall2");
		}

		//Dark room puzzle -- can't see true description of the room while you don't have torch
		if (currentRoom.getIsDark() && !searchInv("torch")) {			
			System.out.println(currentRoom.getTitle() + "\n" + Room.getDarkMsg());
		}
		//Secret room puzzle -- can't go to secret room if bookshelf blocking it hasn't been moved
		else if (currentRoom.equals(roomList.get("Shrine")) && bookshelf){
			System.out.println("You can't go there");
			currentRoom = roomList.get("Lab2");
			//Standard room message	
		} 
		else {
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

	void eatItem(String food) {

		for(Item item: invList) {
			if(item.itemName.equals(food.toLowerCase())){
				if(item.edible) {
					if(player.health > (100 - item.foodPoints)) {
						System.out.println("You're too full to eat");
					} else {
						player.health += item.foodPoints;
						item.location = "";
						System.out.println("you have eaten " + item.itemName + " and regained " + item.foodPoints + " health");
						invList.remove(item);
					}
				}
			}else {
				System.out.println("You can't eat that");
			}
		}
	}
}
