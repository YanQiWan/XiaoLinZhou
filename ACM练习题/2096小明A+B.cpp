#include<stdio.h>
int main()
{
	long long A,B,result;
	int T;
	scanf("%d",&T);
	while(T--)
	{
		scanf("%lld%lld",&A,&B);
		result=(A+B)%100;
		printf("%lld\n",result);
	}
	return 0;
}