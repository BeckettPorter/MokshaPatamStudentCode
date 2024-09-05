import java.util.LinkedList;
import java.util.Queue;

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
        int[] numMovesTaken = new int[boardsize + 1];
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitedLocs = new boolean[boardsize + 1];

        queue.add(1);
        while (currentPos != boardsize)
        {
            if (queue.isEmpty())
            {
                return -1;
            }

            currentPos = queue.remove();

            if (currentPos == boardsize)
            {
                return numMovesTaken[boardsize];
            }

            for (int i = 1; i <= 6; i++)
            {
                if (currentPos + i > boardsize)
                {
                    break;
                }

                int node = currentPos + i;

                if (doesLocationContainLadderOrSnake(node, snakes))
                {
                    node = whereDoesLadderOrSnakeGo(node, snakes);
                }
                else if (doesLocationContainLadderOrSnake(node, ladders))
                {
                    node = whereDoesLadderOrSnakeGo(node, ladders);
                }

                if (!visitedLocs[node])
                {
                    visitedLocs[node] = true;
                    queue.add(node);
                    numMovesTaken[node] = numMovesTaken[currentPos] + 1;
                }
            }
        }

        return numMovesTaken[boardsize];
    }


    private static int whereDoesLadderOrSnakeGo(int ladderStartLoc, int[][] laddersAr)
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

    private static boolean doesLocationContainLadderOrSnake(int loc, int[][] snakesAr)
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