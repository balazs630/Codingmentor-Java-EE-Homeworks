package hu.oktatas.java.ee.restservices;

import hu.oktatas.java.ee.amusementpark.AmusementPark;
import hu.oktatas.java.ee.guestbook.GuestBook;
import hu.oktatas.java.ee.amusementpark.Machine;
import hu.oktatas.java.ee.guestbook.Opinion;
import hu.oktatas.java.ee.visitor.Visitor;
import hu.oktatas.java.ee.dao.AmusementParkDao;
import hu.oktatas.java.ee.dao.GuestBookDao;
import hu.oktatas.java.ee.dao.MachineDao;
import hu.oktatas.java.ee.dao.ReportDao;
import hu.oktatas.java.ee.dao.VisitorDao;
import hu.oktatas.java.ee.exceptions.ParkIsFullException;
import hu.oktatas.java.ee.exceptions.VisitorAgeLimitException;
import hu.oktatas.java.ee.dto.ReportListDTO;
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
@Path("/visitor")
public class VisitorResource {

    @EJB
    private VisitorDao visitorDao;

    @EJB
    private MachineDao machineDao;

    @EJB
    private AmusementParkDao parkDao;

    @EJB
    private GuestBookDao bookDao;

    @EJB
    private ReportDao reportDao;

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getVisitorById(@PathParam("id") Long id) {
        Visitor visitor = visitorDao.find(id);
        if (null != visitor) {
            return Response.ok(visitor).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @GET
    @Path("/{id}/reports")
    @Produces(APPLICATION_JSON)
    public Response getReportsOfPark(@PathParam("id") Long id) {
        Visitor visitor = visitorDao.find(id);
        if (null != visitor && null != visitor.getPark()) {
            ReportListDTO reports = reportDao.findReportsOfVisitorWrittenInPark(id, visitor.getPark().getId());
            return Response.ok(reports).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createNewVisitor(Visitor newVisitor) {
        Visitor visitor = visitorDao.find(newVisitor.getId());
        if (null == visitor) {
            Visitor createdVisitor = visitorDao.create(newVisitor);
            return Response.ok(createdVisitor).build();
        }
        return Response.status(CONFLICT).build();
    }

    @POST
    @Path("/{id}/writeOpinion")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response writeToGuestBook(@PathParam("id") Long id, Opinion newReport) {
        Visitor visitor = visitorDao.find(id);
        Opinion report = reportDao.find(newReport.getId());
        if (null != visitor && null != visitor.getPark() && null == report) {
            Opinion createdReport = reportDao.create(newReport);
            GuestBook book = visitor.getPark().getGuestBook();
            visitor.addReportToGuestBook(createdReport);
            bookDao.update(book);
            Visitor writingVisitor = visitorDao.update(visitor);
            return Response.ok(writingVisitor).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateVisitor(@PathParam("id") Long id, Visitor newVisitor) {
        Visitor visitor = visitorDao.find(id);
        if (null != visitor) {
            visitor.setName(newVisitor.getName());
            visitor.setAge(newVisitor.getAge());
            visitor.setMoney(newVisitor.getMoney());
            Visitor updatedVisitor = visitorDao.update(visitor);
            return Response.ok(updatedVisitor).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}/enter/{parkId}")
    @Produces(APPLICATION_JSON)
    public Response enterPark(@PathParam("id") Long id, @PathParam("parkId") Long parkId) throws ParkIsFullException {
        Visitor visitor = visitorDao.find(id);
        AmusementPark park = parkDao.find(parkId);
        if (null != visitor && null != park) {
            visitor.enterAmusementPark(park);
            parkDao.update(park);
            Visitor enteredVisitor = visitorDao.update(visitor);
            return Response.ok(enteredVisitor).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}/leave")
    @Produces(APPLICATION_JSON)
    public Response leavePark(@PathParam("id") Long id) {
        Visitor visitor = visitorDao.find(id);
        if (null != visitor) {
            AmusementPark park = visitor.getPark();
            visitor.leaveAmusementPark(park);
            parkDao.update(park);
            Visitor leavedVisitor = visitorDao.update(visitor);
            return Response.ok(leavedVisitor).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}/try/{machineId}")
    @Produces(APPLICATION_JSON)
    public Response rideMachine(@PathParam("id") Long id, @PathParam("machineId") Long machineId) throws VisitorAgeLimitException {
        Visitor visitor = visitorDao.find(id);
        Machine machine = machineDao.find(machineId);
        if (null != visitor && null != machine) {
            visitor.rideMachine(machine);
            machineDao.update(machine);
            Visitor ridingVisitor = visitorDao.update(visitor);
            return Response.ok(ridingVisitor).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}/getOff/{machineId}")
    @Produces(APPLICATION_JSON)
    public Response getOffMachine(@PathParam("id") Long id, @PathParam("machineId") Long machineId) {
        Visitor visitor = visitorDao.find(id);
        Machine machine = machineDao.find(machineId);
        if (null != visitor && null != machine) {
            visitor.getOffMachine(machine);
            machineDao.update(machine);
            Visitor restingVisitor = visitorDao.update(visitor);
            return Response.ok(restingVisitor).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteVisitorById(@PathParam("id") Long id) {
        Visitor visitor = visitorDao.find(id);
        if (visitor != null) {
            Visitor deletedVisitor = visitorDao.delete(visitor);
            return Response.ok(deletedVisitor).build();
        }
        return Response.status(BAD_REQUEST).build();
    }
}
