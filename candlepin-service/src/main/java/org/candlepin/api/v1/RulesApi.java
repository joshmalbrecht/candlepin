package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ExceptionMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/rules")
@Api(description = "the Rules API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface RulesApi {

    /**
     * Retrieves the Rules
     *
     * @return Returns the rules
     * @return No rules file found in the database
     * @return Could not read rules file
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "text/plain", "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the Rules", tags={ "rules" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns the rules", response = String.class),
        @ApiResponse(code = 404, message = "No rules file found in the database", response = ExceptionMessage.class),
        @ApiResponse(code = 503, message = "Could not read rules file", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    String getRules();

}
