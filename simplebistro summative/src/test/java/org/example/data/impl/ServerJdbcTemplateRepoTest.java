package org.example.data.impl;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.mappers.ServerMapper;
import org.example.model.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerJdbcTemplateRepoTest {

    private JdbcTemplate jdbcTemplate;
    private ServerMapper mapper;
    private ServerJdbcTemplateRepo repo;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        mapper = mock(ServerMapper.class);
        repo = new ServerJdbcTemplateRepo(jdbcTemplate, mapper);
    }

    @Test
    void testGetAllAvailableServers_success() {
        Server server = new Server();
        server.setServerId(1);
        server.setFirstName("Jane");

        LocalDate today = LocalDate.now();
        when(jdbcTemplate.query(anyString(), eq(mapper), eq(today), eq(today)))
                .thenReturn(List.of(server));

        List<Server> result = repo.getAllAvailableServers(today);

        assertEquals(1, result.size());
        assertEquals("Jane", result.get(0).getFirstName());
        verify(jdbcTemplate).query(anyString(), eq(mapper), eq(today), eq(today));
    }

    @Test
    void testGetAllAvailableServers_internalError() {
        LocalDate today = LocalDate.now();
        when(jdbcTemplate.query(anyString(), any(), any(), any()))
                .thenThrow(new DataAccessException("DB issue") {});

        assertThrows(InternalErrorException.class, () -> repo.getAllAvailableServers(today));
    }

    @Test
    void testGetServerById_success() {
        Server expected = new Server();
        expected.setServerId(1);
        expected.setFirstName("Sam");

        when(jdbcTemplate.queryForObject(anyString(), eq(mapper), eq(1)))
                .thenReturn(expected);

        Server actual = repo.getServerById(1);

        assertEquals(expected, actual);
    }

    @Test
    void testGetServerById_notFound() {
        when(jdbcTemplate.queryForObject(anyString(), eq(mapper), eq(1)))
                .thenThrow(new DataAccessException("Not found") {});

        assertThrows(RecordNotFoundException.class, () -> repo.getServerById(1));
    }
}
