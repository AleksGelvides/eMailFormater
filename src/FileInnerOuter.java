import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInnerOuter {

    public static void copyAndFormating(String filename, String outDir, String outFileName) throws IOException {
        String mNoFormat = inputFiles(filename);
        outputFiles(formater(mNoFormat), outDir, outFileName);
    }

    private static String inputFiles(String fileName) throws IOException {
        String mails = "";
        FileInputStream fis = new FileInputStream(fileName);
        int i;
        while ((i = fis.read()) != -1){
            mails += String.valueOf((char)i);
        }
        fis.close();
        return mails;
    }

    private static String[] formater(String mNoFormat){
        Pattern p = Pattern.compile(".*?(\\Wru|\\Wcom|\\Wua)");
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

    private static void outputFiles(String[] arr, String outDir, String fileName) throws IOException{
        String puth = outDir.concat(fileName);
        FileOutputStream fos = new FileOutputStream(fileName);
        for(int i = 0; i < arr.length; i++){
            fos.write(arr[i].toString().getBytes());
            fos.write("\n".getBytes());
        }
        fos.close();
    }
}
