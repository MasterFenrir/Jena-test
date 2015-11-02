import org.apache.jena.rdf.model.*;

/**
 * Wrapper for simple queries for the RDF file
 */
public class QueryModel {

    private static final String baseURI = "http://jena-test.com/";

    private Model rdfModel = null;

    public QueryModel() {
        this.rdfModel = (ModelLoader.getInstance()).getModel();
    }

    public Resource getPerson(String id) {
        return rdfModel.getResource(baseURI + id);
    }

    public Bag getAction(String personId) {
        // Create a bag
        Bag actions = rdfModel.createBag();

        Property actionProp = rdfModel.createProperty(baseURI + "#action");

        // select all the resources with a VCARD.FN property
        // whose value ends with "Smith"
        StmtIterator iter = rdfModel.listStatements(
                new SimpleSelector(null, actionProp, (RDFNode) null) {
                });

        // Add the  to the bag
        while (iter.hasNext()) {
            actions.add(iter.nextStatement().getSubject());
        }

        NodeIterator iter2 = actions.iterator();
        if (iter2.hasNext()) {
            System.out.println("The bag contains:");
            while (iter2.hasNext()) {
                System.out.println("  " +
                        ((Resource) iter2.next())
                                .getProperty(actionProp)
                                .getString());
            }
        } else {
            System.out.println("The bag is empty");
        }

        return actions;
    }

}
