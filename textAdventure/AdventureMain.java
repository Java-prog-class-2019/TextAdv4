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

	Scanner sc = new Scanner(System.in);
	HashMap<String,Room> roomList = new HashMap<String, Room>();
	Room currentRoom; 
	ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<Item> invList = new ArrayList<Item>();
	Player player;
	String word1, word2, word3;
	boolean safe;
	boolean bookshelf = true; // Secret room puzzle
	boolean frozenPipes = true,artifactInPlace = false; // Helicopter puzzle
	boolean won = false, dead = false; // win/lose conditions
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
			if (player.health <= 0) {
				dead = true;
				System.out.println("You have died");
				playing = false;
			}
			if (won) {
				System.out.println("You have successfully escaped the facility");
				playing = false;
			}
		}
		sc.close();
		System.out.println("Thank you for playing");
	}

	

	void setUp() {
		Item.makeItem(items); //this will make all items and add them to the items arraylist
		Room.setupRooms(roomList);
		currentRoom = roomList.get("Lab1");
		System.out.println("You just woke up from a deep sleep. "
				+ "\nyou have no idea where you are but you know you need to escape\n"
				+ "you can use help to get started\n");
		System.out.println(currentRoom.toString());
		player = new Player();
	}

	String getCommand() { //gets user input
		Scanner sc = new Scanner(System.in);
		System.out.println("\nPlease enter a command:");
		String text = sc.nextLine();
		text  = text.trim();
		return text;
	}

	boolean parseCommand(String text) {
		text = text.toLowerCase().trim();	
		text = text.toUpperCase();
		String words[] = text.split(" ");
		ArrayList<String> wordlist = new ArrayList<String>(Arrays.asList(words));		//array list of words
		for(int i=0; i< wordlist.size(); i++) {
			if (wordlist.get(i).equals("THE")) wordlist.remove(i--);
			if (wordlist.get(i).equals("TO")) wordlist.remove(i--);	
		}
		word1 = wordlist.get(0);
		if(wordlist.size() >= 2) {
			word2 = wordlist.get(1);
		}

		switch(word1) {		
		case "N": case "S": case "W": case "E": case "U": case "D": 
		case "NORTH": case "SOUTH": case "WEST": case "EAST": case "UP": case "DOWN":
			moveToRoom(text.charAt(0));
			break;
		case "I": case "INVENTORY": case "INV":
			displayInv();
			break;
		case "EXIT":
			System.out.print("Do you really want to quit the game? ");
			String ans = getCommand().toUpperCase();
			if (ans.equals("YES") || ans.equals("Y")) {
				System.out.print("Bye. ");
				return false;
			}
		case "EAT":
			eatItem(word2);
			break;
		case "READ":
			readItem(word2);
			break;
		case "DROP":
			dropItem(word2);
			break;
		case "MOVE":
			if(word2.equals("BOOKSHELF")) {
				bookshelf = false;
				System.out.println("You have moved the bookshelf. From where it used to be, a door is now visible.");
			}

			break;
		case "HELP":
			System.out.println("Here is a list of commands you can use:\nNorth, South, East, West, Up, Down\nEat\nSearch\nInventory\nExit");
			break;
		case "SEARCH":
			searchRoom();
			break;
		case "HELICOPTER":
			if (currentRoom.equals(roomList.get("Helipad"))) heliPuzzle();
			else System.out.println("You're not in the right area");
			break;
		case "USE":
			if(currentRoom.equals(roomList.get("Helipad"))) {
				if(word2.equals("FLAMETHROWER")) {
					if (searchInv("flamethrower")){
						frozenPipes = false;
						System.out.println("You have melted the frozen pipes");
					}
				}
				

				if(word2.equals("ARTIFACT")) {
					if (searchInv("artifact")){
						artifactInPlace = true;
						System.out.println("The artifact fit perfectly inside the gap of the instrument panel");	
					}
				}

			}
			break;
		case "LEAVE":
			System.out.println("You awaken your dormant powers of temporal manipulation to travel behind the universal curtain to escape.\n"
					+ "you end up on some beach in what looks like Hawaii.\n"
					+ "GG I guess?\n");
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
		//cold puzzle -- take damage if room is cold and player doesn't have jacket
		if (currentRoom.getIsCold() && !safe) {
			System.out.println(Room.getcoldMsg());
			player.health -= 50;
		}
		else {
			System.out.println(currentRoom.toString());
		}
	}

	boolean searchInv( String s) {
		System.out.println("Your Inventory");
		for (Item item : invList) {
			if (item.itemName.equals("worn jacket")) {
				safe = true;
			}
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
					if(food.equals("hammer")) {
						System.out.println("you have eaten a hammer and died, what did you expect");
					} else {
						if(player.health > (100 - item.foodPoints)) {
							System.out.println("You're too full to eat");
						} else {
							player.health += item.foodPoints;
							item.location = "";
							System.out.println("you have eaten " + item.itemName + " and regained " + item.foodPoints + " health");
						}
					}
				}else {
					System.out.println("You can't eat that");
				}
			} else {
				System.out.println("you don't have that");
			}
		}
	}

	void readItem(String text) {
		for(Item item: invList) {
			if(item.itemName.equals(text.toLowerCase())){
				if(item.canRead) {
					if(text.equals("researchpaper")) {
						System.out.println("research paper text");
					} 
					else if (text.equals("newspaperclipping")) {
						System.out.println("newspaper clipping text");
					}
					else if (text.equals("artifact")) {
						System.out.println("Your head hurts simply just trying to understand the mysterious language.");
					}
				}else {
					System.out.println("You can't read that");
				}
			} else {
				System.out.println("you don't have that");
			}
		}
	}

	void dropItem(String object) {
		for (Item item: invList) {
			if(item.itemName.equals(object.toLowerCase())) {
				item.location = currentRoom.getTitle();
				System.out.println("you dropped " + item.itemName);
			}
		}
	}

	void heliPuzzle() {
		if (frozenPipes) System.out.println("The helicopter's pipes are frozen. Perhaps you could melt it");
		if (!searchInv("kerosene")) System.out.println("The engine needs fuel");
		if (!artifactInPlace) System.out.println("There is a gap inside the instrument panel. The missing piece seems instrumental to the functioning of the helicopter");
		if (searchInv("kerosene") && !frozenPipes && artifactInPlace) System.out.println("Everything seems to be in order, perhaps you could try operating the helicopter");
	}

	void leave() {
		if (searchInv("kerosene") && !frozenPipes && artifactInPlace) {
			System.out.println("Using your dormant power of planar manipulation, you successfully travel behind the universal curtain.\n"
					+"You successfully end up on a sunny beach somewhere in what looks like Hawaii");
			won = true;
		}
		else System.out.println("You tried to start the helicopter, but it does not function. Perhaps you're still missing a few pieces");
	}
}
