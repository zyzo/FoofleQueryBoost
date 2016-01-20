package sparqlclient;

import java.util.Map;

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
public class FoofleReformulate {
	
	 /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SparqlClient sparqlClient = new SparqlClient("localhost:3030");
        String prefix = "PREFIX : <http://ontologies.alwaysdata.net/space#>\n" +
						"PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
						"PREFIX owl:  <http://www.w3.org/2002/07/owl#>\n" +
						"PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>\n";
        String query = prefix + "SELECT ?label\n" +
						"WHERE {\n" +
						  "?prix rdfs:label ?label\n" +
						"}";
        System.out.println("Query : ");
        System.out.println(query);
        System.out.println("Result : ");
        Iterable<Map<String, String>> results = sparqlClient.select(query);
        for (Map<String, String> result : results) {
            System.out.println(result.get("label"));
        }
    }
     
}
