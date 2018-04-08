#include<stdio.h>
#include<iostream>
using namespace std;
int main()
{
	int t,n,i,sum;
	char s[1002];
	scanf("%d",&t);
	while(t--)
	{
		scanf("%d",&n);
		scanf("%s",&s);
		sum=0;
		for(i=0;i<n;)
		{
			if(s[i]=='.')
			{
				sum++;
				i+=3;
			}
			else
				i++;
		}
		printf("%d\n",sum);
	}
	return 0;
}