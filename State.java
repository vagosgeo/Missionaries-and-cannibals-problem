import java.util.ArrayList;
import java.lang.Math;

public class State{

    private int leftChemicalA;
    private int leftChemicalB;
    private int rightChemicalA;
    private int rightChemicalB;
    private boolean dropper; 
    private State father;
    private int hypothesis;
    private int moves;
    private Game game;


    public State(){
	
	}

    public State(Game game){
        this.game=game;
    }
	
    public State (int leftChemicalA, int leftChemicalB, int rightChemicalA, int rightChemicalB, boolean dropper, int moves, State father,Game game){
            this.moves=moves;
            this.leftChemicalA=leftChemicalA;
            this.leftChemicalB=leftChemicalB;
            this.rightChemicalA=rightChemicalA;
            this.rightChemicalB=rightChemicalB;
            this.dropper=dropper;
            this.father = father;
            this.game=game;
    }

    public State getFather(){
        return father;
    }

    public int getMoves() {
        return moves;
    } 

    public void setHypothesis(int h){
        this.hypothesis=h;
    }

    public int getHypothesis(){
        return this.hypothesis;
    }

    public int getLeftChemicalA() {
        return this.leftChemicalA;
    }

    public void setLeftChemicalA(int leftChemicalA) {
        this.leftChemicalA = leftChemicalA;
    }

    public int getLeftChemicalB() {
        return this.leftChemicalB;
    }

    public void setLeftChemicalB(int leftChemicalB) {
        this.leftChemicalB = leftChemicalB;
    }

    public int getRightChemicalA() {
        return this.rightChemicalA;
    }

    public void setRightChemicalA(int rightChemicalA) {
        this.rightChemicalA = rightChemicalA;
    }

    public int getRightChemicalB() {
        return this.rightChemicalB;
    }

    public void setRightChemicalB(int rightChemicalB) {
        this.rightChemicalB = rightChemicalB;
    }

    public boolean isdropper() {
        return this.dropper;
    }


    public boolean isTerminal(){
		return (this.leftChemicalA==0 && this.leftChemicalB==0);
	}

    public boolean isAcceptable(){         
        return ((leftChemicalB>=leftChemicalA|| leftChemicalB ==0) && (rightChemicalB>= rightChemicalA || rightChemicalB==0));
    }
    
    public ArrayList<State> createChild(){
   
        ArrayList<State> toReturn = new ArrayList<>();
        State father= new State(getLeftChemicalA(),getLeftChemicalB(),getRightChemicalA(),getRightChemicalB(),dropper,getMoves(),getFather(),this.game);
        
        if (dropper)
        {
            for (int c = 0; c<Math.min(game.getM(),rightChemicalA); c++)
            {   State s1=new State(leftChemicalA -c, leftChemicalB , rightChemicalA + c, rightChemicalB , !dropper , moves++,father,this.game);
                if(s1.isAcceptable()){
                    s1.setHypothesis(s1.getMoves()+s1.heuristic());
                toReturn.add(s1);
                }
                for (int m = c; m<Math.min(game.getM()-c,rightChemicalB); m++ )
                {   State s=new State(leftChemicalA -c, leftChemicalB -m, rightChemicalA + c, rightChemicalB + m, !dropper , moves++,father,this.game);
                    if(s.isAcceptable()){
                        s.setHypothesis(s.getMoves()+1+s.heuristic());
                    toReturn.add(s);
                    }
                }                   
            }
        }   
        
        else
        {for (int c = 0; c<Math.min(game.getM(),leftChemicalA); c++)
            {   State s1=new State(leftChemicalA +c, leftChemicalB , rightChemicalA - c, rightChemicalB , !dropper , moves++,father,this.game);
                if(s1.isAcceptable()){
                    s1.setHypothesis(moves+1+s1.heuristic());
                toReturn.add(s1);
                }
                for (int m = c; m<Math.min(game.getM()-c,leftChemicalB); m++ )
                {   State s =new State(leftChemicalA +c, leftChemicalB +m, rightChemicalA -c, rightChemicalB - m, !dropper , moves++,father,this.game);
                    if(s.isAcceptable()){
                        s.setHypothesis(moves+1+s.heuristic());
                    toReturn.add(s);
                    }
                    
                }                  
            }
        }
            
        return toReturn;
    }

    public int heuristic(){

        if(isTerminal()){
            return 0;
        }
            
        int cost;
            
        int sum= getLeftChemicalA()+getLeftChemicalB();    
        if(!dropper){
            switch (sum){ 
                case 1: cost=1; break;
                default : cost= 2*sum-3; 
            }
        }
        else{
            cost=2*sum;
        }
        return cost;
    }

    public void currentState()
    {
        System.out.print(">>>>>>>>>>>>>>>>>>>>\nCurrently on Left side(starting side): ");
        System.out.print("ChemicalA:"+leftChemicalA + "/n"+ "ChemicalB"+leftChemicalB);
        System.out.print("\nCurrently on Right side(ending side): ");      
        System.out.print("ChemicalA:"+rightChemicalA + "/n"+ "ChemicalB"+rightChemicalB);       
        System.out.print("\n>>>>>>>>>>>>>>>>>>>>\n");
    }           
}
	

    
    
    
