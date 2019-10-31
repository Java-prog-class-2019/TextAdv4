package textAdventure;

import java.util.ArrayList;

/**
 * create static values for character (exmp: health, inventory size,) Figure out
 * how to track player progression and movement
 */
class Player {
	//IDEAS, can be changed
	
	int health = 100, invSize /*temperature? */; // Health, inventory size
	
	 //Can move to main advMain in the parse command loop to count how many commands player has inputted.
	int cmdCount; 
	
	//Boolean flags can keep track of if they have visited specific rooms
	boolean hall1Fg = false, secretRoom = false /*etc */;
	
	//Arraylist for inventory? Can add or remove the items if we did arraylist
	//Can also do booleans to keep track of if player has object. E.g. if they 
	//picked up hammer, hammerFg = true, and while(hammer is true) {they can do specific cmds}
	//and when they put hammer down, hammer = false
	//ArrayList<Item> invList = new ArrayList<Item>(/*max?*/);
	//Should there be a max inv size?
	
	
}
