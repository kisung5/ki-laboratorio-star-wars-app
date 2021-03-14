import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {


    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Main.class);

        GuiTable gui = new GuiTable();
        ApiAccess api = new ApiAccess();

        try {
            gui.addTable(api.getSWAPI());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        gui.show();

    }
}
