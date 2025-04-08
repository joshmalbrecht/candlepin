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

@Path("/crl")
@Api(description = "the CertificateRevocationList API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface CertificateRevocationListApi {

    /**
     * Retrieves the list of all revoked certificate serial ids that are not expired
     *
     * @return Certificate revocation list successfully retrieved
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Retrieves the list of all revoked certificate serial ids that are not expired", tags={ "certificate_revocation_list" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Certificate revocation list successfully retrieved", response = Long.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    List<Long> getCurrentCrl();

}
