package homework3;

import java.util.HashMap;

public class Router {

    private HashMap<Router, Integer> distances;
    private String name;
    public Router(String name) {
        this.distances = new HashMap<>();
        this.name = name;
    }

    public void onInit() throws InterruptedException {
    	
    	for (Neighbor neighbor : Network.getNeighbors(this)) {
    		distances.put(neighbor.router, neighbor.cost);
    	}
    	
    	for (Neighbor neighbor : Network.getNeighbors(this)) {
    		Network.sendDistanceMessage(new Message(this, neighbor.router, distances));
    	}
    }
    

    public void onDistanceMessage(Message message) throws InterruptedException {
    	
    	boolean mapChanged = false;
    	for (Router router : message.distances.keySet()) {
    		if (this.distances.containsKey(router)) {
    			if (message.sender.distances.get(message.receiver) + message.distances.get(router) < distances.get(router)) {
    				mapChanged = true;
    				distances.put(router, message.sender.distances.get(message.receiver) + message.distances.get(router));
    			}
    		}
    	}
    	
    	if (mapChanged) {
        	for (Neighbor neighbor : Network.getNeighbors(this)) {
        			Network.sendDistanceMessage(new Message(this, neighbor.router, distances));
        	}
    	}
    }
    	


    public void dumpDistanceTable() {
        System.out.println("router: " + this);
        for(Router r : distances.keySet()){
            System.out.println("\t" + r + "\t" + distances.get(r));
        }
    }

    @Override
    public String toString(){
        return "Router: " + name;
    }
}
