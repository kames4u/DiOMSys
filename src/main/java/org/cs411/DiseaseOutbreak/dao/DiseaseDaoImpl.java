package org.cs411.DiseaseOutbreak.dao;

import org.cs411.DiseaseOutbreak.Model.Disease;

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

    public boolean updateDiseaseDetail(Disease disease) {

        JDBCConnection jdbcConnection = new JDBCConnection();
        Connection connection = jdbcConnection.getConnnection();

        System.out.println(disease.getDiseaseName());

        return true;
    }
}