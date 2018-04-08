#include<stdio.h>
const int N=50;
int maxrow=0,maxcol=0,count=0;
char area[N+2][N+2]={' '};

int max(int a,int b)
{
	return a>b?a:b;
}

void clear(int i,int j,char area[][N+2]){
	area[i][j]=' ';
	count--;
	if(i>0&&area[i-1][j]=='*')
		clear(i-1,j,area);
	if(j<maxcol+1&&area[i][j+1]=='*')
		clear(i,j+1,area);
	if(i<maxrow+1&&area[i+1][j]=='*')
		clear(i+1,j,area);
	if(j>0&&area[i][j-1]=='*')
		clear(i,j-1,area);
}

int main()
{
	char ch;
	int i,j;
	FILE *filein;
	filein=fopen("house.txt","read");
	for(i=1;i<N+1;i++)
	{
		ch=fgetc(filein);
		if(ch!=EOF)
			for(j=1;j<N+1;j++)
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
					maxcol=max(j-1,maxcol);
					break;
				}
			}
		else
		{
			maxrow=max(i-1,maxrow);
			break;
		}
	}
	for(i=0;i<maxrow+2;i++)
	{
		for(j=0;j<maxcol+2;j++)
		
			if(area[i][j]=='\0'||area[i][j]==EOF||area[i][j]==' ')
			{
				area[i][j]='*';
				count++;
			}
	}
	printf("%d %d %d\n",maxrow,maxcol,count);
	for(i=0;i<maxrow+2;i++)
	{
		for(j=0;j<maxcol+2;j++)
			printf("%c",area[i][j]);
		printf("\n");
	}
	clear(0,0,area);
	printf("Ãæ»ýÎª:%d\n",count);
	for(i=0;i<maxrow+2;i++)
	{
		for(j=0;j<maxcol+2;j++)
			printf("%c",area[i][j]);
		printf("\n");
	}
	return 0;
}
