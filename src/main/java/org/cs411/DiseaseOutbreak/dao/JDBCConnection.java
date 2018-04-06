package org.cs411.DiseaseOutbreak.dao;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {

    static int lport;
    static String rhost;
    static int rport;
    static Properties prop = new Properties();
    static InputStream input = null;

    static {
        try{
            go();
            System.out.println("SSH Connection");
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void go(){

        loadProperties();

        // get the property value and print it out
        String user = prop.getProperty("sshuser");
        String password = prop.getProperty("sshpassword");
        String host = prop.getProperty("sshhost");
        int port=22;
        try
        {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            lport = 4321;
            rhost = "localhost";
            rport = 3306;
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
        }
        catch(Exception e){System.err.print(e);}
    }

    public static void loadProperties() {

        InputStream input = null;

        try {

            String filename = "config.properties";
            input = JDBCConnection.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection getConnnection() {
        Connection connection = null;

        try {
            String connectionURL = "jdbc:mysql://" + rhost +":" + lport + "/" + "outbreak";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, prop.getProperty("dbuser"),
                    prop.getProperty("dbpassword"));

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed");
            System.out.println(e.getLocalizedMessage());

        }
        return connection;
    }
}