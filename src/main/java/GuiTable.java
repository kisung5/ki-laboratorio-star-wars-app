import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

public class GuiTable {

    // frame
    private JFrame f;
    // Table
    private JTable j;

    // Data to be displayed in the JTable
//    ArrayList<ArrayList<String>>
    String[][] data;
//    = {
//            { "Kundan Kumar Jha", "4031", "CSE" },
//            { "Anand Jha", "6014", "IT" }
//    };

    // Column Names
    String[] columnNames = { "Nombre", "Altura", "Nacimiento" };

    // Constructor
    GuiTable()
    {
        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Personajes de Star Wars");

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Frame Size
        f.setSize(500, 200);
    }

    public  void addTable(String[][] data) {
        this.data = data;

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
    }

    public void show() {
        // Frame Visible = true
        f.setVisible(true);
    }

}
