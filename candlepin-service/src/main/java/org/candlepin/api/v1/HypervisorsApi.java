package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.AsyncJobStatusDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/hypervisors/{owner}")
@Api(description = "the Hypervisors API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface HypervisorsApi {

    /**
     * Updates last check in date of all consumers of the given reporterId.
     *
     * @param owner Owner key
     * @param reporterId 
     * @return A successful operation
     * @return Provided reporter_id is absent or empty.
     * @return Owner with provided key was not found.
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/heartbeat")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates last check in date of all consumers of the given reporterId.", tags={ "hypervisors" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 400, message = "Provided reporter_id is absent or empty.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner with provided key was not found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO hypervisorHeartbeatUpdate(@PathParam("owner") @ApiParam("Owner key") String owner,@QueryParam("reporter_id")   String reporterId);


    /**
     * Creates or Updates the list of Hypervisor hosts Allows agents such as virt-who to update hosts' information . This is typically used when a host is unable to register to candlepin via subscription manager. In situations where consumers already exist it is probably best not to allow creation of new hypervisor consumers.  Most consumers do not have a hypervisorId attribute, so that should be added manually when necessary by the management environment. Default is true. If false is specified, hypervisorIds that are not found will result in a failed state of the job. 
     *
     * @param owner Owner key
     * @param createMissing Specify whether or not to create missing hypervisors. Default is true.  If false is specified, hypervisorIds that are not found will result in failed entries in the resulting HypervisorCheckInResult.
     * @param reporterId 
     * @param body Host and Guest mapping details
     * @return A successful operation
     * @return Host to guest mapping was not provided for hypervisor update.
     * @return Owner with provided key was not found.
     * @return An unexpected exception has occurred
     */
    @POST
    @Consumes({ "text/plain" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates or Updates the list of Hypervisor hosts Allows agents such as virt-who to update hosts' information . This is typically used when a host is unable to register to candlepin via subscription manager. In situations where consumers already exist it is probably best not to allow creation of new hypervisor consumers.  Most consumers do not have a hypervisorId attribute, so that should be added manually when necessary by the management environment. Default is true. If false is specified, hypervisorIds that are not found will result in a failed state of the job. ", tags={ "hypervisors" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 400, message = "Host to guest mapping was not provided for hypervisor update.", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Owner with provided key was not found.", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO hypervisorUpdateAsync(@PathParam("owner") @ApiParam("Owner key") String owner,@QueryParam("create_missing") @DefaultValue("true")  @ApiParam("Specify whether or not to create missing hypervisors. Default is true.  If false is specified, hypervisorIds that are not found will result in failed entries in the resulting HypervisorCheckInResult.")  Boolean createMissing,@QueryParam("reporter_id")   String reporterId,@Valid String body);

}
