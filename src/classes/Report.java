package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Report extends Database{
    private String reportId;
    private String reportType;
    private String reportDescription;
    private String reportBy;
    
    public Report(){
        
    }

    public Report(String reportId, String reportBy, String reportType, String reportDescription ) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.reportDescription = reportDescription;
        this.reportBy = reportBy;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getReportBy() {
        return reportBy;
    }

    public void setReportBy(String reportBy) {
        this.reportBy = reportBy;
    }

    
    @Override
    public void addData() {
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        final String query = "INSERT INTO report (ID,TYPE,DESCRIPTION,NAME)" + "VALUES('" + reportId + "', '" + reportType + "', '" + reportDescription + "', '" + reportBy + "')";
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Successfully Added");
    }

    @Override
    public void updateData() {
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        final String query = "UPDATE report SET TYPE = '" + reportType + "', DESCRIPTION = '" + reportDescription + "', NAME = '" + reportBy + "' WHERE ID = '" + reportId + "'";
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Successfully Updated");
    }

    @Override
    public void deleteData() {
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        final String query = "DELETE FROM report WHERE ID = '" + reportId + "'";
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Successfully Deleted");
    }

    @Override
    public List<Object[]> getData() {
        List<Object[]> reportDetails = new ArrayList<>();
        String query = "SELECT * FROM report";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        ResultSet resultSet = db.runSelect(query);

        try {
            while (resultSet.next()) {
                // Retrieve data from the result set and add to the list
                String id = resultSet.getString("ID");
                String name = resultSet.getString("TYPE");
                String contact = resultSet.getString("DESCRIPTION");
                String email = resultSet.getString("NAME");


                Object[] row = {id, name, contact, email};
                reportDetails.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        return reportDetails;
    }
}
