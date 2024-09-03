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

        int currentPos = 0;
        int numMovesTaken = 0;

        while(currentPos != boardsize)
        {
            if (getDistanceToClosestLadder(currentPos, ladders) != -1)
            {
                numMovesTaken += calcNumDiceRollsToReachLocation(currentPos,
                        getDistanceToClosestLadder(currentPos, ladders));


                int closestLadderPos = currentPos + getDistanceToClosestLadder(currentPos, ladders);

                for (int i = 0; i < ladders.length; i++)
                {
                    if (ladders[i][0] == closestLadderPos)
                    {
                        currentPos += ladders[i][1];
                    }
                }
            }
            // Otherwise, this means that there is no ladder between the currentPos and end of board.
            else
            {
                numMovesTaken += calcNumDiceRollsToReachLocation(currentPos, boardsize);
                currentPos = boardsize;
            }
        }

        return numMovesTaken;
    }


    // Method I made to calculate the minimum number of roles needed to reach a target location from a start point.
    private static int calcNumDiceRollsToReachLocation(int startLocation, int targetLocation)
    {
        int currentIndex = startLocation;
        int numRolls = 0;

        while (currentIndex != targetLocation)
        {
            if ((targetLocation - currentIndex) >= 6)
            {
                currentIndex += 6;
                numRolls++;
            }
            else
            {
                currentIndex += (targetLocation - currentIndex);
                numRolls++;
            }
        }
        return numRolls;
    }

    private static int getDistanceToClosestLadder(int startLocation, int[][] laddersAr)
    {
        int currentClosestLadderPos = laddersAr[0][0] - startLocation;
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
}
