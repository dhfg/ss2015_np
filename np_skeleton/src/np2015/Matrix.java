package np2015;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



// verwaltet alle Spalten
public class Matrix extends Thread{
	
	private ArrayList<Spalten> alleSpalten = new ArrayList<Spalten>();
	private GraphInfo ginfo = new GraphInfo(0, 0);
	public static int height;
	public static int width;
	public int iterationen = 100;
	
	//Konstruktor
	//TODO: macht es sinn darauf zu bestehen das der initiale Knoten mit dem Konstruktor eingeführt wird?
	public Matrix(GraphInfo ginfo){	
		this.ginfo = ginfo;
		this.height = ginfo.height;
		this.width = ginfo.width;
	}
	/**
	 * spalten starten,
	 * solange es nicht konvergiert weitermachen
	 * austauschen aufuren
	 * */
	
	public void initializeMatrix(){
//		while(true){
			startSpalten();
			organisiereAustauschAkkus();
//		}
	}
	
	
	public void organisiereAustauschAkkus(){
	/*	Iterator<Spalten> spaltenIt = alleSpalten.iterator();
		int i = 0;
		while (spaltenIt.hasNext()) {
			Spalten currentSpalte = spaltenIt.next();

			*/
			Iterator<Knoten> knotenIt = NPOsmose.allNodes.iterator();
			while(knotenIt.hasNext()){
				Knoten currentKnoten = knotenIt.next();				
				austauschAkkus(currentKnoten);
			}
		}
		
	public void austauschAkkus(Knoten k){
		//links
		Knoten n = k.get_neighbor_links(NPOsmose.allNodes);
		// an Eva: hier kann es sein das es noch keinen nachbarknoten gibt, 
		//dann muss er noch erzeugt werden und in die passende spalte eingetragen werden 
		if (n == null){
			create_new_node(n.getX()-1, n.getY());
			
			
		}
		
		
		
		double rate = k.getAkkuL();
		n.setAkku(rate);
		k.setOutflow(k.getOutflow()+rate);
		n.setInflow(n.getInflow()+rate);
		//rechts
		n = k.get_neighbor_rechts(NPOsmose.allNodes);
		if (n == null){
			create_new_node(n.getX()+1, n.getY());
		}
		rate = k.getAkkuL();
		n.setAkku(rate);
		k.setOutflow(k.getOutflow()+rate);
		n.setInflow(n.getInflow()+rate);
	}
	
	// x ist die spalte
	// erzeugt wenn möglich neuen Knoten und trägt ihn passend überall ein
	public Knoten create_new_node(int x, int y){
			if ( x <= Matrix.height && x >= 0 && y <= Matrix.width && y >= 0){
					Knoten new_node = new Knoten(x, y, 0.0, NPOsmose.allNodes);
					// wird geschaut, ob passende spalte existiert, wenn ja wird Wert eingetragen
					for ( Spalten s : alleSpalten){
							if (s.getSpalte() == x){
								s.getKnoten().add(new_node);
								return new_node;
							}
					}
					// wenn nicht wird spalte erzeugt und der wert dann eingetragen
				//	Spalten new_column = new Spalten (x, ); //
					
					
				
				}
			return null;
			}
		
	
	
	
	
		//TODO akku vom link und rechts ändern	
	
	public void startSpalten(){
		System.out.println("alle S " +alleSpalten);
		List<Spalten> spaltenList = new ArrayList<Spalten>();
		Iterator<Spalten> spaltenIt = alleSpalten.iterator();
		while (spaltenIt.hasNext()) {
			Thread threadSpalten = spaltenIt.next();
			threadSpalten.start();
		}	
	}
	
	
	public GraphInfo getGinfo() {
		return ginfo;
	}
	public void setGinfo(GraphInfo ginfo) {
		this.ginfo = ginfo;
	}
	public static int getHeight() {
		return height;
	}
	public static void setHeight(int height) {
		Matrix.height = height;
	}
	public static int getWidth() {
		return width;
	}
	public static void setWidth(int width) {
		Matrix.width = width;
	}
	public int getIterationen() {
		return iterationen;
	}
	public void setIterationen(int iterationen) {
		this.iterationen = iterationen;
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
