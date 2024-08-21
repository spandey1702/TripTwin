//define the user interface to view the ratings of a Userprofile of other users. Calls JDBC code to extract the ratings and display them
package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class RatingFeedbackGUI extends JFrame {
    private JTable ratingFeedbackTable;

    public RatingFeedbackGUI(DefaultTableModel model) {
        setTitle("Rating and Feedback Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//components
        ratingFeedbackTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(ratingFeedbackTable);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}