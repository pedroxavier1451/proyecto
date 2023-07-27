package ec.edu.ups.proyecto.servicios;

import java.util.List;

import ec.edu.ups.proyecto.modelo.Vehiculo;
import ec.edu.ups.proyecto.negocio.GestionVehiculos;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("Vehiculo")

public class GVehiculoService {
	
	@Inject GestionVehiculos gestionVehiculo;
	
	//guardar vehiculos
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response saveVehiculo(Vehiculo v) {
		try {
			gestionVehiculo.guardarVehiculo(v);
			return Response.status(Response.Status.OK).entity(v).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			Error error = new Error();
			error.setCodigo(99);
			error.setMensaje("Error al guardar: " + e.getMessage());
			return Response.status(Response.Status.OK).entity(error).build();
		}
	}
	
	//listar los vehiculos
	
	@GET
	@Path("vehiculosLista")
	@Produces("application/json")
	public Response getVehiculo() {
		List<Vehiculo> lista=gestionVehiculo.getVehiculos();
		return Response.status(Response.Status.OK).entity(lista).build();
	}
	
	@DELETE
	@Path("delete/{placa}")
	public Response deleteVehiculo(@PathParam("placa") String placa)
	{
		try {
			gestionVehiculo.delete(placa);
			return Response.status(Response.Status.OK).build();
			
		}catch (Exception e) {
			var error = new Error();
			error.setCodigo(98);
			error.setMensaje("Error al eliminar: "+e.getMessage());
			return Response.status(Response.Status.OK).entity(error).build();
		}
	}

}
