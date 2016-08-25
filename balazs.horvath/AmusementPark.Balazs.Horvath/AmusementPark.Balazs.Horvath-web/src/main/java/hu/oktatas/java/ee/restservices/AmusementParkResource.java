package hu.oktatas.java.ee.restservices;

import hu.oktatas.java.ee.dao.amusementpark.AmusementPark;
import hu.oktatas.java.ee.dao.amusementpark.Machine;
import hu.oktatas.java.ee.dao.AmusementParkDao;
import hu.oktatas.java.ee.dao.MachineDao;
import hu.oktatas.java.ee.exceptions.MachineNotAffordableException;
import hu.oktatas.java.ee.dto.MachineListDTO;
import hu.oktatas.java.ee.dto.VisitorListDTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Stateless
@Path("/park")
public class AmusementParkResource {

    @EJB
    private AmusementParkDao parkDao;

    @EJB
    private MachineDao machineDao;

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getParkById(@PathParam("id") Long id) {
        AmusementPark park = parkDao.find(id);
        if (null != park) {
            return Response.ok(park).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @GET
    @Path("/{id}/visitors")
    @Produces(APPLICATION_JSON)
    public Response getVisitors(@PathParam("id") Long id) {
        AmusementPark park = parkDao.find(id);
        if (null != park) {
            VisitorListDTO visitors = parkDao.findVisitors(id);
            return Response.ok(visitors).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @GET
    @Path("/{id}/machines")
    @Produces(APPLICATION_JSON)
    public Response getMechines(@PathParam("id") Long id) {
        AmusementPark park = parkDao.find(id);
        if (null != park) {
            MachineListDTO machines = parkDao.findMachines(id);
            return Response.ok(machines).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @GET
    @Path("/{id}/inactiveVisitors")
    @Produces(TEXT_PLAIN)
    public Response getVisitorsOnRestState(@PathParam("id") Long id) {
        AmusementPark park = parkDao.find(id);
        if (null != park) {
            Long count = parkDao.countVisitorsInRestState(id);
            return Response.ok(count).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @GET
    @Path("/{id}/inactiveMachines")
    @Produces(TEXT_PLAIN)
    public Response getNumberOfInactiveMachines(@PathParam("id") Long id) {
        AmusementPark park = parkDao.find(id);
        if (null != park) {
            Long count = parkDao.countInactiveMachines(id);
            return Response.ok(count).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createNewPark(AmusementPark newPark) {
        AmusementPark park = parkDao.find(newPark.getId());
        if (null == park) {
            AmusementPark createdPark = parkDao.create(newPark);
            return Response.ok(createdPark).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateParkById(@PathParam("id") Long id, AmusementPark newPark) {
        AmusementPark park = parkDao.find(id);
        if (null != park) {
            park.setName(newPark.getName());
            park.setAddress(newPark.getAddress());
            park.setSpaceRequired(newPark.getSpaceRequired());
            park.setEntranceFee(newPark.getEntranceFee());
            park.setFund(newPark.getFund());
            AmusementPark updatedPark = parkDao.update(park);
            return Response.ok(updatedPark).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}/purchase/{machineId}")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response buyNewMachine(@PathParam("id") Long id, @PathParam("machineId") Long machineId) throws MachineNotAffordableException {
        AmusementPark park = parkDao.find(id);
        Machine machine = machineDao.find(machineId);
        if (null != park && null != machine) {
            park.buyNewMachine(machine);
            machineDao.update(machine);
            AmusementPark expandedPark = parkDao.update(park);
            return Response.ok(expandedPark).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}/sell/{machineId}")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response sellOldMachine(@PathParam("id") Long id, @PathParam("machineId") Long machineId) {
        AmusementPark park = parkDao.find(id);
        Machine machine = machineDao.find(machineId);
        if (null != park && null != machine) {
            park.removeOldMachine(machine);
            machineDao.update(machine);
            AmusementPark remainedPark = parkDao.update(park);
            return Response.ok(remainedPark).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteParkById(@PathParam("id") Long id) {
        AmusementPark park = parkDao.find(id);
        if (null != park) {
            AmusementPark deletedPark = parkDao.delete(park);
            return Response.ok(deletedPark).build();
        }
        return Response.status(BAD_REQUEST).build();
    }
}
