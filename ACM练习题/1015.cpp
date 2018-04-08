#include<stdio.h>
#include<math.h>
#include<string.h>
#include<memory.h>
char answer[6];
char codes[28];
bool visited[28]={0};
int target;
int N;
int find;
int let2num(char let)
{
	return let-'0';
}
int func()
{
	return int(let2num(answer[0])-pow(let2num(answer[1]),1)+pow(let2num(answer[2]),3)-pow(let2num(answer[3]),4)+pow(let2num(answer[4]),5));
}
void order()
{
	int i, j;
	char t;
	int flag;
	for (i = 0; i<N; i++)
	{
		flag = 0;
		for (j = 0; j<N - i - 1; j++)
			if (codes[j + 1]> codes[j])
			{
				t = codes[j + 1];
				codes[j + 1] = codes[j];
				codes[j] = t;
				flag = 1;
			}
		if (flag == 0)break;
	}
}
void getLength(){
	for(int i=0;codes[i]!='\0';i++);
	N=i;
}
void DFS(int index){
	if(find==1)
		return;
	if(index==5)
	{
		if(func()==target)
		{
			find=1;
			codes[index]='\0';
		}
		return;
	}
	for(int i=0;i<N;i++)
	{
		if(visited[i]!=1)
		{
			answer[index]=codes[i];
			index++;
			visited[i]=1;
			DFS(index);
			index--;
			answer[index]=0;
			visited[i]=0;
		}
	}
}
int main()
{
	while(scanf("%d %s",&target,&codes)!=EOF&&(target!=0||strcmp(codes,"END")!=0))
	{
		find=0;
		getLength();
		order();
		memset(visited,0,sizeof(visited));
		DFS(0);
		if(find==1)
			printf("%s\n",answer);
		else
			printf("no solution\n");
	}
	return 0;
}