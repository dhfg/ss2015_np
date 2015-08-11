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
			
			/**
			 *  top
			 */
			double rate = actual.getCurrent_value()*ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top);
			
			//wenn NachbarOben einen Wert empfangen soll, speichere ihn
			if (neighbor_oben == null && rate > 0 ){
					// erzeugt neuen Knoten mit inizialem current_vallue 0.0
					//speichere in HelpListe!
					neighbor_oben = actual.createNode(actual.getX(),actual.getY()-1, helpListe);
			}
			//wenn nachbarOben existiert, speichere die rate in den Akku von currentNode und NachbarOben 
			if (neighbor_oben != null){
				//System.out.println("übergangsrate nach oben: "+ actual.getCurrent_value()*
					//	ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
				neighbor_oben.setAkku(neighbor_oben.getAkku() + actual.getCurrent_value()*
						ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
			
				actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
						ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
			}
		
			/**
			 * bottom
			 * */
			rate = actual.getCurrent_value()*ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom);

			//wenn NachbarUnten einen Wert empfangen soll, speichere ihn
			if (neighbor_unten == null && rate > 0 ){
				// erzeugt neuen Knoten mit inizialem current_vallue 0.0
				//speichere in HelpListe
				neighbor_unten = actual.createNode(actual.getX(),actual.getY()+1, helpListe);
			}
			//wenn nachbarUnten existiert, speichere die rate in den Akku von currentNode und NachbarOben 
			if (neighbor_unten != null){
//				System.out.println("übergangsrate nach unten: "+ actual.getCurrent_value()*
//						ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
				neighbor_unten.setAkku(neighbor_unten.getAkku() + actual.getCurrent_value()*
						ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
				actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
						ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
			}	
			
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
