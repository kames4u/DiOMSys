package org.cs411.DiseaseOutbreak.Model;

public class Disease {

    private int diseaseID;
    private String diseaseName;
    private int occuredYear;
    private int occuredMonth;
    private String occuredCity;
    private String occuredState;
    private String status;
    private int noOfIllness;
    private int noOfDeath;
    private int noOfHospitalized;
    private String dataSource;
    private String category;

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getOccuredYear() {
        return occuredYear;
    }

    public void setOccuredYear(int occuredYear) {
        this.occuredYear = occuredYear;
    }

    public int getOccuredMonth() {
        return occuredMonth;
    }

    public void setOccuredMonth(int occuredMonth) {
        this.occuredMonth = occuredMonth;
    }

    public String getOccuredCity() {
        return occuredCity;
    }

    public void setOccuredCity(String occuredCity) {
        this.occuredCity = occuredCity;
    }

    public String getOccuredState() {
        return occuredState;
    }

    public void setOccuredState(String occuredState) {
        this.occuredState = occuredState;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNoOfIllness() {
        return noOfIllness;
    }

    public void setNoOfIllness(int noOfIllness) {
        this.noOfIllness = noOfIllness;
    }

    public int getNoOfDeath() {
        return noOfDeath;
    }

    public void setNoOfDeath(int noOfDeath) {
        this.noOfDeath = noOfDeath;
    }

    public int getNoOfHospitalized() {
        return noOfHospitalized;
    }

    public void setNoOfHospitalized(int noOfHospitalized) {
        this.noOfHospitalized = noOfHospitalized;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
