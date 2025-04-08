package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ExceptionMessage;
import org.candlepin.dto.api.server.v1.OwnerDTO;
import org.candlepin.dto.api.server.v1.RoleDTO;
import org.candlepin.dto.api.server.v1.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/users")
@Api(description = "the Users API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface UsersApi {

    /**
     * Creates a User
     *
     * @param userDTO A user to be created
     * @return User successfuly created
     * @return User data is null or empty, username is not specified and similar errors
     * @return User already exists
     * @return An unexpected exception has occurred
     */
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates a User", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "User successfuly created", response = UserDTO.class),
        @ApiResponse(code = 400, message = "User data is null or empty, username is not specified and similar errors", response = ExceptionMessage.class),
        @ApiResponse(code = 409, message = "User already exists", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    UserDTO createUser(@Valid @NotNull UserDTO userDTO);


    /**
     * Removes a User
     *
     * @param username The username of the existing user
     * @return User was deleted successfuly
     * @return Username is null or empty
     * @return User not found
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{username}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a User", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "User was deleted successfuly", response = Void.class),
        @ApiResponse(code = 400, message = "Username is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "User not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteUser(@PathParam("username") @ApiParam("The username of the existing user") String username);


    /**
     * Retrieves a single User
     *
     * @param username The username of the existing user
     * @return A single user
     * @return Username is null or empty
     * @return User not found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{username}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single User", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A single user", response = UserDTO.class),
        @ApiResponse(code = 400, message = "Username is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "User not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    UserDTO getUserInfo(@PathParam("username") @ApiParam("The username of the existing user") String username);


    /**
     * Retrieves a list of Roles by User
     *
     * @param username The username of the existing user
     * @return A list of user roles
     * @return Username is null or empty
     * @return User not found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{username}/roles")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Roles by User", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of user roles", response = RoleDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Username is null or empty", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "User not found", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<RoleDTO> getUserRoles(@PathParam("username") @ApiParam("The username of the existing user") String username);


    /**
     * Retrieve a list of owners the user can register systems to
     *
     * @param username The username of the existing user
     * @return A list of user owners
     * @return Username is null or empty
     * @return User not found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{username}/owners")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieve a list of owners the user can register systems to", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of user owners", response = OwnerDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Username is null or empty", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "User not found", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<OwnerDTO> listUserOwners(@PathParam("username") @ApiParam("The username of the existing user") String username);


    /**
     * Retrieves a list of Users
     *
     * @return A list of users
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Users", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of users", response = UserDTO.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<UserDTO> listUsers();


    /**
     * Updates a User
     *
     * @param username The username of the existing user
     * @param userDTO The updated user
     * @return the updated user
     * @return Username is null or empty
     * @return User not found
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{username}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates a User", tags={ "users" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "the updated user", response = UserDTO.class),
        @ApiResponse(code = 400, message = "Username is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "User not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    UserDTO updateUser(@PathParam("username") @ApiParam("The username of the existing user") String username,@Valid @NotNull UserDTO userDTO);

}
