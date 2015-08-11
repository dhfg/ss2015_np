package np2015;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// verwaltet alle Knoten einer Spalte
public class Spalten extends Thread {

	
	private int pos; 
	private int iterationen;
	private int spalte = 0;
	private GraphInfo ginfo = new GraphInfo(0, 0);
	private ArrayList<Knoten> knoten = new ArrayList<Knoten>();
	// für parallele lösung
	private ArrayList<Double> akku_R = new ArrayList<Double>();
	private ArrayList<Double> akku_L = new ArrayList<Double>();
	//TODO: brauchen wir eine vierte arraylist? wenn ja warum ? kann man das nicht in der hauptstuktur verwalten  
	
	// TODO: wo muss ich diese allNOdes liste speichern?
	//TODO: vielleicht besseren konstruktor ?? 

	//Konstruktor
	public Spalten(int spalte, int iter, GraphInfo ginfo){
		this.spalte = spalte;
		this.iterationen = iter;
		this.ginfo = ginfo;
	}
	
	// startet Berechnungen
	public void run(){
		//while(true) {
		pBerechnung();
		//}
		
	}

	public synchronized void pBerechnung(){
		//helpListe zum Zwischenspeichern der Nachbar Knoten
		ArrayList<Knoten> helpListe = new ArrayList<Knoten>();
		//iteriere über die Knoten in der Knotenliste und berechne die Rate zu NachbarKnotenOben und NachbarKnotenUnten
		Iterator<Knoten> nodes = knoten.iterator();
		while (nodes.hasNext()) {
			Knoten actual = nodes.next();
			Knoten neighbor_oben = actual.get_neighbor_oben(knoten);
			Knoten neighbor_unten = actual.get_neighbor_unten(knoten);
			//neighbor Oben
			calculateNeighborTopBottom(actual, neighbor_oben, helpListe, true);
			//neighbor unten
			calculateNeighborTopBottom(actual, neighbor_unten, helpListe, false);
		}
		//Iteriere über helpListe um die Knoten in knotenListe einzufügen
		knoten.addAll(helpListe);
		Iterator<Knoten> j = knoten.iterator();
		while (j.hasNext()) {
				Knoten actual = j.next();
				actual.setCurrent_value(actual.getCurrent_value()+ actual.getAkku());
				actual.setAkku(0.0);
		}
		System.out.println("nach berechnung "+knoten+ " spalte "+spalte);
	}
	
	public void calculateNeighborLeftRight(Knoten acutal, Knoten neighbor, boolean left){
		
	}
	
	public void calculateNeighborTopBottom(Knoten actual, Knoten neighbor, ArrayList<Knoten> helpListe, boolean top){
		Neighbor neighbour = Neighbor.Bottom;
		if (top){neighbour = Neighbor.Top;}
		double rate = actual.getCurrent_value()*ginfo.getRateForTarget(actual.getX(), actual.getY(), neighbour);
		//wenn NachbarOben einen Wert empfangen soll, speichere ihn
		if (neighbor == null && rate > 0 ){
				// erzeugt neuen Knoten mit inizialem current_vallue 0.0
				//speichere in HelpListe!
				neighbor = actual.createNode(actual.getX(),actual.getY()-1, helpListe);
		}
		//wenn nachbarOben existiert, speichere die rate in den Akku von currentNode und NachbarOben 
		if (neighbor != null){
			//System.out.println("übergangsrate nach oben: "+ actual.getCurrent_value()*
				//	ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
			neighbor.setAkku(neighbor.getAkku() + actual.getCurrent_value()*
					ginfo.getRateForTarget(actual.getX(), actual.getY(), neighbour));
		
			actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
					ginfo.getRateForTarget(actual.getX(), actual.getY(), neighbour));
		}
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
