#include<stdio.h>
#include<string.h>
#include<memory.h>
char a[1005];
char b[1005];
char result[1005];

void add(char longnum[],char shortnum[],char resultnum[])
{
	int longlen=strlen(longnum);
	int shortlen=strlen(shortnum);
	int carry=0;
	int i;
	for(i=0;i<longlen;i++)
	{
		resultnum[i]=longnum[longlen-i-1];
	}
	for(i=0;i<shortlen;i++)
	{
		resultnum[i]=resultnum[i]+shortnum[shortlen-i-1]+carry-'0';
		if(resultnum[i]-'0'>=10)
		{
			carry=1;
			resultnum[i]-=10;
		}
		else
			carry=0;
	}
	while(carry&&i<=longlen)
	{
		if(resultnum[i]=='\0')
			resultnum[i]='1';
		else
		{
			resultnum[i]+=1;
			if(resultnum[i]-'0'>=10)
			{
				carry=1;
				resultnum[i]-=10;
			}
			else
				carry=0;
		}
		i++;
	}
}

int main()
{
	int n;
	int i;
	int j;
	scanf("%d",&n);
	for(i=0;i<n;i++)
	{
		scanf("%s%s",&a,&b);
		memset(result,'\0',sizeof(result));
		if(strlen(a)>strlen(b))
			add(a,b,result);
		else
			add(b,a,result);
		int length=strlen(result);
		printf("Case %d:\n%s + %s = ",i+1,a,b);
		for(j=0;j<length+1;j++)
		{
			printf("%c",result[length-j-1]);
		}
		printf("\n\n");
	}
	return 0;
}
