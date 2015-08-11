package np2015;

import java.util.ArrayList;
import java.util.List;

// verwaltet alle Knoten einer Spalte
public class Spalten extends Thread{
	
	private int pos; 
	private int iterationen;
	private int spalte = 0;
	
	private ArrayList<Knoten> knoten = new ArrayList<Knoten>();
	// für parallele lösung
	private ArrayList<Double> akku_R = new ArrayList<Double>();
	private ArrayList<Double> akku_L = new ArrayList<Double>();
	//TODO: brauchen wir eine vierte arraylist? wenn ja warum ? kann man das nicht in der hauptstuktur verwalten  
	
	// TODO: wo muss ich diese allNOdes liste speichern?
	//TODO: vielleicht besseren konstruktor ?? 
	

	
	//Konstruktor
	public Spalten(int spalte, int iter){
		this.spalte = spalte;
		this.iterationen = iter;
	}
	
	// TODO: rausfinden ob set funktioniert auch wenn die liste leer ist 
	
	
	public void set_knoten(Knoten k){
		knoten.add(k);	
	}
	
	// gibt knoten an der position i in der arraylist knoten zurück
	public Knoten get_knoten(int pos){
			// TODO: nachsehen wie das ist, wenn die klasse knoten implementiert ist, ist nicht korrekt sortiert  
			return knoten.get(pos);
	}
	
	public void set_akku_R(double cap){
			akku_R.add(cap);
	}
	public double get_akku_R(){	
		return akku_R.get(pos);
	}
	public int get(){
		return pos; 
	}
	
	public void set(int i ){
		pos = i;
	}
	
	public void set_akku_L(double cap){
		akku_L.add(cap);
	}
	
	public double get_akku_L(){
		
		return akku_L.get(pos);
		
	}
	
	public int getIterationen() {
		return iterationen;
	}

	public void setIterationen(int iterationen) {
		this.iterationen = iterationen;
	}
	
	public String toString(){
		return ("( number: "+ spalte + " knoten: " + knoten +" akku_R: "+ akku_R + " akku_L: " + akku_L + ") \n");
	}

}
