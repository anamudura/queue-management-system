import java.util.Random;

public class Client implements Comparable {
    private String id;
    private String tArrival;
    private String tService;

    public void setId( int id) {
        this.id = Integer.toString(id);
    }

    public void settArrival(int tArrival) {
        this.tArrival = Integer.toString(tArrival);
    }

    public void settService(int tService) {
        this.tService = Integer.toString(tService);
    }

    public int getId() {
        return Integer.parseInt(id);
    }

    public int gettArrival() {
        return Integer.parseInt(tArrival);
    }

    public int gettService() {
        return Integer.parseInt(tService);
    }

   public int GenerateID(int n)
   {
       Random rand = new Random();
       int i=rand.nextInt(n);
       return i;

   }
   public int GenerateService(int smin,int smax)
   {
       Random rand = new Random();
       int service = rand.nextInt(smax-smin)+smin;
       return service;
   }
   public int GenerateArrival(int tmin,int tmax)
   {
       Random rand = new Random();
       int arrival = rand.nextInt(tmax-tmin)+tmin;
       return arrival;
   }
   public int compareTo(Object x)
   {
       Client y = (Client) x;
       return this.gettArrival()-y.gettArrival();
   }

}
