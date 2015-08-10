package np2015;

import java.util.Iterator;
import java.util.List;



public class Seq_Berechnung {

	/**
	 *sequenzielle Berechnung nach Vorlage aus der Projektbeschreibung!
	 * 
	 * @param allNodes
	 * @param ginfo
	 */
	public void seqBerechnung(List<Knoten> allNodes, GraphInfo ginfo){
		
		Iterator<Knoten> i = allNodes.iterator();
		while (i.hasNext()) {
					Knoten actual = i.next();
					Knoten neighbor_oben = actual.get_neighbor_oben(allNodes);
					Knoten neighbor_unten= actual.get_neighbor_unten(allNodes);
					Knoten neighbor_rechts = actual.get_neighbor_rechts(allNodes);
					Knoten neighbor_links = actual.get_neighbor_links(allNodes);
					
					// top
					neighbor_oben.setAkku(neighbor_oben.getAkku() + actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
					
					actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
					// down
					neighbor_unten.setAkku(neighbor_unten.getAkku() + actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
					actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
					// right
					neighbor_rechts.setAkku(neighbor_rechts.getAkku() + actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Right));
					actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Right));
					//left
					neighbor_links.setAkku(neighbor_links.getAkku() + actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Left));
					actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Left));
			}
		
		Iterator<Knoten> j = allNodes.iterator();
		while (j.hasNext()) {
				Knoten actual = j.next();
				actual.setCurrent_value(actual.getCurrent_value()+ actual.getAkku());
				actual.setAkku(0.0);
		}
	}
	
}
