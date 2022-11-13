import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Main {
    private static final int PORT = 8989;
    private static final File FILE_TSV = new File("categories.tsv");

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> productsAndCategories = readerTSV(FILE_TSV);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            Categories categories = new Categories();
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    PurchaseInfo info = mapper.readValue(in.readLine(), PurchaseInfo.class);
                    categories.setCategoryFromTSV(info.getTitle(), productsAndCategories);
                    categories.setSum(info.getSum());
                    categories.setCategories();
                    out.println(categories.calcMaxCategory(categories.getCategories()));
                }
            }
        } catch (
                IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }


    }

    static Map<String, String> readerTSV(File fileTSV) {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileTSV))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String product = line.trim().split("\t")[0];
                String category = line.trim().split("\t")[1];
                map.put(product, category);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}