package Client;

import java.util.LinkedList;
import java.util.List;

public class Model {

    private final List<Observer< Model, Object >> observers;

    public Model(){
        this.observers= new LinkedList<>();
    }
    //TODO EVERYTHINGGGGGG
    public void addObserver( Observer< Model, Object > obs ) {
        this.observers.add( obs );
    }

    private void announce( String arg ) {
        for ( var obs : this.observers ) {
            obs.update( this, arg );
        }
    }
}
