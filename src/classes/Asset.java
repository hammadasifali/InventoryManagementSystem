package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Asset extends Database {
    
    private String assetId;
    private String assetName;
    private String assetDescription;
    private String assetQuantity;
    private String assetCompany;
    private String assetPrice;
    
    public Asset(){
        
    }

    public Asset(String assetId, String assetName, String assetDescription, String assetQuantity, String assetCompany, String assetprice) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.assetDescription = assetDescription;
        this.assetQuantity = assetQuantity;
        this.assetCompany = assetCompany;
        this.assetPrice = assetprice;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public String getAssetQuantity() {
        return assetQuantity;
    }

    public void setAssetQuantity(String assetQuantity) {
        this.assetQuantity = assetQuantity;
    }

    public String getAssetCompany() {
        return assetCompany;
    }

    public void setAssetCompany(String assetCompany) {
        this.assetCompany = assetCompany;
    }

    public String getAssetPrice() {
        return assetPrice;
    }

    public void setAssetPrice(String assetprice) {
        this.assetPrice = assetprice;
    }

    @Override
    public void addData() {
        String query = "INSERT INTO assets (ID, NAME, DESCRIPTION, QUANTITY, COMPANY, PRICE)" + "VALUES('"+ assetId +"', '"+ assetName +"'"
                + ", '"+ assetDescription +"', '"+ assetQuantity +"', '"+ assetCompany +"', '"+ assetPrice +"')";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Added Successfully");
    }

    @Override
    public void updateData() {
        String query = "UPDATE assets SET NAME='" + assetName + "',DESCRIPTION='" + assetDescription + "', QUANTITY='" + assetQuantity + "',COMPANY='" + assetCompany + "',PRICE='" + assetPrice + "' WHERE ID='" + assetId + "'";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Updated Successfully ");
    }

    @Override
    public void deleteData() {
        String query = "DELETE FROM assets WHERE ID='" + assetId + "'";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Deleted Successfully");
    }

    @Override
    public List<Object[]> getData() {
        List<Object[]> assetsDetails = new ArrayList<>();
        String query = "SELECT * FROM assets";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        ResultSet resultSet = db.runSelect(query);

        try {
            while (resultSet.next()) {
                // Retrieve data from the result set and add to the list
                String id = resultSet.getString("ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                String quantity = resultSet.getString("QUANTITY");
                String company = resultSet.getString("COMPANY");
                String price = resultSet.getString("PRICE");


                Object[] row = {id, name, description, quantity, company, price};
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
