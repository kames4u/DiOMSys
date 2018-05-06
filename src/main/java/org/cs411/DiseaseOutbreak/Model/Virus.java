package org.cs411.DiseaseOutbreak.Model;

public class Virus {

    private int virusID;
    private String virusName;
    private int avgOccur;

    public int getVirusID() {
        return virusID;
    }

    public void setVirusID(int virusID) {
        this.virusID = virusID;
    }

    public String getVirusName() {
        return virusName;
    }

    public void setVirusName(String virusName) {
        this.virusName = virusName;
    }

    public int getAvgOccur() {
        return avgOccur;
    }

    public void setAvgOccur(int avgOccur) {
        this.avgOccur = avgOccur;
    }
}
