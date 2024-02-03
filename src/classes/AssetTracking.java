package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AssetTracking extends Database{
    
    private String id;
    private String assetName;
    private String userName;
    private String working;
    private String outOfOrder;
    private String repairable;
    
    public AssetTracking(){
        
    }     
            
    public AssetTracking(String id, String assetName, String userName, String working, String outOfOrder, String repairable) {
        this.id = id;
        this.assetName = assetName;
        this.userName = userName;
        this.working = working;
        this.outOfOrder = outOfOrder;
        this.repairable = repairable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String Working) {
        this.working = working;
    }

    public String getOutOfOrder() {
        return outOfOrder;
    }

    public void setOutOfOrder(String outOfOrder) {
        this.outOfOrder = outOfOrder;
    }

    public String getRepairable() {
        return repairable;
    }

    public void setRepairable(String repairable) {
        this.repairable = repairable;
    } 

    @Override
    public void updateData() {
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        final String query = "UPDATE assetTracking SET ASSET = '"+ assetName +"', [USER[ = '"+userName+"', WORKING = '"+working+"', OUTOFORDER = '"+outOfOrder+"', REPAIRABLE = '"+repairable+"' WHERE ID = '"+id+"';";
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Successfully");
    }

    @Override
    public void addData() {
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        final String query = "INSERT INTO assetTracking (ID, ASSET, [USER] , WORKING, OUTOFORDER,REPAIRABLE)" + 
                "VALUES ('"+id+"', '"+assetName+"', '"+userName+"', '"+working+"', '"+outOfOrder+"', '"+repairable+"');";
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Successfully");
    }

    @Override
    public void deleteData() {
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        final String query = "DELETE FROM assetTracking WHERE ID = '" + id + "'";
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Successfully");
    }

    @Override
    public List<Object[]> getData() {
        List<Object[]> assetsDetails = new ArrayList<>();
        final String query = "SELECT * FROM assetTracking";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        ResultSet resultSet = db.runSelect(query);

        try {
            while (resultSet.next()) {
                // Retrieve data from the result set and add to the list
                id = resultSet.getString("ID");
                assetName = resultSet.getString("ASSET");
                userName = resultSet.getString("USER");
                working = resultSet.getString("WORKING");
                outOfOrder = resultSet.getString("OUTOFORDER");
                repairable = resultSet.getString("REPAIRABLE");


                Object[] row = {id, assetName, userName, working, outOfOrder, repairable};
                assetsDetails.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        return assetsDetails;
    }
    
}
