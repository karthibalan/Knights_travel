import javax.swing.*;
import java.awt.*;
import java.util.*;

class Board2 {

static JFrame frame;
static JPanel squares[][] = new JPanel[8][8];

public Board2() {
    frame = new JFrame("Simplified Chess");
    frame.setSize(500, 500);
    frame.setLayout(new GridLayout(8, 8));

    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            squares[i][j] = new JPanel();

            if ((i + j) % 2 == 0) {
                squares[i][j].setBackground(Color.black);
            } else {
                squares[i][j].setBackground(Color.white);
            }   
            frame.add(squares[i][j]);
        }
    }


    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
}

}

class Node
{
	int x, y, dist;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Node(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Node node = (Node) o;

		if (x != node.x) return false;
		if (y != node.y) return false;
		return dist == node.dist;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		result = 31 * result + dist;
		return result;
	}
}

class Main extends Board2
{	static int[][] parent=new int[8][8];
	static int[][] parent1=new int[8][8];
	static int z,c;
	static void path(int x2, int y2){
		squares[x2][y2].setBackground(Color.red);
		
	}

	private static int[] row = { 2, 2, -2, -2, 1, 1, -1, -1 };
	private static int[] col = { -1, 1, 1, -1, 2, -2, 2, -2 };

	private static boolean valid(int x, int y, int N)
	{
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;

		return true;
	}

	public static int BFS(Node src, Node dest, int N)
	{

		Set<Node> visited = new HashSet<>();


		Queue<Node> q = new ArrayDeque<>();
		q.add(src);
		while (!q.isEmpty())
		{
			Node node = q.poll();

			int x = node.x;
			int y = node.y;
			int dist = node.dist;
			int z1,c1,z2,c2;
			if (x == dest.x && y == dest.y){
				path(x,y);
				z=parent[x][y];
				c=parent1[x][y];
				path(z,c);
				while(z!=src.x && c!=src.y){
					z1=z;c1=c;
					
					z=parent[z1][c1];
					c=parent1[z1][c1];
					path(z,c);
				}
				path(src.x,src.y);
				return dist;
				}

			if (!visited.contains(node))
			{
				visited.add(node);

				for (int i = 0; i < 8; ++i)
				{	

					int x1 = node.x + row[i];
					int y1 = node.y + col[i];

					if (valid(x1, y1, N)){
						q.add(new Node(x1, y1, dist + 1));
						if(parent[x1][y1]==0 && parent1[x1][y1]==0){
						parent[x1][y1]=node.x;
						parent1[x1][y1]=node.y;}
						
					}
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	public static void main(String[] args)
	{
		Board2 a=new Board2();
		Scanner sc=new Scanner(System.in);
		int N = 8,a1,b1;
		

		System.out.println("Enter  source");
		a1=sc.nextInt();
		b1=sc.nextInt();
		Node src = new Node(a1, b1);
		System.out.println("Enter Destination");
		a1=sc.nextInt();
		b1=sc.nextInt();
		
		

		Node dest = new Node(a1, b1);

		System.out.println("Minimum number of steps required is " + BFS(src, dest, N));
	}
}
