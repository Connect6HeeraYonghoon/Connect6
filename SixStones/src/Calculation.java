import java.awt.Color;

public class Calculation {
	static int[][] weight = new int[19][19];
	int[][] weightStatus = new int[19][19]; // 없으면 0이고,검은색 1, 흰색 2, 착수 3
	FourDetection fourDetection = new FourDetection();
	final static int blackStone = 200;
	final static int whiteStone = 300;
	final static int blockStone = 400;
	final static int blackWeight = -100;
	final static int whiteWeight = -50;
	static int min;

	public Calculation() {
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++) {
				weight[i][j] = 0;
				weightStatus[i][j] = 0;
			}
	}

	public void doCalculation() {
		int x = Memory.points.get(Memory.points.size() - 1). i;
		int y = Memory.points.get(Memory.points.size() - 1).j;
		min = 10000000;
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
				

				fourDetection.checkFourDetection(x, y);

				for (int i = 0; i < weight.length; i++) {
					for (int j = 0; j < weight.length; j++) {
						if (min == weight[i][j]) {
							System.out.println((char) (i + 65) + " " + j);
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
}
