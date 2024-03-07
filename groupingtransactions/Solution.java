import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'groupTransactions' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY transactions as parameter.
     */

    public static List<String> groupTransactions(List<String> transactions) {
        // Write your code here

        //
        // WARNING: Please do not use GitHub Copilot, ChatGPT, or other AI assistants
        //          when solving this problem!
        //
        // We use these tools in our coding too, but in our interviews, we also don't
        // allow using these, and want to see how we do without them.
        //
        HashMap<String, Integer> map = new HashMap<>();
        for (String transaction : transactions) map.put(transaction, map.getOrDefault(transaction, 0) + 1);
        List<String> keys = new ArrayList<>(map.keySet());
        keys.remove("prune");
        Collections.sort(keys);
        List<String> result = new ArrayList<>();
        if (map.containsKey("prune")) result.add("prune " + map.get("prune"));
        for (String key : keys) result.add(key + " " + map.get(key));
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int transactionsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> transactions = IntStream.range(0, transactionsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(toList());

        List<String> result = Result.groupTransactions(transactions);

        bufferedWriter.write(String.join("\n", result) + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }
}
