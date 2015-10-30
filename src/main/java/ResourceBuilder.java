import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Builds a RDF resource for the given input
 */
public class ResourceBuilder {

    private static final String baseURI = "http://jena-test.com/";
    private static final String fileName = "rdf-data.rdf";

    public void createPerson(String id, String fullName) throws Exception {
        if (id == null || fullName == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        Model newModel = ModelFactory.createDefaultModel();

        // Create the resource
        Resource newPerson = newModel.createResource(baseURI + id);

        // Add the property
        newPerson.addProperty(VCARD.FN, fullName);

        // Write the person to a file
        newModel.write(new PrintStream(new FileOutputStream(fileName)));
    }

    public void putAction(String id, String action, String dateTime) throws Exception {
        if (id == null || action == null || dateTime == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        Model rdfModel = ModelLoader.getInstance().getModel();

        // Query the person
        Resource person = new QueryModel().getPerson(id);

        // Create new action resource
        Property actionProp = rdfModel.createProperty(baseURI + id + "#action");
        Resource newAction = rdfModel.createResource();

        // Action name property
        Property actionNameProp = rdfModel.createProperty(baseURI + id + "#action");
        newAction.addProperty(actionNameProp, action);

        // Datetime property
        Property dateTimeProp = rdfModel.createProperty(baseURI + id + "#action");
        newAction.addProperty(dateTimeProp, dateTime);

        // Add the new action resource to the person
        rdfModel.add(person, actionProp, newAction);

        // Write the action to a file
        write();
    }

    private void write() {
        try {
            Model rdfModel = ModelLoader.getInstance().getModel();
            rdfModel.write(new PrintStream(new FileOutputStream(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
