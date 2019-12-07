
package com.orlando.java.web;

import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author orlando 
 * [orlandogonz.dev@gmail.com]
 * [www.linkedin.com/in/orlandogonz]
 * [www.github.com/orlandogonz]
 */

//Todo lo que sea "/employee entrara a esta recurso
//La URI del recurso. Cuando se acceda a la url "/employees" accedemos a la clase EmployeesResource
@Path("/employees") //https:localhost:8080/employee-service-rest/api/employees
public class EmployeeResource {
    
    EmployeeDAO dao = new EmployeeDAO();
    
    public EmployeeResource(){
        super ();
    }
    
    @GET //GET consulta a los recursos
    
    //Que tipo de dato produce. Tendra un parametro que recibira un Application_Json
    @Produces({MediaType.APPLICATION_JSON})//Especifica los tipos de medios de Internet de respuesta
    public Response findAll(){       
        
        return Response.status(200).entity(this.dao.findAll()).build();
    }
    
    @GET //GET consulta a los recursos       
    @Path("{id}")//Definimos el parametro "id" y sera /employees/ el parametro a recibir
    //https:localhost:8080/employee-service-rest/api/employees/ [parametro]
    //https:localhost:8080/employee-service-rest/api/employees/id
    @Produces({MediaType.APPLICATION_JSON}) //Especifica los tipos de medios de Internet de respuesta. Recibira un Arrays en formato JSON
    public Response findById(@PathParam ("id") String id){
        
        return Response.status(200).entity(this.dao.findById(Integer.parseInt(id))).build();
    }
    
    @GET //GET consulta a los recursos
    @Path("{id}/reports")
    //https:localhost:8080/employee-service/api/employees/id/reports
    
    @Produces({MediaType.APPLICATION_JSON})//Especifica los tipos de medios de Internet de respuesta
    public Response findByManager(@PathParam("id") String managerId){
        
        return Response.status(Response.Status.OK).entity(this.dao.findByManager(Integer.parseInt(managerId))).build();
    }
    
    @POST //POST inserta nuevos recursos
    @Consumes({MediaType.APPLICATION_JSON})//Especifica los tipos de medios de Internet aceptados.    
    public Response add(Employee e) throws URISyntaxException{
        
        //Llamamos a EmployeesDAO y le pasamos el metodo create con "e" como parametro
        this.dao.create(e);
        
        //Voy a devolver el nuevo recurso generado
        return Response.created(new URI("/employee-service/api/employees" + e.getId())).build();
        
    }
    
    @PUT //PUT actualiza recursos
    @Path("/{id}") //Parametro "/[parametro]"
    @Consumes({MediaType.APPLICATION_JSON})//Especifica los tipos de medios de Internet aceptados.
    @Produces({MediaType.APPLICATION_JSON})    
    public Response update(@PathParam("id") Integer id, Employee e){
        
        if(this.dao.findById(id) == null){
            return Response.status(400).build();
        }
        
        e.setId(id);
        this.dao.update(e);
        
        return Response.status(200).entity(e).build();  
        
    }
    
    @DELETE //DELETE borra recursos
    @Path("/{id}") //Especifica la ruta relativa para una clase o método de recursos.
    public Response delete(@PathParam("id") Integer id){
        
        if(this.dao.findById(id) == null){
            return Response.status(400).build();            
        }
        
        this.dao.remove(this.dao.findById(id));
        return Response.status(204).entity("Employee " + id + " deleted successfully!").build();
    }

    
}
