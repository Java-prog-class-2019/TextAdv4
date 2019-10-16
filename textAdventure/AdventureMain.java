package textAdventure;

import java.util.ArrayList;

/* TO DO LIST --> make into ISSUES on GitHub
 * Create object constructor for rooms (array list or hashmap?)
 * Create object constructor for items 
 * create static values for character (exmp: health, inventory size,)
 * Figure out how to track player progression and movement
 * figure out how to make a window with the Console
 * create setup method and main game loop
 */

	public class AdventureMain {
	
	public static void main(String[]args) {
		new AdventureMain().gamemain(); //this means that we don't need to make everything static
	}	

	ArrayList<Item> items = new ArrayList<Item>();
	//Put global variables here^^^ 
	
	//main game Method
	void gamemain() {
		Setup();
		
		
		
		
		//while(true) {
			
		//}
	}
	
	void Setup() {
		Item.makeItem(items); //this will make all items and add them to the items arraylist
	}
}
	
	
