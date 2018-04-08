#include<iostream>
#include<stdio.h>
using namespace std;
int direction[4]={0,1,2,3};
char resultdir[4]={'N','E','S','W'};
int main()
{
	int n,i,face;
	char way[1002];
	while(scanf("%d",&n)!=EOF)
	{
		face=0;
		cin>>way;
		for(i=0;i<n;i++)
		{	
			if(way[i]=='L')
				face=(4+face-1)%4;
			else
				face=(face+1)%4;
		}
		cout<<resultdir[face]<<endl;
	}
	return 0;
}