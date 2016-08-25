///////////////////////////////////////////
AmusementPark Documentation: REST endpoints
///////////////////////////////////////////

AmusementParkResource.java
---------------------------

@GET

/AmusementPark.Balazs.Horvath-web/webresources/park/{id}

/AmusementPark.Balazs.Horvath-web/webresources/park/{id}/visitors

/AmusementPark.Balazs.Horvath-web/webresources/park/{id}/machines

/AmusementPark.Balazs.Horvath-web/webresources/park/{id}/inactiveVisitors

/AmusementPark.Balazs.Horvath-web/webresources/park/{id}/inactiveMachines


@POST

/AmusementPark.Balazs.Horvath-web/webresources/park


@PUT
/AmusementPark.Balazs.Horvath-web/webresources/park/{id}

/AmusementPark.Balazs.Horvath-web/webresources/park/{id}/purchase/{machineId}

/AmusementPark.Balazs.Horvath-web/webresources/park/{id}/sell/{machineId}


@DELETE

/AmusementPark.Balazs.Horvath-web/webresources/park/{id}





MachineResource.java
---------------------

@GET

/AmusementPark.Balazs.Horvath-web/webresources/machine/{id}

/AmusementPark.Balazs.Horvath-web/webresources/machine/{id}/people


@POST

/AmusementPark.Balazs.Horvath-web/webresources/machine


@PUT

/AmusementPark.Balazs.Horvath-web/webresources/machine/{id}

/AmusementPark.Balazs.Horvath-web/webresources/machine/{id}/activate

/AmusementPark.Balazs.Horvath-web/webresources/machine/{id}/deactivate

/AmusementPark.Balazs.Horvath-web/webresources/machine/{id}/income


@DELETE

/AmusementPark.Balazs.Horvath-web/webresources/machine/{id}





VisitorResource.java
---------------------

@GET

/AmusementPark.Balazs.Horvath-web/webresources/visitor/{id}

/AmusementPark.Balazs.Horvath-web/webresources/visitor/{id}/reports


@POST

/AmusementPark.Balazs.Horvath-web/webresources/visitor

/AmusementPark.Balazs.Horvath-web/webresources/visitor/{id}/writeOpinion


@PUT

/AmusementPark.Balazs.Horvath-web/webresources/visitor/{id}/

/AmusementPark.Balazs.Horvath-web/webresources/visitor/{id}/enter/{parkId}

/AmusementPark.Balazs.Horvath-web/webresources/visitor/{id}/leave

/AmusementPark.Balazs.Horvath-web/webresources/visitor/{id}/try/{machineId}

/AmusementPark.Balazs.Horvath-web/webresources/visitor/{id}/getOff/{machineId}


@DELETE

/AmusementPark.Balazs.Horvath-web/webresources/visitor/{id}
