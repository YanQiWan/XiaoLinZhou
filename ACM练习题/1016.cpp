#include<stdio.h>
#include<memory.h>
bool prime[41]={0,1,1,1,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0};
int num[22]={0};
int visited[22]={0};
int n;
void DFS(int i)
{
	int j;
	if(i==n&&prime[num[i]+num[1]]==1)
	{
		for(j=1;j<n;j++)
			printf("%d ",num[j]);
		printf("%d\n",num[n]);
		return;
	}
	for(j=2;j<=n;j++)
	{
		if(visited[j]==0&&prime[j+num[i]]==1)
		{
			visited[j]=1;
			num[i+1]=j;
			DFS(i+1);
			num[i+1]=0;
			visited[j]=0;
		}
	}
}
int main()
{
	int T=1;
	while(scanf("%d",&n)!=EOF)
	{
		num[1]=1;
		visited[1]=0;
		printf("Case %d:\n",T);
		DFS(1);
		printf("\n");
		T++;
	}
	return 0;
}