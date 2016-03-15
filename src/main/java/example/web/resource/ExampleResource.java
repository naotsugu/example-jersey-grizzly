package example.web.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.StringJoiner;

@Path("example")
public class ExampleResource {


    /**
     * <pre>
     *     curl 'http://localhost:8090/example/hello'
     * </pre>
     */
    @Path("hello")
    @GET
    public String pathHello() {
        return "hello";
    }



    /**
     * <pre>
     *     curl 'http://localhost:8090/example' -X POST -d 'HELLO'
     * </pre>
     */
    @POST
    public Response echoPost(InputStream is) {
        return Response.ok(is).build();
    }



    /**
     * <pre>
     *     curl 'http://localhost:8090/example?name=Thomas'
     * </pre>
     */
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String queryParam(@QueryParam("name") String name,
                             @QueryParam("age") @DefaultValue("30") int age) {
        return name + " " + age;
    }



    @QueryParam("fieldValue")
    private String fieldValue;

    /**
     * <pre>
     *     curl 'http://localhost:8090/example/field?fieldValue=Who'
     * </pre>
     */
    @GET
    @Path("field")
    public Response queryParamField() {
        return Response.ok(fieldValue).build();
    }



    /**
     * <pre>
     *     curl 'http://localhost:8090/example/path/Eddie'
     * </pre>
     */
    @GET
    @Path("path/{name}")
    public Response pathParam(@PathParam("name") String name) {
        return Response.ok(name).build();
    }



    /**
     * <pre>
     *     curl 'http://localhost:8090/example/path/Eddie-Vedder'
     * </pre>
     */
    @GET
    @Path("path/{firstname}-{lastname}")
    public Response pathParams(@PathParam("firstname") String first, @PathParam("lastname") String last) {
        return Response.ok(first + " " + last).build();
    }



    /**
     * <pre>
     *     curl 'http://localhost:8090/example/path/9999'
     * </pre>
     */
    @GET
    @Path("path/{id : \\d+}")
    public Response pathParamsRegexp(@PathParam("id") int id) {
        return Response.ok("id[" + id + "]").build();
    }



    /**
     * <pre>
     *     curl 'http://localhost:8090/example/matrix;author=Michael;year=2016'
     * </pre>
     */
    @GET
    @Path("matrix")
    public Response matrixParam(@MatrixParam("author") String author, @MatrixParam("year") int year) {
        return Response.ok("MatrixParam[" + author + " " + year + "]").build();
    }



    /**
     * <pre>
     *     curl 'http://localhost:8090/example/matrix/e50;color=black/2016'
     * </pre>
     */
    @GET
    @Path("matrix/{model}/{year}")
    public Response pathSegment(@PathParam("model") PathSegment car, @PathParam("year") int year) {
        return Response.ok("PathSegment[" + car.getMatrixParameters().getFirst("color") + "]").build();
    }



    /**
     * <pre>
     *     curl 'http://localhost:8090/example/header' -H 'X-Name: Joe'
     * </pre>
     */
    @GET
    @Path("header")
    public Response header(@HeaderParam("X-Name") String name) {
        return Response.ok("header[" + name + "]").build();
    }



    /**
     * <pre>
     *     curl 'http://localhost:8090/example/cookie' -b 'name=cookie'
     * </pre>
     */
    @GET
    @Path("cookie")
    public Response cookie(@CookieParam("name") String name) {
        return Response.ok("cookie[" + name + "]").build();
    }



    /**
     * <pre>
     *     curl 'http://localhost:8090/example/form' -X POST -d 'number=9999'
     * </pre>
     */
    @POST
    @Path("form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response form(@FormParam("number") int number) {
        return Response.ok("form[" + number + "]").build();
    }


    /**
     * <pre>
     *     curl 'http://localhost:8090/example/bean' -X POST -d 'firstName=F&lastName=L'
     * </pre>
     */
    @POST
    @Path("bean")
    public Response bean(@BeanParam Input in) {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        return Response.ok("BeanParam" +
                joiner.add(in.getFirstName())
                      .add(in.getLastName())
                      .add(in.getContentType()).toString()).build();
    }

    public static class Input {
        @FormParam("firstName")
        String firstName;
        @FormParam("lastName")
        String lastName;
        @HeaderParam("Content-Type")
        String contentType;

        public String getFirstName() { return firstName;}
        public String getLastName() { return lastName; }
        public String getContentType() { return contentType; }

        public void setFirstName(String firstName) { this.firstName = firstName;}
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setContentType(String contentType) { this.contentType = contentType; }
    }

}
