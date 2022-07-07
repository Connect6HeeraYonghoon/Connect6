//몰루랄라
public class FourDetection {
	int[][] endPoint = new int[8][2];

	public void checkFourDetection(int x, int y) {
		
		if (checkHorizontal(x, y) > 4) {
			if(GamePanel.bwMatrix[x][y] == 1) {
				Calculation.weight[endPoint[2][0]][endPoint[2][1]] = Calculation.blackWeight;
				Calculation.weight[endPoint[6][0]][endPoint[6][1]] = Calculation.blackWeight;				
			}
			else if(GamePanel.bwMatrix[x][y] == 2) {
				Calculation.weight[endPoint[2][0]][endPoint[2][1]] = Calculation.whiteWeight;
				Calculation.weight[endPoint[6][0]][endPoint[6][1]] = Calculation.whiteWeight;				
			}
		}
		if (checkVertical(x, y) > 4) {
			if(GamePanel.bwMatrix[x][y] == 1) {
				Calculation.weight[endPoint[0][0]][endPoint[0][1]] = Calculation.blackWeight;
				Calculation.weight[endPoint[4][0]][endPoint[4][1]] = Calculation.blackWeight;				
			}
			else if(GamePanel.bwMatrix[x][y] == 2) {
				Calculation.weight[endPoint[0][0]][endPoint[0][1]] = Calculation.whiteWeight;
				Calculation.weight[endPoint[4][0]][endPoint[4][1]] = Calculation.whiteWeight;				
			}
		}
		if (checkDiagonal(x, y) > 4) {
			if(GamePanel.bwMatrix[x][y] == 1) {                                              
				Calculation.weight[endPoint[7][0]][endPoint[7][1]] = Calculation.blackWeight;
				Calculation.weight[endPoint[3][0]][endPoint[3][1]] = Calculation.blackWeight;
			}                                                                                
			else if(GamePanel.bwMatrix[x][y] == 2) {                                         
				Calculation.weight[endPoint[7][0]][endPoint[7][1]] = Calculation.whiteWeight;
				Calculation.weight[endPoint[3][0]][endPoint[3][1]] = Calculation.whiteWeight;
			}                                                                                
		}
		if (checkReverseDiagonal(x, y) > 4) {
			if(GamePanel.bwMatrix[x][y] == 1) {                                              
				Calculation.weight[endPoint[5][0]][endPoint[5][1]] = Calculation.blackWeight;
				Calculation.weight[endPoint[1][0]][endPoint[1][1]] = Calculation.blackWeight;
			}                                                                                
			else if(GamePanel.bwMatrix[x][y] == 2) {                                         
				Calculation.weight[endPoint[5][0]][endPoint[5][1]] = Calculation.whiteWeight;
				Calculation.weight[endPoint[1][0]][endPoint[1][1]] = Calculation.whiteWeight;
			}                                                                                
		}
	}

	// vertical check
	private int checkVertical(int x, int y) {
		return checkUp(x, y, GamePanel.bwMatrix[x][y]) + checkDown(x, y, GamePanel.bwMatrix[x][y]);
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

	// Horizontal check
	private int checkHorizontal(int x, int y) {
		return checkLeft(x, y, GamePanel.bwMatrix[x][y]) + checkRight(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkRight(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[2][0] = x;
				endPoint[2][1] = y;
				return 0;
			}
			return checkRight(x + 1, y, stoneColor) + 1;

		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkLeft(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[6][0] = x;
				endPoint[6][1] = y;
				return 0;
			}
			return checkLeft(x - 1, y, stoneColor) + 1;
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

	// ReverseDiagonal check
	private int checkReverseDiagonal(int x, int y) {
		return checkURU(x, y, GamePanel.bwMatrix[x][y]) + checkLLD(x, y, GamePanel.bwMatrix[x][y]);
	}

	private int checkLLD(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[5][0] = x;
				endPoint[5][1] = y;
				return 0;
			}
			return checkLLD(x - 1, y + 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkURU(int x, int y, int stoneColor) {
		try {
			if (GamePanel.bwMatrix[x][y] != stoneColor) {
				endPoint[1][0] = x;
				endPoint[1][1] = y;
				return 0;
			}
			return checkURU(x + 1, y - 1, stoneColor) + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
}
