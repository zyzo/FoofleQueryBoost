package sparqlclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sparqlclient.Pair;
/**
 * Reformulate queries using the server Sparql (instantiated with the ontology from
 * http://www.irit.fr/recherches/MELODI/ontologies/FilmographieV1.owl
 * http://www.irit.fr/recherches/MELODI/ontologies/FilmographieV1_Instances.owl
 */
/**
 * Solution pour régler problème de mots clés au même niveau :
 * - pondéré (ajouter poids du mot)
 * - plusieurs requetes -> fusionne résultat
 *
 */
public class FoofleReformulate2_n {
	
public static List<String> split_Req(String[] req){
		
		List<String> l = new ArrayList();
		for(String st : req){
			l.add(st);
		}
		return l;
	}
	
	public static List<Pair<String,String>> combine(List<String> l){
		List<Pair<String,String>> pairList = new ArrayList<Pair<String,String>>();
		while(l.size() >1){
			for(int i=1; i< l.size();i++){
				pairList.add(new Pair(l.get(0), l.get(i)));
			}
			l.remove(0);
		}
		return pairList;
	}
	
	 /**
     * @param args the command line arguments
     */
    public static List<String> strategy3(String[] args) {
    	List<String> l =  new ArrayList();
    	List<String> l2 =  new ArrayList();
    		l = split_Req(args);
    		l2 = split_Req(args);
    	//int nb_var = l.size();
    	List<Pair<String,String>> pairList = new ArrayList<Pair<String,String>>();
    	
    	pairList=combine(l2);
    	
    	/*for(int i=0; i<(pairList.size()-1);i++){
    		System.out.println(pairList.get(i).getL() + pairList.get(i).getR());
    	}*/
    	
        SparqlClient sparqlClient = new SparqlClient("localhost:3030");
        String prefix = "PREFIX : <http://ontologies.alwaysdata.net/space#>\n" +
						"PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
						"PREFIX owl:  <http://www.w3.org/2002/07/owl#>\n" +
						"PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n";
        
        String query;
        for(int i=0;i <pairList.size();i++){
        query = prefix + "SELECT ?label\n" +
						"WHERE {\n" +
						  "?search1 rdfs:label '"+ pairList.get(i).getL() +"'@fr.\n" +
						  "?search2 rdfs:label '"+ pairList.get(i).getR() +"'@fr.\n" +
						  "?search1 ?search2 ?ressource.\n" +
						  "?ressource rdfs:label ?label\n" +
						"}";
        System.out.println("Query : ");
        System.out.println(query);
        System.out.println("Result : ");
        Iterable<Map<String, String>> results = sparqlClient.select(query);
        for (Map<String, String> result : results) {
        	String val = result.get("label");
        	if(!l.contains(val)){
            l.add(val);
        	}
        }
        }
        System.out.println("After for loop 1");
        
        for(int i=0;i <pairList.size();i++){
            query = prefix + "SELECT ?label\n" +
    						"WHERE {\n" +
    						  "?search1 rdfs:label '"+ pairList.get(i).getL() +"'@fr.\n" +
    						  "?search2 rdfs:label '"+ pairList.get(i).getR() +"'@fr.\n" +
    						  "?search2 ?search1 ?ressource.\n" +
    						  "?ressource rdfs:label ?label\n" +
    						"}";
            System.out.println("Query : ");
            System.out.println(query);
            System.out.println("Result : ");
            Iterable<Map<String, String>> results2 = sparqlClient.select(query);
            for (Map<String, String> result : results2) {
            	String val = result.get("label");
            	if(!l.contains(val)){
                l.add(val);
            	}
            }
        }
        	
        for(int i=0;i <pairList.size();i++){
    	query = prefix + "SELECT ?label\n" +
    						"WHERE {\n" +
    						  "?search1 rdfs:label '"+ pairList.get(i).getL() +"'.\n" +
    						  "?search2 rdfs:label '"+ pairList.get(i).getR() +"'@fr.\n" +
    						  "?search2 ?search1 ?ressource.\n" +
    						  "?ressource rdfs:label ?label\n" +
    						"}";
            System.out.println("Query : ");
            System.out.println(query);
            System.out.println("Result : ");
            Iterable<Map<String, String>> results3 = sparqlClient.select(query);
            for (Map<String, String> result : results3) {
            	String val = result.get("label");
            	if(!l.contains(val)){
                l.add(val);
            	}
            }
        }
            
        for(int i=0;i <pairList.size();i++){
        	query = prefix + "SELECT ?label\n" +
    						"WHERE {\n" +
    						  "?search1 rdfs:label '"+ pairList.get(i).getL() +"'.\n" +
    						  "?search2 rdfs:label '"+ pairList.get(i).getR() +"'@fr.\n" +
    						  "?search1 ?search2 ?ressource.\n" +
    						  "?ressource rdfs:label ?label\n" +
    						"}";
            System.out.println("Query : ");
            System.out.println(query);
            System.out.println("Result : ");
            Iterable<Map<String, String>> results4 = sparqlClient.select(query);
            for (Map<String, String> result : results4) {
            	String val = result.get("label");
            	if(!l.contains(val)){
                l.add(val);
            	}
            }
        }
        	
        for(int i=0;i <pairList.size();i++){
    	query = prefix + "SELECT ?label\n" +
    						"WHERE {\n" +
    						  "?search1 rdfs:label '"+ pairList.get(i).getL() +"'@fr.\n" +
    						  "?search2 rdfs:label '"+ pairList.get(i).getR() +"'.\n" +
    						  "?search2 ?search1 ?ressource.\n" +
    						  "?ressource rdfs:label ?label\n" +
    						"}";
            System.out.println("Query : ");
            System.out.println(query);
            System.out.println("Result : ");
            Iterable<Map<String, String>> results5 = sparqlClient.select(query);
            for (Map<String, String> result : results5) {
            	String val = result.get("label");
            	if(!l.contains(val)){
                l.add(val);
            	}
            }
        }
            
        for(int i=0;i <pairList.size();i++){
        	query = prefix + "SELECT ?label\n" +
    						"WHERE {\n" +
    						  "?search1 rdfs:label '"+ pairList.get(i).getL() +"'@fr.\n" +
    						  "?search2 rdfs:label '"+ pairList.get(i).getR() +"'.\n" +
    						  "?search1 ?search2 ?ressource.\n" +
    						  "?ressource rdfs:label ?label\n" +
    						"}";
            System.out.println("Query : ");
            System.out.println(query);
            System.out.println("Result : ");
            Iterable<Map<String, String>> results6 = sparqlClient.select(query);
            for (Map<String, String> result : results6) {
            	String val = result.get("label");
            	if(!l.contains(val)){
                l.add(val);
            	}
            }
            
        }
    	
    	System.out.println(l.toString());
    	return l;
    }
     
}
