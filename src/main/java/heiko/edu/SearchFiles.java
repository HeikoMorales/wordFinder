package heiko.edu;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SearchFiles extends RecursiveTask<List<String>> {

    String mainDirectory;
    String word;

    public SearchFiles(String mainDirectory, String word) {
        this.mainDirectory = mainDirectory;
        this.word = word;
    }

    @Override
    protected List<String> compute() {

        List<RecursiveTask<List<String>>> tasks = new LinkedList<>();
        List<String> result = new LinkedList<>();
        File[] files = new File(mainDirectory).listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                SearchFiles searchFiles = new SearchFiles(file.getAbsolutePath(), word);
                tasks.add(searchFiles);
                searchFiles.fork();

            } else {
                // find the word in the file
                try {
                    Path path = Paths.get(file.getAbsolutePath());
                    boolean find = Files.lines(path, StandardCharsets.ISO_8859_1).filter(a -> a.contains(word))
                            .findAny().isPresent();

                    /*****************************************************************************************************************
                    *   En caso de quere contar las veces que aparece el word en el archivo se puede usar:                           *
				    *   int count += Files.lines(path, StandardCharsets.ISO_8859_1).filter(a -> a.contains(palabraBuscada)).count(); *
                    ******************************************************************************************************************/
                    
                    if (find) {
                        result.add(file.getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (RecursiveTask<List<String>> task : tasks) {

            List<String> tmp = task.join();
            for (String string : tmp) {
                if (!result.contains(string)) {
                    result.add(string);
                }
            }
        }

        return result;
    }

}
