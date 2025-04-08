package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ExceptionMessage;
import org.candlepin.dto.api.server.v1.QueueStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/admin")
@Api(description = "the Admin API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface AdminApi {

    /**
     * Basic information on the ActiveMQ queues and how many messages are pending in each.
     *
     * @return Queue stats successfully retrieved.
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/queues")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Basic information on the ActiveMQ queues and how many messages are pending in each.", tags={ "admin" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Queue stats successfully retrieved.", response = QueueStatus.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    Iterable<QueueStatus> getQueueStats();


    /**
     * Deprecated. Formerly used to finish deployment by creating a default admin user in select environments, but no longer performs any action. Will be removed entirely in a future release. 
     *
     * @return always returns a 200
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/init")
    @Produces({ "text/plain", "application/json" })
    @ApiOperation(value = "", notes = "Deprecated. Formerly used to finish deployment by creating a default admin user in select environments, but no longer performs any action. Will be removed entirely in a future release. ", tags={ "admin" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "always returns a 200", response = String.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    String initialize();

}
