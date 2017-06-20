package rs.team15.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class listOfTables implements Serializable{

    private Set<String> fruits = new HashSet<String>(0);

    public listOfTables(){
    	
    }

	public Set<String> getFruits() {
		return fruits;
	}

	public void setFruits(Set<String> fruits) {
		this.fruits = fruits;
	}
    
    

}