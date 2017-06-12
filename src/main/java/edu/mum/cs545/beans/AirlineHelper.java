package edu.mum.cs545.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.FlightService;


@Named
@SessionScoped
public class AirlineHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String flight;
	
	@Inject
	AirlineService airlineService;
	@Inject
	FlightService flightService;
	
	
	public String createAirline(Airline airline){
		airline.addFlight(flightService.findByNumber(this.flight).get(0));
		airlineService.create(airline);
		return "created";
	}
	
	public List<Flight> getFlights(){
		return flightService.findAll();
	}
	
	public void setFlight(String flight){
		this.flight = flight;
	}
	
	public String getFlight(){
		return flight;
	}
}
