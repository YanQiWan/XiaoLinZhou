#include<iostream>
using namespace std;
char map[9][9];
int start_x,start_y,end_x,end_y;
int N,M,T,flag;
int direction[4][2]={{1,0},{0,1},{-1,0},{0,-1}};
void DFS(int x,int y,int t)
{
	if(flag==1)
		return;
	if(map[x][y]=='X')
		return;
	if(x==end_x&&y==end_y&&t==T)
	{
		flag=1;
		return;
	}
	if(x<1||x>N||y<1||y>M)
		return;
	if(t>T)return;
	for(int i=0;i<4;i++)
	{
		map[x][y]='X';
		DFS(x+direction[i][0],y+direction[i][1],t+1);
		map[x][y]='.';
		if(flag==1)
			return;
	}
}
int main()
{
	int i,j,block;
	while(cin>>N>>M>>T)
	{
		block=0;
		if(N==0&&M==0&&T==0)
			break;
		for(i=1;i<=N;i++)
		{
			for(j=1;j<=M;j++)
			{
				cin>>map[i][j];
				if(map[i][j]=='S')
				{
					start_x=i;start_y=j;continue;
				}
				if(map[i][j]=='D')
				{
					end_x=i;end_y=j;continue;
				}
				if(map[i][j]=='X')
					block++;
			}
		}
		if(T>(N*M-block))
		{
			cout<<"NO"<<endl;
			continue;
		}
		if((start_x+start_y+end_x+end_y+T)%2!=0)
		{
			cout<<"NO"<<endl;
			continue;
		}
		flag=0;
		DFS(start_x,start_y,0);
		if(flag==1)
			cout<<"YES"<<endl;
		else
			cout<<"NO"<<endl;
	}
	return 0;
}
