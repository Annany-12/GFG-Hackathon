#include <iostream>
#include <vector>
#include <memory>
using namespace std;

// The main calculation for the logic gate which is compulsory to all gates.
class LogicGate {
public:
    virtual bool calculate() const = 0;
};

// Logic for AND Gate.
class AndGate : public LogicGate {
private:
    bool input1;
    bool input2;

public:
    AndGate(bool input1, bool input2) : input1(input1), input2(input2) {}

    virtual bool calculate() const override {
        return input1 && input2;
    }
};

// Logic for OR Gate.
class OrGate : public LogicGate {
private:
    bool input1;
    bool input2;

public:
    OrGate(bool input1, bool input2) : input1(input1), input2(input2) {}

    virtual bool calculate() const override {
        return input1 || input2;
    }
};

// Logic for NOT Gate.
class NotGate : public LogicGate {
private:
    bool input;

public:
    NotGate(bool input) : input(input) {}

    virtual bool calculate() const override {
        return !input;
    }
};

unique_ptr<LogicGate> mergeGates(const vector<unique_ptr<LogicGate>>& gates) {

    if (gates.size() != 2) {
        cerr << "Merge functionality currently supports merging two gates only." << endl;
        return nullptr;
    }

    bool input1 = gates[0]->calculate();
    bool input2 = gates[1]->calculate();

    bool result = (input1 || input2) && !(input1 && input2);

    return make_unique<AndGate>(input1, input2);
}

int main() {
    bool input1, input2;
    cout << "Inputs for AND gates (1 for true, 0 for false): " << endl;
    cout << "Input 1: ";
    cin >> input1;
    cout << "Input 2: ";
    cin >> input2;

    AndGate andGate(input1, input2);
    cout << "AND Gate: " << andGate.calculate() << endl;

    cout << "Inputs for OR gates (1 for true, 0 for false): " << endl;
    cout << "Input 1: ";
    cin >> input1;
    cout << "Input 2: ";
    cin >> input2;

    OrGate orGate(input1, input2);
    cout << "OR Gate: " << orGate.calculate() << endl;

    cout << "Inputs for OR gates (1 for true, 0 for false): " << endl;
    cout << "Input: ";
    cin >> input1;

    NotGate notGate(input1);
    cout << "NOT Gate: " << notGate.calculate() << endl;

   // Functionality for the merge logic/functoin.
    vector<unique_ptr<LogicGate>> gatesToMerge;
    int numGatesToMerge;

    cout << "Enter the number of gates you want to merge: ";
    cin >> numGatesToMerge;

   // Input for the merge_gates fucntion.
   
    for (int i = 0; i < numGatesToMerge; ++i) {
        string gateType;
        cout << "Enter gate type (AND, OR, NOT): ";
        cin >> gateType;


        if (gateType == "AND") {
            cout << "Enter input1 for AND gate: ";
            cin >> input1;
            cout << "Enter input2 for AND gate: ";
            cin >> input2;
            gatesToMerge.push_back(make_unique<AndGate>(input1, input2));
        } 
        
        else if (gateType == "OR") {
            cout << "Enter input1 for OR gate: ";
            cin >> input1;
            cout << "Enter input2 for OR gate: ";
            cin >> input2;
            gatesToMerge.push_back(make_unique<OrGate>(input1, input2));
        } 
        
        else if (gateType == "NOT") {
            cout << "Enter input for NOT gate: ";
            cin >> input1;
            gatesToMerge.push_back(make_unique<NotGate>(input1));
        }
    }

    auto mergedGate = mergeGates(gatesToMerge);

    if (mergedGate) {
        cout << "Merged Gate: " << mergedGate->calculate() << endl;
    } else {
        cerr << "Failed to merge gates." << endl;
    }

    return 0;
}
