/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Beckett Porter
 *
 */

public class MokshaPatam {

    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {

        int currentPos = 1;
        int numMovesTaken = 0;

        while (currentPos != boardsize)
        {
            int closestLadderDistance = getDistanceToClosestLadder(currentPos, ladders);

            // If there is a ladder between current position and end of board.
            if (closestLadderDistance != -1)
            {
                for(int i = 6; i >= 1; i--)
                {
                    // Checks if ladder is within 6 of the current pos, and if so, goes straight to it.
                    if (closestLadderDistance <= 6)
                    {
                        currentPos = whereDoesLadderGo(currentPos +
                                closestLadderDistance, ladders);
                        numMovesTaken++;
                        break;
                    }
                    if (!doesLocationContainSnake(currentPos + i, snakes))
                    {
                        currentPos += i;
                        numMovesTaken++;
                        break;
                    }
                }
            }
            else
            {
                for(int i = 6; i >= 1; i--)
                {
                    if (!doesLocationContainSnake(currentPos + i, snakes) && currentPos + i <= boardsize)
                    {
                        currentPos += i;
                        numMovesTaken++;

                        break;
                    }
                }
            }
        }

        return numMovesTaken;
    }


    private static int whereDoesLadderGo(int ladderStartLoc, int[][] laddersAr)
    {
        int endLoc = -1;

        for (int i = 0; i < laddersAr.length; i++)
        {
            if (ladderStartLoc == laddersAr[i][0])
            {
                endLoc = laddersAr[i][1];
            }

        }
        return endLoc;
    }


    private static int getDistanceToClosestLadder(int startLocation, int[][] laddersAr)
    {
        int currentClosestLadderPos = Integer.MAX_VALUE;
        boolean foundLadder = false;

        for (int i = 0; i < laddersAr.length; i++)
        {
            int ladderPos = laddersAr[i][0];
            if (ladderPos > startLocation && ladderPos - startLocation < currentClosestLadderPos)
            {
                currentClosestLadderPos = ladderPos;
                foundLadder = true;
            }
        }

        if (foundLadder)
        {
            return currentClosestLadderPos - startLocation;
        }
        // Return -1 if can't find ladder (would mean they are near the end).
        return -1;
    }

    private static boolean doesLocationContainSnake(int loc, int[][] snakesAr)
    {
        for (int i = 0; i < snakesAr.length; i++)
        {
            if (snakesAr[i][0] == loc) {
                return true;
            }
        }
        return false;
    }
}