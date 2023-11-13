#include <iostream>

int sum(int num1, int num2);

int max(int num1, int num2){
   int result = num1 > num2 ? num1 : num2;
   return result;
}

int main(){
    std::cout << "-------" << std::endl;
    std::cout << "Hello world!" << std::endl;
    std::cout << "-------" << std::endl;

    int number;
    char name[100] = "Annany";

    std::cout << "Number: ";
    std::cin >> number; 

    std::cout << "Entered number is " << number << std::endl;
    std::cout << "-------" << std::endl;

    std::cout << "String: " << name << std::endl;

    std::cout << "-------" << std::endl;
    if(number > 100){
        std::cout << "Greater than 100";
    }
    else if(number == 100){
        std::cout << "Equal to 100" << std::endl;
    }
    else if(number < 100){
        std::cout << "Less than 100" << std::endl;
    }
    else{
        std::cout << "Not a number" << std::endl;
    }
    std::cout << "-------" << std::endl;
    for(int i=1; i<=number; i++){
        for(int j=1; j<=i; j++){
            std::cout << "* ";
        }
        std::cout << std::endl;
    }
    std::cout << "-------" << std::endl;
    for(int i=0; name[i] != '\0'; i++){
        std::cout << name[i] << std::endl;
    }
    std::cout << "-------" << std::endl;
    int a = 10;
    int b = 10;

    int num1, num2 = 0;

    int res = sum(a, b);
    std::cout << "Sum: " << res << std::endl;
    std::cout << "Sum: " << sum(30, 50) << std:: endl;

    std::cout << "Num1: ";
    std::cin >> num1;
    std::cout << "Num2: ";
    std::cin >> num2;

    std::cout << "Sum for nums: " << sum(num1, num2) << std::endl;

    std::cout << "-------" << std::endl;
    std::cout << "Max: " << max(num1, num2) << std::endl;

    return 0;
}

int sum(int num1, int num2){
    int result = num1 + num2;
    return result;
}
