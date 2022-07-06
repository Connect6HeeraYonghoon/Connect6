
public class FourDetection {
	int[][] endPoint = new int[8][2];
	public void checkFourDetection(int x, int y) {
		if (checkVertical(x, y) > 4) {
			Calculation.weight[endPoint[0][0]][endPoint[0][1]] = -50;
			Calculation.weight[endPoint[4][0]][endPoint[4][1]] = -50;
			System.out.println(" up :" + endPoint[0][0] + " " + endPoint[0][1]);
			System.out.println(" down :" +endPoint[4][0] + " " + endPoint[4][1]);
			return;
		}
		if (checkDiagonal(x, y) > 4) {
			Calculation.weight[endPoint[7][0]][endPoint[7][1]] = -50;
			Calculation.weight[endPoint[3][0]][endPoint[3][1]] = -50;
			System.out.println(" UpperLeft :" + endPoint[7][0] + " " + endPoint[7][1]);
			System.out.println(" LowerRight :" +endPoint[3][0] + " " + endPoint[3][1]);
			return;
		}
	}
	
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
	
	private int checkDiagonal(int x, int y) {
		return checkULU(x, y, GamePanel.bwMatrix[x][y]) + checkLRD(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkLRD(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[3][0] = x;
				endPoint[3][1] = y;	
				return 0;
			}
			return checkLRD(x + 1, y + 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkULU(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
						
				endPoint[7][0] = x;
				endPoint[7][1] = y;
				return 0;
			}
			return checkULU(x - 1, y - 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
}
