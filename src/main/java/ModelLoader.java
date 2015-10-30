import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Returns the Model object for the RDF file
 */
public class ModelLoader {

    private static ModelLoader _instance;

    private static Model rdfModel = null;
    private static final String fileName = "rdf-data.rdf";

    private ModelLoader() {
        // Create a new model
        Model model = ModelFactory.createDefaultModel();

        // Read the RDF file
        InputStream in = FileManager.get().open(fileName);

        // Create an empty file if it doesn't exist
        if (in == null) {
            File f = new File(fileName);
            try {
                f.createNewFile();
                in = FileManager.get().open(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Connect the RDF file with the model
        rdfModel = model.read(in, null);
    }

    private synchronized static void createInstance() {
        if (_instance == null)
            _instance = new ModelLoader();
    }

    public static ModelLoader getInstance() {
        if (_instance == null)
            createInstance();

        return _instance;
    }

    public Model getModel() {
        return rdfModel;
    }

}
