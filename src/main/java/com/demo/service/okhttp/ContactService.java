package com.demo.service.okhttp;

import com.demo.model.dto.ContactDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.Objects;

@ApplicationScoped
public class ContactService {
    private final OkHttpClient client = new OkHttpClient();

    public ContactDTO getContact(Long contactId) {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .addPathSegment("contacts")
                .addPathSegment(String.valueOf(contactId))
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Error with code: " + response.code());
                System.out.println("Error message: " + response.message());
                throw new WebApplicationException(response.code());
            } else {
                String json = Objects.requireNonNull(response.body()).string();
                System.out.println("Response body: " + json);
                return toContact(json);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private ContactDTO toContact(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, ContactDTO.class);
    }
}
