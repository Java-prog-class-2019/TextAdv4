package textAdventure;

import java.util.ArrayList;

class Item{

	boolean consumable, edible;
	String usedOn, desc, itemName;
	private int foodPoints;
	private String location;

	//constructor. It is private so items can only be made from this class. eg. makeItem().
	private Item(String itemName, String usedOn, String desc, String location, boolean consumable, boolean edible, int foodPoints) {	
		this.itemName = itemName;
		this.usedOn = usedOn;
		this.desc = desc;
		this.location = location;
		this.consumable = consumable;
		this.edible = edible;
		this.foodPoints = foodPoints;
	}


	String getUses(String u) {
		return u;
	}
	String getDesc(String d) {
		return d;
	}

	String getName(String n) {
		return n;
	}

	boolean getConsumable(boolean c) {
		return c;
	}

	boolean getEdible(boolean e) {
		return e;
	}

	//make all items
	static void makeItem(ArrayList<Item> items) {
		items.add(new Item("Hammer", "Boards", "A small, worn hammer that looks like it could fall apart at any moment","MaintArea" , true, false,0));
		items.add(new Item("ScrewDriver", "Screws", "A regular flathead screwdriver, it looks pretty worn", "MaintArea", false, false,0));
		items.add(new Item("Old Bread","Yourself","A stale piece of bread that as far as you can tell isn't mouldy, yet", "MessHall", true, true,0));
		items.add(new Item("Torch", "???","Torch Descp",/*placeholder room */ "lab2", false,false,0)); //3

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
