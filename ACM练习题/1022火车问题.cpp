#include<stdio.h>
#include<stack>
#include<string.h>
#include<iostream>
using namespace std;
char O1[20];
char O2[20];
int n;
bool output[60];
stack<int> train;
int main()
{
	int i1,i2,j,i;
	train.push(100);
	while(scanf("%d%s%s",&n,&O1,&O2)!=EOF)
	{
		i1=0,i2=0,j=0;
		while(train.top()!=100)
			train.pop();
		while(true)
		{
			if(i2==n)
			{
				printf("Yes.\n");
				for(i=0;i<2*n;i++)
					if(output[i])
						printf("out\n");
					else
						printf("in\n");
				printf("FINISH\n");
				break;
			}
			if(i1==n&&train.top()!=O2[i2])
			{
				printf("No.\n");
				printf("FINISH\n");
				break;
			}
			if(train.top()==O2[i2])
			{
				i2++;
				train.pop();
				output[j++]=1;
			}
			else
			{
				train.push(O1[i1++]);
				output[j++]=0;
			}
		}
	}
	return 0;
}
