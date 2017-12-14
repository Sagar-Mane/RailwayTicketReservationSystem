package com.cmpe275.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author arunabh.shrivastava
 *
 */
@Entity
@Table(name = "TICKET")
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    Transaction transaction;
    @ManyToOne(fetch=FetchType.EAGER)
    private Search train;
    private Long price;
    private String duration;
    private Date dateOfJourney;

    public Ticket(){}

    public Ticket(Search search){
        this.train = search;
        this.price = search.getPrice();
        this.duration = search.getDuration();
    }

    public Ticket(Search search, Date dateOfJourney) {
        this.train = search;
        this.price = search.getPrice();
        this.duration = search.getDuration();
        this.dateOfJourney = dateOfJourney;
    }

    public Search getTrain() {
        return train;
    }

    public void setTrain(Search train) {
        this.train = train;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getDateOfJourney() {
        return dateOfJourney;
    }

    public void setDateOfJourney(Date dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }
}
