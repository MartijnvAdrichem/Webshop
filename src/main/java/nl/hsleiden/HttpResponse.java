package nl.hsleiden;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.Collections.singletonMap;

public class HttpResponse {

	Response.Status status;
	String message;

	public HttpResponse(Response.Status status, String message ){
		this.status = status;
		this.message = message;

	}

	public Response send(){
		String[] messages =  new String[1];
		messages[0] = message;

		return Response
				.status(status.getStatusCode())
				.type(MediaType.APPLICATION_JSON)
				.entity(singletonMap("errors", messages))
				.build();
	}


}
