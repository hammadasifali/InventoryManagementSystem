package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class User extends Database {
    
    private String userId;
    private String userName;
    private String userContact;
    private String userEmail;
    
    public User(){
        
    }

    public User(String userId, String userName, String userContact, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userContact = userContact;
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    
    @Override
    public void addData() {
        String query = "INSERT INTO user (ID, NAME, CONTACT, EMAIL)" + "VALUES('"+ userId +"', '"+ userName +"'"
                + ", '"+ userContact +"', '"+ userEmail +"')";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Added Successfully");
    }

    @Override
    public void updateData() {
        String query = "UPDATE user SET NAME='" + userName + "',CONTACT='" + userContact + "', EMAIL='" + userEmail + "' WHERE ID='" + userId + "'";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Updated Successfully ");
    }

    @Override
    public void deleteData() {
        String query = "DELETE FROM user WHERE ID='" + userId + "'";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        db.runDML(query);
        JOptionPane.showMessageDialog(null, "Deleted Successfully");
    }

    @Override
    public List<Object[]> getData() {
        List<Object[]> usersDetails = new ArrayList<>();
        String query = "SELECT * FROM user";
        DbConnection db = new DbConnection("jdbc:ucanaccess://project.accdb");
        ResultSet resultSet = db.runSelect(query);

        try {
            while (resultSet.next()) {
                // Retrieve data from the result set and add to the list
                String id = resultSet.getString("ID");
                String name = resultSet.getString("NAME");
                String contact = resultSet.getString("CONTACT");
                String email = resultSet.getString("EMAIL");


                Object[] row = {id, name, contact, email};
                usersDetails.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        return usersDetails;
    }
}
