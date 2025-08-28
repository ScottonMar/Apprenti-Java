package org.example.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Server;

import java.time.LocalDate;
import java.util.List;

public interface ServerRepo {
    List<Server> getAllAvailableServers(LocalDate date) throws InternalErrorException;
    Server getServerById(int id) throws RecordNotFoundException, InternalErrorException;
}
