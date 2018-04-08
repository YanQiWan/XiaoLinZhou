#include<stdio.h>
struct Job{
	int D;
	int P;
	Job *next;
};
class JobList
{
public:
	Job *first;
	Job *end;
	int n;
	JobList(){first = new Job;first->next=NULL;}
	void insert(Job val)
	{
		if(first->next==NULL)
		{
			Job *p = new Job;
			p->D=val.D;
			p->P=val.P;
			first->next=p;
			end=p;
			end->next=NULL;
			return;
		}
		Job *r1 = first;
		Job *r2 = r1->next;
		while(val.D>r2->D)
		{
			r1=r2;
			if(r1->next==NULL)
				break;
			else
				r2=r1->next;
		}
		if(val.P<=r1->P)
			return;
		if(val.P>=r2->P)
		{
			Job *p = new Job;
			p->D=val.D;
			p->P=val.P;
			r1->next=p;
			p->next=NULL;
			end=p;
		}
		else
		{
			Job *p = new Job;
			p->D=val.D;
			p->P=val.P;
			r1->next=p;
			p->next=r2;
		}
	}
	
};

int main()
{
	int M,N,i,A,maxP;
	while(scanf("%d %d",&N,&M)!=EOF)
	{
		JobList joblist;
		Job job;
		for(i=1;i<=N;i++)
		{
			scanf("%d %d",&job.D,&job.P);
			joblist.insert(job);
		}
		for(i=1;i<=M;i++)
		{
			scanf("%d",&A);
			Job *index = joblist.first;
			while(index->next!=NULL)
			{
				if(A>=index->next->D)
					index=index->next;
				else
					break;
			}
			maxP=index->P;
			printf("%d\n",maxP);
		}
	}
	return 0;
}