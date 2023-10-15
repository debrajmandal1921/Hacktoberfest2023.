import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GraphIsomorphism {
    public static void main(String[] args) {
        String graph1 = "g6<<_3>";
        String graph2 = "g6<<_5>";

        boolean isIsomorphic = checkIsomorphism(graph1, graph2);

        if (isIsomorphic) {
            System.out.println("The two graphs are isomorphic.");
        } else {
            System.out.println("The two graphs are not isomorphic.");
        }
    }

    public static boolean checkIsomorphism(String graph1, String graph2) {
        try {
            Process process = Runtime.getRuntime().exec("nauty/gtools/shortg -f -q");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            process.getOutputStream().write(graph1.getBytes());
            process.getOutputStream().write(graph2.getBytes());
            process.getOutputStream().close();

            String result;
            while ((result = reader.readLine()) != null) {
                if (result.startsWith(">1")) {
                    return true; // Isomorphic
                }
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}
