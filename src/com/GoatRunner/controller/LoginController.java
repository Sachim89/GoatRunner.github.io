package com.GoatRunner.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.GoatRunner.exception.GoatRunnerException;
import com.GoatRunner.model.User;
import com.GoatRunner.services.LoginService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Path("/user")
public class LoginController {

	ObjectMapper mapper = new ObjectMapper();
	@Context
	private HttpServletRequest httpServletRequest;

	@Path("/login")
	@GET
	public Response loginUser(@QueryParam("userId") int studentId, @QueryParam("password") String password) {
		System.out.println("Entered");
		User user = new User();
		String responseObject = "";
		try {
			user = LoginService.login(studentId, password);
			HttpSession session = httpServletRequest.getSession(true);
			session.setAttribute(Integer.toString(studentId), user);
			session.setMaxInactiveInterval(420);
		} catch (GoatRunnerException e) {
			return Response.status(Status.BAD_REQUEST).build();
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
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

	@Path("/signup")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response signUp(String userString) {
		Gson g = new Gson();
		User user = g.fromJson(userString, User.class);
		System.out.println("Entered");
		try {
			LoginService.signup(user);
		} catch (GoatRunnerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).build();
	}

	@Path("/logout")
	@GET
	public Response logout(@QueryParam("userId") int userId) {
		String response = "";
		try {
			if (LoginService.userValidation(userId)) {

				response = "User successfully logged out";
				HttpSession session = httpServletRequest.getSession(false);
				session.removeAttribute(Integer.toString(userId));
				session.getMaxInactiveInterval();
			}
		} catch (SQLException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("User could not logout").build();
		} catch (GoatRunnerException e) {
			return Response.status(Status.BAD_REQUEST).entity("The user does not exist").build();
		}
		return Response.status(Status.OK).entity(response).build();
	}
}
