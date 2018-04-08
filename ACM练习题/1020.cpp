#include<iostream>
using namespace std;
#include<stack>
int main()
{
	char str[10002],t,result[10002],s;
	int n,i=0,j,num;
	cin>>n;
	while(n--)
	{
		cin>>str;
		t=str[0];
		num=1;
		i=0,j=1;
		while((s=str[j++])!='\0')
		{
			if(s!=t)
			{
				if(num==1)
					result[i++]=t;
				else
				{
					stack<int> k;
					do{
						k.push(num%10);
					}while((num=num/10)!=0);
					while(!k.empty())
					{
						result[i++]='0'+k.top();
						k.pop();
					}
					result[i++]=t;
					num=1;
				}
				t=s;
			}
			else
				num++;
		}
		if(num==1)
			result[i++]=t;
		else
		{
			stack<int> k;
			do{
				k.push(num%10);
			}while((num=num/10)!=0);
			while(!k.empty())
			{
				result[i++]='0'+k.top();
				k.pop();
			}
			result[i++]=t;
		}
		result[i]='\0';
		cout<<result<<endl;
	}
	return 0;
}