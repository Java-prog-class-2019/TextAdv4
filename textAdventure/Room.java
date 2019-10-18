package textAdventure;

import java.util.*;

class Room{

	/***********Constructor**************/
	Room(String title, String description){
		this.title = title;
		this.description = description;  
	}

	/***** Instance variables (no static variable needed) *****/
	private String title;   //the short display name of the room
	private String description;  //the description that is displayed when you first enter the room or type "look"
	private String N,S,W,E,U,D;  //these are exits that point to the HashMap name of other rooms.
	private boolean dark=false; //is the room dark (so you need a flashlight or else yoou die if you stay here)
	private boolean visited=false; //has the user visited this room already?
	ArrayList<String> items = new ArrayList<String>(); //items in this room

	//Maintenance Dark Message
	static String darkMsg = "It's pitch black. You can’t see anything. It’s oddly warm down here.";

	/******getters and setters *****/
	String getTitle()  { return title; }
	String getDesc()  { return description; }
	boolean hasVisited() { return visited; }
	void visit()   { visited = true; }
	boolean getIsDark()  { return this.dark; }

	public void setExits(String N, String S, String W, String E, String U, String D) {
		this.N = N;
		this.E = E;
		this.S = S;
		this.W = W;  
		this.U = U;  
		this.D = D;  
	}

	public String getExit(Character c) {
		switch (c) {
		case 'N': return  this.N;
		case 'E': return  this.E;
		case 'S': return this.S;
		case 'W': return this.W;
		case 'U': return this.U;
		case 'D': return this.D;
		default: return ""; //Return some other value to indicate a non-existent direction?
		}
	}

	@Override
	public String toString(){
		//String s = String.format("Title=%-25s\t%nDescription=%s",title,description);  
		String s = title + "\n"+ description; 
		return s;
	}

	static void setupRooms(HashMap<String,Room> roomList) {

		Room r = new Room("Airlock","Airlock Descp");
		r.setExits("","","Hall2","Outside","","");       
		roomList.put("Airlock", r);

		r = new Room("Armoury","The walls of the room are covered in shelves and cabinets with various armaments stored within them.\n"
				+ "It seems that whoever was here had left in a hurry as rifles, side arms and ammunition are scattered all over the floor.");
		r.setExits("","Hall1","","","","");
		roomList.put("Armoury",r);

		r = new Room("Dig Site","Digsite descp");
		r.setExits("Outside","","","","","");
		roomList.put("DigSite", r);

		r = new Room("Hall 1","Hall1 descp");
		r.setExits("Armoury","Hall2","Lab1","MedBay","","");
		roomList.put("Hall1",r);

		r = new Room("Hall 2","A small corridor with three doors leading west, south and east.\n"
				+ "There are signs above the doors that read “Mess Hall”, “ Living Quarters”, and “Airlock” respectively.\n"
				+"There’s a panel in the floor with a missing bolt. Perhaps it leads somewhere.");
		r.setExits("Hall1","LivQuar","MessHall","Airlock","","MaintArea");    
		roomList.put("Hall2",r);

		r = new Room("Helicopter Pad","A large concrete circle rests in the snow with a helicopter sitting on top.\n"
				+ "The helicopter seems to be functional but you try to turn it on and it doesn’t start up.\n"
				+"It looks like its out of fuel. Maybe you could escape if you found some.");
		r.setExits("","Outside","","","","");  
		roomList.put("Helipad",r);

		r = new Room("Kitchen","A cluttered kitchen area that looks absolutely destroyed.\n"
				+"Scorch marks and soot cover the walls as if something had caught fire.\n"
				+ "Pots, pans, and cutlery lay strewn about the counters and floor.\n"
				+"A single knife is imbedded halfway into the wall and a strang black liquid seems to be seeping out around");
		r.setExits("","","","MessHall","","");   
		roomList.put("Kitchen", r);
		// N S W E U D << exit sequence
		r = new Room("Lab 1","A dimly lit room, it feels cold and damp almost like somethings breathing down the back of your neck.\n"
				+ "Large scratches are carved into the metal walls near the door.\n"
				+ "Lab equipment lays scattered around the floor and a bookshelf rests awkwardly against a wall");
		r.setExits("Lab2","","","Hall1","","");  
		roomList.put("Lab1",r); 

		r = new Room("Lab 2","This laboratory seems to be in pristine condition. The counter tops are clean and neat.\n"
				+ "However, the operation table in the middle of the room seems to be missing.\n"
				+ "An outline of dust covers the floor where it use to be. Four bolt holes lie in the corners of the dusty outline.");
		r.setExits("","Lab1","Shrine","","","");  
		roomList.put("Lab2",r); 

		r = new Room("Living Quarters","Living quarters descp");
		r.setExits("Hall2", "","", "", "","");       
		roomList.put("LivQuar", r);

		r = new Room("Maintenance Area", "The room is dimly with the only source of light being the torch you found.\n"
				+"A generator is built into the farside of the room, It seems to be turned off.\n"
				+"You discover that the source of the heat is a large boiler on the west wall, it seems to be operational.\n"
				+"A small storage shelf lies on the east wall. There is a small hammer laying on top of it.");
		r.setExits("","","","","Hall2","");
		r.getIsDark();       
		roomList.put("MaintArea", r);

		r = new Room("Med Bay", "A med bay with cabinets stocked full of different medications.\n"
				+ "A desk sits in the corner with blank requisition forms sitting atop it,\n"
				+"a row of beds lays lined up along another wall separated by curtains,\n"
				+ "each one is made up nice and clean except for one that’s missing a blanket\n"
				+ "and has a blood stain on the pillow at the head of the bed.");
		r.setExits("","","Hall1","","","");
		roomList.put("MedBay",r);

		r = new Room("Mess Hall","A large room with three long tables lined up in rows. \n"
				+"It seems like there was a rushed exit as the tables and chairs are in disarray.\n"
				+"Food has been left on the table but most of it seems in edible at this point. \n"
				+"No use in picking it up.");
		r.setExits("","","Kitchen","Hall2","","");   
		roomList.put("MessHall", r);

		r = new Room("Outside","Outside descp");
		r.setExits("Helipad","DigSite","Airlock","","","");  
		roomList.put("Outside",r);

		r = new Room("Secret Room", "This room was hidden behind a bookshelf. It seems to be some\n"
				+"sort of altar room lit only by candles. Symbols made from a dark liquid cover the floor.\n"
				+"The room is too dark to see what the liquid is.");
		r.setExits("","","","Lab2","","");
		roomList.put("Shrine", r);

		/*//Prints all rooms 
		for (Room m : roomList.values()){
			System.out.println(m.toString());
			System.out.println();
		}*/
	}
}
