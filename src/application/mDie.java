package application;

import java.io.File;
import java.util.TreeMap;

public class mDie {
	private int id, value;
	private boolean lock;
	private String facePicture, facePictureLock; //Sollte zum Board Model ausgelagert werden, da die Variable refreshed werden muss.
	private TreeMap<Integer, String> facePictures = new TreeMap<Integer, String>();
	private TreeMap<Integer, String> facePicturesLock = new TreeMap<Integer, String>();
	
	public mDie(int id){
		initializeFacePictures();
		initializeFacePicturesLock();
		this.id = id;
		this.value = this.roll();
		this.facePicture = this.setFacePicture();
		this.facePictureLock = this.setFacePictureLock();
		this.lock = false;
	}
	
	//Der W�rfel wird neu gew�rfelt und das zugeh�rige Bild wird zugewiesen.
	
	public int roll(){
		this.value = (int) Math.floor(Math.random() * 5) + 1;
		this.setFacePicture();
		this.setFacePictureLock();
		return value;
	}
	
	//Stringausgabe des W�rfels
	
	public String toString(){
		return "ID: " + this.id + ", value: " + this.value + " Anzeige: " + this.facePicture;
	}
	
	//Initialisierung der W�rfelbilder
	
	public TreeMap<Integer, String> initializeFacePictures(){
		facePictures.put(1, "application"+File.separator+"Dice"+File.separator+"dice_one.png");
		facePictures.put(2, "application"+File.separator+"Dice"+File.separator+"dice_two.png");
		facePictures.put(3, "application"+File.separator+"Dice"+File.separator+"dice_three.png");
		facePictures.put(4, "application"+File.separator+"Dice"+File.separator+"dice_attack.png");
		facePictures.put(5, "application"+File.separator+"Dice"+File.separator+"dice_heart.png");
		return facePictures;
	}
	
	public TreeMap<Integer, String> initializeFacePicturesLock(){
		facePicturesLock.put(1, "application"+File.separator+"Dice"+File.separator+"dice_one_ok.png");
		facePicturesLock.put(2, "application"+File.separator+"Dice"+File.separator+"dice_two_ok.png");
		facePicturesLock.put(3, "application"+File.separator+"Dice"+File.separator+"dice_three_ok.png");
		facePicturesLock.put(4, "application"+File.separator+"Dice"+File.separator+"dice_attack_ok.png");
		facePicturesLock.put(5, "application"+File.separator+"Dice"+File.separator+"dice_heart_ok.png");
		return facePicturesLock;
	}
	
	// Lock getter/setter
	
	public boolean getLock(){
		return this.lock;
	}
	
	public void setLock(){
		this.lock = true;
	}
	
	//Schl�sselfunktion f�r die Lock Variable
	
	public void removeLock(){
		this.lock = false;
	}
	
	//Value getter
	
	public int getValue(){
		return this.value;
	}
	
	//ID getter
	
	public int getID(){
		return this.id;
	}
	
	//FacePicture getter/setter
	
	public String getFacePicture(){
		return this.facePicture;
	}
	
	public String setFacePicture(){
		return this.facePicture = facePictures.get(this.value);
	}
	
	//FacePictureLock getter/setter
	
	public String getFacePictureLock(){
		return this.facePictureLock;
	}
	
	public String setFacePictureLock(){
		return this.facePictureLock = facePicturesLock.get(this.value);
	}
	
/*	public static void main(String[] args) {

		mDie one = new mDie(1);
		mDie two = new mDie(2);
		mDie three = new mDie(3);
		mDie four = new mDie(4);
		
		System.out.println(one.toString()+ "\n" + two.toString() + "\n" + three.toString() + "\n" + four.toString());
		
		one.roll();
		two.roll();
		three.roll();
		four.roll();
		
		System.out.println(one.toString()+ "\n" + two.toString() + "\n" + three.toString() + "\n" + four.toString());
	}*/

}
