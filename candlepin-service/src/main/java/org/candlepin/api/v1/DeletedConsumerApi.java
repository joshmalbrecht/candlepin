package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.DeletedConsumerDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;
import java.time.OffsetDateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/deleted_consumers")
@Api(description = "the DeletedConsumer API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface DeletedConsumerApi {

    /**
     * Retrieves a list of deleted consumers by deletion date or all. List returned is the deleted Consumers.
     *
     * @param date Filter deleted consumers to those on or after this date
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return A list of deleted consumers
     * @return Date is invalid
     * @return Not Found
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a list of deleted consumers by deletion date or all. List returned is the deleted Consumers.", tags={ "deleted_consumer" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of deleted consumers", response = DeletedConsumerDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Date is invalid", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Not Found", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<DeletedConsumerDTO> listByDate(@QueryParam("date")  @ApiParam("Filter deleted consumers to those on or after this date")  OffsetDateTime date,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);

}
