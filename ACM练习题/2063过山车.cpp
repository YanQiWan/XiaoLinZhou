#include<stdio.h>
#include<string.h>
const int MAX=510;
int M,N;
bool Arc[MAX][MAX];
int ymatch[MAX],xmatch[MAX];
bool ycheck[MAX];
bool SearchPath(int x)
{
	for(int j=1;j<=M;j++)
		if(Arc[j][x]&&!ycheck[j])
		{
			ycheck[j]=true;
			if(ymatch[j]==-1||SearchPath(ymatch[j]))
			{
				ymatch[j]=x,xmatch[x]=j;
				return true;
			}
		}
	return false;
}
int MaxMatch()
{
	int max=0;int i;
	for(i=1;i<=N;i++)
	{			
		memset(ycheck,0,sizeof(ycheck));
		if(SearchPath(i))max++;
	}
	return max;
}
int main()
{
	int K,i,j,t;
	while(scanf("%d",&K)!=EOF&&K)
	{
		scanf("%d%d",&M,&N);
		memset(Arc,0,sizeof(Arc));
		memset(ymatch,-1,sizeof(ymatch));
		memset(xmatch,-1,sizeof(xmatch));
		for(t=1;t<=K;t++)
		{
			scanf("%d%d",&i,&j);
			Arc[i][j]=1;
		}
		printf("%d\n",MaxMatch());
	}
	return 0;
}
/*
5 3 3
1 1
1 2
1 3
2 1
3 1
7 4 4
1 1
1 2
2 2
2 3
3 1
3 2
4 4
0
*/