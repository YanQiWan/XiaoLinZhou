#include<stdio.h>
#include<vector>
#include<iostream>
#include<fstream>
using namespace std;
const int MAX=50000;
int main()
{
	int i,j,n;
	vector<int> vec;
	for(i=2;i<MAX;i++)
		vec.push_back(i);
	for(i=0;i<500;i++)
	{
		j=i+vec.at(i);
		while(j<vec.size())
		{
			vec.erase(vec.begin()+j);
			j=j+vec.at(i)-1;
		}
	}
	scanf("%d",&n);
	while(n)
	{
		printf("%d\n",vec.at(n-1));
		scanf("%d",&n);
	}
	return 0; 
}
