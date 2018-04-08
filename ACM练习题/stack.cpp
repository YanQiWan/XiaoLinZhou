#include <iostream>
#include <stack>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
using namespace std;
stack<char>number;
char start[10000],end[10000];
int len1,len2;
char  output[20000];
int l1,l2;
void dfs(int k)
{
    if(l2==len1)
    {
        for(int i=0;i<2*len1;i++)
        cout<<output[i]<<' ';
        cout<<endl;
    }
    if(l1<len1)
    {
        number.push(start[l1]);
        output[k]='i';
        l1++;
        dfs(k+1);
        l1--;
        output[k]='\0';
        number.pop();
    }
    if(!number.empty()&&number.top()==end[l2])
    {
        l2++;
        output[k]='o';
        number.pop();
        dfs(k+1);
        l2--;
        number.push(end[l2]);
        output[k]=0;
    }
}
int main()
{
    while(scanf("%s %s",start,end)!=EOF)
    {
        len1=strlen(start);
        len2=strlen(end);
        if(len1!=len2)
        {
            cout<<'['<<endl;
            cout<<']'<<endl;

        }
        else
        {
            l1=0;
            l2=0;
            cout<<'['<<endl;
            dfs(0);
            cout<<']'<<endl;
        }
    }
    return 0;
}
