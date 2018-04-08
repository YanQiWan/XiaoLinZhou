#include<stdio.h>
int main()
{
	int N,T,m,n,a,b,result;
	while(scanf("%d",&N)!=EOF)
	{
		while(N--)
		{
			T=1;
			while(scanf("%d %d",&n,&m)!=EOF&&(m!=0||n!=0))
			{
				result=0;
				for(a=1;a<n;a++)
					for(b=a+1;b<n;b++)
						if(((a*a+b*b+m)%(a*b))==0)
							result++;
				printf("Case %d: %d\n",T,result);
				T++;
			}
			if(N)printf("\n");
		}
	}
	return 0;
}