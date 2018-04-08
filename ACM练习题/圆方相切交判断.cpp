#include<stdio.h>
#include<math.h>

struct point{
	double x;
	double y;
}rec[4];

double pointdis[4]={0};

double getpointdis(point circlepoint,point recpoint){
	double result;
	result=sqrt(pow((circlepoint.x-recpoint.x),2)+pow(circlepoint.y-recpoint.y,2));
	return result;
}

double max(double a,double b){
	return a>b?a:b;
}

double min(double a,double b){
	return a<b?a:b;
}

double max(double a,double b,double c,double d){
	return max(a,max(b,max(c,d)));
}

double min(double a,double b,double c,double d){
	return min(a,min(b,min(c,d)));
}

int main()
{
	int i,j,n,area;
	double x,y,r,x1,y1,x2,y2;
	double left,right,above,below,maxpdis,minpdis,minedis;
	bool flag;
	scanf("%d",&n);
	for(i=0;i<n;i++)
	{
		scanf("%lf%lf%lf%lf%lf%lf%lf",&x,&y,&r,&x1,&y1,&x2,&y2);
		point circlepoint={x,y};
		left=x1<x2?x1:x2;
		right=x1>x2?x1:x2;
		below=y1<y2?y1:y2;
		above=y1>y2?y1:y2;
		rec[0].x=rec[3].x=left;
		rec[1].x=rec[2].x=right;
		rec[0].y=rec[1].y=above;
		rec[2].y=rec[3].y=below;
		if(x>left&&x<right&&y>above)
			area=1;
		else if(x>right&&y>below&&y<above)
			area=2;
		else if(x>left&&x<right&&y<below)
			area=3;
		else if(x<left&&y>below&&y<above)
			area=4;
		else if(x>=left&&x<=right&&y>=below&&y<=above)
			area=5;
		else
			area=6;
		for(j=0;j<4;j++)
		{
			pointdis[j]=getpointdis(circlepoint,rec[j]);
		}
		maxpdis=max(pointdis[0],pointdis[1],pointdis[2],pointdis[3]);
		minedis=min(right-x,x-left,above-y,y-below);
		minpdis=min(pointdis[0],pointdis[1],pointdis[2],pointdis[3]);
		switch(area){
		case 1:
			flag=!(r<y-above||r>maxpdis);
			break;
		case 2:
			flag=!(r<x-right||r>maxpdis);
			break;
		case 3:
			flag=!(r<below-y||r>maxpdis);
			break;
		case 4:
			flag=!(r<left-x||r>maxpdis);
			break;
		case 5:
			flag=!(r<minedis||r>maxpdis);
			break;
		case 6:
			flag=!(r<minpdis||r>maxpdis);
			break;
		default:
			break;
		}
		if(flag)
			printf("YES\n");
		else
			printf("NO\n");
	}
	return 0;
}