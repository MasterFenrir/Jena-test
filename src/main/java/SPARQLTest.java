import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;

public class SPARQLTest {

    public static void main(String[] args) {
        Model model = ModelLoader.getInstance().getModel();

        String queryString = "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#>" +
                "SELECT ?name " +
                "WHERE { ?x vcard:FN ?name }";
        Query query = QueryFactory.create(queryString) ;
        try {
            QueryExecution qexec = QueryExecutionFactory.create(query, model);
            ResultSet results = qexec.execSelect() ;
            for ( ; results.hasNext() ; )
            {
                QuerySolution soln = results.nextSolution() ;
                RDFNode x = soln.get("name") ;       // Get a result variable by name.

                System.out.println(x.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
