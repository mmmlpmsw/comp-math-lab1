package mmmlpmsw.comp_math.lab1;

import mmmlpmsw.comp_math.lab1.Gaussian_elimination.LinearSystem;

import java.io.*;
import java.util.Scanner;

public class InputReader {
    private Scanner scanner;
//    public InputReader() {
//        Scanner scanner = new Scanner(System.in);
//    }
    public void process() throws IOException {
        System.out.println(getHelp());
        while (true) {
            System.out.print(">>> ");
            String response = processCommand(getNextCommand());
            System.out.println(response);
        }
    }

    private String processCommand(String request) {
        if (request == null) {
            System.exit(0);
        }
        request = request.trim();
        UserCommand command = divideCommand(request);
        String result = new String("");

        switch (command.name) {
            case "exit":
                System.exit(0);
            case "file":
                try {
                    LinearSystem linearSystem = new FileInputReader().getFromFile(command.value);
                    OutputCombiner combiner = new OutputCombiner(linearSystem);
                    if (!combiner.combineOutput())
                        return "";
                    else combiner.combineOutput();
                    return "";
                } catch (InputParseException e) {
                    System.out.print(e.getMessage());
                    return "";
                }
                //todo
            case "random":
            case "enter":


            case "help":
                return getHelp();


            default:
                return "No such command: " + command.name + " please, use 'help;' to know, how to use it.";
        }
    }

    private UserCommand divideCommand(String request) {
        int spacePosition = request.indexOf(' ');
        if (spacePosition == -1) {return new UserCommand(request, null);}
        else {return new UserCommand(request.substring(0, spacePosition), request.substring(spacePosition+1));}
    }

    private String getNextCommand() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        boolean inString = false;
        loop: do {
            String raw = reader.readLine();
            if (raw == null) return null;
            char[] data = raw.toCharArray();
            for (char current : data) {
                if (current != ';' || inString) {  builder.append(current); }
                if (current == '"') { inString = !inString;}
                if (current == ';' && !inString) break loop;
            }
        } while (true);
        return builder.toString();
    }

    private String getHelp() {
        return "To enter from file - use 'file <filename>', " +
                "\nto randomize coefficients use 'random <number of unknowns>;',  " +
                "\nto enter equation coefficients from the keyboard use 'enter;' and wait for further directions, " +
                "\nto exit enter 'exit;'" +
                "\nto get help use 'help;'. All commands must end in ';'.";
    }

    private static class UserCommand {
        String name;
        String value;

        UserCommand (String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

}
