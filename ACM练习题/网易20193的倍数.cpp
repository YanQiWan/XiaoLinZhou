#include<iostream>
using namespace std;
int main()
{
	int l,r,result,t;
	while(cin>>l>>r)
	{
		result=0;
		t=((r-l)+1)%3;
		if(t==1&&l%3!=1&&r%3!=1)
			result++;
		if(t==2)
			result=result+(l%3!=1)+(r%3!=1);
		result+=((r-l)+1)/3*2;
		cout<<result<<endl;
	}
	return 0;
}
