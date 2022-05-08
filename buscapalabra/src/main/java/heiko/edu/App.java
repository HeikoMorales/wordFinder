package heiko.edu;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public final class App {

    final static String MAIN_DIRECTORY = "C:\\Users\\heiko\\Desktop\\files";
    Scanner scanner;
    
    private App() {
        scanner = new Scanner(System.in);

    }

    private void wordSearch() {
        System.out.println("[INPUT] Enter the word to search: ");
        String word = scanner.nextLine();

        try {
            long start = System.currentTimeMillis();
            
            ForkJoinPool pool = new ForkJoinPool();
            List<String> files = pool.invoke(new SearchFiles(MAIN_DIRECTORY, word));
            
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.SECONDS);



            long end = System.currentTimeMillis();
			System.out.println("[INFO] cost time: " + (end-start));
            printFiles(files);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printFiles(List<String> files) {
        System.out.println("[INFO] Found " + files.size() + " files.");
        for (String file : files) {
            System.out.println(file);
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.wordSearch();
    }
}
