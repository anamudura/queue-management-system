public class Pool {
    private Client c;
    public Pool()
    {
        c = new Client();
    }
    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }

    public void GenerateClient(int n, int tmin, int tmax, int smin, int smax) {
        c.setId(c.GenerateID(n));
        c.settArrival(c.GenerateArrival(tmin, tmax));
        c.settService(c.GenerateService(smin, smax));
    }

}
