#include<stack>
#include<stdio.h>
using namespace std;
int main()
{
	stack<char> c;
	stack<char> d;
	stack<int> e;
	char a[1010];
	char b[1010];
	int sum=0;
	int n,i,j=0;
	scanf("%d",&n);
	for(int k=0;k<n;k++)
	{
		scanf("%s %s",&a,&b);
		for(i=0;a[i]!='\0';i++)
		{
			c.push(a[i]);
		} 
		for(i=0;b[i]!='\0';i++)
		{
			d.push(b[i]);
		}
		while(!c.empty()&&!d.empty())//不应该是a栈或b栈不空就循环，!c.empty()||!d.empty()是错的
		{
			sum=c.top()+d.top()-'0'-'0'+j;
			e.push(sum%10);
			j=sum/10;
			c.pop();
			d.pop();
		}
		while(!c.empty())
		{
			sum=c.top()+j-'0';
			e.push(sum%10);
			j=sum/10;
			c.pop();
		}
		while(!d.empty())
		{
			sum=d.top()+j-'0';
			e.push(sum%10);
			j=sum/10;
			d.pop();
		}
		if(j==1)
			e.push(1);
		printf("Case %d:\n%s + %s = ",k+1,a,b);
		while(!e.empty())
		{
			printf("%d",e.top());
			e.pop();
		}
		printf("\n");
	}
	return 0;
}
