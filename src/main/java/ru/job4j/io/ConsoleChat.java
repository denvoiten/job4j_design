package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botAnswer = readPhrases();
        List<String> chatLog = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String help = String.format("%n\"%s\" - отключить бота%n\"%s\" - включить бота%n\"%s\" - закрыть чат", STOP, CONTINUE, OUT);
        String greeting = "Добро пожаловать в чат." + help;
        System.out.println(greeting);
        chatLog.add(greeting);
        String userText = "";
        boolean botOn = true;
        while (scanner.hasNext()) {
            userText = scanner.nextLine().toLowerCase();
            chatLog.add(userText);
            if (STOP.equals(userText)) {
                botOn = false;
                String stop = "Бот остановлен";
                System.out.println(stop);
                chatLog.add(stop);
            }
            if (CONTINUE.equals(userText) && !botOn) {
                String start = "Бот запущен";
                System.out.println(start);
                chatLog.add(start);
                botOn = true;
                continue;
            }
            if (OUT.equals(userText)) {
                scanner.close();
                String bye = "Прощай";
                chatLog.add(bye);
                System.out.println(bye);
                break;
            }
            if (botOn) {
                String answer = botAnswer.get(random.nextInt(botAnswer.size()));
                chatLog.add(answer);
                System.out.println(answer);
            }
        }
        saveLog(chatLog);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            br.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./chat.log", "./answer.txt");
        cc.run();
    }
}
