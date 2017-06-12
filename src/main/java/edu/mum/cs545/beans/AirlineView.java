package edu.mum.cs545.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

 
@Named("airlineView")
@ApplicationScoped
public class AirlineView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Airline> airlines;
	
     
    @Inject
    private AirlineService airlineService;
 
    public AirlineView(){

    }
    
    @PostConstruct
    public void init() {
    	airlines = airlineService.findAll();
    }
     
    public List<Airline> getAirlines() {
        return airlines;
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Airline Edited", (event.getObject()).toString());
         FacesContext.getCurrentInstance().addMessage(null, msg);
         System.out.println("onRowEdit: " + event.getObject());
         airlineService.update((Airline)event.getObject());
    }
    
   
    public void onDelete(Airline airline) {
         System.out.println("onRowDelete: " + airline);
         airlineService.delete(airline);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ( event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        System.out.println("OLD: " + oldValue);
        System.out.println("NEW: " + newValue);
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
 
}