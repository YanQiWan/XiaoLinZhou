#include<stdio.h>
#include<algorithm>
int a[100002];
int N;
#define INT_MAX 1000000001
void Merge(int p,int q,int r)
{
	int i,j,k;
	int n1=q-p+1;
	int n2=r-q;
	int *L=new int[n1+1];
	int *R=new int[n2+1];
	for(i=0;i<n1;i++)
	{
		L[i]=a[i+p];
	}
	for(j=0;j<n2;j++)
		R[j]=a[j+q+1];
	L[n1]=INT_MAX;
	R[n2]=INT_MAX;
	i=0;
	j=0;
	for(k=p;k<=r;k++)
	{
		if(L[i]<=R[j])
			a[k]=L[i++];
		else
			a[k]=R[j++];
	}

}
void MergeSort(int p,int r)
{
	if(p<r)
	{
		int q = (p+r)/2;
		MergeSort(p,q);
		MergeSort(q+1,r);
		Merge(p,q,r);
	}
}

int main()
{
	int result,i;
	while(scanf("%d",&N)!=EOF)
	{
		result=0;
		for(i=0;i<N;i++)
		{
			scanf("%d",&a[i]);
		}
		MergeSort(0,N-1);
		if(N%2==0)
		{
			for(i=N-1;i>=0;i=i-2)
			{
				result+=a[i]-a[i-1];
			}
		}
		else
		{
			for(i=N-1;i>=1;i=i-2)
			{
				result+=a[i]-a[i-1];
			}
			result+=a[0];
		}
		printf("%d\n",result);
	}
	
	return 0;
}
