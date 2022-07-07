import java.awt.Color;
import java.util.ArrayList;

public class Calculation {
	Memory decisionPoints = new Memory();
	Stones stone;
	static int[][] weight = new int[19][19];
	int[][] weightStatus = new int[19][19]; // 없으면 0이고,검은색 1, 흰색 2, 착수 3
	FourDetection fourDetection = new FourDetection();
	final static int blackStone = 200;
	final static int whiteStone = 300;
	final static int blockStone = 400;
	final static int blackWeight = -100;
	final static int whiteWeight = -50;
	final static int black2 = -20;
	final static int black3 = -30;
	final static int white2 = -15;
	final static int white3 = -25;
	int decisionCount;

	public Calculation() {
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++) {
				weight[i][j] = 0;
				weightStatus[i][j] = 0;
			}
	}

	public void doCalculation() {

		int min = 100000000;
		int x = Memory.points.get(Memory.points.size() - 1).i;
		int y = Memory.points.get(Memory.points.size() - 1).j;

		if (Memory.points.size() <= Frame.blockCount) {
			weightStatus[x][y] = 3;
			weight[x][y] = blockStone;
		} else {
			try {
				if (Memory.points.get(Memory.points.size() - 1).color == Color.BLACK) { // 검은색
					for (int i = 1; i < 6; i++) {
						if (y - i >= 0)
							weight[x][y - i] += -1;
						if (x + i <= 18 && y - i >= 0)
							weight[x + i][y - i] += -1;
						if (x + i <= 18)
							weight[x + i][y] += -1;
						if (x + i <= 18 && y + i <= 18)
							weight[x + i][y + i] += -1;
						if (y + i <= 18)
							weight[x][y + i] += -1;
						if (x - i >= 0 && y + i <= 18)
							weight[x - i][y + i] += -1;
						if (x - i >= 0)
							weight[x - i][y] += -1;
						if (x - i >= 0 && y - i >= 0)
							weight[x - i][y - i] += -1;
					}

					weightStatus[x][y] = 1;
					weight[x][y] = blackStone;

				} else if (Memory.points.get(Memory.points.size() - 1).color == Color.WHITE) {// 흰색
					for (int i = 1; i < 6; i++) {
						if (y - i >= 0)
							weight[x][y - i] += 1;
						if (x + i <= 18 && y - i >= 0)
							weight[x + i][y - i] += 1;
						if (x + i <= 18)
							weight[x + i][y] += 1;
						if (x + i <= 18 && y + i <= 18)
							weight[x + i][y + i] += 1;
						if (y + i <= 18)
							weight[x][y + i] += 1;
						if (x - i >= 0 && y + i <= 18)
							weight[x - i][y + i] += 1;
						if (x - i >= 0)
							weight[x - i][y] += 1;
						if (x - i >= 0 && y - i >= 0)
							weight[x - i][y - i] += 1;
					}
					weightStatus[x][y] = 2;
					weight[x][y] = whiteStone;
				}

				fourDetection.checkFourDetection(x, y);

				if (Memory.points.size() > 2) {
					for (int i = 0; i < 19; i++) {
						for (int j = 0; j < 19; j++) {
							if (weightStatus[i][j] == 1) {
								weight[i][j] = blackStone;
							} else if (weightStatus[i][j] == 2) {
								weight[i][j] = whiteStone;
							} else if (weightStatus[i][j] == 3) {
								weight[i][j] = blockStone;
							}
							if (weight[i][j] <= blackWeight + 5 && weight[i][j] >= blackWeight - 5)
								weight[i][j] = blackWeight;
							if (weight[i][j] <= whiteWeight + 5 && weight[i][j] >= whiteWeight - 5)
								weight[i][j] = whiteWeight;
							if (min > weight[i][j]) {
								min = weight[i][j];
							}

						}
					}
				}
				
				weightSelect(min);

				decisionCount = 0;
				
				for (int i = 0; i < weight.length; i++) {
					for (int j = 0; j < weight.length; j++) {
						if (min == weight[i][j]) {
							decisionCount++;
//							System.out.println("데이터 넣기");
							Memory.decisionPoints.add(new Stones(i, j, Color.BLACK, false));
//							System.out.println(decisionCount + " 가능한 곳 " + (char) (i + 65) + " " + j);
							System.out.println(i + " " + j);
						}
					}
				}
				
				if (decisionCount < 3) {
					if (((Memory.points.size() + 1) / 2) % 2 == 0) {
						while (!Memory.decisionPoints.isEmpty()) {
//							if(Memory.points.peek().color == Color.BLACK) {
//								System.out.println("메모리에 들어있는 값 : " + Memory.points.peek().i + " " + Memory.points.peek().j);
								x = Memory.decisionPoints.peek().i;
								y = Memory.decisionPoints.peek().j;
								weight[x][y] = blackStone;
								
								for (int i = 1; i < 6; i++) {
									if (y - i >= 0)
										weight[x][y - i] += -1;
									if (x + i <= 18 && y - i >= 0)
										weight[x + i][y - i] += -1;
									if (x + i <= 18)
										weight[x + i][y] += -1;
									if (x + i <= 18 && y + i <= 18)
										weight[x + i][y + i] += -1;
									if (y + i <= 18)
										weight[x][y + i] += -1;
									if (x - i >= 0 && y + i <= 18)
										weight[x - i][y + i] += -1;
									if (x - i >= 0)
										weight[x - i][y] += -1;
									if (x - i >= 0 && y - i >= 0)
										weight[x - i][y - i] += -1;
								}
//							}
							Memory.points.add(Memory.decisionPoints.pop());
						}
					}

				}

				Memory.decisionPoints.clear();
				
//				int n = (int)Math.random()*(1) + (-5);
				
				
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
	
	public void weightSelect(int min) {
		int x = Memory.points.get(Memory.points.size() - 1).i;
		int y = Memory.points.get(Memory.points.size() - 1).j;
		
		
		if (((Memory.points.size() + 1) / 2) % 2 == 0) {
			for (int i = 1; i < 6; i++) {
				if (weight[x][y - i] == min) {
					weight[x][y - i] = min - 10;
					return;
				}
				if (weight[x + i][y - i] == min) {
					weight[x + i][y - i] = min - 10;
					return;
				}
				if (weight[x + i][y] == min) {
					weight[x + i][y] = min - 10;
					return ;
				}
				if (weight[x + i][y + i] == min) {
					weight[x + i][y + i] = min - 10;
					return ;
				}
				if (weight[x][y + i] == min) {
					weight[x][y + i] = min -10;
					return;
				}
				if (weight[x - i][y + i] == min) {
					weight[x - i][y + i] = min - 10;
					return ;
				}
				if (weight[x - i][y] == min) {
					weight[x - i][y] = min - 10;
					return ;
				}
				if (weight[x - i][y - i] == min ) {
					weight[x - i][y - i] = min - 10;
					return ;
				}
			}
		}
		
		return;
	}
}
