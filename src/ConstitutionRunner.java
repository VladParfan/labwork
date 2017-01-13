package file;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConstitutionRunner {

    public static void main(String[] args) throws FileNotFoundException {

        String path = "src/file/konstytucja.txt";
        Scanner input = new Scanner(System.in);
        int choise, choiseFrom = 0, choiseTo = 0;

        Constitution cn1 = new Constitution();

        String strFromFile = cn1.loadData(path);
        strFromFile = cn1.validate(strFromFile);
        cn1.parse(strFromFile);

        do {
            System.out.println("1 - Article search \n2 - Articles search \n3 - Chapter");
            System.out.print("Choose type of searching:");
            choise = input.nextInt();

            switch (choise) {
                case 1:
                    do {
                        System.out.print("Enter an Article number:");
                        choise = input.nextInt();
                        if (choise > 243 || choise < 0) {
                            System.out.println("This article does not exist. Available articles 1 - 243");
                        } else {
                            cn1.search(choise);
                        }
                    } while (choise > 243 || choise < 0);
                    break;
                case 2:
                    System.out.print("Show articles from ( please enter number) :");
                    choiseFrom = input.nextInt();
                    do {
                        System.out.print("to(please enter number):");
                        choiseTo = input.nextInt();
                    } while (choiseTo < choiseFrom);

                    for (int i = choiseFrom; i <= choiseTo; i++) {
                        cn1.search(i);
                    }
                    break;
                case 3:
                    System.out.print("Enter Chapter number :");
                    choise = input.nextInt();
                    cn1.searchChapter(choise);
                    break;

            }
            System.out.print("Continue search - 1 / End - 0:");
            choise = input.nextInt();
        } while (choise != 0);
    }
}
