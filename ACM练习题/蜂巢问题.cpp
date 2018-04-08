#include<stdio.h>

int diff[50]={0};

int res[50]={0};

int main()
{
	int temp1=0,temp2=0;
	int i,j,n=0,a=0,b=0;
	scanf("%d",&n);
	for(i=0;i<n;i++)
	{
		scanf("%d %d",&a,&b);
		diff[i]=b-a;
	}
	for(i=0;i<n;i++)
	{
		temp1=1,temp2=2;
		for(j=1;j<=diff[i];j++)
		{
			if(j==1)
			{
				res[i]=1;
			}
			else if(j==2)
			{
				res[i]=2;
			}
			else
			{
				res[i]=temp1+temp2;
				temp1=temp2;
				temp2=res[i];
			}
		}
	}
	for(i=0;i<n;i++)
	{
		printf("%d\n",res[i]);
	}
	printf("\n");
	return 0;
}