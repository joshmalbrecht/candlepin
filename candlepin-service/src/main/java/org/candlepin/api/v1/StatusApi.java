package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ExceptionMessage;
import org.candlepin.dto.api.server.v1.StatusDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/status")
@Api(description = "the Status API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface StatusApi {

    /**
     * Returns status of the server
     *
     * @return Status information about running Candlepin server
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Returns status of the server", tags={ "status" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status information about running Candlepin server", response = StatusDTO.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    StatusDTO status();

}
