package org.abik.game2048;

import java.util.Random;

public class GameLogic {
    private int[][] nums;
    private Random random;

    public GameLogic() {
        random = new Random();
        nums = new int[][] { {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
        addRandomTile();
    }

    // Return the current game board
    public int[][] getBoard() {
        return nums;
    }

    // Create a new tile in a random empty cell
    private void addRandomTile() {
        boolean found = false;
        while (!found) {
            int randA = random.nextInt(4);
            int randB = random.nextInt(4);
            if (nums[randA][randB] == 0) {
                nums[randA][randB] = 2;
                found = true;
            }
        }
    }

    // Check win condition
    public boolean hasWon() {
        for (int[] row : nums) {
            for (int tile : row) {
                if (tile == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check loss condition
    public boolean hasLost() {
        int fullTiles = 0;
        for (int[] row : nums) {
            for (int tile : row) {
                if (tile != 0) {
                    fullTiles++;
                }
            }
        }

        // If all cells are filled
        if (fullTiles == 16) {
            // Check if any merge is possible
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums[i].length; j++) {
                    // Check adjacent tiles
                    if (j < nums[i].length - 1 && nums[i][j] == nums[i][j + 1]) { // Check right
                        return false; // Merge is possible
                    }
                    if (i < nums.length - 1 && nums[i][j] == nums[i + 1][j]) { // Check down
                        return false; // Merge is possible
                    }
                }
            }
        }
        // All cells are filled and no merges are possible
        return fullTiles == 16;
    }

    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    private boolean arraysEqual(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Tile movement methods
    public void moveLeft() {
        int[][] previousState = copyBoard(nums); // Save previous state
        GroupA(nums);
        MoveA(nums);
        if (!arraysEqual(previousState, nums)) { // If state changed
            addRandomTile();
        }
    }

    public void moveRight() {
        int[][] previousState = copyBoard(nums); // Save previous state
        GroupD(nums);
        MoveD(nums);
        if (!arraysEqual(previousState, nums)) { // If state changed
            addRandomTile();
        }
    }

    public void moveUp() {
        int[][] previousState = copyBoard(nums); // Save previous state
        GroupW(nums);
        MoveW(nums);
        if (!arraysEqual(previousState, nums)) { // If state changed
            addRandomTile();
        }
    }

    public void moveDown() {
        int[][] previousState = copyBoard(nums); // Save previous state
        GroupS(nums);
        MoveS(nums);
        if (!arraysEqual(previousState, nums)) { // If state changed
            addRandomTile();
        }
    }

    // Logic operations
    static void GroupA(int[][] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                if (num[i][j] != 0) {
                    // Grouping
                    if (j < 3 && num[i][j] == num[i][j + 1]) {
                        num[i][j] += num[i][j + 1];
                        num[i][j + 1] = 0;
                    } else if (j < 2 && num[i][j] == num[i][j + 2] && num[i][j + 1] == 0) {
                        num[i][j] += num[i][j + 2];
                        num[i][j + 2] = 0;
                    } else if (j < 1 && num[i][j] == num[i][j + 3] && num[i][j + 1] == 0 && num[i][j + 2] == 0) {
                        num[i][j] += num[i][j + 3];
                        num[i][j + 3] = 0;
                    }
                }
            }
        }
    }

    static void MoveA(int[][] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                if (num[i][j] !=  0) {
                    // Movement
                    if (j > 0) {
                        if (num[i][0] == 0) {
                            num[i][0] = num[i][j];
                            num[i][j] = 0;
                        } else if (j > 1 && num[i][1] == 0) {
                            num[i][1] = num[i][j];
                            num[i][j] = 0;
                        } else if (j > 2 && num[i][2] == 0) {
                            num[i][2] = num[i][j];
                            num[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    static void GroupD(int[][] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = num[i].length - 1; j >= 0; j--) {
                if (num[i][j] != 0) {
                    // Grouping
                    if (j > 0 && num[i][j] == num[i][j - 1]) {
                        num[i][j] += num[i][j - 1];
                        num[i][j - 1] = 0;
                    } else if (j > 1 && num[i][j] == num[i][j - 2] && num[i][j - 1] == 0) {
                        num[i][j] += num[i][j - 2];
                        num[i][j - 2] = 0;
                    } else if (j > 2 && num[i][j] == num[i][j - 3] && num[i][j - 1] == 0 && num[i][j - 2] == 0) {
                        num[i][j] += num[i][j - 3];
                        num[i][j - 3] = 0;
                    }
                }
            }
        }
    }

    static void MoveD(int[][] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = num[i].length - 1; j >= 0; j--) {
                if (num[i][j] !=  0) {
                    // Movement
                    if (j < 3) {
                        if (num[i][3] == 0) {
                            num[i][3] = num[i][j];
                            num[i][j] = 0;
                        } else if (j < 2 && num[i][2] == 0) {
                            num[i][2] = num[i][j];
                            num[i][j] = 0;
                        } else if (j < 1 && num[i][1] == 0) {
                            num[i][1] = num[i][j];
                            num[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    static void GroupW(int[][] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                if (num[i][j] != 0) {
                    // Grouping
                    if (i < 3 && num[i][j] == num[i + 1][j]) {
                        num[i][j] += num[i + 1][j];
                        num[i + 1][j] = 0;
                    } else if (i < 2 && num[i][j] == num[i + 2][j] && num[i + 1][j] == 0) {
                        num[i][j] += num[i + 2][j];
                        num[i + 2][j] = 0;
                    } else if (i < 1 && num[i][j] == num[i + 3][j] && num[i + 1][j] == 0 && num[i + 2][j] == 0) {
                        num[i][j] += num[i + 3][j];
                        num[i + 3][j] = 0;
                    }
                }
            }
        }
    }

    static void MoveW(int[][] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                if (num[i][j] !=  0) {
                    // Movement
                    if (i > 0) {
                        if (num[0][j] == 0) {
                            num[0][j] = num[i][j];
                            num[i][j] = 0;
                        } else if (i > 1 && num[1][j] == 0) {
                            num[1][j] = num[i][j];
                            num[i][j] = 0;
                        } else if (i > 2 && num[2][j] == 0) {
                            num[2][j] = num[i][j];
                            num[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    static void GroupS(int[][] num) {
        for (int i = num.length - 1; i >= 0; i--) {
            for (int j = 0; j < num[i].length; j++) {
                if (num[i][j] != 0) {
                    // Grouping
                    if (i > 0 && num[i][j] == num[i - 1][j]) {
                        num[i][j] += num[i - 1][j];
                        num[i - 1][j] = 0;
                    } else if (i > 1 && num[i][j] == num[i - 2][j] && num[i - 1][j] == 0) {
                        num[i][j] += num[i - 2][j];
                        num[i - 2][j] = 0;
                    } else if (i > 2 && num[i][j] == num[i - 3][j] && num[i - 1][j] == 0 && num[i - 2][j] == 0) {
                        num[i][j] += num[i - 3][j];
                        num[i - 3][j] = 0;
                    }
                }
            }
        }
    }

    static void MoveS(int[][] num) {
        for (int i = num.length - 1; i >= 0; i--)  {
            for (int j = 0; j < num[i].length; j++) {
                if (num[i][j] !=  0) {
                    // Movement
                    if (i < 3) {
                        if (num[3][j] == 0) {
                            num[3][j] = num[i][j];
                            num[i][j] = 0;
                        } else if (i < 2 && num[2][j] == 0) {
                            num[2][j] = num[i][j];
                            num[i][j] = 0;
                        } else if (i < 1 && num[1][j] == 0) {
                            num[1][j] = num[i][j];
                            num[i][j] = 0;
                        }
                    }
                }
            }
        }
    }
}
