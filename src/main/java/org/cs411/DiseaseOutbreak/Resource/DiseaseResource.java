package org.cs411.DiseaseOutbreak.Resource;

import com.google.gson.Gson;
import org.cs411.DiseaseOutbreak.dao.DiseaseDaoImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/disease")
public class DiseaseResource {

    @GET
    //@Path("/diseasedetails")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDisease() {
        String diseaseDetails = null;
        List diseaseList = null;

        DiseaseDaoImpl diseaseDaoImpl = new DiseaseDaoImpl();

        diseaseList = diseaseDaoImpl.getDiseaseDetails();

        Gson gson = new Gson();
        diseaseDetails = gson.toJson(diseaseList);
        return diseaseDetails;
    }

}