import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {

    private static Statistics statistics;
    private static final Scanner scanner = new Scanner(System.in);

    private static List<String> words = new ArrayList<>();
    private static final List<Character> lettersInWord = new ArrayList<>();

    private static int errorCount = 0;
    private static final int MAX_ERROR = 6;
    private static final Path Path_TO_WORDS_FILE = Paths.get("resources", "Words.txt");


    private static final String TRYAGAIN_MESSAGE = "Хотите попробовать еще раз? Введите да, чтобы начать, или любой другой символ для выхода";
    private static final String GAMESTARTPRINT_MESSAGE = "Игра начинается, загадано слово:";
    private static final String THANKSFORGAME_MESSAGE = "Спасибо за игру";
    private static final String WIN_MESSAGE = "Поздравляю, вы победили";
    private static final String REPEATED_MESSAGE = "Эта буква уже вводилась, попробуйте другую.";
    private static final String USED_LETTERS = "Список использованных букв: ";
    private static final String FIRST_GAME_MESSAGE = "Вы хотите начать новую игру? Введите да, чтобы начать, или другой символ для выхода";
    private static final String WORD_WAS_MESSAGE = "Было загаданно слово ";
    private final static String PLAY = "да";
    private final static String FILE_NOT_FOUND_MESSAGE = "Файл не найден";

    private static StringBuilder resultWord;



    public static void main(String[] args) {

        statistics = new Statistics();
        Statistics.printStatistic();

        System.out.println(FIRST_GAME_MESSAGE);

        boolean playGame = scanner.next().equalsIgnoreCase(PLAY);

        while (playGame) {

            reserGameStateCounters();
            System.out.println(GAMESTARTPRINT_MESSAGE);
            playGame();
            System.out.println(TRYAGAIN_MESSAGE);
            playGame = scanner.next().equalsIgnoreCase(PLAY);
        }

        System.out.println(THANKSFORGAME_MESSAGE);

        Statistics.saveStatisticToFile();
    }
    private static void reserGameStateCounters(){
        errorCount = 0;
        lettersInWord.clear();
    }

    private static String getRandomWordFromFile(){
        loadWordsFromFile();

        return takeRandomWord();
    }

    private static StringBuilder makeMaskOnWord(String word) {
        StringBuilder mask = new StringBuilder();
        mask.append("*".repeat(word.length()));
        return mask;
    }

    private static void playGame() {

        String word = getRandomWordFromFile();
        List<Character> wordCharList = convertWordToCharList(word);
        System.out.println();
        resultWord = makeMaskOnWord(word);
        System.out.println(resultWord);

        System.out.println();


        while (!isGameFinished(word)) {

            createListOfUsedLetters();
            char letter = Character.toLowerCase(inputLetter());
            if(!checkLetter(letter,wordCharList)){
                continue;
            }
            lettersInWord.add(letter);
            System.out.println(resultWord);

        }
        finalizeGame(word);
        updateStatisticsCounters(word);

    }

    private static boolean isGameFinished(String word) {
        return resultWord.toString().equals(word) || errorCount >= MAX_ERROR;
    }

    private static boolean checkLetter(char letter, List<Character> wordCharList){

        if (isLetterRepeated(letter)) {
            System.out.println(REPEATED_MESSAGE);
            return false;
        }

        incrementErrorCountIfIncorrectLetter(letter, wordCharList);

        if (isIncorrectGuess(letter,wordCharList)) {
            printHangmanPhraseAndPicture(errorCount);
        }

        recordGuessedLetterInResultWord(letter, wordCharList);
        System.out.println();
        return true;
    }



    private static void finalizeGame(String word){
        if (resultWord.toString().equals(word)) {
            System.out.println(WIN_MESSAGE);

        } else if (errorCount >= MAX_ERROR) {
            System.out.println(WORD_WAS_MESSAGE + word);
        }
    }

    private static void updateStatisticsCounters(String word){
        if (resultWord.toString().equals(word)) {
            statistics.incrementWinCount();
        } else {
            statistics.incrementLoseCount();
        }
    }

    private  static boolean isIncorrectGuess(char letter, List<Character> wordCharList ){
        return !(lettersInWord.contains(letter)) && !(wordCharList.contains(letter));
    }

    private static void loadWordsFromFile(){
        try {
            List<String> wordsInRandomcase = Files.readAllLines(Path_TO_WORDS_FILE);
            words= wordsInRandomcase.stream().map(String::toLowerCase).toList();
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND_MESSAGE);
        }
    }
    private static String takeRandomWord() {
        Random random = new Random();
        int randomIndexFromCollection = random.nextInt(words.size());
        return words.get(randomIndexFromCollection);
    }

    private static List<Character> convertWordToCharList(String word) {
        ArrayList<Character> wordChar = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            wordChar.add(word.charAt(i));

        }
        return wordChar;
    }



    private static char inputLetter() {
        return scanner.next().charAt(0);
    }

    private static void createListOfUsedLetters() {
        if (!(lettersInWord.isEmpty())) {
            System.out.println(USED_LETTERS);
            for (Character letter : lettersInWord) {
                System.out.print(letter + ", ");
            }
            System.out.println();
        }
    }

    private static boolean isLetterRepeated(char letter) {
        return lettersInWord.contains(letter);
    }

    private static void incrementErrorCountIfIncorrectLetter(char letter, List<Character> wordCharList) {
        if (!(wordCharList.contains(letter))) {
            errorCount++;
        }
    }

    private static void recordGuessedLetterInResultWord(char letter, List<Character> wordAsCharacterRepresentation) {
        for (int i = 0; i < wordAsCharacterRepresentation.size(); i++) {
            if (wordAsCharacterRepresentation.get(i) == letter) {
                resultWord.setCharAt(i, letter);
            }
        }
    }

    private static void printHangmanPhraseAndPicture(int errorCount) {

        HangmanResultPharase.printPhrase(errorCount);
        System.out.println();
        HangManPicture.printPicture(errorCount - 1);
    }

}