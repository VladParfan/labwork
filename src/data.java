package file;

import java.io.FileNotFoundException;

public interface data {
    String loadData(String path)throws FileNotFoundException;
    String validate(String str);
    void show(Article obj);
    void search(int number);
    //String search(int from, int to);
    void parse(String str);
}
