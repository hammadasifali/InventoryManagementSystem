package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Request extends Database{
    private String requestId;
    private String requestBy;
    private String requestItem;
    private String requestDate;
    
    public Request(){
    
    }

    public Request(String requestId, String requestBy, String requestItem, String requestDate) {
        this.requestId = requestId;
        this.requestBy = requestBy;
        this.requestItem = requestItem;
        this.requestDate = requestDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestItem() {
        return requestItem;
    }

    public void setRequestItem(String requestItem) {
        this.requestItem = requestItem;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }        

    @Override
    public void addData() {
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        final String query = "INSERT INTO request (ID,NAME,ITEM,DATE)" + "VALUES('" + requestId + "', '" + requestBy + "', '" + requestItem + "', '" + requestDate + "')";
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Successfully Added");
    }

    @Override
    public void updateData() {
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        final String query = "UPDATE request SET NAME = '" + requestBy + "', ITEM = '" + requestItem + "', DATE = '" + requestDate + "' WHERE ID = '" + requestId + "'";
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Successfully Updated");
    }

    @Override
    public void deleteData() {
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        final String query = "DELETE FROM report WHERE ID = '" + requestId + "'";
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Successfully Deleted");
    }

    @Override
    public List<Object[]> getData() {
        List<Object[]> requestDetails = new ArrayList<>();
        String query = "SELECT * FROM request";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        ResultSet resultSet = db.runSelect(query);

        try {
            while (resultSet.next()) {
                // Retrieve data from the result set and add to the list
                String id = resultSet.getString("ID");
                String name = resultSet.getString("NAME");
                String contact = resultSet.getString("ITEM");
                String email = resultSet.getString("DATE");


                Object[] row = {id, name, contact, email};
                requestDetails.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        return requestDetails;
    }
}
