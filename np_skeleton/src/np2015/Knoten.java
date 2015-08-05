package np2015;

import java.util.ArrayList;
import java.util.List;

public class Knoten{
	
	private int x = 0;
	private int y = 0;
	private double current_value = 0;
	private List<Knoten>  allNodes = new ArrayList<Knoten>();
	
	
	/**
	 * @param x
	 * @param y
	 * @param current_value
	 *
	 * Konstruktor
	 */
	public Knoten(int x, int y, double current_value, List<Knoten> allNodes){
		this.x = x; // ----
		this.y = y; // |
		this.current_value = current_value;
		allNodes.add(this);
		System.out.println(allNodes);
	}
	
	
	/**
	 * TODO: vielleicht in aller oberste klasse ziehen, dann spart man sich diese allNodes liste und kann vond dort aus mit hilfe der internen Knotenlisten von 
	 * den spalten suchen
	 * @param actual_node
	 * @return Liste mit oberen und unteren Nachbaren
	 */
	public  List<Knoten> get_neighbor_intern(List<Knoten> allNodes ){
		
		List<Knoten> neighbor = new ArrayList<Knoten>();
		// stimmt das so oder ist das umgekehrt?!
		int pos_neighbor_o = this.getY()-1;
		int pos_neighbor_u = this.getY()+1;
		
		int spalte = this.getX();
		for (Knoten k : allNodes){
			if (k.getX() == spalte && k.getY() == pos_neighbor_o){
				neighbor.add(k);
			}
		}
		if( neighbor.size() == 0){
			neighbor.add(null);
		}
		
		for (Knoten k : allNodes){
			if (k.getX() == spalte && k.getY() == pos_neighbor_u){
				neighbor.add(k);
			}
		}
		if( neighbor.size() == 1){
				neighbor.add(null);
		}
			
		return neighbor;
	}

	
	/**
	 * 
	 * @param actual_node
	 * @return Liste mit rechtem und linken Nachbaren
	 */
	public List<Knoten> get_neighbor_extern(List<Knoten> allNodes ){
		List<Knoten> neighbor_extern = new ArrayList<Knoten>();
		int zeile = this.getY();
		int pos_neighbor_r = this.getX()+1;
		int pos_neighbor_l = this.getX()-1;
		
		for ( Knoten k : allNodes ){
			if (k.getY() == zeile && k.getX() == pos_neighbor_r ){
				neighbor_extern.add(k);
			}
		}
		if (neighbor_extern.size() == 0){
			neighbor_extern.add(null);
		}
		for ( Knoten k : allNodes){
			if (k.getY() == zeile && k.getX() == pos_neighbor_l){
				neighbor_extern.add(k);
			}
		}
		if (neighbor_extern.size() == 1){
				neighbor_extern.add(null);
			}
		return neighbor_extern;
	}
	
	
	/**
	 * getter und setter für alle Felder von Knoten
	 */
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getCurrent_value() {
		return current_value;
	}

	public void setCurrent_value(double current_value) {
		this.current_value = current_value;
	}
	
	//TODO: == operator überschreiben
	public boolean equals(Knoten other){
		return true;
	}
	
	/**
	 *to string funktion um Knoten ausgeben zu können
	 */
	public String toString(){
		return( "( x: " + x + " y: " + y + " value: " + current_value + " )");
		
	}
}
