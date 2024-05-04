#include <iostream>
#include <string>

using namespace std;

string input;
int i,error;

void E();
void T();
void Eprime();
void Tprime();
void F();

int main()
{
    i=0;
    error=0;
    cout<<"Enter an arithmetic expression:  ";
    cin>>input;
    E();
    if(input.length()==i&&error==0)
        cout<<"String is successfully parsed\n";
    else
        cout<<"String is not parsed successfully\n";
    return 0;
}

void E()
{
    T();
    Eprime();
}

void Eprime()
{
    if(input[i]=='+')
    {
        i++;
        T();
        Eprime();
    }
}

void T()
{
    F();
    Tprime();
}

void Tprime()
{
    if(input[i]=='*')
    {
        i++;
        F();
        Tprime();
    }
}

void F()
{
    if(isalnum(input[i])) i++;
    else if(input[i]=='(')
    {
        i++;
        E();
        if(input[i]==')')
        i++;
        else error=1;
    }  
    else error=1;
}