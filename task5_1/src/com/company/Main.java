package com.company;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class Main {

    private final String userName = "root";
    private final String password = "Tasks99.";
    private final String serverName = "localhost";
    private final int portNumber = 3306;
    private final String dbName = "Task5";
    private final String tableName = "Transactions";

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        conn = DriverManager.getConnection("jdbc:mysql://"
                        + this.serverName + ":" + this.portNumber + "/" + this.dbName,
                connectionProps);

        return conn;
    }


    public boolean executeUpdate(Connection conn, String command) throws SQLException {
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

    Connection conn;

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

    public void krijoTab() {
        try {
            String createString =
                    "CREATE TABLE " + this.tableName + " ( " +
                            "ID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                            "TOTALIPARA DOUBLE NOT NULL, " +
                            "ULJE1 DOUBLE(100,2) NOT NULL, " +
                            "ULJE2 DOUBLE(100,2) NOT NULL, " +
                            "ULJE3 DOUBLE(100,2) NOT NULL, " +
                            "ULJE4 DOUBLE(100,2) NOT NULL, " +
                            "NRPROD INTEGER NOT NULL, " +
                            "TOTALIPARA3 DOUBLE(100,2) NOT NULL, " +
                            "TOTALI DOUBLE(100,2) NOT NULL " +
                            ")";
            this.executeUpdate(conn, createString);
            System.out.println("The table was created");
        } catch (SQLException e) {
            System.out.println("ERROR:The table wasn't created!");
            e.printStackTrace();
            return;
        }
    }

    public void shtoData(double totalPara, double ulja1, double ulja2, double ulja3, double ulja4, double totalPara3, int nrProdukteve, double total) {
        try {
            String addRecordString =
                    "INSERT INTO " + this.tableName + " (TOTALIPARA, ULJE1, ULJE2, ULJE3, ULJE4, NRPROD, TOTALIPARA3, TOTALI) " +
                            "VALUES( " + totalPara + " , " + ulja1 + ", " + ulja2 + " , " + ulja3 + " , " + ulja4 + " , " + nrProdukteve + ", " + totalPara3 + ", " + total + ")";
            this.executeUpdate(conn, addRecordString);
            System.out.println("The records were added");
        } catch (SQLException e) {
            System.out.println("ERROR:The records weren't added");
            e.printStackTrace();
            return;
        }
    }

    public void afisho() {
        try {
            String querySELECTALL = "SELECT * FROM Transactions";
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(querySELECTALL);
            // iterate through the java resultset
            System.out.println("All data");
            while (rs.next()) {
                int id = rs.getInt("ID");
                double totaliPara = rs.getDouble("TOTALIPARA");
                double ulje1 = rs.getDouble("ULJE1");
                double ulje2 = rs.getDouble("ULJE2");
                double ulje3 = rs.getDouble("ULJE3");
                double ulje4 = rs.getDouble("ULJE4");
                double totalPara3 = rs.getDouble("TOTALIPARA3");
                int nrProd = rs.getInt("NRPROD");
                double total = rs.getDouble("TOTALI");

                System.out.format("Transaction %s -> Total in the beginning: %s, discount1: %s, discount2: %s, discount3: %s, discount4:%s, Nr.of products: %s. Paid  %s \n", id, totaliPara, ulje1, ulje2, ulje3, ulje4, nrProd, total);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Could not show the results");
            e.printStackTrace();
            return;
        }

    }


    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        ArrayList<ProduktIZgjedhur> produktet = new ArrayList<>();
        System.out.println("The products \n 1. TV 30 channels\n " +
                "2. TV 40 channels\n " +
                "3. TV 100 channels \n " +
                "4. Internet \n " +
                "5. Telephone \n Press 0 to get the total.");

        Map<Integer,Double> produktCmim = new HashMap<>();
        produktCmim.put(1,100.0);
        produktCmim.put(2,200.0);
        produktCmim.put(3,300.0);
        produktCmim.put(4,150.0);
        produktCmim.put(5,400.0);
        Map<Integer,String> produktEmer = new HashMap<>();
        produktEmer.put(1,"TV 30 chan");
        produktEmer.put(2,"TV 40 chan");
        produktEmer.put(3,"TV 100 chan");
        produktEmer.put(4,"Internet");
        produktEmer.put(5,"Telephone");
        int produkti;
        int saHere;
        do
        {
            System.out.println("Pick a product (1-5)");
            produkti = console.nextInt();
            if(produkti==0)
                break;
            System.out.println("Quantity:");
            saHere= console.nextInt();
            if (produkti<=1&&produkti>=5) {
            System.out.println("Please, pick a number from 1 to 5");
            continue;
        }
            produktet.add(new ProduktIZgjedhur(produkti, saHere, produktCmim.get(produkti)));
            double total = produktCmim.get(produkti)*saHere;
            System.out.println(saHere+ " "+produktEmer.get(produkti)+ ", $"+total);
        }while (produkti!=0);


        int n1, n2, n3, n4;
        Scanner in = new Scanner(new FileReader("./input.txt"));

        Rules rules = new Rules(in.nextLine(), in.nextLine(), in.nextLine(), in.nextLine());
        n1 = rules.getRulesOrder().get(1);
        n2 = rules.getRulesOrder().get(2);
        n3 = rules.getRulesOrder().get(3);
        n4 = rules.getRulesOrder().get(4);
        Fatura fatura = new Fatura(produktet);
        System.out.println("Total before sales $" + fatura.getTotali() + ".");

        fatura.llogaritTotalin(n1, n2, n3, n4, rules);
        Main prove = new Main();
        prove.connect();
        //prove.krijoTab();
        prove.shtoData(fatura.getTotaliNeFillim(), fatura.getUljaPerfundimtare1(), fatura.getUljaPerfundimtare2(), fatura.getUljaPerfundimtare3(), fatura.getUljaPerfundimtare4(), fatura.getTotaliPara3(), fatura.getNumri(), fatura.getTotali());
        prove.afisho();

    }
}
