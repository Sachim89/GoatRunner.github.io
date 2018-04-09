package com.GoatRunner.controller;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONException;
import org.json.JSONObject;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.BookingDetails;
import com.GoatRunner.services.BookingOtherService;
import com.GoatRunner.services.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * This class handles booking requests for booking and cancellation
 * 
 * @author Apoorva
 *
 */
@Path("/ride")
public class BookingController {
	
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * 
	 * @param booking
	 * @return
	 */
	@Path("/book")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response bookARide(String bookingDetailsString) {
		System.out.println("Entered book");
		String responseObject = "";
		BookingDetails bookingDetails = new BookingDetails();
		try {

			JSONObject bookingRequestDetails = new JSONObject(bookingDetailsString);
			bookingDetails = BookingService.book(bookingRequestDetails);
			responseObject = mapper.writeValueAsString(bookingDetails);
		} catch (GoatRunnerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(responseObject).build();
	}

	@Path("/cancel")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })

	public Response cancellingARide(@QueryParam("bookingId") Integer bookingId) {

		System.out.println("Entered");
		try {
			BookingOtherService.cancel(bookingId);
		} catch (GoatRunnerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).build();
	}

	@Path("/complete")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response completingARide(@QueryParam("bookingId") Integer bookingId) {

		System.out.println("Entered");
		try {
			BookingOtherService.complete(bookingId);
		} catch (GoatRunnerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).build();
	}

}
