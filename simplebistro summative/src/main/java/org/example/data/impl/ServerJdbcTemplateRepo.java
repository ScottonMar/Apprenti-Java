package org.example.data.impl;

import org.example.data.ServerRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.mappers.ServerMapper;
import org.example.model.Server;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ServerJdbcTemplateRepo implements ServerRepo {

    private final JdbcTemplate jdbcTemplate;
    private final ServerMapper mapper;

    public ServerJdbcTemplateRepo(JdbcTemplate jdbcTemplate, ServerMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<Server> getAllAvailableServers(LocalDate date) throws InternalErrorException {
        try {
            String sql = "SELECT * FROM server WHERE HireDate <= ? AND (TermDate IS NULL OR TermDate >= ?)";
            return jdbcTemplate.query(sql, mapper, date, date);
        } catch (DataAccessException e) {
            throw new InternalErrorException("Error retrieving available servers", e);
        }
    }

    @Override
    public Server getServerById(int id) throws RecordNotFoundException {
        try {
            String sql = "SELECT * FROM server WHERE ServerID = ?";
            return jdbcTemplate.queryForObject(sql, mapper, id);
        } catch (DataAccessException e) {
            throw new RecordNotFoundException("Server not found with ID: " + id);
        }
    }
}
