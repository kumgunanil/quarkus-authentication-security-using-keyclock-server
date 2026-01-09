package com.anilit;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class Resource {

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @RolesAllowed({"student", "professor", "admin"})
    @Path("/course_list")
    @Produces(MediaType.TEXT_PLAIN)

    public Response getCourseList(){
        return Response.ok("Course List: CS ME EE. Logged user name: " +
                securityIdentity.getPrincipal().getName()).build();
    }

    @GET
    @RolesAllowed({"professor", "admin"})
    @Path("/add_course")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addCourse(){
        return Response.ok("Added Course: CE" +
                securityIdentity).build();
    }

    @GET
    @RolesAllowed({"admin"})
    @Path("/update_course")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateCourse(){
        return Response.ok("updated Course: CSE" +
                securityIdentity).build();
    }

    @GET
    @Path("/delete_course")
    @RolesAllowed({"admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourse(){
        return Response.ok("deleted Course: ME" +
                securityIdentity).build();
    }
}
