#include<stdio.h>
int main()
{
	int F[3];
	int n;
	while(scanf("%d",&n)!=EOF)
	{
		F[0]=1,F[1]=2,F[2]=1;
		for(int i=2;i<=n;i++)
		{
			F[2]=(F[1]+F[0])%3;
			F[0]=F[1];
			F[1]=F[2];
		}
		if(F[2]%3==0)
			printf("yes\n");
		else
			printf("no\n");
	}
	return 0;
}
