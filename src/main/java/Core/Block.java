package Core;

public class Block {

    Integer firstLine;

    Integer beginingInd;
    Integer endInd;

    String[] allCode;

    public Block(Interpretator interpretator) {
        this.firstLine = interpretator.getCurLine();
        this.allCode = interpretator.allCode;
    }

    public String[] getBody() {
        beginingInd = findeBegining();

        String[] body = findeBody();


        return body;
    }

    private String[] findeBody() {

        String str;
        Integer counter = 1;
        Integer i = beginingInd;

        while (counter > 0) {

            if (!allCode[i].isEmpty()) {

                if (allCode[i].charAt(0) == '}') {
                    counter--;
                } else {
                    if (allCode[i].charAt(allCode[i].length() - 1) == '{') {
                        counter++;
                    }
                }
            }
            i++;
        }

        i--;

        String[] resBody = new String[i - beginingInd];
        System.arraycopy(allCode, beginingInd, resBody, 0, i - beginingInd );

        endInd = i;

        return resBody;
    }

    private Integer findeBegining() {
        Integer strIndex = this.firstLine + 1;
        Integer a = -1;
        // находим начало блока
        while (a == -1) {
            a = allCode[strIndex].indexOf("{");
            strIndex++;
        }

        //считаем что блок начинается со след строки после "{"
        return strIndex;
    }

    public Integer getEndIndex() {
        return null;
    }
}
