import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Set<String> availableCommands = Set.of("exit", "echo", "type");

        do {

            String[] commandWithArgs = input.split(" ", 2);

            String command = commandWithArgs[0];

            List<String> arguments = new ArrayList<>();

            if(commandWithArgs.length > 1) {
                arguments.addAll(Arrays.asList(commandWithArgs).subList(1, commandWithArgs.length));
            }

            switch (command) {
                case "exit":
                    char firstArgumentChar = arguments.getFirst().charAt(0);
                    if (Character.isDigit(firstArgumentChar)) {
                        System.exit(Integer.parseInt(String.valueOf(firstArgumentChar)));
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
                case "type":
                    String firstArgument = arguments.getFirst();
                    if (availableCommands.contains(firstArgument)) {
                        System.out.println(firstArgument + " is a shell builtin");
                    } else {
                        System.out.println(firstArgument + ": command not found");
                    }
                    break;
                default:
                    System.out.println(command + ": command not found");
            }


            System.out.print("$ ");

            input = scanner.nextLine();

        } while (!input.isEmpty());
        scanner.close();
    }
}
