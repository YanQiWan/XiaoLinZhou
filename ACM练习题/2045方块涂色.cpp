#include<stdio.h>
#include<cstdlib>
long long output[55];
long long buffer[55];
int main()
{
	output[1]=1;output[2]=2;
	buffer[1]=1;buffer[2]=2;
	int i,n;
	for(i=3;i<=50;i++)
	{
		buffer[i]=2*buffer[i-1];
		output[i]=buffer[i]-output[i-1];	
	}
	while(scanf("%d",&n)!=EOF)
		printf("%lld\n",output[n]*3);
	return 0;
}
