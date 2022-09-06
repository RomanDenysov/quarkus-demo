package com.demo.resource;

import io.quarkus.arc.All;
import io.quarkus.runtime.configuration.ProfileManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("profile")
@Produces("application/json")
@Consumes("application/json")
public class ProfileResource {

    @GET
    public Profile getCurrentProfile() {
        return new Profile(ProfileManager.getActiveProfile());
    }

    @AllArgsConstructor @Getter
    private static class Profile {
        private String profile;
    }
}
