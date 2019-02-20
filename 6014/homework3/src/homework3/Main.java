package homework3;

/*
 * Implement the onInit and onDistanceMessage methods in the Router class of the code provided 
 * in this directory so that the routers use the Bellman Ford algorithm to compute routing information. 
 * Use the static methods in the Network class to help you with this. These methods shouldn't be more than ~20 lines of code or so!

Once you have a working implementation, test your algorithm on a variety of network sizes. 
Plot the number of messages required to converge as a function of network size. 
Since the networks are probablisitically generated, 
you might want to try several networks of each size to get a sense of the distribution.
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {

    	double sum = 0;
    	int runs = 1000;
    	for (int i = 0; i < runs; i++) {
//          Network.makeSimpleNetwork(); //use this for testing/debugging
            Network.makeProbablisticNetwork(5120); //use this for the plotting part
            Network.dump();

            Network.startup();
            Network.runBellmanFord();

//            System.out.println("done building tables!");
//            for(Router r : Network.getRouters()){
//                r.dumpDistanceTable();
//            }

            sum += Network.getMessageCount();
    	}
//        System.out.println("total messages: " + Network.getMessageCount());

        System.out.println("Average Messages: " + (sum/runs));
    }
}
