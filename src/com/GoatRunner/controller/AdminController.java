package com.GoatRunner.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.CurrentRides;
import com.GoatRunner.services.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/admin")
public class AdminController {

	ObjectMapper mapper = new ObjectMapper();


	/**
	 * 
	 * @param admin
	 * @return
	 */
	@Path("/view")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })

	public Response adminView(@QueryParam("cabNo") Integer cabNo) {
		System.out.println("Entered cab details");
		String responseObject = "";
		List<CurrentRides> list = new ArrayList<CurrentRides>();
		try {
			list = AdminService.cancel(cabNo);
			responseObject = mapper.writeValueAsString(list);
		} catch (GoatRunnerException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(Status.OK).entity(responseObject).build();
	}

}
