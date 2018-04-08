#include<math.h>
#include<stdio.h>
#include<string.h>
#include<iostream>
using namespace std;
struct student
{
	char name[22];
	int No;
	int num;
}stu[1005];
void sort(student stu[],int N);
int main()
{
	int T,n,i,j,t,no;
	scanf("%d",&T);
	while(T--)
	{
		scanf("%d",&n);
		for(i=0;i<n;i++)
		{
			scanf("%s%d",&stu[i].name,&stu[i].No);
			t=0;no=stu[i].No;
			for(j=2;j*j<=no;j++)
			{
				if(stu[i].No%j==0)
				{
					t++;
					while(stu[i].No%j==0)
						stu[i].No/=j;
				}
            }
			if(stu[i].No>1)t++;
			stu[i].num=t;
		}
		sort(stu,n);
		printf("%s\n",stu[0].name);
	}
	return 0;
}
void sort(student stu[],int N)
{
	int i,j,flag;
	student t;
	for(i=0;i<N;i++)
	{
		flag=0;
		for(j=0;j<N-i-1;j++)
		{
			if(stu[j].num<stu[j+1].num)
			{
				t=stu[j];
				stu[j]=stu[j+1];
				stu[j+1]=t;
				flag=1;
			}
			else if(stu[j].num==stu[j+1].num)
				if(strcmp(stu[j].name,stu[j+1].name)>0)
				{
					t=stu[j];
					stu[j]=stu[j+1];
					stu[j+1]=t;
					flag=1;
				}
		}
		if(flag==0)break;
	}
}
/*
2
3
Lily 45
Kate 56
Amanda 8
5
Sara 55
Ella 42
Cristina 210
Cozzi 7
Emma 12
*/