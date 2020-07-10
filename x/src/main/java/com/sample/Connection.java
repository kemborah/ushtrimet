package com.sample;

import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Connection {
    private final String userName = "root";
    private final String password = "Tasks99.";
    private final String serverName = "localhost";
    private final int portNumber = 3306;
    private final String dbName = "x";
    private final String tableName = "user";


    public java.sql.Connection getConnection() throws SQLException {
        java.sql.Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        conn = DriverManager.getConnection("jdbc:mysql://"
                        + this.serverName + ":" + this.portNumber + "/" + this.dbName,
                connectionProps);

        return conn;
    }


    public boolean executeUpdate(java.sql.Connection conn, String command) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(command); // This will throw a SQLException if it fails
            return true;
        } finally {
            // This will run whether we throw an exception or not
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public java.sql.Connection getConn() {
        return conn;
    }

    java.sql.Connection conn;

    public void connect() {
        try {
            conn = this.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not connect to the database");
            e.printStackTrace();
            return;
        }
    }

    public boolean checkUser(String username, String password) {
        boolean exists = false;
        try {
            String querySELECTALL = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(querySELECTALL);
            // iterate through the java resultset
            if (rs.next() != false) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return exists;
        }
        return exists;
    }


    public String getRole(int id) {
        String role;
        try {
            String querySELECTALL = "SELECT role FROM user WHERE id='" + id + "'";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(querySELECTALL);
            // iterate through the java resultset
            if (rs.next() == false) {
                role = "";
            } else {
                role = rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "sql exception";
        }
        return role;
    }


    public ResultSet getProducts() {
        ResultSet rs= null;
        try {
            String querySELECTALL = "SELECT *,price+surcharge AS 'total' FROM product CROSS JOIN size";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            rs = st.executeQuery(querySELECTALL);
            // iterate through the java resultset
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getSizeInfo(int szId) {
        ResultSet rs= null;
        try {
            String querySELECTALL = "SELECT size.name,size.surcharge FROM size WHERE id='"+szId+"'";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            rs = st.executeQuery(querySELECTALL);
            // iterate through the java resultset
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public ResultSet getProductInfo(int pdId) {
        ResultSet rs= null;
        try {
            String querySELECTALL = "SELECT product.name,product.price,product.commission FROM product WHERE id='"+pdId+"'";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            rs = st.executeQuery(querySELECTALL);
            // iterate through the java resultset
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public ResultSet getInventory(int prodId) {
        ResultSet rs= null;
        try {
            String querySELECTALL = "SELECT *, product.price+size.surcharge AS 'total'from inventory " +
                    "LEFT JOIN product ON inventory.product = product.id " +
                    "LEFT JOIN size ON inventory.size = size.id WHERE product.id= " +prodId+
                    " order by total";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            rs = st.executeQuery(querySELECTALL);
            // iterate through the java resultset
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public int getInventoryForProduct(int pdId, int szId) {
        ResultSet rs;
        int inventory=0;
        try {
            String querySELECTALL = "SELECT COUNT(*) FROM inventory WHERE product='"+pdId+"' AND size='"+szId+"' AND inventory.state='available'";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            rs = st.executeQuery(querySELECTALL);
            // iterate through the java resultset
            if (rs.next() != false) {
                inventory = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }


    public int getId(String username, String password) {
        int id = -1;
        try {
            String querySELECTALL = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(querySELECTALL);
            // iterate through the java resultset
            if (rs.next() != false) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }

    public void drop() {
        // Drop the table
        try {
            String dropString = "DROP TABLE " + this.tableName;
            this.executeUpdate(conn, dropString);
            System.out.println("Dropped the table");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not drop the table");
            e.printStackTrace();
            return;
        }
    }

    public void updateInventory(int barcode,String state,int size){
        try {
            String updateProduct =
                    "UPDATE inventory SET state='"+state+"', size='"+size+"' WHERE barcode='"+barcode+"'";
            this.executeUpdate(conn, updateProduct);
        } catch (SQLException e) {
            System.out.println("GABIM: NUK u krye perditesimi");
            e.printStackTrace();
            return;
        }

    }
    public void updateProduct(int productId,int sizeId,String name,String description,Double cost,Double price,
                              Double commission,String size,Double surcharge){
        try {
            String updateProduct =
                    "UPDATE product SET name='"+name+"',description='"+description+"',cost='"+cost+"',price='"+price+"'," +
                            "commission='"+commission+"' WHERE id='"+productId+"'";
            String updateSize =
                    "UPDATE size SET"+
                            " name='"+size+"'," +
                            "surcharge='"+surcharge+"'" +
                            " WHERE id='"+sizeId+"'";
            this.executeUpdate(conn, updateProduct);
            this.executeUpdate(conn, updateSize);
        } catch (SQLException e) {
            System.out.println("GABIM: NUK u krye perditesimi");
            e.printStackTrace();
            return;
        }

    }

    public void deleteProduct(int productId){
        try {
            String deleteProduct =
                    "DELETE FROM product WHERE id="+productId;
            Statement st=conn.createStatement();
            st.executeUpdate(deleteProduct);
        } catch (SQLException e) {
            System.out.println("GABIM: NUK u krye perditesimi");
            e.printStackTrace();
            return;
        }}

    public void deleteInventory(int barcode){
        try {
            String deleteProduct =
                    "DELETE FROM inventory WHERE barcode="+barcode;
            Statement st=conn.createStatement();
            st.executeUpdate(deleteProduct);
        } catch (SQLException e) {
            System.out.println("GABIM: NUK u krye perditesimi");
            e.printStackTrace();
            return;
        }}

    public void addInventory(int prodId,int size){
        try {
            String addProduct =
                    "INSERT INTO inventory (state, product, size) VALUES (1,"+prodId+","+size+")";
            Statement st=conn.createStatement();
            st.executeUpdate(addProduct);
        } catch (SQLException e) {
            System.out.println("GABIM: NUK u krye perditesimi");
            e.printStackTrace();
            return;
        }}

    public ResultSet getInventory2(int pdId, int szId, int qt) {
        ResultSet rs= null;
        try {
            String querySELECTALL = "SELECT inventory.barcode from inventory " +
                    "LEFT JOIN product ON inventory.product = product.id " +
                    "LEFT JOIN size ON inventory.size = size.id WHERE product.id= " +pdId+
                    " AND size.id= "+szId+ " AND inventory.state='available' limit "+qt;
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            rs = st.executeQuery(querySELECTALL);
            // iterate through the java resultset
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public void addTransaction(int barcode, Double total,int userId){
        try {
            String addProduct =
                    "INSERT INTO transaction (date, barcode, sold_price,user_id) VALUES (NOW(),"+barcode+","+total+","+userId+")";
            Statement st=conn.createStatement();
            st.executeUpdate(addProduct);
        } catch (SQLException e) {
            System.out.println("GABIM: NUK u krye perditesimi");
            e.printStackTrace();
            return;
        }}


    public void run() {
        connect();
    }}

