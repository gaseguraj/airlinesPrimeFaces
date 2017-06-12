package edu.mum.cs545.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.FlightService;

 
@Named("flightView")
@ApplicationScoped
public class FlightView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Flight> flights;
	private List<Flight> filteredFlights;
     
    @Inject
    private FlightService flightService;
    @Inject
    private AirlineService airlineService;
    @Inject
    private AirplaneService airplaneService;
 
    public void flightView(){

    }
    
    @PostConstruct
    public void init() {
    	flights = flightService.findAll();
    }
     
    public List<Flight> getFlights() {
        return flights;
    }
    
    public List<Flight> getFilteredFlights() {
        return filteredFlights;
    }
 
    public void setFilteredFlights(List<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
    }
 
    public List<String> getAirline(){
    	List<Airline> airline = airlineService.findAll();
    	List<String> listAirline = new ArrayList<>();
    	for(Airline air: airline){
    		System.out.println(air.getName());
    		listAirline.add(air.getName());
    	}
    	
    	return listAirline;
    }
    
    public List<String> getAirplane(){
    	List<Airplane> airplane = airplaneService.findAll();
    	List<String> listAirplane = new ArrayList<>();
    	for(Airplane air: airplane){
    		listAirplane.add(air.getModel());
    	}
    	
    	return listAirplane;
    }
    
 
 
}