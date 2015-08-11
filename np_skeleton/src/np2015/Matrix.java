package np2015;

import java.util.ArrayList;



// verwaltet alle Spalten
public class Matrix {
	
	private ArrayList<Spalten> alleSpalten = new ArrayList<Spalten>();
	private GraphInfo ginfo = new GraphInfo(0, 0);
	public static int height;
	public static int width;
	
	//Konstruktor
	//TODO: macht es sinn darauf zu bestehen das der initiale Knoten mit dem Konstruktor eingeführt wird?
	public Matrix(GraphInfo ginfo){	
		this.ginfo = ginfo;
		this.height = ginfo.height;
		this.width = ginfo.width;
	}
	
	public void addSpalte(Spalten p){
		ArrayList<Spalten> alleSpalten = getAlleSpalten();
		alleSpalten.add(p);
	}


	public ArrayList<Spalten> getAlleSpalten() {
		return alleSpalten;
	}


	public void setAlleSpalten(ArrayList<Spalten> alleSpalten) {
		this.alleSpalten = alleSpalten;
	}
	
	
	public String toString(){
		return("(" + getAlleSpalten()+ ")" );
	}

	
	
	
	
}
