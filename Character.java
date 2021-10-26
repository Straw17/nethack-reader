public class Character implements Comparable<Character> {
	String rawInfo;
	String[] splitInfo;
	
	int score, dungeonNum, dungeonLvl, maxLvl, HP, maxHP;
	String role, race, gender, alignment, name, deathCause;
	
	public Character(String rawInfo) {
		this.rawInfo = rawInfo;
		splitInfo = rawInfo.split(" ", 16);
		
		score = Integer.parseInt(splitInfo[1]);
		dungeonNum = Integer.parseInt(splitInfo[2]);
		dungeonLvl = Integer.parseInt(splitInfo[3]);
		maxLvl = Integer.parseInt(splitInfo[4]);
		HP = Integer.parseInt(splitInfo[5]);
		maxHP = Integer.parseInt(splitInfo[6]);
		
		role = convertRole(splitInfo[11]);
		race = convertRace(splitInfo[12]);
		gender = splitInfo[13] == "Mal" ? "Male" : "Female";
		alignment = convertAlignment(splitInfo[14]);
		name = splitInfo[15].split(",")[0];
		deathCause = splitInfo[15].split(",")[1];
		
		deathCause = deathCause.substring(0, 1).toUpperCase() + deathCause.substring(1);
	}
	
	@Override
	public int compareTo(Character c) {
		return (c.score - this.score);
	}
	
	public void printInfo() {
		System.out.print(alignment + " ");
		System.out.print(race + " ");
		System.out.print(role);
	}
	
	public String returnInfo() {
		return (alignment + " " + race + " " + role);
	}
	
	public int getInfoLen() {
		return (alignment.length() + race.length() + role.length() + 2);
	}
	
	private String convertRole(String rawRole) {
		switch(rawRole) {
		case "Arc":
			return "Archeologist";
		case "Bar":
			return "Barbarian";
		case "Cav":
			return "Caveman";
		case "Hea":
			return "Healer";
		case "Kni":
			return "Knight";
		case "Mon":
			return "Monk";
		case "Pri":
			return "Priest";
		case "Ran":
			return "Ranger";
		case "Rog":
			return "Rogue";
		case "Sam":
			return "Samurai";
		case "Tou":
			return "Tourist";
		case "Val":
			return "Valkyrie";
		case "Wiz":
			return "Wizard";
		default:
			return "Error!";
		}
	}
	
	private String convertRace(String rawRace) {
		switch(rawRace) {
		case "Dwa":
			return "Dwarvish";
		case "Elf":
			return "Elvish";
		case "Gno":
			return "Gnome";
		case "Hum":
			return "Human";
		case "Orc":
			return "Orcish";
		default:
			return "Error!";
		}
	}
	
	private String convertAlignment(String rawAlignment) {
		switch(rawAlignment) {
		case "Cha":
			return "Chaotic";
		case "Law":
			return "Lawful";
		case "Neu":
			return "Neutral";
		default:
			return "Error!";
		}
	}
}