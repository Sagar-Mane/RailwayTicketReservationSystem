package com.cmpe275.repository;

import com.cmpe275.domain.Search;
import com.cmpe275.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author arunabh.shrivastava
 */
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findAllByDateOfJourneyAndTrain(Date dateOfJourney, Search train);

}
