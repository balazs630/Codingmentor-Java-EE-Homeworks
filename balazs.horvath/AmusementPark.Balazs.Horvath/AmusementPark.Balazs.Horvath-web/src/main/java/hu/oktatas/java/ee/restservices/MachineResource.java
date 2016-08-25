package hu.oktatas.java.ee.restservices;

import hu.oktatas.java.ee.dao.amusementpark.AmusementPark;
import hu.oktatas.java.ee.dao.amusementpark.Machine;
import hu.oktatas.java.ee.dao.AmusementParkDao;
import hu.oktatas.java.ee.dao.MachineDao;
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
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CONFLICT;

@Stateless
@Path("/machine")
public class MachineResource {

    @EJB
    private MachineDao machineDao;

    @EJB
    private AmusementParkDao parkDao;

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getMachineById(@PathParam("id") Long id) {
        Machine machine = machineDao.find(id);
        if (null != machine) {
            return Response.ok(machine).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @GET
    @Path("/{id}/people")
    @Produces(APPLICATION_JSON)
    public Response getPeople(@PathParam("id") Long id) {
        Machine machine = machineDao.find(id);
        if (null != machine) {
            VisitorListDTO people = machineDao.findRiders(id);
            return Response.ok(people).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createMachine(Machine newMachine) {
        Machine machine = machineDao.find(newMachine.getId());
        if (null == machine) {
            Machine createdMachine = machineDao.create(newMachine);
            return Response.ok(createdMachine).build();
        }
        return Response.status(CONFLICT).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateMachine(@PathParam("id") Long id, Machine newMachine) {
        Machine machine = machineDao.find(id);
        if (null != machine) {
            machine.setName(newMachine.getName());
            machine.setAgeLimit(newMachine.getAgeLimit());
            machine.setIncome(newMachine.getIncome());
            machine.setMaxPeopleUsing(newMachine.getMaxPeopleUsing());
            machine.setMachinePrice(newMachine.getMachinePrice());
            machine.setSpaceRequired(newMachine.getSpaceRequired());
            machine.setTicketPrice(newMachine.getTicketPrice());
            machine.setType(newMachine.getType());
            Machine updatedMachine = machineDao.update(machine);
            return Response.ok(updatedMachine).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}/activate")
    @Produces(APPLICATION_JSON)
    public Response activateMachine(@PathParam("id") Long id) {
        Machine machine = machineDao.find(id);
        if (null != machine) {
            machine.setActive(true);
            Machine activatedMachine = machineDao.update(machine);
            return Response.ok(activatedMachine).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}/deactivate")
    @Produces(APPLICATION_JSON)
    public Response deactivateMachine(@PathParam("id") Long id) {
        Machine machine = machineDao.find(id);
        if (null != machine) {
            machine.setActive(false);
            Machine activatedMachine = machineDao.update(machine);
            return Response.ok(activatedMachine).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}/income")
    @Produces(APPLICATION_JSON)
    public Response transferIncome(@PathParam("id") Long id) {
        Machine machine = machineDao.find(id);
        if (null != machine && null != machine.getPark()) {
            AmusementPark park = machine.getPark();
            machine.transferIncomeToPark();
            parkDao.update(park);
            Machine transferingMachine = machineDao.update(machine);
            return Response.ok(transferingMachine).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteMachine(@PathParam("id") Long id) {
        Machine machine = machineDao.find(id);
        if (null != machine) {
            Machine deletedMachine = machineDao.delete(machine);
            return Response.ok(deletedMachine).build();
        }
        return Response.status(BAD_REQUEST).build();
    }
}
