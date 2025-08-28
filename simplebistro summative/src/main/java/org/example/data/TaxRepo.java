package org.example.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Tax;

import java.time.LocalDate;

public interface TaxRepo {
    Tax getCurrentTax(LocalDate date) throws RecordNotFoundException, InternalErrorException;
}
