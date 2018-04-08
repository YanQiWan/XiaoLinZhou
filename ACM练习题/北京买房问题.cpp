#include<stdio.h>

const int N=50;

int maxrow=0,maxcol=0,count=0;

char area[N][N]={' '};

int max(int a,int b)
{
	return a>b?a:b;
}

void clear(int i,int j,char area[][N]){
	area[i][j]=' ';
	count--;
	if(i>1&&area[i-1][j]=='*')
		clear(i-1,j,area);
	if(j<maxcol-1&&area[i][j+1]=='*')
		clear(i,j+1,area);
	if(i<maxrow-1&&area[i+1][j]=='*')
		clear(i+1,j,area);
	if(j>1&&area[i][j-1]=='*')
		clear(i,j-1,area);
}

int main()
{
	char ch;
	int i,j;
	FILE *filein;
	filein=fopen("1.txt","read");
	for(i=0;i<N;i++)
	{
		ch=fgetc(filein);
		if(ch!=EOF)
			for(j=0;j<N;j++)
			{
				if(ch!='\n')
				{
					if(ch=='*')
						count++;
					area[i][j]=ch;
					ch=fgetc(filein);
				}
				else
					{
					maxcol=max(j,maxcol);
					break;
				}
			}
		else
		{
			maxrow=max(i,maxrow);
			break;
		}
	}
	for(i=0;i<maxrow;i++)
	{
		for(j=0;j<maxcol;j++)
			if(area[i][j]=='\0'||area[i][j]==EOF)
			{
				area[i][j]='*';
				count++;
			}
	}
	printf("%d %d %d\n",maxrow,maxcol,count);
	for(i=0;i<maxrow;i++)
	{
		for(j=0;j<maxcol;j++)
			printf("%c",area[i][j]);
		printf("\n");
	}
	for(j=0;j<maxcol;j++)
		if(area[0][j]=='*')
			clear(0,j,area);
	for(j=0;j<maxcol;j++)
		if(area[maxrow-1][j]=='*')
			clear(maxrow-1,j,area);
	for(i=0;i<maxrow;i++)//
		if(area[i][0]=='*')
			clear(i,0,area);
	for(i=0;i<maxrow;i++)
		if(area[i][maxcol-1]=='*')
			clear(i,maxcol-1,area);
	printf("%d\n",count);
	for(i=0;i<maxrow;i++)
	{
		for(j=0;j<maxcol;j++)
			printf("%c",area[i][j]);
		printf("\n");
	}
	return 0;
}
