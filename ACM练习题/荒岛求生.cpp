#include<stdio.h>
#include<math.h>

const double r=7.5;
const double edge=50;
const double MAX=1000;

int startnum,endnum;

int n;	

double mindist[102]={0};
int minstep[102]={0};
double finaldist[102]={0};
int finalstep[102]={0};
int Finalstep[102]={0};
struct point{
	double x;
	double y;
}points[102];

int end[102]={0};
double graph[102][102]={0};

double min(double a,double b)
{
	return a<b?a:b;
}

double min(double a,double b,double c,double d)
{
	return min(a,min(b,(min(c,d))));
}

double getdistance(point a,point b)
{
	return sqrt(pow(a.x-b.x,2)+pow(a.y-b.y,2));
}

void dijkstra(int start)
{
	int i,j;
	double dist[102];
	bool S[102];
	int num=1;
	for(i=0;i<=n;i++)
	{
		dist[i]=graph[start][i];
		S[i]=0;
		if(dist[i]!=MAX)
			minstep[i]++;
	}
	dist[start]=0;
	S[start]=1;	
	for(num=1;num<=n;num++)
	{
		double min=MAX;
		int k=start;
		for(i=0;i<=n;i++)
		{
			if(S[i]==0&&dist[i]!=0&&dist[i]<=min)
			{
				k=i;
				min=dist[i];
			}
		}
		S[k]=1;
		mindist[k]=min;
		for(j=0;j<=n;j++)
			if(S[j]==0&&graph[k][j]<MAX)
			{
				if(dist[j]>dist[k]+graph[k][j])
				{
					dist[j]=dist[k]+graph[k][j];
					minstep[j]=minstep[k]+1;
				}
			}
	}
}

int main()
{
	double resultdis;
	int resultstep=0;
	int Resultstep=0;
	point origin={0,0};
	int i,j;
	double ptdis,egdis;
	double d;
	while(scanf("%d%lf",&n,&d)!=EOF)
	{
		points[0].x=points[0].y=0;
		int k=0;
		int p=0;
		for(i=1;i<=n;i++)
		{
			scanf("%lf%lf",&points[i].x,&points[i].y);
		}
		if(d>=edge-r)
		{
			printf("%.2lf 1\n",edge-r);
			continue;
		}
		startnum=endnum=0;
		for(i=1;i<=n;i++)
		{
			egdis=min(points[i].x+edge,edge-points[i].x,points[i].y+edge,edge-points[i].y);
			if(egdis<=d)
			{
				end[endnum]=i;
				endnum++;
			}
		}
		if(endnum==0)
		{
			printf("can\'t be saved\n");
			continue;
		}
		for(i=0;i<=n;i++)
		{
			for(j=0;j<=i;j++)
			{
				ptdis=getdistance(points[i],points[j]);
				if(((i==0||j==0)&&ptdis<=r+d)||ptdis<=d)
					graph[i][j]=graph[j][i]=ptdis;
				else
					graph[i][j]=graph[j][i]=MAX;
			}
		}
		for(i=0;i<=n;i++)
			minstep[i]=0;
		dijkstra(0);
		for(j=0;j<endnum;j++)
		{
			finalstep[k]=minstep[end[j]]+1;
			finaldist[k]=mindist[end[j]]+min(points[end[j]].x+edge,edge-points[end[j]].x,points[end[j]].y+edge,edge-points[end[j]].y)-r;
			k++;
		}
		if(k==0)
		{
			printf("can\'t be saved\n");
		}
		else
		{
			resultdis=finaldist[0];
			resultstep=finalstep[0];
			for(i=0;i<k-1;i++)
			{
				
				if(resultdis>finaldist[i+1])
				{
					resultdis=finaldist[i+1];
					resultstep=finalstep[i+1];
				}
			}
			if(resultdis+r>=MAX)
			{
				printf("can\'t be saved\n");
				continue;
			}
			else
				for(i=0;i<k;i++)
					if(finaldist[i]==resultdis)
					{
						Finalstep[p]=finalstep[i];
						p++;
					}
			Resultstep=Finalstep[0];
			for(i=0;i<p-1;i++)
			{
				if(Resultstep>Finalstep[i+1])
				{
					Resultstep=Finalstep[i+1];
				}
			}
			printf("%.2lf %d\n",resultdis,Resultstep);
		}
	}	
	return 0;
}