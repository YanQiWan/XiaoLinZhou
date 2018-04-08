#include<stdio.h>
int main()
{
	int N,i,before,after,t,sum;
	while(scanf("%d",&N)!=EOF&&N!=0)
	{
		sum=0;before=0;
		for(i=1;i<=N;i++)
		{
			scanf("%d",&after);
			t=after-before;
			if(t>=0)
				sum+=6*t;
			else
				sum+=4*(-t);
			before=after;
		}
		sum+=N*5;
		printf("%d\n",sum);
	}
	return 0;
}
