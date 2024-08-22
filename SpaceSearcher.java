import java.util.ArrayList;
import java.util.HashSet;

public class SpaceSearcher
{
    private PQueue q;
    private HashSet<State> closedSet;

    SpaceSearcher()
    {
        this.q = null;
        this.closedSet = null;
    }

    public State AstarClosedSet(State initialState)
    {
        this.q = new PQueue(new IntegerComparator());
        this.closedSet = new HashSet<>();
        this.q.add(initialState);
        while(this.q.peek()!=null)
        {
            
            State currentState = this.q.getMin();
            if(currentState.isTerminal())
            {
                return currentState;
            }

             
            if(!closedSet.contains(currentState))
            {
                this.closedSet.add(currentState);
                ArrayList<State> states = new ArrayList<>();
                states.addAll(currentState.createChild());
                PQueue p = new PQueue(new IntegerComparator());
                for (State state : states) {
                    p.add(state);
                }
            }
        }
        return null;
    }
}