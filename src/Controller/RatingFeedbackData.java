// this class defines the backend logic to connect to DB to retreive USer Ratings provided by other users.
package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class RatingFeedbackData {
    public DefaultTableModel getRatingFeedbackData(String username) {
        DefaultTableModel model = new DefaultTableModel();

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/final_project?", "spandey", "Project@2024");

            // Create SQL statement
            String sql = "SELECT raterUsername, rating, feedback FROM final_project.ratings WHERE Username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            // Execute query
            ResultSet resultSet = statement.executeQuery();

            // Get metadata about the result set
            java.sql.ResultSetMetaData metaData = resultSet.getMetaData();

            // Get column count
            int columnCount = metaData.getColumnCount();

            // Add columns to table model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to table model
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                model.addRow(row);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // You might want to handle this exception differently in a real application
        }

        return model;
    }
}
