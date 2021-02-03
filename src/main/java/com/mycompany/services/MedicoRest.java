package com.mycompany.services;

import com.mycompany.entidades.Medico;
import com.mycompany.session.MedicoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("Medico")
public class MedicoRest {

    @EJB
    private MedicoFacade medicoFacade;

    @GET
    @Produces((MediaType.APPLICATION_JSON))
    public List<Medico> findAll() {
        return medicoFacade.findAll();
    }

    @GET
    @Produces((MediaType.APPLICATION_JSON))
    @Path("{id}")
    public Medico findbyId(@PathParam("id") Integer id) {
        return medicoFacade.find(id);
    }

    @DELETE
    @Consumes((MediaType.APPLICATION_JSON))
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        medicoFacade.remove(medicoFacade.find(id));
        return Response.ok("DATO ELIMINADO", MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes((MediaType.APPLICATION_JSON))
    public Response create(Medico medico) {
        medicoFacade.create(medico);
        return Response.ok("DATO INGRESADO", MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Consumes((MediaType.APPLICATION_JSON))
    @Path("{id}")
    public Response edit(@PathParam("id") Integer id, Medico medico) {
        Medico newMedico = medicoFacade.find(id);
        newMedico.setApellido(medico.getApellido());
        newMedico.setNombre(medico.getNombre());
        newMedico.setDireccion(medico.getDireccion());
        newMedico.setEdad(medico.getEdad());
        newMedico.setEmail(medico.getEmail());
        newMedico.setEspecialidad(medico.getEspecialidad());
        newMedico.setFechaIngreso(medico.getFechaIngreso());
        newMedico.setFoto(medico.getFoto());
        newMedico.setTelefono(medico.getTelefono());
        medicoFacade.edit(newMedico);
        return Response.ok("DATOS ACTUALIZADOS", MediaType.APPLICATION_JSON).build();
    }

}
