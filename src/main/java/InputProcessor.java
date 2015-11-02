import org.apache.jena.base.Sys;
import org.apache.jena.rdf.model.*;

import java.io.File;

/**
 * Gets input from user and processes it within Jena
 */
public class InputProcessor {

    private static final String baseURI = "http://jena-test.com/";
    private static final String fileName = "rdf-data.rdf";

    public static void main(String[] args) {
        System.out.println("Pietje: " + "Ik heb vandaag gefietsd");

        String id = "pietje";

        // Clear the RDF file
        try {
            new File(fileName).delete();
        } catch (Exception e) {
            System.err.println(e);
        }

        // Create a new person
        ResourceBuilder builder = new ResourceBuilder();

        try {
            builder.createPerson(id, "Pietje Bruisma");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Define some input
        String action = "fietsen";
        String dateTime = "30-10-2015 20:34:23";

        // Add it to the RDF file
        Relations relations = new Relations();

        relations.ikDoe(id, action, dateTime);

        // Query the RDF models
        QueryModel query = new QueryModel();
        Resource person = query.getPerson(id);

        // TODO: query the RDF model to get back the lastest action
        // Get the action
        Bag actions = query.getAction(id);
        String actionString = "fietsen";

        // Translate the query results to a Dutch response
        Templates template = new Templates();
        System.out.println("Virtual Coach: " + template.ikDoe(actionString));

    }

}
