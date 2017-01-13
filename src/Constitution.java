package file;

import java.util.ArrayList;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.regex.Pattern;

public class Constitution implements data {
    ArrayList<Article> Articles = new ArrayList<Article>();

    @Override
    public String loadData(String path) throws FileNotFoundException {
        StringBuilder str = new StringBuilder();
        File file = new File(path);

        exists(path);

        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    str.append(s);
                    str.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str.toString();
    }

    @Override
    public String validate(String str) {
        String token = "-\n";
        String replaceToken1 = "Kancelaria Sejmu";
        String replaceToken2 = "2009-11-16";
        char replaceToken3 = 65533;
        char repT3 = 160;
        String result;

        result = str.toString();
        result = result.replaceAll(token, "");
        result = result.replaceAll(replaceToken1, "");
        result = result.replaceAll(replaceToken2, "");
        result = result.replace(replaceToken3, repT3);
        return result;
    }

    @Override
    public void show(Article obj) {
        System.out.print("Chapter number: " + obj.getChapterID() + "; ");
        System.out.println(" Article number: " + obj.getID());
        System.out.print("Full text: " + obj.getText());
    }

    @Override
    public void search(int number) {
        int objIndex = 0;
        for (Article temp : Articles) {
            if (temp.getID() == number) {
                objIndex = Articles.indexOf(temp);
                break;
            }
        }
        show(Articles.get(objIndex));
    }

    public void searchChapter(int choise) {
        for (Article temp : Articles) {
            if (temp.getChapterID() == choise) {
                show(temp);
            }
        }

    }

    @Override
    public void parse(String str) {
        StringBuilder tempStr = new StringBuilder(str);
        String chapterToken = "Rozdzial ";
        String articleToken = "Art. ";
        String tokenLine;
        String chTokenLine;
        int chapterId;
        String cutArticleStr, cutChapterStr;

        int startChapterIndex, endChapterIndex;
        int startArticleIndex, endArticleIndex;

        startChapterIndex = tempStr.indexOf(chapterToken);
        cutChapterStr = tempStr.substring(startChapterIndex);

        while (cutChapterStr.length() != 0) {
            cutChapterStr = cutChapterStr.replaceFirst(chapterToken, "");
            tempStr.setLength(0);
            tempStr.insert(0, cutChapterStr);
            endChapterIndex = cutChapterStr.indexOf(chapterToken);
            if (endChapterIndex != -1) {
                chTokenLine = tempStr.substring(0, endChapterIndex);
            } else {
                chTokenLine = tempStr.toString();
            }

            String[] chItem = chTokenLine.split("\\n", 2);

            switch (chItem[0]) {
                case "I":
                    chapterId = 1;
                    break;
                case "II":
                    chapterId = 2;
                    break;
                case "III":
                    chapterId = 3;
                    break;
                case "IV":
                    chapterId = 4;
                    break;
                case "V":
                    chapterId = 5;
                    break;
                case "VI":
                    chapterId = 6;
                    break;
                case "VII":
                    chapterId = 7;
                    break;
                case "VIII":
                    chapterId = 8;
                    break;
                case "IX":
                    chapterId = 9;
                    break;
                case "X":
                    chapterId = 10;
                    break;
                case "XI":
                    chapterId = 11;
                    break;
                case "XII":
                    chapterId = 12;
                    break;
                case "XIII":
                    chapterId = 13;
                    break;
                default:
                    chapterId = 0;
            }

            startArticleIndex = chTokenLine.indexOf(articleToken);
            cutArticleStr = chTokenLine.substring(startArticleIndex);

            while (cutArticleStr.length() != 0) {
                cutArticleStr = cutArticleStr.replaceFirst(articleToken, "");
                tempStr.setLength(0);
                tempStr.insert(0, cutArticleStr);
                endArticleIndex = cutArticleStr.indexOf(articleToken);
                if (endArticleIndex != -1) {
                    tokenLine = tempStr.substring(0, endArticleIndex);
                } else {
                    tokenLine = tempStr.toString();
                }
                Article tempArt = new Article(0, 0, "");
                String[] item = tokenLine.split("\\.", 2);
                tempArt.setChapterID(chapterId);
                tempArt.setID(Integer.parseInt(item[0]));
                tempArt.setText(item[1]);
                Articles.add(tempArt);
                cutArticleStr = cutArticleStr.replaceFirst(Pattern.quote(tokenLine), "");
            }
            cutChapterStr = cutChapterStr.replaceFirst(Pattern.quote(chTokenLine), "");
        }
    }

    private static void exists(String path) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }
}
