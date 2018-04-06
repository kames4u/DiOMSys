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
                    "SELECT diseaseName, occuredYear, occuredMonth, " +
                            "occuredCity, occuredState, status, noOfIllness, noOfDeath, " +
                            "noOfHospitalized, dataSource, category " +
                            "FROM outbreak.ETL_DiseaseInfo " +
                            "WHERE diseaseID IN (1,55, 301, 67, 95, 104, 155)");

            System.out.println("Query :" + ps.toString());

            ResultSet rs = ps.executeQuery();

            System.out.println("Execution Success");

            while (rs.next()) {
                Disease disease = new Disease();
                disease.setDiseaseName(rs.getString("diseaseName"));
                disease.setOccuredState(rs.getString("occuredState"));

                diseaseData.add(disease);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return diseaseData;
    }

}