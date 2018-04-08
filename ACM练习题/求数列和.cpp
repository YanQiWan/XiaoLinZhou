#include<stdio.h>
int main()
{
	int n;
	double num[55];
	double a=2,b=1,sum=0,t;
	for(int i=1;i<=50;i++)
	{
		sum+=a/b;
		num[i]=sum;
		t=a;
		a=a+b;
		b=t;
	}
	while(scanf("%d",&n)!=EOF)
		printf("%.6f\n",num[n]);
	return 0;
}