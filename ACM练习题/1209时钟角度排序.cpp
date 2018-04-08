#include<stdio.h>
#include<math.h>
struct Time
{
	int hh;
	int mm;
	double angle;
}time[6];
void sort(Time t[],int N);
double Abs(double x);
int main()
{
	int T,i;
	scanf("%d",&T);
	while(T--)
	{
		for(i=0;i<5;i++)
		{
			scanf("%d",&time[i].hh);
			getchar();
			scanf("%d",&time[i].mm);
			time[i].angle=Abs(double(time[i].hh%12)*30-5.5*double(time[i].mm));
			if(time[i].angle>180)
				time[i].angle=360-time[i].angle;
		}
		sort(time,5);
		printf("%02d:%02d\n",time[2].hh,time[2].mm);
	}
	return 0;
}
void sort(Time time[],int N)
{
	int i,j,flag;
	Time t;
	for(i=0;i<N;i++)
	{
		flag=0;
		for(j=0;j<N-i-1;j++)
		{
			if(time[j].angle>time[j+1].angle)
			{
				t=time[j];
				time[j]=time[j+1];
				time[j+1]=t;
				flag=1;
			}
			else if(time[j].angle==time[j+1].angle)
				if(time[j].hh*60+time[j].mm>time[j+1].hh*60+time[j+1].mm)
				{
					t=time[j];
					time[j]=time[j+1];
					time[j+1]=t;
					flag=1;
				}
		}
		if(flag==0)break;
	}
}
double Abs(double x)
{
	return x>=0?x:-x;
}
/*
3
00:00 01:00 02:00 03:00 12:00
06:05 07:10 21:01 03:00 12:55
11:05 12:05 13:05 14:05 15:05
*/