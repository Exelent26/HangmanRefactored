class HangManPicture {

    private static final String[][] PICTURESARRAY = {
            {
                    "---------",
                    "|        ",
                    "|        ",
                    "|        ",
                    "|        ",
                    "|        ",
                    "---------",
            },
            {
                    "---------",
                    "|   |    ",
                    "|   O    ",
                    "|        ",
                    "|        ",
                    "|        ",
                    "---------",
            },
            {
                    "---------",
                    "|   |    ",
                    "|   O    ",
                    "|  /|    ",
                    "|        ",
                    "|        ",
                    "---------",
            },
            {
                    "---------",
                    "|   |    ",
                    "|   O    ",
                    "|  /|\\  ",
                    "|        ",
                    "|        ",
                    "---------",
            },
            {
                    "---------",
                    "|   |    ",
                    "|   O    ",
                    "|  /|\\  ",
                    "|  /     ",
                    "|        ",
                    "---------",
            },
            {
                    "---------",
                    "|   |    ",
                    "|   O    ",
                    "|  /|\\  ",
                    "|  / \\  ",
                    "|        ",
                    "---------",
            }

    };
    static void printPicture(int numPicture) {
        String[] picture = PICTURESARRAY[numPicture];
        for(String line : picture) {
            System.out.println(line);
        }
    }

}