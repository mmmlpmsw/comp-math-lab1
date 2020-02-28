package mmmlpmsw.comp_math.lab1;

import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Equation;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.LinearSystem;
import static mmmlpmsw.comp_math.lab1.utils.Utilities.colorize;

import java.io.*;
import java.util.Scanner;

public class InputReader {
    private Scanner scanner;
    public InputReader() {
        scanner = new Scanner(System.in);
    }
    public void process() throws IOException {
        System.out.println(getHelp());
        while (true) {
            try {
                System.out.print("Enter the command >>> ");
                String response = processCommand(getNextCommand());
                System.out.println(response);
            } catch (InputParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String processCommand(String request) throws InputParseException {
        if (request == null) {
            System.exit(0);
        }
        request = request.trim();
        UserCommand command = divideCommand(request);

        switch (command.name) {
            case "exit":
                System.exit(0);
            case "file":
                LinearSystem linearSystem = new FileInputReader().getFromFile(command.value);
                OutputCombiner combiner = new OutputCombiner(linearSystem);
                if (!combiner.combineOutput())
                    return "";
                return "";

            case "random":
                try {
                    if (command.value.equals("0") || command.value == "")
                        throw new InputParseException(colorize("[[RED]]Incorrect command, try again[[RESET]]\n"));
                    int numberOfUnknowns = parse(command.value);
                    ParseStringToEquation.check(numberOfUnknowns);
                    Equation[] equations = new Equation[numberOfUnknowns];
                    System.out.println("Coefficients: ");
                    for (int i = 0; i < numberOfUnknowns; i++) {
                        double[] coefficients = new double[numberOfUnknowns + 1];
                        for (int j = 0; j < numberOfUnknowns + 1; j++) {
                            double coefficient = Math.random() * 200 - 100;
                            coefficient = Math.round(coefficient * 100) / 100.0;
                            coefficients[j] = coefficient;
                            System.out.print(coefficient + " ");
                        }
                        System.out.println();
                        equations[i] = new Equation(coefficients);
                    }
                    LinearSystem system = new LinearSystem(equations);
                    OutputCombiner outputCombiner = new OutputCombiner(system);
                    outputCombiner.combineOutput();
                    return "";
                } catch (NullPointerException e) {
                    throw new InputParseException(colorize("[[RED]]Enter the number of the unknowns.[[RESET]]"));
                }

            case "enter": {
                System.out.print("Enter the number of unknowns >>> ");
                int count = parse(scanner.nextLine());
                ParseStringToEquation.check(count);
                System.out.println("In each string enter the coefficients of the system >>> ");
                Equation[] equations = new Equation[count];
                for (int i = 0; i < count; i++)
                    equations[i] = ParseStringToEquation.trimStringToEquation(count, scanner.nextLine());
                LinearSystem system = new LinearSystem(equations);
                OutputCombiner outputCombiner = new OutputCombiner(system);
                outputCombiner.combineOutput();
                return "";
            }

            case "help":
                return getHelp();

            default:
                return "No such command: " + command.name + " please, use 'help;' to know, how to use it.";
        }
    }

    private UserCommand divideCommand(String request) {
        int spacePosition = request.indexOf(' ');
        if (spacePosition == -1) {return new UserCommand(request, null);}
        else return new UserCommand(request.substring(0, spacePosition), request.substring(spacePosition+1));
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
        return colorize( "To enter from file - use '[[BLUE]]file <filename>;'[[RESET]], " +
                "\nto randomize coefficients use '[[BLUE]]random <number of unknowns>;[[RESET]]',  " +
                "\nto enter equation coefficients from the keyboard use '[[BLUE]]enter;[[RESET]]' and wait for further directions, " +
                "\nto exit enter '[[BLUE]]exit;[[RESET]]'" +
                "\nto get help use '[[BLUE]]help;[[RESET]]'. All commands must end in '[[BRIGHT_CYAN]];[[RESET]]'.");
    }

    private int parse (String string) throws InputParseException {
        try {
            Integer number = Integer.parseInt(string);
            return number;
        } catch (NumberFormatException e) {
            throw new InputParseException(colorize("[[RED]]Error: not an integer.[[RESET]]"));
        }
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