import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        do {

            String[] commandWithArgs = input.split(" ", 2);

            String command = commandWithArgs[0];

            List<String> arguments = new ArrayList<>();

            if(commandWithArgs.length > 1) {
                arguments.addAll(Arrays.asList(commandWithArgs).subList(1, commandWithArgs.length));
            }

            switch (command) {
                case "exit":
                    char firstArgument = arguments.getFirst().charAt(0);
                    if (Character.isDigit(firstArgument)) {
                        System.exit(Integer.parseInt(String.valueOf(firstArgument)));
                    } else {
                        System.exit(0);
                    }
                    break;
                case "echo":
                    for (String argument : arguments) {
                        System.out.print(argument);
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println(input + ": command not found");
            }


            System.out.print("$ ");

            input = scanner.nextLine();

        } while (!input.isEmpty());
        scanner.close();
    }
}
