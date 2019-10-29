package textAdventure;

import java.util.ArrayList;

class Item{

	boolean consumable, edible, canRead;
	String usedOn, desc;
	String itemName;
	int foodPoints;
	String location;
	
	
	//constructor. It is private so items can only be made from this class. eg. makeItem().
	private Item(String itemName, String usedOn, String desc, String location, boolean consumable, boolean edible, int foodPoints, boolean canRead) {	
		this.itemName = itemName;
		this.usedOn = usedOn;
		this.desc = desc;
		//Location will be room name, not key in roomList
		this.location = location;
		this.edible = edible;
		this.foodPoints = foodPoints;
		this.canRead = canRead;
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
	boolean getReadable(boolean r) {
		return r;
	}

	//make all items
	static void makeItem(ArrayList<Item> items) {
		items.add(new Item("hammer", "Boards", "A small, worn hammer that looks like it could fall apart at any moment","Maintenance Area" , true, true,-10000,false));
		items.add(new Item("screwDriver", "Screws", "A regular flathead screwdriver, it looks pretty worn", "Maintenance Area", false, false,0,false));
		items.add(new Item("torch", "","TEST","Lab 1" , false, false, 0,false));
		items.add(new Item("bread","Yourself","A stale piece of bread that as far as you can tell isn't mouldy, yet", "Mess Hall", true, true,0,false));
		items.add(new Item("keycard", "", "A small plastic card used to open certain doors around the facility", "Armoury", false, false, 0,false));
		
	

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
