import java.util.*;

/**
 * A simple data structure to store readings from each
 * client in a separate list.
 **/
public class ReadingsDatabase { 
    /** Hash table mapping client ID to a list of readings **/
    private Map<Long, List<Reading>> clientReadings = new HashMap<Long, List<Reading>>();

    /**
     * Appends a new reading to the client's list of data.
     **/
    public synchronized  void addReading(Reading r) {
        long id = r.getID();
        if (!clientReadings.containsKey(id)) {
            clientReadings.put(id, new ArrayList<Reading>());
        }
        List<Reading> l =clientReadings.get(id);
        l.add(r);
    }
}



/*
 * 4. Synchronization
 * b.   The addReading method is synchronized. So the ReadingsDatabase object get
 *      locked. Hashmap is the common object for all clients.This way the hashmap 
 *      in the object can be locked. So multiple clients cannot access to the hashmap 
 *      similataneously. So data should write and read from the hashmap serially. 
 *      
 */