package com.demo.service;

import com.demo.model.dto.UserDTO;
import com.demo.model.entity.Role;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@QuarkusTest
public class UserServiceTest {
    @Inject
    UserService userService;

    @Test
    public void testGetById() {
        UserDTO user = userService.getById(1L);

        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("Boris", user.getName());
        Assertions.assertEquals(Role.ADMIN, user.getRole());

        Assertions.assertThrows(WebApplicationException.class,
                () -> userService.getById(1000L));
    }

    @Test
    public void testGetAll() {
        List<UserDTO> users = userService.getAll();

        Assertions.assertNotEquals(0, users.size());

        try {
            UserDTO userFromList = users.stream()
                    .filter(u -> u.getId().equals(1L))
                    .findFirst()
                    .orElseThrow(WebApplicationException::new);

            UserDTO userFromDb = userService.getById(1L);

            Assertions.assertEquals(userFromDb, userFromList);
        } catch (WebApplicationException e) {
            Assertions.fail();
        }
    }

    @Test
    public void testCreate() {
        UserDTO userToCreate =UserDTO.builder()
                .name("Ioan")
                .role(Role.CLIENT)
                .build();

        try {
            userService.create(userToCreate);

            UserDTO createdUser = userService.getAll().stream()
                    .filter(u -> u.getName().equals("Ioan"))
                    .findFirst()
                    .orElseThrow(WebApplicationException::new);

            Assertions.assertEquals(userToCreate.getName(), createdUser.getName());
            Assertions.assertEquals(userToCreate.getRole(), createdUser.getRole());
        } catch (WebApplicationException e) {
            Assertions.fail();
        }

        Assertions.assertThrows(WebApplicationException.class,
                () -> userService.create(null));

        Assertions.assertThrows(WebApplicationException.class,
                () -> userService.create(userToCreate));
    }

    @Test
    public void testUpdate() {
        Long userToUpdateId = 1L;
        UserDTO userToUpdate = UserDTO.builder()
                .id(2L)
                .name("Lion")
                .role(Role.VENDOR)
                .build();

        try {
            userService.update(userToUpdateId, userToUpdate);
        } catch (WebApplicationException e) {
            Assertions.fail();
        }

        UserDTO updatedUser = userService.getById(userToUpdateId);

        Assertions.assertEquals(userToUpdate.getRole(), updatedUser.getRole());
        Assertions.assertEquals(userToUpdate.getName(), updatedUser.getName());
        Assertions.assertNotEquals(userToUpdate.getId(), updatedUser.getId());

        Assertions.assertThrows(WebApplicationException.class,
                () -> userService.update(userToUpdateId, userToUpdate));

        Assertions.assertThrows(WebApplicationException.class,
                () -> userService.update(1000L, userToUpdate));

        Assertions.assertThrows(WebApplicationException.class,
                () -> userService.update(userToUpdateId, null));
    }

    @Test
    public void testDelete() {
        UserDTO userToCreate = UserDTO.builder()
                .name("Henry")
                .role(Role.CLIENT)
                .build();

        try {
            userService.create(userToCreate);

            UserDTO user = userService.getAll()
                    .stream()
                    .filter(u -> u.getName().equals("Henry"))
                    .findFirst()
                    .orElseThrow(WebApplicationException::new);

            userService.delete(user.getId());
        } catch (WebApplicationException e) {
            Assertions.fail();
        }

        Assertions.assertThrows(WebApplicationException.class,
                () -> userService.getAll()
                        .stream()
                        .filter(u -> u.getName().equals("Henry"))
                        .findFirst()
                        .orElseThrow(WebApplicationException::new));

        Assertions.assertThrows(WebApplicationException.class,
                () -> userService.delete(1000L));
    }
}
