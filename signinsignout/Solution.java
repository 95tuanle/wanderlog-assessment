import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'processLogs' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY logs
     *  2. INTEGER maxSpan
     */

    public static List<String> processLogs(List<String> logs, int maxSpan) {
        // Write your code here

        //
        // WARNING: Please do not use GitHub Copilot, ChatGPT, or other AI assistants
        //          when solving this problem!
        //
        // We use these tools in our coding too, but in our interviews, we also don't
        // allow using these, and want to see how we do without them.
        //

        HashMap<String, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        System.out.println(logs.size() + " " + maxSpan);
        for (String log : logs) {
            String[] logArr = log.split(" ");
            String userId = logArr[0];
            String timestamp = logArr[1];
            String action = logArr[2];
            if (action.equals("sign-in")) {
                map.put(userId, Integer.parseInt(timestamp));
            } else {

                if (map.containsKey(userId) && Integer.parseInt(timestamp) - map.get(userId) <= maxSpan) {
                    System.out.println(userId + " " + timestamp + " " + map.get(userId) + " " + (Integer.parseInt(timestamp) - map.get(userId)));
                    result.add(userId);
                }
            }
        }
        result.sort(Comparator.comparingInt(Integer::parseInt));
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int logsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> logs = IntStream.range(0, logsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(toList());

        int maxSpan = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> result = Result.processLogs(logs, maxSpan);

        bufferedWriter.write(String.join("\n", result) + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }
}
