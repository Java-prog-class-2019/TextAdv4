package textAdventure;

import java.util.ArrayList;

class Item{

	int foodPoints;
	boolean consumable, edible;
	String usedOn, desc;
	String itemName;
	String location;
	
	//constructor. It is private so items can only be made from this class. eg. makeItem().
	private Item(String itemName, String usedOn, String desc, String location, boolean edible, int foodPoints) {	
		this.itemName = itemName;
		this.usedOn = usedOn;
		this.desc = desc;
		this.location = location;
		this.edible = edible;
		this.foodPoints = foodPoints;
	}

	String getUses(String u) {
		return u;
	}
	String getDesc(String d) {
		return d;
	}

	static String getName(String n) {
		return n;
	}

	boolean getConsumable(boolean c) {
		return c;
	}

	boolean getEdible(boolean e) {
		return e;
	}
	int getFoodPoints(int f) {
		return f;
	}

	//make all items
	static void makeItem(ArrayList<Item> items) {
		items.add(new Item("hammer", "Boards", "A small, worn hammer that looks like it could fall apart at any moment","Maintenance Area" , true, -1000));
		items.add(new Item("ScrewDriver", "Screws", "A regular flathead screwdriver, it looks pretty worn", "Maintenance Area", false,0));
		items.add(new Item("torch", "","TEST","Lab 1" , false, 0));
		items.add(new Item("bread","Yourself","A stale piece of bread that as far as you can tell isn't mouldy, yet", "Mess Hall", true,10));
		items.add(new Item("Keycard", "", "A small plastic card used to open certain doors around the facility", "Armoury", false, 0));
		
		
	

		/*//DEBUG
		for(Item i: items) {
			System.out.println(i.toString());
			System.out.print("\n");			
		}*/
	}
	
	@Override
	public String toString() {
		String s = String.format("Item Name: %s\nItem Description: %s",itemName, desc);
		return s;
	}
}
