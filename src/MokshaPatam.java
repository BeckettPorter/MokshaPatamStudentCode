import java.util.LinkedList;
import java.util.Queue;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Beckett Porter
 * Completed on: 9/5/2024
 */

public class MokshaPatam
{
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes)
    {
        // Declare and initialize variables
        int currentPos = 1;
        int[] numMovesTaken = new int[boardsize + 1];
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitedLocs = new boolean[boardsize + 1];

        // Add the first position to the queue.
        queue.add(1);

        // While loop that runs as long as we haven't reached the end of the board.
        while (currentPos != boardsize)
        {
            // Check to make sure queue isn't empty (meaning the board is impossible) but if so, return -1.
            if (queue.isEmpty())
            {
                return -1;
            }

            // Remove the first value in the queue and set that as the current position.
            currentPos = queue.remove();

            // For loop to check each possible dice roll.
            for (int i = 1; i <= 6; i++)
            {
                // Check to make sure the dice roll doesn't go past the end of the board.
                if (currentPos + i > boardsize)
                {
                    break;
                }

                // Set the temp node to the current position plus the dice roll.
                int node = currentPos + i;

                // Checks for if the location has either a ladder or snake, and if so set the new location accordingly.
                if (doesLocationContainLadderOrSnake(node, snakes))
                {
                    node = whereDoesLadderOrSnakeGo(node, snakes);
                }
                else if (doesLocationContainLadderOrSnake(node, ladders))
                {
                    node = whereDoesLadderOrSnakeGo(node, ladders);
                }

                // If the temp node location hasn't been visited, set it to visited,
                // add it to the queue, and set the number of moves taken to reach the node to one
                // more than amount taken to reach the previous node.
                if (!visitedLocs[node])
                {
                    visitedLocs[node] = true;
                    queue.add(node);
                    numMovesTaken[node] = numMovesTaken[currentPos] + 1;
                }
            }
        }
        // Return the number of moves taken to reach the last position on the board.
        return numMovesTaken[boardsize];
    }

    // Helper method to check where a ladder or snake goes given an array.
    private static int whereDoesLadderOrSnakeGo(int StartLoc, int[][] Ar)
    {
        int endLoc = -1;

        for (int i = 0; i < Ar.length; i++)
        {
            if (StartLoc == Ar[i][0])
            {
                endLoc = Ar[i][1];
            }
        }
        return endLoc;
    }

    // Helper method that checks if a given location contains either a ladder or snake, given an array.
    private static boolean doesLocationContainLadderOrSnake(int loc, int[][] Ar)
    {
        for (int i = 0; i < Ar.length; i++)
        {
            if (Ar[i][0] == loc) {
                return true;
            }
        }
        return false;
    }
}