import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

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

}
