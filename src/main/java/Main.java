import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        do {

            String [] commands = input.split(" ");
            if (commands[0].equals("exit")) {
                if (commands.length > 1 && (Character.isDigit(commands[1].charAt(0)))) {
                    System.exit(Integer.parseInt(commands[1]));
                } else {
                    System.exit(0);
                }

            }

            System.out.println(input + ": command not found");
            System.out.print("$ ");

            input = scanner.nextLine();

        } while (!input.isEmpty());
        scanner.close();
    }
}
