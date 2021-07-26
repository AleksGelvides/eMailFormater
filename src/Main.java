import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true){
            String comand;
            Scanner scanner = new Scanner(System.in);
            FileInnerOuter fio = new FileInnerOuter();
            System.out.println("Укажите какой файл нужно отформатировать");
            System.out.println("Или введите \"Выход\" что бы закрыть программу");
            System.out.print("Пропишите полный путь к файлу или команду выхода: ");
            comand = scanner.nextLine();
            if (comand.equalsIgnoreCase("выход")){
                break;
            } else
                fio.copyAndFormating(comand);
        }
    }
}
