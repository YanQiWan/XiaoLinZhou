#include<stdio.h>
int main()
{
	int A,B,n,i;
	int f[60];
	while(scanf("%d%d%d",&A,&B,&n)!=EOF&&(A!=0||B!=0||n!=0))
	{
		n%=49;
		if(n==1||n==2)
		{
			printf("1\n");
			continue;
		}
		f[1]=f[2]=1;
		for(i=3;i<=n;i++)
			f[i]=(A*f[i-1]+B*f[i-2])%7;
		printf("%d\n",f[i-1]);
	}
	return 0;
}