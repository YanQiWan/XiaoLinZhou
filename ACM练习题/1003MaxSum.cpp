#include<iostream>
#include<stdio.h>
using namespace std;
int main()
{
	int T,N,num[100002],sum,start,end,maxsum;
	int t,i,k;
	scanf("%d",&T);
	for(t=1;t<=T;t++)
	{
		scanf("%d",&N);
		for(i=1;i<=N;i++)
			scanf("%d",&num[i]);
		sum=0,maxsum=-1001,start=1,k=1;
		for(i=1;i<=N;i++)
		{
			sum+=num[i];
			if(sum>maxsum)
			{
				end=i;
				start=k;
				maxsum=sum;
			}
			if(sum<0)
			{
				sum=0;
				k=i+1;
			}
		}
		printf("Case %d:\n%d %d %d\n",t,maxsum,start,end);
		if(t!=T)
			cout<<endl;
	}
	return 0;
}