#include <iostream>
#include <vector>
#include <memory>

// Base class for a generic logic gate
class LogicGate {
public:
    virtual bool evaluate() const = 0;
};

// Class for the AND gate
class AndGate : public LogicGate {
private:
    bool input1;
    bool input2;

public:
    AndGate(bool input1, bool input2) : input1(input1), input2(input2) {}

    virtual bool evaluate() const override {
        return input1 && input2;
    }
};

// Class for the OR gate
class OrGate : public LogicGate {
private:
    bool input1;
    bool input2;

public:
    OrGate(bool input1, bool input2) : input1(input1), input2(input2) {}

    virtual bool evaluate() const override {
        return input1 || input2;
    }
};

// Class for the NOT gate
class NotGate : public LogicGate {
private:
    bool input;

public:
    NotGate(bool input) : input(input) {}

    virtual bool evaluate() const override {
        return !input;
    }
};

// Function to merge gates dynamically
std::unique_ptr<LogicGate> mergeGates(const std::vector<std::unique_ptr<LogicGate>>& gates) {
    // For simplicity, assume the user is merging two gates only
    if (gates.size() != 2) {
        std::cerr << "Merge functionality currently supports merging two gates only." << std::endl;
        return nullptr;
    }

    bool input1 = gates[0]->evaluate();
    bool input2 = gates[1]->evaluate();

    // XOR gate logic
    bool result = (input1 || input2) && !(input1 && input2);

    return std::make_unique<AndGate>(input1, input2);
}

int main() {
    // User input for AND gate
    bool input1, input2;
    std::cout << "Enter input1 for AND gate (1 for true, 0 for false): ";
    std::cin >> input1;
    std::cout << "Enter input2 for AND gate (1 for true, 0 for false): ";
    std::cin >> input2;

    AndGate andGate(input1, input2);
    std::cout << "AND Gate: " << andGate.evaluate() << std::endl;

    // User input for OR gate
    std::cout << "Enter input1 for OR gate (1 for true, 0 for false): ";
    std::cin >> input1;
    std::cout << "Enter input2 for OR gate (1 for true, 0 for false): ";
    std::cin >> input2;

    OrGate orGate(input1, input2);
    std::cout << "OR Gate: " << orGate.evaluate() << std::endl;

    // User input for NOT gate
    std::cout << "Enter input for NOT gate (1 for true, 0 for false): ";
    std::cin >> input1;

    NotGate notGate(input1);
    std::cout << "NOT Gate: " << notGate.evaluate() << std::endl;

    // Merge gates dynamically
    std::vector<std::unique_ptr<LogicGate>> gatesToMerge;
    int numGatesToMerge;

    std::cout << "Enter the number of gates you want to merge: ";
    std::cin >> numGatesToMerge;

    for (int i = 0; i < numGatesToMerge; ++i) {
        std::string gateType;
        std::cout << "Enter gate type (AND, OR, NOT): ";
        std::cin >> gateType;

        // Create gate based on user input (for simplicity, assuming the user provides valid input)
        if (gateType == "AND") {
            std::cout << "Enter input1 for AND gate: ";
            std::cin >> input1;
            std::cout << "Enter input2 for AND gate: ";
            std::cin >> input2;
            gatesToMerge.push_back(std::make_unique<AndGate>(input1, input2));
        } else if (gateType == "OR") {
            std::cout << "Enter input1 for OR gate: ";
            std::cin >> input1;
            std::cout << "Enter input2 for OR gate: ";
            std::cin >> input2;
            gatesToMerge.push_back(std::make_unique<OrGate>(input1, input2));
        } else if (gateType == "NOT") {
            std::cout << "Enter input for NOT gate: ";
            std::cin >> input1;
            gatesToMerge.push_back(std::make_unique<NotGate>(input1));
        }
    }

    // Merge gates
    auto mergedGate = mergeGates(gatesToMerge);

    if (mergedGate) {
        std::cout << "Merged Gate: " << mergedGate->evaluate() << std::endl;
    } else {
        std::cerr << "Failed to merge gates." << std::endl;
    }

    return 0;
}
