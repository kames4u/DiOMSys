package org.cs411.DiseaseOutbreak.dao;

import org.cs411.DiseaseOutbreak.Model.Disease;
import org.cs411.DiseaseOutbreak.Model.Virus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiseaseDaoImpl {

    public List getDiseaseDetails() {

        List diseaseData = new ArrayList<Disease>();

        JDBCConnection jdbcConnection = new JDBCConnection();

        Connection connection = jdbcConnection.getConnnection();

        try {

//            PreparedStatement ps = connection.prepareStatement(
//                    "SELECT * FROM outbreak.ETL_DiseaseInfo");

          //  PreparedStatement ps = connection.prepareStatement("select * FROM outbreak.DiseaseInfo");

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT d1.diseaseID, v1.virusSpecies as diseaseName, d1.occuredYear, d1.occuredMonth, " +
                            "d1.occuredCity, d1.occuredState, " +
                            "d1.noOfIllness, d1.noOfHospitalized, d1.noOfDeath, " +
                            "d1.dataSource, d1.category, 'Confirmed' as status " +
                            "FROM outbreak.DiseaseInfo d1, outbreak.Virus v1 " +
                            "WHERE d1.virusID = v1.virusID" );
                            //"WHERE diseaseID IN (1,55, 301, 67, 95, 104, 155)");

            System.out.println("Query :" + ps.toString());

            ResultSet rs = ps.executeQuery();

            System.out.println("Execution Success");

            while (rs.next()) {
                Disease disease = new Disease();
                disease.setDiseaseID(rs.getInt("diseaseID"));
                disease.setDiseaseName(rs.getString("diseaseName"));
                disease.setOccuredCity(rs.getString("occuredCity"));
                disease.setOccuredState(rs.getString("occuredState"));
                disease.setOccuredYear(rs.getInt("occuredYear"));
                disease.setOccuredMonth(rs.getInt("occuredMonth"));
                disease.setNoOfIllness(rs.getInt("noOfIllness"));
                disease.setNoOfHospitalized(rs.getInt("noOfHospitalized"));
                disease.setNoOfDeath(rs.getInt("noOfDeath"));
                disease.setDataSource(rs.getString("dataSource"));
                disease.setCategory(rs.getString("category"));
                disease.setStatus(rs.getString("status"));
                diseaseData.add(disease);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return diseaseData;
    }

    public List getVirusDetails() {

        List virusData = new ArrayList<Virus>();

        JDBCConnection jdbcConnection = new JDBCConnection();

        Connection connection = jdbcConnection.getConnnection();

        try {

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT v1.virusID, v1.virusSpecies, v1.avgOccurance " +
                            "FROM outbreak.Virus v1 " );

            System.out.println("Query :" + ps.toString());

            ResultSet rs = ps.executeQuery();

            System.out.println("Execution Success");

            while (rs.next()) {
                Virus virus = new Virus();
                virus.setVirusID(rs.getInt("virusID"));
                virus.setVirusName(rs.getString("virusSpecies"));
                virus.setAvgOccur(rs.getInt("avgOccurance"));
                virusData.add(virus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return virusData;
    }

    public boolean updateDiseaseDetail(Disease disease) {

        JDBCConnection jdbcConnection = new JDBCConnection();
        Connection connection = jdbcConnection.getConnnection();

        try{
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE outbreak.DiseaseInfo " +
                            "SET occuredYear = " + disease.getOccuredYear()  +
                            " , occuredMonth = " + disease.getOccuredMonth() +
                            " , occuredCity = '" + disease.getOccuredCity() +
                            "' , occuredState = '" + disease.getOccuredState()  +
                            "' , status = '" + disease.getStatus() +
                            "' , noOfIllness = " + disease.getNoOfIllness() +
                            " , noOfDeath = " + disease.getNoOfDeath()  +
                            " , noOfHospitalized = " + disease.getNoOfHospitalized() +
                            " WHERE diseaseID = " + disease.getDiseaseID() );

            System.out.println(ps.toString());
            int result = ps.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteDiseaseDetail(int diseaseId) {
        JDBCConnection jdbcConnection = new JDBCConnection();
        Connection connection = jdbcConnection.getConnnection();
        boolean result = false;

        try{
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM outbreak.DiseaseInfo " +
                            "WHERE diseaseID = " + diseaseId);

            System.out.println(ps.toString());
            result = ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public int createDisease(Disease disease) {

        JDBCConnection jdbcConnection = new JDBCConnection();
        Connection connection = jdbcConnection.getConnnection();
        int diseaseID = 0;

        try{
            PreparedStatement ps0 = connection.prepareStatement(
                    "Select virusID FROM outbreak.Virus " +
                            "WHERE virusSpecies = '" + disease.getDiseaseName() + "'");

            System.out.println(ps0.toString());

            ResultSet rs0  = ps0.executeQuery();
            int virusID = 0;
            while (rs0.next()){
                virusID = rs0.getInt("virusID");
            }

            System.out.println("VisusID " + virusID);

            PreparedStatement ps1 = connection.prepareStatement(
                    "Select Max(diseaseID) as maxDiseaseId FROM outbreak.DiseaseInfo");

            System.out.println(ps1.toString());

            ResultSet rs1  = ps1.executeQuery();

            while (rs1.next()){
                diseaseID = rs1.getInt("maxDiseaseId");
            }

            System.out.println("diseaseID " + diseaseID);

            if (diseaseID != 0){
                diseaseID = diseaseID + 1;
            }

            PreparedStatement ps2 = connection.prepareStatement("INSERT INTO outbreak.DiseaseInfo " +
                    "(`diseaseID`,`occuredYear`,`occuredMonth`," +
                    "`occuredCity`,`occuredState`,`status`," +
                    "`noOfIllness`,`noOfDeath`,`noOfHospitalized`," +
                    "`dataSource`,`category`,`virusID`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ");

            ps2.setInt(1, diseaseID);
            ps2.setInt(2, disease.getOccuredYear());
            ps2.setInt(3, disease.getOccuredMonth());
            ps2.setString(4, disease.getOccuredCity());
            ps2.setString(5, disease.getOccuredState());
            ps2.setString(6, disease.getStatus());
            ps2.setInt(7, disease.getNoOfIllness());
            ps2.setInt(8, disease.getNoOfDeath());
            ps2.setInt(9, disease.getNoOfHospitalized());
            ps2.setString(10, "UserReported");
            ps2.setString(11, "FoodBorne");
            ps2.setInt(12, virusID);

            System.out.println(ps2.toString());

            ps2.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return diseaseID;
    }
}