package com.demo.resource;

import com.demo.model.dto.UserDTO;
import com.demo.model.entity.Role;
import com.demo.service.UserService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

@QuarkusTest
public class UserResourceTest {
    @InjectMock
    UserService userService;
    @Inject
    UserResource userResource;

    private final Long existentId = 1L;
    private final Long nonExistentId = 1000L;

    @BeforeEach
    public void setUp() {
        Mockito.when(userService.getById(existentId)).thenReturn(
                UserDTO.builder().id(existentId).name("Aion").role(Role.CLIENT).build());
        Mockito.when(userService.getById(nonExistentId)).thenThrow(
                WebApplicationException.class);
    }

    @Test
    public void testGet() {
        UserDTO user = userResource.getSingleUserById(existentId);

        Assertions.assertEquals(existentId, user.getId());
        Assertions.assertEquals("Aion", user.getName());
        Assertions.assertEquals(Role.CLIENT, user.getRole());

        Assertions.assertThrows(WebApplicationException.class,
                () -> userResource.getSingleUserById(nonExistentId));
    }
}
