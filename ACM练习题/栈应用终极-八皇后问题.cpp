#include<iostream>
#include<fstream>
using namespace std;
#include<math.h>
//栈
const int StackSize=100;
const int QueenSize=8;
ofstream fileout("result.txt");
template<class DataType>
class SeqStack
{
public:
    SeqStack(){top=-1;count=0;}
    ~SeqStack(){}
    void Push(DataType x);
    DataType Pop();
    DataType GetTop(){if(top!=-1) return data[top];}
	//关键的三个函数
	void Queen(int row,SeqStack<DataType> &q);
	bool Judge(SeqStack<DataType> q);
	void OutPut(SeqStack<DataType> &q);
    int Empty()
	{
		if(top==-1)return 1;
		else return 0;
	}
    DataType data[StackSize];
    int top;
	int count;//解的个数
};
template<class DataType>//顺序栈入栈算法
void SeqStack<DataType>::Push(DataType x)
{
    if(top==StackSize-1)throw"上溢";
    data[++top]=x;
}
template<class DataType>//顺序栈出栈算法
DataType SeqStack<DataType>::Pop()
{
    if(top==-1)throw"下溢";
    DataType x=data[top];
	top--;
    return x;
}
template<class DataType>
void SeqStack<DataType>::Queen(int row,SeqStack &q)//按次遍历横排
{
	for(int col=0;col<QueenSize;col++)//遍历一行中的每一列
	{
		q.Push(col);//把这一排的列号入栈
		if(Judge(q))//如果判断成功
		{
			if(row+1<QueenSize)//如果还有行没有插入女王
				q.Queen(row+1,q);//递归插入女王
			else
				OutPut(q);//如果八行都已经插入过女王，那么就输出这一种解法
		}
		q.Pop();//如果判断不成功，或者如果递归结束，就将上一步入栈的元素出栈。
	}
}
template<class DataType>
bool SeqStack<DataType>::Judge(SeqStack q)//判断函数
{
	/*这个算法很有来头，在这里，i代表第i行，q.data[i]代表第i行皇后所处的列数，top为当前行，q.GetTop()为当前列
	由于我是逐行往进插入皇后所以行数不可能相同，q.GetTop()==q.data[i]判断的是是否在同一列上，
	(q.top-i)==abs(q.GetTop()-q.data[i])这句话的意思是，横坐标的差是否等于纵坐标差的绝对值，相等则在对角线上
	注：abs()为绝对值函数*/
	for(int i=0;i<q.top;i++)
		if(q.GetTop()==q.data[i]||(q.top-i)==abs(q.GetTop()-q.data[i]))
			return 0;
	return 1;
}
template<class DataType>
void SeqStack<DataType>::OutPut(SeqStack &q)
{
	bool flag=true;//定义一个标志
	q.count++;//每运行一次结果数加一
	fileout<<"摆放方式"<<q.count<<"为:"<<endl;
	for(int row=0;row<QueenSize;row++)//输出行数
	{
		flag=true;//先令标志为1
		for(int col=0;col<QueenSize;col++)//输出列数
		{	
			if(col==q.data[row]&&flag)//q.data[row]为每行元素中Queen所在的列数，如果在这行已经输出了一个女王，那么就不在此行再进行女王的比较（flag=0）
			{
				flag=false;
				fileout<<"Q"<<" ";
			}
			else
				fileout<<"1"<<" ";
		}
		fileout<<endl;
	}
	fileout<<"-----------------"<<endl;
}

int main()
{
	SeqStack<int> q;
	q.Queen(0,q);
	fileout<<"共有"<<q.count<<"组解"<<endl;
	fileout.close();
	cout<<"请在同路径下的result.txt中查看"<<endl;
	return 0;
}
