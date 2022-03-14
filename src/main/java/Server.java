import java.util.LinkedList;
import java.util.Queue;

public class Server implements Runnable{
   private Queue<Client> queue;
   private Integer waitingTime;
   public Server()
   {
       queue = new LinkedList<Client>();

   }
    @Override
    public void run() {

    }

}
