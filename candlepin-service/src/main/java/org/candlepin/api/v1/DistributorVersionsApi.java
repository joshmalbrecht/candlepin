package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.DistributorVersionDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/distributor_versions")
@Api(description = "the DistributorVersions API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface DistributorVersionsApi {

    /**
     * Creates a Distributor Version
     *
     * @param distributorVersionDTO A new distributor version to be created
     * @return A successful operation
     * @return A distributor version already exists
     * @return An unexpected exception has occurred
     */
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Creates a Distributor Version", tags={ "distributor_versions" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = DistributorVersionDTO.class),
        @ApiResponse(code = 400, message = "A distributor version already exists", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    DistributorVersionDTO create(@Valid @NotNull DistributorVersionDTO distributorVersionDTO);


    /**
     * Deletes a Distributor Version
     *
     * @param id 
     * @return A successful operation
     * @return Distributor version could not be found using the provided ID
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Deletes a Distributor Version", tags={ "distributor_versions" })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "A successful operation", response = Void.class),
        @ApiResponse(code = 404, message = "Distributor version could not be found using the provided ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    void delete(@PathParam("id") String id);


    /**
     * Retrieves a list of Distributor Versions
     *
     * @param nameSearch 
     * @param capability 
     * @return A successful operation
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of Distributor Versions", tags={ "distributor_versions" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = DistributorVersionDTO.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<DistributorVersionDTO> getVersions(@QueryParam("name_search")   String nameSearch,@QueryParam("capability")   String capability);


    /**
     * Updates a Distributor Version
     *
     * @param id 
     * @param distributorVersionDTO The fields and updated values to apply to the specified distributor version
     * @return A successful operation
     * @return A distibutor version could not be found using the provided ID
     * @return An unexpected exception has occurred
     */
    @PUT
    @Path("/{id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Updates a Distributor Version", tags={ "distributor_versions" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful operation", response = DistributorVersionDTO.class),
        @ApiResponse(code = 404, message = "A distibutor version could not be found using the provided ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    DistributorVersionDTO update(@PathParam("id") String id,@Valid @NotNull DistributorVersionDTO distributorVersionDTO);

}
