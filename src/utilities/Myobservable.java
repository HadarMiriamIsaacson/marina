package utilities;

import java.util.Vector;

public class Myobservable {
    private Vector<Myobserver> lst = new Vector<>();
    public void register(Myobserver obs){
        lst.add(obs);
    }
    public void unregister(Myobserver obs){
        lst.remove(obs);
    }
    protected void notifyobservers(){
        for(Myobserver obs:lst){
            obs.update(this);
        }
    }
}
