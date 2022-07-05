
import java.awt.Color;

public class Calculation {
	int[][] weight = new int[19][19];
	int[][] weightStatus = new int[19][19]; //없으면 0이고,검은색 1, 흰색 2, 착수 3

	final static int blackStone = 200;
	final static int whiteStone = 300;
	final static int blockStone = 400;
	final static int straightStion = -50;
	
	public Calculation() {
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++) {
				weight[i][j] = 0;
				weightStatus[i][j] = 0;
			}
	}

	public void doCalculation() {
		int x = Memory.points.get(Memory.points.size() - 1).i;
		int y = Memory.points.get(Memory.points.size() - 1).j;
		int min = 10000000;
		try {
			if (Memory.points.get(Memory.points.size() - 1).color == Color.BLACK) { // 검은색
//				if(weightStatus[x][y] == 0)
				weight[x][y - 1] += -1;
				weight[x + 1][y - 1] += -1;
				weight[x + 1][y] += -1;
				weight[x + 1][y + 1] += -1;
				weight[x][y + 1] += -1;
				weight[x - 1][y + 1] += -1;
				weight[x - 1][y] += -1;
				weight[x - 1][y - 1] += -1;
				
				weightStatus[x][y] = 1;
				weight[x][y] = blackStone;
				
			} else if (Memory.points.get(Memory.points.size() - 1).color == Color.WHITE) {// 흰색
				weight[x][y - 1] += 1;
				weight[x + 1][y - 1] += 1;
				weight[x + 1][y] += 1;
				weight[x + 1][y + 1] += 1;
				weight[x][y + 1] += 1;
				weight[x - 1][y + 1] += 1;
				weight[x - 1][y] += 1;
				weight[x - 1][y - 1] += 1;
				
				weightStatus[x][y] = 2;
				weight[x][y] = whiteStone;
			}
			if(Memory.points.size() > 2) {            
	            for (int i = 0; i < 19; i++) {
	               for (int j = 0; j < 19; j++) {
	                  if(weightStatus[i][j] == 1) {
	                     weight[i][j] = blackStone;
	                  }else if(weightStatus[i][j] == 2) {
	                     weight[i][j] = whiteStone;
	                  }else if(weightStatus[i][j] == 3) {
	                     weight[i][j] = blockStone;
	                  }
	                  if(min > weight[i][j]) {
	                     min = weight [i][j];
	                  }
	                  
	               }
	            }
	         }
	         for (int i = 0; i < weight.length; i++) {
	            for (int j = 0; j < weight.length; j++) {
	               if(min == weight[i][j]) {
	                  System.out.println(i + " "+ j);
	               }
	            }
	         }

			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 19; j++) {
					System.out.print("|" + weight[j][i] + "\t");
				}
				System.out.println("|");
			}
			System.out.println("---------------------------------------------------");
			
			
		} catch (IndexOutOfBoundsException e) {

		}

	}
}