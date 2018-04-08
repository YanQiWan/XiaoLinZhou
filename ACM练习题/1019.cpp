#include<iostream>
using namespace std;
int a,b;
int gcd(int x1,int x2)
{
	if(x2==0)
		return x1;
	else
		return gcd(x2,x1%x2);
}
void max()
{
	if(a<b)
	{
		int t = a;
		a = b;
		b = t;
	}
}
int main()
{
	int n,t;
	cin>>n;
	while(n--)
	{
		cin>>t;
		cin>>a;
		t--;
		b=a;
		while(t--)
		{
			cin>>a;
			max();
			b=a/gcd(a,b)*b;
		}
		cout<<b<<endl;
	}
	return 0;
}