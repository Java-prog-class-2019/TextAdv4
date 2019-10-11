package textAdventure;

class Item{
	
	boolean consumable, edible;
	String usedOn, desc, itemName;
	
	
	private Item(String itemName, String usedOn, String desc, boolean consumable, boolean edible) {	
		this.itemName = itemName;
		this.usedOn = usedOn;
		this.desc = desc;
		this.consumable = consumable;
		this.edible = edible;
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
	
	static Item makeItem(String n, String u, String d, boolean c, boolean e) {
		return new Item(n, u, d, c, e);
		
	}
	
	@Override
	public String toString() {
		String s = String.format("Item Name: %s\nItem Description: %s",itemName, desc);
		return s;
	}
}
