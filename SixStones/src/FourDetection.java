
public class FourDetection {
	//이잰 됄꺼야
	int[][] endPoint = new int[8][2];
	public void checkFourDetection(int x, int y) {
		if (checkVertical(x, y) > 4) {
			Calculation.weight[endPoint[0][0]][endPoint[0][1]] = -50;
			Calculation.weight[endPoint[4][0]][endPoint[4][1]] = -50;
			System.out.println("윗값 :" + endPoint[0][0] + " " + endPoint[0][1]);
			System.out.println("아랫값 :" +endPoint[4][0] + " " + endPoint[4][1]);
			System.out.println("Hello");
			return ;
		}
	}
	
	//세로
	private int checkVertical(int x, int y) {
		return checkUp(x, y, GamePanel.bwMatrix[x][y]) + checkDown(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkDown(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[0][0] = x;
				endPoint[0][1] = y;
				return 0;
			}
			return checkDown(x, y - 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkUp(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[4][0] = x;
				endPoint[4][1] = y;
				return 0;
			}
			return checkUp(x, y + 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
}
