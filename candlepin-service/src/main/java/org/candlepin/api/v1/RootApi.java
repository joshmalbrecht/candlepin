package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.ExceptionMessage;
import org.candlepin.dto.api.server.v1.Link;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("")
@Api(description = "the Root API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface RootApi {

    /**
     * Retrieves a list of links corresponding to Root resources
     *
     * @return OK
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of links corresponding to Root resources", tags={ "root" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Link.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    Iterable<Link> getRootResources();

}
