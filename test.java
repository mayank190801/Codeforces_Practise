Certainly! Here is the equivalent Java code for the provided Python script:

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PuzzleSolver {

    private static final String FILE = "input.txt";

    // Define the possible next pipes for each pipe type
    private static final Map<Character, Set<int[]>> NEXT_PIPE = Map.of(
            '|', Set.of(new int[]{0, 1}, new int[]{0, -1}),
            '-', Set.of(new int[]{1, 0}, new int[]{-1, 0}),
            'L', Set.of(new int[]{0, -1}, new int[]{1, 0}),
            'J', Set.of(new int[]{0, -1}, new int[]{-1, 0}),
            '7', Set.of(new int[]{-1, 0}, new int[]{0, 1}),
            'F', Set.of(new int[]{0, 1}, new int[]{1, 0})
    );

    public static void main(String[] args) {
        char[][] grid;
        Map<int[], Integer> dist = new HashMap<>();
        int[] start = null;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            int y = 0;
            while ((line = br.readLine()) != null) {
                char[] row = line.trim().toCharArray();
                for (int x = 0; x < row.length; x++) {
                    if (row[x] == 'S') {
                        start = new int[]{x, y};
                        break;
                    }
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        grid[start[1]][start[0]] = 'F'; // <-- magic constant
        int pipeDist = 0;
        int[] pipe = start;
        while (!dist.containsKey(pipe)) {
            dist.put(pipe, pipeDist);
            pipeDist++;

            int x = pipe[0];
            int y = pipe[1];
            char c = grid[y][x];

            for (int[] next : NEXT_PIPE.get(c)) {
                int nx = x + next[0];
                int ny = y + next[1];
                if (!dist.containsKey(new int[]{nx, ny})) {
                    pipe = new int[]{nx, ny};
                    break;
                }
            }
        }

        System.out.println(pipeDist / 2);

        // How many tiles are enclosed by the loop?
        int tileCount = 0;
        for (int y = 0; y < grid.length; y++) {
            int parity = 0;
            for (int x = 0; x < grid[y].length; x++) {
                if (!dist.containsKey(new int[]{x, y})) {
                    if (parity % 2 == 1) {
                        tileCount++;
                    }
                    continue;
                }

                char c = grid[y][x];
                if (c == '|' || c == 'L' || c == 'J') {
                    parity++;
                }
            }
        }

        System.out.println(tileCount);
    }
}
```

Note: Please ensure that you replace the placeholder `/* code to read the grid from file */` with the appropriate code to read the grid from the file in Java.
