package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.CertificateSerialDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/serials/{id}")
@Api(description = "the CertificateSerial API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface CertificateSerialApi {

    /**
     * Retrieves a single Certificate Serial
     *
     * @param id The ID of the certificate serial to retrieve
     * @return 
     * @return A certificate serial could not be found using the provided ID
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves a single Certificate Serial", tags={ "certificate_serial" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = CertificateSerialDTO.class),
        @ApiResponse(code = 404, message = "A certificate serial could not be found using the provided ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    CertificateSerialDTO getCertificateSerial(@PathParam("id") @ApiParam("The ID of the certificate serial to retrieve") Long id);

}
