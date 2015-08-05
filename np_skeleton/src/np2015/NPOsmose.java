package np2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class NPOsmose {
	
	public static List<Knoten > allNodes = new ArrayList<Knoten>(); // muss gelockt werden bei allen aufrufen!! 
																	//oder werden knoten nur sequenziell erstellT?
	

	public static void main(String[] args) throws IOException, InterruptedException {
		Gson gson = new Gson();
		String json = "";
		// read data in
		if (args.length != 0) {
			Path path = Paths.get(args[0]);
			try {
				json = new String(Files.readAllBytes(path)); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("You must provide the serialized file as the first argument!");
		}
		GraphInfo ginfo = gson.fromJson(json, GraphInfo.class);
		
		
		// Your implementation can now access ginfo to read out all important values
		
		/**
		 * test für Knoten
		 */
		Knoten mitte = new Knoten(1,1,1.0, allNodes);
		Knoten oben = new Knoten(1,0,1.0, allNodes);
		Knoten unten = new Knoten(1,2,1.0, allNodes);
		Knoten links = new Knoten(0,1,1.0, allNodes);
		Knoten rechts = new Knoten(2,1,1.0, allNodes);
		
		System.out.println(mitte.toString());
		System.out.println(oben.toString());
		System.out.println(unten.toString());
		System.out.println(links.toString());
		System.out.println(rechts.toString());	
		
		List<Knoten> result = mitte.get_neighbor_intern(allNodes);
		List<Knoten> result_ =  mitte.get_neighbor_extern(allNodes);
		System.out.println("result " + result) ;
		System.out.print("result_: " +result_);
		
		Spalten s = new Spalten(0);
		System.out.print(s.toString());
		
		s.set_knoten(mitte);
		s.set_akku_L(1.0);
		s.set_akku_R(1.0);
		System.out.print(s.toString());
		
		/**
		 * original ende der datei
		 */
	//	ImageConvertible graph = null; // <--- you should implement ImageConvertible to write the graph out
	//	ginfo.write2File("./result.txt", graph);
	}

}
