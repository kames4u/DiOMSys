package org.cs411.DiseaseOutbreak.Resource;

import com.google.gson.Gson;
import org.cs411.DiseaseOutbreak.dao.DiseaseDaoImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/virus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VirusResource {

    @GET
    public Response getVirus() {
        String virusDetails = null;
        List virusList = null;

        DiseaseDaoImpl diseaseDaoImpl = new DiseaseDaoImpl();

        virusList = diseaseDaoImpl.getVirusDetails();

        Gson gson = new Gson();
        virusDetails = gson.toJson(virusList);
        //return diseaseDetails;

        return Response.ok()
                .entity(virusDetails)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
                .allow("OPTIONS").build();
    }

}