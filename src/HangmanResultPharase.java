public class HangmanResultPharase {
    private static final String ERROR_TEMPLATE = "Совершена %s ошибка, ";
    private static final String PLAY_ERROR_TEMPLATE = ERROR_TEMPLATE + "у вас осталось жизней: %d";
    private static final String LOSE_ERROR_TEMPLATE = ERROR_TEMPLATE + "Вы проиграли";
    private static final String[] EROR_NAMES = {"первая", "вторая", "третья","четвертая", "пятая", "шестая"};
    private static final int MAX_ERROR = 6;



    public static void printPhrase(int numberOfError){
        String nameOfError = EROR_NAMES[numberOfError-1];
        if(numberOfError == MAX_ERROR){
            System.out.printf(LOSE_ERROR_TEMPLATE, nameOfError);
            System.out.println();
        }else {
            int livesRemain = MAX_ERROR - numberOfError;
            System.out.printf(PLAY_ERROR_TEMPLATE, nameOfError, livesRemain);
            System.out.println();
        }

    }


}
