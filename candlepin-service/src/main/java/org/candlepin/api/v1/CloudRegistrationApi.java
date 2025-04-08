package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.CloudAuthenticationResultDTO;
import org.candlepin.dto.api.server.v1.CloudRegistrationDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/cloud")
@Api(description = "the CloudRegistration API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface CloudRegistrationApi {

    /**
     * Cancels all jobs associated with the specified cloud account ID (for testing purpose only)
     *
     * @param cloudAccountId The ID of the cloud account
     * @return List of ids for jobs associated with the cloud account id that were cancelled.
     * @return Cloud account ID is null or empty
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/jobs/orgsetup/{cloud_account_id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Cancels all jobs associated with the specified cloud account ID (for testing purpose only)", tags={ "cloud_registration" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of ids for jobs associated with the cloud account id that were cancelled.", response = String.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Cloud account ID is null or empty", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    List<String> cancelCloudAccountJobs(@PathParam("cloud_account_id") @ApiParam("The ID of the cloud account") String cloudAccountId);


    /**
     * Verifies provided cloud registration data and returns an authentication token. By default the token is valid for 10 minutes
     *
     * @param cloudRegistrationDTO Cloud registration data
     * @param version Version of cloud authentication
     * @return Cloud authorization successful. When the version of authorization requested is 2, application/json is returned that contains a JWT token amongst other data. Otherwise, only a JWT token in text/plain format is returned.
     * @return CloudRegistrationInfo is null
     * @return Cloud provider or account details could not be resolved to an organization
     * @return Cloud registration is not supported by this Candlepin instance
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/authorize")
    @Consumes({ "application/json" })
    @Produces({ "text/plain", "application/json" })
    @ApiOperation(value = "", notes = "Verifies provided cloud registration data and returns an authentication token. By default the token is valid for 10 minutes", tags={ "cloud_registration" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Cloud authorization successful. When the version of authorization requested is 2, application/json is returned that contains a JWT token amongst other data. Otherwise, only a JWT token in text/plain format is returned.", response = String.class),
        @ApiResponse(code = 400, message = "CloudRegistrationInfo is null", response = ExceptionMessage.class),
        @ApiResponse(code = 401, message = "Cloud provider or account details could not be resolved to an organization", response = ExceptionMessage.class),
        @ApiResponse(code = 501, message = "Cloud registration is not supported by this Candlepin instance", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    javax.ws.rs.core.Response cloudAuthorize(@Valid @NotNull CloudRegistrationDTO cloudRegistrationDTO,@QueryParam("version")  @ApiParam("Version of cloud authentication")  Integer version);


    /**
     * Removes Anonymous Consumers (for testing purposes only)
     *
     * @param cloudAccountId The ID of the Cloud Account
     * @return A successful operation.
     * @return Anonymous Consumers could not be deleted due to insufficient permissions.
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/consumers/anonymous")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Removes Anonymous Consumers (for testing purposes only)", tags={ "cloud_registration" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "A successful operation.", response = Void.class),
        @ApiResponse(code = 403, message = "Anonymous Consumers could not be deleted due to insufficient permissions.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteAnonymousConsumersByAccountId(@QueryParam("cloud_account_id") @NotNull  @ApiParam("The ID of the Cloud Account")  String cloudAccountId);

}
