#include<iostream>
using namespace std;
#include<iomanip>
int output[25][25];
void cube(int n)
{
	int i=1,j=n/2+1,k,flag=1;
	for(k=1;k<=n*n;k++)
	{
		output[i][j]=k;
		if(flag==n)
		{	
			if(i!=n) i++;
			else i=0;
			flag=1;
		}
		else
		{
			if(i==1) i=n;
			else i--;
			if(j==n) j=1;
			else j++;
			flag++;
		}
	}
	for(i=1;i<=n;i++)
	{
		for(j=1;j<=n;j++)
			cout<<std::right<<setw(4)<<output[i][j];
		cout<<endl;
	}
}
int main()
{
	int T,n;
	cin>>T;
	while(T--)
	{
		cin>>n;
		cube(n);
	}
	return 0;
}