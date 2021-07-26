import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInnerOuter {
    static int num = 0;
    public void copyAndFormating(String pathFile) {
        String outfile = pathFile.concat("Formating.txt");
        try {
            InnOutStreams.outputFiles(Formater.formaterMails(InnOutStreams.inputFiles(pathFile)), outfile);
            System.out.println("Форматирование завершенно");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Возникла ошибка вывода: " + e.getMessage());
        }
    }

    private static class Formater{

        private static String[] formaterMails(StringBuilder mNoFormat){
            Pattern p = Pattern.compile("[A-aZ-z0-9].*?(\\Wru|\\Wcom|\\Wua)");
            Matcher mLenght = p.matcher(mNoFormat);
            int lenght= 0;
            while(mLenght.find()){
                lenght++;
            }
            String[] mails = new String[lenght];
            Matcher mMail = p.matcher(mNoFormat);
            for (int i = 0; i < mails.length; i++){
                if(mMail.find()){
                    int start = mMail.start();
                    int end = mMail.end();
                    mails[i] = mNoFormat.substring(start, end);
                }
            }
            return mails;
        }
    }

    private static class InnOutStreams{

        private static StringBuilder inputFiles(String fileName) throws IOException {
            StringBuilder mails = new StringBuilder("");
            FileInputStream fis = new FileInputStream(fileName);
            int i;
            while ((i = fis.read()) != -1){
                mails.append(String.valueOf((char)i));
            }
            fis.close();
            return mails;
        }

        private static void outputFiles(String[] arr, String fileName) throws IOException {
            FileOutputStream fos = new FileOutputStream(fileName);
            for(int i = 0; i < arr.length; i++){
                fos.write(arr[i].getBytes());
                fos.write("\n".getBytes());
            }
            fos.close();
        }
    }
}
