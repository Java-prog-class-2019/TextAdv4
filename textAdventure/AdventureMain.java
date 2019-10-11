package textAdventure;

import java.util.ArrayList;

/* TO DO LIST
 * Create object constructor for rooms (array list or hashmap?)
 * Create object constructor for items 
 * create static values for character (exmp: health, inventory size,)
 * Figure out how to track player progression and movement
 * figure out how to make a window with the Console
 * create setup method and main game loop
 */

	public class AdventureMain {
	
	public static void main(String[]args) {
		Gamemain();
	}	

	
	//Put global variables here^^^ 
	
	//main game Method
	static void Gamemain() {
		Setup();
		
		ArrayList<Item> items = new ArrayList<Item>();
		
		items.add(Item.makeItem("Hammer", "Boards", "A small, worn hammer that looks like it could fall apart at any moment", true, false));
		items.add(Item.makeItem("ScrewDriver", "Screws", "A regular flathead screwdriver, it looks pretty worn", false, false));
		items.add(Item.makeItem("Old Bread","Yourself","A stale piece of bread that as far as you can tell isn't mouldy, yet", true, true));
		for(Item i: items) {
			System.out.println(i.toString());
			System.out.print("\n");
			
		}
		while(true) {
			
		}
	}
	
	static void Setup() {
		
	}

}
	
	
