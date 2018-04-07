package org.cs411.DiseaseOutbreak.Resource;

import com.google.gson.Gson;
import org.cs411.DiseaseOutbreak.Model.Disease;
import org.cs411.DiseaseOutbreak.dao.DiseaseDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disease")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DiseaseResource {

    @GET
    public Response getDisease() {
        String diseaseDetails = null;
        List diseaseList = null;

        DiseaseDaoImpl diseaseDaoImpl = new DiseaseDaoImpl();

        diseaseList = diseaseDaoImpl.getDiseaseDetails();

        Gson gson = new Gson();
        diseaseDetails = gson.toJson(diseaseList);
        //return diseaseDetails;

        return Response.ok()
                .entity(diseaseDetails)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
                .allow("OPTIONS").build();
    }

    @Path("/{diseaseId}")
    @PUT
    public Response updateDisease(@PathParam("diseaseId") final int diseaseId, Disease disease) {
        String diseaseDetails = " ";

        DiseaseDaoImpl diseaseDaoImpl = new DiseaseDaoImpl();

       boolean result = diseaseDaoImpl.updateDiseaseDetail(disease);

        Gson gson = new Gson();
        diseaseDetails = gson.toJson(disease);

        return Response.ok()
                .entity(diseaseDetails)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
                .allow("OPTIONS").build();
    }

    @Path("/{diseaseId}")
    @DELETE
    public Response deleteDisease(@PathParam("diseaseId") final int diseaseId, Disease disease) {
        String diseaseDetails = " ";

        System.out.println("Into OPTIONS" + disease.getOccuredState());

        return Response.ok()
                .entity(diseaseDetails)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
                .allow("OPTIONS").build();
    }

}