import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

abstract class LogicGate {
    public abstract boolean calculate();
}

class AndGate extends LogicGate {
    private boolean input1;
    private boolean input2;

    public AndGate(boolean input1, boolean input2) {
        this.input1 = input1;
        this.input2 = input2;
    }

    public boolean calculate() {
        return input1 && input2;
    }
}

class OrGate extends LogicGate {
    private boolean input1;
    private boolean input2;

    public OrGate(boolean input1, boolean input2) {
        this.input1 = input1;
        this.input2 = input2;
    }

    public boolean calculate() {
        return input1 || input2;
    }
}

class NotGate extends LogicGate {
    private boolean input;

    public NotGate(boolean input) {
        this.input = input;
    }

    public boolean calculate() {
        return !input;
    }
}

public class Main {
    public static LogicGate mergeGates(List<LogicGate> gates) {
        if (gates.size() != 2) {
            System.err.println("Merge functionality currently supports merging two gates only.");
            return null;
        }
        boolean input1 = gates.get(0).calculate();
        boolean input2 = gates.get(1).calculate();
        return new AndGate(input1, input2);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean input1, input2;
        System.out.println("Inputs for AND gates (1 for true, 0 for false): ");
        System.out.print("Input 1: ");
        input1 = scanner.nextBoolean();
        System.out.print("Input 2: ");
        input2 = scanner.nextBoolean();
        AndGate andGate = new AndGate(input1, input2);
        System.out.println("AND Gate: " + andGate.calculate());
        System.out.println("Inputs for OR gates (1 for true, 0 for false): ");
        System.out.print("Input 1: ");
        input1 = scanner.nextBoolean();
        System.out.print("Input 2: ");
        input2 = scanner.nextBoolean();
        OrGate orGate = new OrGate(input1, input2);
        System.out.println("OR Gate: " + orGate.calculate());
        System.out.println("Inputs for OR gates (1 for true, 0 for false): ");
        System.out.print("Input: ");
        input1 = scanner.nextBoolean();
        NotGate notGate = new NotGate(input1);
        System.out.println("NOT Gate: " + notGate.calculate());

        List<LogicGate> gatesToMerge = new ArrayList<>();
        int numGatesToMerge;
        System.out.print("Enter the number of gates you want to merge: ");
        numGatesToMerge = scanner.nextInt();
        for (int i = 0; i < numGatesToMerge; ++i) {
            System.out.print("Enter gate type (AND, OR, NOT): ");
            String gateType = scanner.next();
            
            if (gateType.equals("AND")) {
                System.out.print("Enter input1 for AND gate: ");
                input1 = Boolean.parseBoolean(scanner.next());
                System.out.print("Enter input2 for AND gate: ");
                input2 = Boolean.parseBoolean(scanner.next());
                gatesToMerge.add(new AndGate(input1, input2));
            } else if (gateType.equals("OR")) {
                System.out.print("Enter input1 for OR gate: ");
                input1 = Boolean.parseBoolean(scanner.next());
                System.out.print("Enter input2 for OR gate: ");
                input2 = Boolean.parseBoolean(scanner.next());
                gatesToMerge.add(new OrGate(input1, input2));
            } else if (gateType.equals("NOT")) {
                System.out.print("Enter input for NOT gate: ");
                input1 = Boolean.parseBoolean(scanner.next());
                gatesToMerge.add(new NotGate(input1));
            }
            
        }

        scanner.close();

        LogicGate mergedGate = mergeGates(gatesToMerge);
        if (mergedGate != null) {
            System.out.println("Merged Gate: " + mergedGate.calculate());
        } else {
            System.err.println("Failed to merge gates.");
        }
    }
}
