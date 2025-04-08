package org.candlepin.api.v1;

import org.candlepin.dto.api.server.v1.AsyncJobStatusDTO;
import org.candlepin.dto.api.server.v1.ExceptionMessage;
import java.time.OffsetDateTime;
import org.candlepin.dto.api.server.v1.SchedulerStatusDTO;
import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/jobs")
@Api(description = "the Jobs API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2025-03-28T14:05:44.436523329-04:00[America/Detroit]", comments = "Generator version: 7.12.0")
public interface JobsApi {

    /**
     * Cancels the job associated with the specified job ID
     *
     * @param id The ID of the job to cancel
     * @return Job status associated with the specified job ID
     * @return Job ID is null or empty or job is already in a terminal state or otherwise cannot be canceled at this time
     * @return A job could not be found using the provided ID
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Cancels the job associated with the specified job ID", tags={ "jobs" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Job status associated with the specified job ID", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 400, message = "Job ID is null or empty or job is already in a terminal state or otherwise cannot be canceled at this time", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "A job could not be found using the provided ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO cancelJob(@PathParam("id") @ApiParam("The ID of the job to cancel") String id);


    /**
     * Cleans up terminal jobs matching the provided criteria
     *
     * @param id Cleans up jobs based on ids
     * @param key Cleans up jobs based on keys
     * @param state Cleans up jobs based on statuses
     * @param owner Cleans up jobs based on owners
     * @param principal Cleans up jobs based on principals
     * @param origin Cleans up jobs based on origins
     * @param executor Cleans up jobs based on executors
     * @param after Cleans up jobs to those on or after this date
     * @param before Cleans up jobs to those on or before this date
     * @param force Cleans up job forcefully
     * @return Cleaned up jobs count
     * @return Invalid date range
     * @return Unknown job states
     * @return An unexpected exception has occurred
     */
    @DELETE
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Cleans up terminal jobs matching the provided criteria", tags={ "jobs" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Cleaned up jobs count", response = Integer.class),
        @ApiResponse(code = 400, message = "Invalid date range", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "Unknown job states", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    Integer cleanupTerminalJobs(@QueryParam("id")  @ApiParam("Cleans up jobs based on ids")  Set<String> id,@QueryParam("key")  @ApiParam("Cleans up jobs based on keys")  Set<String> key,@QueryParam("state")  @ApiParam("Cleans up jobs based on statuses")  Set<String> state,@QueryParam("owner")  @ApiParam("Cleans up jobs based on owners")  Set<String> owner,@QueryParam("principal")  @ApiParam("Cleans up jobs based on principals")  Set<String> principal,@QueryParam("origin")  @ApiParam("Cleans up jobs based on origins")  Set<String> origin,@QueryParam("executor")  @ApiParam("Cleans up jobs based on executors")  Set<String> executor,@QueryParam("after")  @ApiParam("Cleans up jobs to those on or after this date")  OffsetDateTime after,@QueryParam("before")  @ApiParam("Cleans up jobs to those on or before this date")  OffsetDateTime before,@QueryParam("force") @DefaultValue("false")  @ApiParam("Cleans up job forcefully")  Boolean force);


    /**
     * Fetches the job status associated with the specified job ID
     *
     * @param id The ID of the job to retrieve
     * @return Job status associated with the specified job ID
     * @return Job ID is null or empty
     * @return A job could not be found using the provided ID
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Fetches the job status associated with the specified job ID", tags={ "jobs" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Job status associated with the specified job ID", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 400, message = "Job ID is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 404, message = "A job could not be found using the provided ID", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO getJobStatus(@PathParam("id") @ApiParam("The ID of the job to retrieve") String id);


    /**
     * Fetches the status of the job scheduler for this Candlepin node
     *
     * @return Status of the job scheduler for this Candlepin node
     * @return An unexpected exception has occurred
     */
    @GET
    @Path("/scheduler")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Fetches the status of the job scheduler for this Candlepin node", tags={ "jobs" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status of the job scheduler for this Candlepin node", response = SchedulerStatusDTO.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    SchedulerStatusDTO getSchedulerStatus();


    /**
     * Fetches a set of job statuses matching the given filter options
     *
     * @param id Filter jobs based on ids
     * @param key Filter jobs based on keys
     * @param state Filter jobs based on statuses
     * @param owner Filter jobs based on owners
     * @param principal Filter jobs based on principals
     * @param origin Filter jobs based on origins
     * @param executor Filter jobs based on executors
     * @param after Filter jobs to those on or after this date
     * @param before Filter jobs to those on or before this date
     * @param page Page index to return
     * @param perPage Number of items to return per page
     * @param order Direction of ordering
     * @param sortBy Property to use for ordering
     * @return A set of job statuses matching the given filter options
     * @return Invalid date range.
     * @return Unknown job states.
     * @return An unexpected exception has occurred
     */
    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Fetches a set of job statuses matching the given filter options", tags={ "jobs" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A set of job statuses matching the given filter options", response = AsyncJobStatusDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid date range.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Unknown job states.", response = ExceptionMessage.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class, responseContainer = "List") })
    java.util.stream.Stream<AsyncJobStatusDTO> listJobStatuses(@QueryParam("id")  @ApiParam("Filter jobs based on ids")  Set<String> id,@QueryParam("key")  @ApiParam("Filter jobs based on keys")  Set<String> key,@QueryParam("state")  @ApiParam("Filter jobs based on statuses")  Set<String> state,@QueryParam("owner")  @ApiParam("Filter jobs based on owners")  Set<String> owner,@QueryParam("principal")  @ApiParam("Filter jobs based on principals")  Set<String> principal,@QueryParam("origin")  @ApiParam("Filter jobs based on origins")  Set<String> origin,@QueryParam("executor")  @ApiParam("Filter jobs based on executors")  Set<String> executor,@QueryParam("after")  @ApiParam("Filter jobs to those on or after this date")  OffsetDateTime after,@QueryParam("before")  @ApiParam("Filter jobs to those on or before this date")  OffsetDateTime before,@QueryParam("page")  @ApiParam("Page index to return")  Integer page,@QueryParam("per_page")  @ApiParam("Number of items to return per page")  Integer perPage,@QueryParam("order")  @ApiParam("Direction of ordering")  String order,@QueryParam("sort_by")  @ApiParam("Property to use for ordering")  String sortBy);


    /**
     * Schedules a job using the specified key and job properties
     *
     * @param jobKey Job key
     * @return Status of the job scheduler for this Candlepin node
     * @return Job key is null or empty
     * @return Job is not configured for manual scheduling
     * @return An unexpected exception has occurred while scheduling job
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/schedule/{jobKey}")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Schedules a job using the specified key and job properties", tags={ "jobs" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status of the job scheduler for this Candlepin node", response = AsyncJobStatusDTO.class),
        @ApiResponse(code = 400, message = "Job key is null or empty", response = ExceptionMessage.class),
        @ApiResponse(code = 403, message = "Job is not configured for manual scheduling", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred while scheduling job", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    AsyncJobStatusDTO scheduleJob(@PathParam("jobKey") @ApiParam("Job key") String jobKey);


    /**
     * Enables or disables the job scheduler for this Candlepin node
     *
     * @param running Boolean value to set running status
     * @return Status of the job scheduler for this Candlepin node
     * @return Error setting scheduler status
     * @return An unexpected exception has occurred
     */
    @POST
    @Path("/scheduler")
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "Enables or disables the job scheduler for this Candlepin node", tags={ "jobs" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Status of the job scheduler for this Candlepin node", response = SchedulerStatusDTO.class),
        @ApiResponse(code = 500, message = "Error setting scheduler status", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "An unexpected exception has occurred", response = ExceptionMessage.class) })
    SchedulerStatusDTO setSchedulerStatus(@QueryParam("running") @DefaultValue("true")  @ApiParam("Boolean value to set running status")  Boolean running);

}
