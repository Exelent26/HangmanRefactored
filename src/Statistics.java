import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Statistics {
    private static final Path PATH_TO_STATISTICS = Paths.get("resources", "WinLoseBase.txt");

    private static int winCount;
    private static int loseCount;

    private final static String FILE_NOT_FOUND_MESSAGE = "Файл не найден";

    public Statistics() {
        loadStatistic();
    }
    public void incrementWinCount() {
        winCount++;
    }

    public void incrementLoseCount() {
        loseCount++;
    }

    private static void loadStatistic() {
        try {
            List<String> statisticInf = Files.readAllLines(PATH_TO_STATISTICS);
            winCount = Integer.parseInt(statisticInf.get(0));
            loseCount = Integer.parseInt(statisticInf.get(1));

        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND_MESSAGE);

        }
    }
    public  static void printStatistic(){
        System.out.printf("Статистика предыдущих игр \n Побед: %d \n Поражений: %d \n", winCount, loseCount);
    }

    public static void saveStatisticToFile() {
        try {
            Files.write(PATH_TO_STATISTICS, Arrays.asList(String.valueOf(winCount), String.valueOf(loseCount)));
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND_MESSAGE+ ", не могу записать статистику");
            e.printStackTrace();
        }
    }
}
