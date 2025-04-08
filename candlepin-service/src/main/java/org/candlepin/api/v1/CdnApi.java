package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.CdnDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/cdn")
@Api(description = "the Cdn API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface CdnApi {

    /**
     * Creates a new CDN
     *
     * @param cdnDTO CDN to be created
     * @return CDN successfully created
     * @return CDN label already exists
     * @return An unexpected exception has occurred
     */
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates a new CDN", tags={ "cdn" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "CDN successfully created", response = CdnDTO.class),
        @ApiResponse(code = 400, message = "CDN label already exists", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    CdnDTO createCdn(@Valid @NotNull CdnDTO cdnDTO);


    /**
     * Deletes a CDN
     *
     * @param label 
     * @return CDN successfully deleted
     * @return Multiple CDN instances found with the same label
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{label}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Deletes a CDN", tags={ "cdn" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "CDN successfully deleted", response = Void.class),
        @ApiResponse(code = 500, message = "Multiple CDN instances found with the same label", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void deleteCdn(@PathParam("label") String label);


    /**
     * Retrieves a list of CDN's
     *
     * @return A list of CDN's
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of CDN's", tags={ "cdn" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of CDN's", response = CdnDTO.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<CdnDTO> getContentDeliveryNetworks();


    /**
     * Updates a CDN
     *
     * @param label 
     * @param cdnDTO Fields that needs to be updated for specified CDN
     * @return CDN successfully updated
     * @return No such content delivery network
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{label}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates a CDN", tags={ "cdn" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "CDN successfully updated", response = CdnDTO.class),
        @ApiResponse(code = 404, message = "No such content delivery network", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    CdnDTO updateCdn(@PathParam("label") String label,@Valid @NotNull CdnDTO cdnDTO);

}
