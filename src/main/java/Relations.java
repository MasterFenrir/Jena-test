/**
 * Defines the relations that are implemented in this proof of concept
 */
public class Relations {

    private ResourceBuilder builder = null;

    public Relations() {
        builder = new ResourceBuilder();
    }

    public void ikDoe(String id, String action, String dateTime) {
        try {
            builder.putAction(id, action, dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
