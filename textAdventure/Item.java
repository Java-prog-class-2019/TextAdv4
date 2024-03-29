package textAdventure;

import java.util.ArrayList;

class Item{

	boolean edible, canRead;
	String desc;
	String itemName;
	int foodPoints;
	String location;

	//constructor. It is private so items can only be made from this class. eg. makeItem().
	private Item(String itemName, String desc, String location, boolean edible, int foodPoints, boolean canRead) {	
		this.itemName = itemName;

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
		items.add(new Item("torch","A small plastic flashlight that looks like it could die any moment","Lab 1" , false, 0, false));
		items.add(new Item("hammer", "A small, worn hammer that looks like it could fall apart at any moment","Maintenance Area" , true,0,false));
		items.add(new Item("screwdriver", "A regular flathead screwdriver, it looks pretty worn", "Maintenance Area", false,0,false));
		items.add(new Item("bread","A stale piece of bread that as far as you can tell isn't mouldy, yet", "Mess Hall", true,0,false));
		items.add(new Item("keycard","A small plastic card that can be used to open certain doors in the facility", "Armoury",false, 0, false));
		items.add(new Item("kerosene","Using the research facility's equipment, you collected crude oil and distilled it into kerosene", "Dig Site", false, 0, false));
		items.add(new Item("flamethrower","A worn, but still functioning flamethrower. It seems that it can add heat to melt something", "Armoury",false, 0, false));
		items.add(new Item("artifact","An mysterious looking artifact. While it is in your possession, you feel yourself getting weaker", "Secret Room",false, 0, true));
		items.add(new Item("researchpaper","You found a research paper. It seems as if the scientists were looking to extract oil from the dig site. Perhaps you could read it", "Lab 1",false, 0, true));
		items.add(new Item("jacket","An old wethered jacket that seems a little worse for ware but keeps you warm when you wear it","Living Quarters", false,0, false));
		items.add(new Item("note","There seems to be some writing on it","Dig Site",false,0,true));
		
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
