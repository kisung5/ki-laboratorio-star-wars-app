public class Main {

    public static void main(String[] args) {

        GuiTable gui = new GuiTable();
        ApiAccess api = new ApiAccess();

        gui.addTable(api.getSWAPI());
        gui.show();

    }
}
