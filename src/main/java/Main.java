import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String dir = Path.of("").toAbsolutePath().toString();
        String homeDir = System.getenv("HOME");

        Set<String> availableCommands = Set.of("exit", "echo", "type", "pwd", "cd");

        do {

            String[] commandWithArgs = input.split(" ", 2);

            String command = commandWithArgs[0];

            List<String> arguments = new ArrayList<>();

            if (commandWithArgs.length > 1) {
                arguments.addAll(Arrays.asList(commandWithArgs).subList(1, commandWithArgs.length));
            }

            String firstArgument = arguments.size() > 0 ? arguments.getFirst(): null;
            String path;

            switch (command) {
                case "exit":
                    if (!arguments.isEmpty()) {
                        char firstArgumentChar = arguments.getFirst().charAt(0);
                        if (Character.isDigit(firstArgumentChar)) {
                            System.exit(Integer.parseInt(String.valueOf(firstArgumentChar)));
                        } else {
                            System.exit(0);
                        }
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
                    if (availableCommands.contains(firstArgument)) {
                        System.out.println(firstArgument + " is a shell builtin");
                    } else {
                        path = getPath(firstArgument);
                        if (path != null) {
                            System.out.println(firstArgument + " is " + path);
                        } else {
                            System.out.println(firstArgument + ": not found");
                        }
                    }
                    break;
                case "pwd":
                    System.out.println(dir);
                    break;
                case "cd":
                    if (firstArgument.equals("~")) {
                        dir = homeDir;
                        break;
                    }

                    if (!firstArgument.startsWith("/")) {
                        firstArgument = dir + "/" + firstArgument;
                    }

                    if (Files.isDirectory(Path.of(firstArgument))) {
                        dir = Path.of(firstArgument).normalize().toString();
                    } else {
                        System.out.println(command + ": "+ firstArgument + ": No such file or directory");
                    }
                    break;
                default:
                    path = getPath(command);
                    if (path != null) {
                        List<String> fullPath = new ArrayList<>();

                        fullPath.add(command);
                        fullPath.addAll(arguments);

                        Process process = Runtime.getRuntime().exec(fullPath.toArray(new String[0]));
                        process.getInputStream().transferTo(System.out);
                    } else {
                        System.out.println(command + ": command not found");
                    }
            }


            System.out.print("$ ");

            input = scanner.nextLine();

        } while (!input.isEmpty());
        scanner.close();
    }


    private static String getPath(String parameter) {
        for (String path : System.getenv("PATH").split(":")) {
            Path fullPath = Path.of(path, parameter);
            if (fullPath.toFile().exists()) {
                return fullPath.toString();
            }
        }
        return null;
    }
}
