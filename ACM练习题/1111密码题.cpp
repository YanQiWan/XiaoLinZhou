#include<stdio.h>
#include<math.h>
long long Xr,Xi,Br,Bi;
long long a[110];
bool flag=0;
void Divide(int &k,long long xr,long long xi,long long br,long long bi)
{
	int i;
	int t=pow(br,2)+pow(bi,2);
	if(k>100)
		return;
	if(xr==0&&xi==0)
	{
      	flag=1;
      	return;
    }
	for(i=0;i*i<t;i++)
	{
		long long xr1,xi1;
		xr1=(xr-i)*br+xi*bi;
		xi1=xi*br-(xr-i)*bi;
		if(xr1%t==0&&xi1%t==0)
		{
			a[k]=i;
			k++;
			Divide(k,xr1/t,xi1/t,br,bi);
		}
		if(flag)
			return;
	}
}
int main()
{
	int T,i,j,n;
	scanf("%d",&T);
	for(i=0;i<T;i++)
	{
		flag=0;n=0;
		scanf("%lld%lld%lld%lld",&Xr,&Xi,&Br,&Bi);
		Divide(n,Xr,Xi,Br,Bi);
		if(!flag)
			printf("The code cannot be decrypted.\n");
		else
		{
			printf("%lld",a[n-1]);
			for(j=n-2;j>=0;j--)
				printf(",%lld",a[j]);
			printf("\n");
		}
	}
	return 0;
}
/*
4
93 16 3 2
-935 2475 -11 -15
191 -192 11 -12
1 0 -3 2
*/
