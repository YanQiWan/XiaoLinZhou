#include<iostream>
#include<string.h>
#include<memory.h>
using namespace std;
int main()
{
	char color[17];
	char resultset[1010][17];
	int result[1010];
	int N,num,i,j,max,index;
	bool flag;
	scanf("%d",&N);
	while(N!=0)
	{
		num=0,max=0;
		memset(result,0,sizeof(int)*1010);
		for(i=1;i<=N;i++)
		{
			scanf("%s",&color);
			flag=0;
			for(j=1;j<=num;j++)
			{
				if(strcmp(color,resultset[j])==0)
				{
					flag=1;
					result[j]++;
					break;
				}
			}
			if(flag==0)
			{
				num++;
				strcpy(resultset[num],color);
				result[num]++;
			}
		}
		for(i=1;i<=num;i++)
		{
			if(result[i]>max)
			{
				max=result[i];
				index=i;
			}
		}
		printf("%s\n",resultset[index]);
		scanf("%d",&N);
	}
	return 0;
}
/*
#include <stdio.h>
#include <string.h>

int main()
{
    int N=1,max,e,i,j;
    char color[1010][15];
    int count[1010]={1};
    while(scanf("%d",&N)!=EOF&&N)
    {
        for(i=0;i<N;i++)
            scanf("%s",color[i]);
        for(i=0;i<N-1;i++)
            {
                for(j=i+1;j<N;j++)
                {
                    if(strcmp(color[i],color[j])==0)
                        count[i]++;
                }
            }
        max=count[0];e=0;
        for (i=1;i<N;i++)
        {
            if (max<count[i]) e=i;
        }
        printf("%s\n",color[e]);
    }
    return 0;
}
#include <stdio.h>  
#include <string.h>  
int main()  
{  
    int n,i,j,max,k;  
    int a[1000]= {0};  
    char color[1000][15];  
    while (scanf("%d",&n)!=EOF,n)  
    {  
        for (i=0; i<n; i++)  
        {  
            scanf("%s",color[i]);  
        }  
  
        max=0;  
        int a[1000]= {0};  
  
        for (i=0 ; i<n-1; i++)  
        {  
            for (j=i+1; j<n; j++)  
            {  
                if (strcmp(color[i],color[j])==0)  
                {  
                    a[i]++;  
                }  
            }  
            if(a[i]>max)  
                max=a[i];  
  
        }  
        for (i=0; i<n; i++)  
        {  
            if (a[i]==max)  
            {  
                printf("%s\n",color[i]);  
            }  
        }  
    }  
    return 0;  
} */