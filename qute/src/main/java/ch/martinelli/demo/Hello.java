package ch.martinelli.demo;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class Hello {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance hello(String greeting);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return Templates.hello("");
    }

    @POST
    @Path("/hello")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public TemplateInstance post(@FormParam("name") String name) {
        return Templates.hello("Hello " + name);
    }

}
