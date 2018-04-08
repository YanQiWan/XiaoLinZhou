#include<stdio.h>
#include<iostream>
using namespace std;
struct Trade{
	int F;
	int J;
	double scale;
}trade[1005];
void order(int N);
int main()
{
	int M,N,i;
	double sum;
	while(scanf("%d %d",&M,&N)!=EOF&&M!=-1&&N!=-1)
	{
		for(i=1;i<=N;i++)
		{
			scanf("%d %d",&trade[i].J,&trade[i].F);
			trade[i].scale=float(trade[i].J)/float(trade[i].F);
		}
		order(N);
		i=1,sum=0;
		while(i<=N&&M>0)
		{
			if(M>=trade[i].F)
				sum+=trade[i].J;
			else
				sum+=M*(float(trade[i].J)/float(trade[i].F));
			M-=trade[i].F;
			i++;
		}
		printf("%.3f\n",sum);
	}
	return 0;
}
void order(int N)
{
	int i, j;
	Trade t;
	int flag;
	for (i = 1; i<=N; i++)
	{
		flag = 0;
		for (j = 1; j<=N - i; j++)
			if (trade[j + 1].scale> trade[j].scale)
			{
				t = trade[j + 1];
				trade[j + 1] = trade[j];
				trade[j] = t;
				flag = 1;
			}
		if (flag == 0)break;
	}
}