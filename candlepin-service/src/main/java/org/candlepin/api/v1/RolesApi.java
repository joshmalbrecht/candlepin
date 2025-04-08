package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ExceptionMessage;
import org.candlepin.dto.api.server.v1.PermissionBlueprintDTO;
import org.candlepin.dto.api.server.v1.RoleDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/roles")
@Api(description = "the Roles API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface RolesApi {

    /**
     * Adds a Permission to a Role
     *
     * @param roleName Role name
     * @param permissionBlueprintDTO Permission Blueprint
     * @return Permission to a Role is added
     * @return Access type NONE not supported, or role name is null or empty
     * @return Role not found
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{role_name}/permissions")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Adds a Permission to a Role", tags={ "roles" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Permission to a Role is added", response = RoleDTO.class),
        @ApiResponse(code = 400, message = "Access type NONE not supported, or role name is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Role not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    RoleDTO addRolePermission(@PathParam("role_name") @ApiParam("Role name") String roleName,@Valid @NotNull PermissionBlueprintDTO permissionBlueprintDTO);


    /**
     * Adds a User to a Role
     *
     * @param roleName Role name
     * @param username User name
     * @return User added to the role
     * @return Either role or username is null or empty
     * @return Role or Username not found
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/{role_name}/users/{username}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Adds a User to a Role", tags={ "roles" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "User added to the role", response = RoleDTO.class),
        @ApiResponse(code = 400, message = "Either role or username is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Role or Username not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    RoleDTO addUserToRole(@PathParam("role_name") @ApiParam("Role name") String roleName,@PathParam("username") @ApiParam("User name") String username);


    /**
     * Creates a Role
     *
     * @param roleDTO A role to be created
     * @return Role successfully created.
     * @return Role is null or empty or role name not specified
     * @return Role already exists
     * @return An unexpected exception has occurred
     */
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates a Role", tags={ "roles" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Role successfully created.", response = RoleDTO.class),
        @ApiResponse(code = 400, message = "Role is null or empty or role name not specified", response = ExceptionMessage.class),
        @ApiResponse(code = 409, message = "Role already exists", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    RoleDTO createRole(@Valid @NotNull RoleDTO roleDTO);


    /**
     * Removes a Role
     *
     * @param roleName Role name
     * @return Role successfully deleted.
     * @return Role name is null or empty
     * @return Role not found
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{role_name}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a Role", tags={ "roles" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Role successfully deleted.", response = Void.class),
        @ApiResponse(code = 400, message = "Role name is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Role not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteRoleByName(@PathParam("role_name") @ApiParam("Role name") String roleName);


    /**
     * Removes a User from a Role
     *
     * @param roleName Role name
     * @param username User name
     * @return User from a role is removed
     * @return Either role or username is null or empty
     * @return Role or Username not found
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{role_name}/users/{username}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a User from a Role", tags={ "roles" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "User from a role is removed", response = RoleDTO.class),
        @ApiResponse(code = 400, message = "Either role or username is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Role or Username not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    RoleDTO deleteUserFromRole(@PathParam("role_name") @ApiParam("Role name") String roleName,@PathParam("username") @ApiParam("User name") String username);


    /**
     * Retrieves a single role by its name.
     *
     * @param roleName Role name
     * @return Returns a role
     * @return Role name is null or empty
     * @return Role not found
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{role_name}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single role by its name.", tags={ "roles" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns a role", response = RoleDTO.class),
        @ApiResponse(code = 400, message = "Role name is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Role not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    RoleDTO getRoleByName(@PathParam("role_name") @ApiParam("Role name") String roleName);


    /**
     * Retrieves a list of Roles
     *
     * @return A list of roles
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Roles", tags={ "roles" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of roles", response = RoleDTO.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<RoleDTO> getRoles();


    /**
     * Removes a Permission from a Role
     *
     * @param roleName Role name
     * @param permId Permission Id
     * @return Permission is removed from a role
     * @return Role name is null or empty
     * @return Role not found
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{role_name}/permissions/{perm_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes a Permission from a Role", tags={ "roles" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Permission is removed from a role", response = RoleDTO.class),
        @ApiResponse(code = 400, message = "Role name is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Role not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    RoleDTO removeRolePermission(@PathParam("role_name") @ApiParam("Role name") String roleName,@PathParam("perm_id") @ApiParam("Permission Id") String permId);


    /**
     * Updates Role. To avoid race conditions, we do not support updating the user or permission collections. Currently this call will only update the role name. See the specific nested POST/DELETE calls for modifying users and permissions.
     *
     * @param roleName Role name
     * @param roleDTO Role
     * @return Returns updated role
     * @return Role name is null or empty
     * @return Role not found
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{role_name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates Role. To avoid race conditions, we do not support updating the user or permission collections. Currently this call will only update the role name. See the specific nested POST/DELETE calls for modifying users and permissions.", tags={ "roles" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns updated role", response = RoleDTO.class),
        @ApiResponse(code = 400, message = "Role name is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Role not found", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    RoleDTO updateRole(@PathParam("role_name") @ApiParam("Role name") String roleName,@Valid @NotNull RoleDTO roleDTO);

}
