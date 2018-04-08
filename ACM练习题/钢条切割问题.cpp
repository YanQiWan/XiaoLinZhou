#include<stdio.h>
int P[11]={0,1,5,8,9,10,17,17,20,24,30};
void Cutrod(int r[],int s[],int n)
{
	r[0]=0;
	int q=-1;
	for(int i=1;i<=n;i++)
	{
		q=-1;
		for(int j=1;j<=i;j++)
		{
			if(q<P[j]+r[i-j])
			{
				q=P[j]+r[i-j];
				s[i]=j;
			}
			r[i]=q;
		}
	}
}
int main()
{
	int n;
	scanf("%d",&n);
	int r[100];
	int s[100];
	Cutrod(r,s,n);
	for(int i=1;i<=n;i++)
		printf("%d ",r[i]);
	printf("\n");
	for(i=0;i<n;i++)
		printf("%d ",s[i+1]);
	printf("\n");
	while(n>0)
	{
		printf("%d ",s[n]);
		n=n-s[n];
	}
	return 0;
}
