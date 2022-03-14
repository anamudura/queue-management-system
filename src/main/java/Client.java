import java.util.Random;

public class Client {
    private int id;
    private int tArrival;
    private int tService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int gettArrival() {
        return tArrival;
    }

    public void settArrival(int tArrival) {
        this.tArrival = tArrival;
    }

    public int gettService() {
        return tService;
    }

    public void settService(int tService) {
        this.tService = tService;
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

}
