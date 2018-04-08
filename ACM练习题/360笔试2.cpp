#include<iostream>
#include<stdio.h>
using namespace std;
long factorial(long n)
{
	long result=1;
	for(long i=2;i<=n;i++)
		result*=i;
	return result;
}
long pow(long a,long b)
{
	long result=1;
	for(long i=1;i<=b;i++)
		result*=a;
	return result;
}

long permutations(long n,long k)
{
	return factorial(n)/factorial(n-k);
}
long putRose(long n,long k)
{
	if(k==1)
		return 1;
	else if(n==k)
		return factorial(n);
	else
		return k*((putRose(n-1,k)%772235+putRose(n-1,k-1)%772235)%772235);
}

int main()
{
	long N,K;
	while(scanf("%lld%lld",&N,&K)!=EOF)
	{
		long num = permutations(N,K)*pow(K,N-K);
		printf("%lld\n",num);
	}
	system("PAUSE");
	return 0;
}
