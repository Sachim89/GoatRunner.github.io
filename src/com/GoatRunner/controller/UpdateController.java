package com.GoatRunner.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.User;
import com.GoatRunner.services.StudentUpdateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * This class handles requests for updating student details, 
 * updating student password
 * 
 * @author Rishitha
 *
 */
@Path("/update")
public class UpdateController {
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Path("/update_details")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateInfo(String userString) {
		Gson g = new Gson();
		User user = g.fromJson(userString, User.class);
		System.out.println("Entered");
		String responseObject = "";
		try {
			user=StudentUpdateService.updateInfo(user);
		}  catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Student details could not be updated").build();
		}
			try {
				responseObject = mapper.writeValueAsString(user);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return Response.status(Status.OK).entity(responseObject).build();
		
		}
	
	@Path("/update_password")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updatePassword(@QueryParam("userId") int studentId, @QueryParam("password") String password) {
		System.out.println("Entered");
		String response = "";
		try {
			StudentUpdateService.updatePassword(studentId, password);
				response = "Password successfully changed";
		}  catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Password could not be changed").build();
		}
		
			return Response.status(Status.OK).entity(response).build();
		
		}

}

