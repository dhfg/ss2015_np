package np2015;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




public class Seq_Berechnung{

	/**
	 *sequenzielle Berechnung nach Vorlage aus der Projektbeschreibung!
	 * 
	 * @param allNodes
	 * @param ginfo
	 */
	public void seqBerechnung(List<Knoten> allNodes, GraphInfo ginfo){
		
		//TODO: in schön!! das Objelt so kopieren das es keine refernez auf das alte erhält!!
		List<Knoten> copyList = new ArrayList<Knoten>();
		Iterator<Knoten> l = allNodes.iterator();
		while (l.hasNext()) {
			copyList.add(l.next());
		}	
		Iterator<Knoten> i = copyList.iterator();
		while (i.hasNext()) {
					Knoten actual = i.next();
					Knoten neighbor_oben = actual.get_neighbor_oben(allNodes);
					Knoten neighbor_unten= actual.get_neighbor_unten(allNodes);
					Knoten neighbor_rechts = actual.get_neighbor_rechts(allNodes);
					Knoten neighbor_links = actual.get_neighbor_links(allNodes);
					System.out.println("oben "+neighbor_oben + " unten "+ neighbor_unten + " rechts " + neighbor_rechts + " links "+neighbor_links);
					
					// top
					if (neighbor_oben != null){
					neighbor_oben.setAkku(neighbor_oben.getAkku() + actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
					
					actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
					}
					// down
					if (neighbor_unten != null){
					neighbor_unten.setAkku(neighbor_unten.getAkku() + actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
					actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
					}
					// right
					if (neighbor_rechts != null){
					neighbor_rechts.setAkku(neighbor_rechts.getAkku() + actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Right));
					actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Right));
					}
					//left
					if (neighbor_links != null){
					neighbor_links.setAkku(neighbor_links.getAkku() + actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Left));
					actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
							ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Left));
					}
			}
		
		Iterator<Knoten> j = allNodes.iterator();
		while (j.hasNext()) {
				Knoten actual = j.next();
				actual.setCurrent_value(actual.getCurrent_value()+ actual.getAkku());
				actual.setAkku(0.0);
		}
	}
	
}
