import java.util.*;

	
public class Game {
	private int M;
	private int N;
	private int K;

	public  Game(int M, int N,int K){
		this.M=M;
		this.N=N;
		this.K=K;
		}

	public int getM(){
		return M;
	}
	
	public int getN(){
		return N;
	}
	
	public int getK(){
		return K;
	}


	public static void main(String[] args){
		
		boolean flag = true;

		while (flag) {

			/*Receiving input data from user*/
			System.out.print("Enter the number of chemical compounds: ");
			Scanner s1  = new Scanner(System.in);
			int chemical = s1.nextInt();
			System.out.print("Enter the maximum value of the dropper : ");
			Scanner s2 = new Scanner(System.in);
			int maximum = s2.nextInt();
			System.out.print("Enter the maximum number of moves : ");
			Scanner s3= new Scanner(System.in);
			int moves = s3.nextInt();
			s1.close();
			s2.close();
			s3.close();
			Game game =new Game(maximum,chemical,moves);

			/*Using our starting state as input and waiting for the result to return*/
			SpaceSearcher spaceSearcher = new SpaceSearcher();
			State initialState = new State(game.getN(),game.getN(),0,0,false,0,null,game);
			long start = System.currentTimeMillis();
			State terminalState = spaceSearcher.AstarClosedSet(initialState);
			long end = System.currentTimeMillis();

			/*Using initial state to determine if it's a valid result and print information accordingly*/
			if (terminalState == null) {
				System.out.println("Could not find solution");
			} 
			else 
			{
				/*Creating a temp list to retrieve all the states our algorithm went through
					starting from terminal state back to our starting state
					reversing them*/
				State temp = terminalState;
				ArrayList<State> path = new ArrayList<>();
				path.add(terminalState);
				while (temp.getFather() != null) {
					path.add(temp.getFather());
					temp = temp.getFather();
				}
				Collections.reverse(path);

				
				if (path.get(path.size() - 1).getMoves() > game.getK()  ) {
					System.out.println("No path was found with the given maximum moves");
				} 
				else {
					System.out.println("Finished!");
					System.out.println("\nStates ");
					for (State state : path) {
						state.currentState();
					}
					System.out.println("\nTotal moves: " + path.get(path.size() - 1).getMoves() );
				}
			}
			System.out.println("A* search time: " + (double) (end - start) / 1000 + " sec.");

			System.out.print("\nPress 0 if you want to try again: ");
			Scanner s4 = new Scanner(System.in);
			int input = s4.nextInt();
			s4.close();
			if(input != 0){
				flag = false;
				System.out.println("Goodbye!");
			}
		}
	}
}

	
