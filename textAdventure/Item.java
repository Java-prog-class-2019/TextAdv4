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
		items.add(new Item("torch", "","TEST","Lab 1" , false, false, 0, false));
		items.add(new Item("hammer", "Boards", "A small, worn hammer that looks like it could fall apart at any moment","Maintenance Area" , true, false,0,false));
		items.add(new Item("screwdriver", "Screws", "A regular flathead screwdriver, it looks pretty worn", "Maintenance Area", false, false,0,false));
		items.add(new Item("bread","Yourself","A stale piece of bread that as far as you can tell isn't mouldy, yet", "Mess Hall", true, true,0,false));
		items.add(new Item("keycard","","A small plastic card that can be used to open certain doors in the facility", "Armoury", false,false, 0, false));
		items.add(new Item("kerosene","Helicopter","Using the research facility's equipment, you collected crude oil and distilled it into kerosene", "Dig Site", false, false, 0, false));
		items.add(new Item("flamethrower","Helicopter","A worn, but still functioning flamethrower. It seems that it can add heat to melt something", "Armoury", false,false, 0, false));
		items.add(new Item("artifact","Helicopter","An mysterious looking artifact. While it is in your possession, you feel yourself getting weaker", "Secret Room", false,false, 0, false));
		items.add(new Item("researchpaper","","You found a research paper. It seems as if the scientists were looking to extract oil from the dig site. Perhaps you could read it", "Lab 1", false,false, 0, true));
		items.add(new Item ("coat","","A coat", "Living Quarters",false,false,0,false));
		
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
