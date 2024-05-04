#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// Define token types
typedef enum {
    TOKEN_PLUS,
    TOKEN_MINUS,
    TOKEN_MULTIPLY,
    TOKEN_DIVIDE,
    TOKEN_LPAREN,
    TOKEN_RPAREN,
    TOKEN_NUMBER,
    TOKEN_END,
    TOKEN_INVALID
} TokenType;

// Define token structure
typedef struct {
    TokenType type;
    int value; // Only used for TOKEN_NUMBER
} Token;

// Define stack structure
typedef struct {
    int top;
    int capacity;
    int* array;
} Stack;

// Initialize and manipulate stack
Stack* createStack(int capacity) {
    Stack* stack = (Stack*)malloc(sizeof(Stack));
    stack->capacity = capacity;
    stack->top = -1;
    stack->array = (int*)malloc(stack->capacity * sizeof(int));
    return stack;
}

bool isEmpty(Stack* stack) {
    return stack->top == -1;
}

void push(Stack* stack, int item) {
    stack->array[++stack->top] = item;
}

int pop(Stack* stack) {
    if (!isEmpty(stack))
        return stack->array[stack->top--];
    return -1;
}

int peek(Stack* stack) {
    if (!isEmpty(stack))
        return stack->array[stack->top];
    return -1;
}

// Parse input expression using SLR parsing
bool parseInput(const char* input) {
    // Implement SLR parsing logic here
    // For simplicity, we'll just check if the input has balanced parentheses
    Stack* stack = createStack(strlen(input));
   
    for (int i = 0; i < strlen(input); i++) {
        if (input[i] == '(')
            push(stack, i);
        else if (input[i] == ')') {
            if (isEmpty(stack)) {
                free(stack->array);
                free(stack);
                return false;
            }
            pop(stack);
        }
    }
   
    bool result = isEmpty(stack);
    free(stack->array);
    free(stack);
    return result;
}

int main() {
    char input[100];
    printf("Enter an arithmetic expression: ");
    scanf("%s", input);

    if (parseInput(input)) {
        printf("Input string recognized!\n");
    } else {
        printf("Input string not recognized!\n");
    }

    return 0;
}
