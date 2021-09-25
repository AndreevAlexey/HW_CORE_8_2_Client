import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    // ввод ответа
    private static String getAnswer() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 28989;
        int clientPort;
        String resp;

        try(Socket clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            // порт
            clientPort = Integer.parseInt(in.readLine());
            System.out.println("port: " + clientPort);
            // диалог с сервером, до получения признака конца диалога
            while(true) {
                // сообщение сервреа
                resp = in.readLine();
                // проверка на окончание диалога
                if("End dialog".equals(resp)) {
                    break;
                }
                // вывод вопроса в консоль
                System.out.println(resp);
                // отправка ответа на сервер
                out.println(getAnswer());
            }
            // результат
            resp = in.readLine();
            System.out.println(resp);

        } catch(IOException exp) {
            exp.printStackTrace();
        }
    }
}
