#include<iostream>
#include<fstream>
using namespace std;
#include<math.h>
//ջ
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
	//�ؼ�����������
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
	int count;//��ĸ���
};
template<class DataType>//˳��ջ��ջ�㷨
void SeqStack<DataType>::Push(DataType x)
{
    if(top==StackSize-1)throw"����";
    data[++top]=x;
}
template<class DataType>//˳��ջ��ջ�㷨
DataType SeqStack<DataType>::Pop()
{
    if(top==-1)throw"����";
    DataType x=data[top];
	top--;
    return x;
}
template<class DataType>
void SeqStack<DataType>::Queen(int row,SeqStack &q)//���α�������
{
	for(int col=0;col<QueenSize;col++)//����һ���е�ÿһ��
	{
		q.Push(col);//����һ�ŵ��к���ջ
		if(Judge(q))//����жϳɹ�
		{
			if(row+1<QueenSize)//���������û�в���Ů��
				q.Queen(row+1,q);//�ݹ����Ů��
			else
				OutPut(q);//������ж��Ѿ������Ů������ô�������һ�ֽⷨ
		}
		q.Pop();//����жϲ��ɹ�����������ݹ�������ͽ���һ����ջ��Ԫ�س�ջ��
	}
}
template<class DataType>
bool SeqStack<DataType>::Judge(SeqStack q)//�жϺ���
{
	/*����㷨������ͷ�������i�����i�У�q.data[i]�����i�лʺ�������������topΪ��ǰ�У�q.GetTop()Ϊ��ǰ��
	��������������������ʺ�����������������ͬ��q.GetTop()==q.data[i]�жϵ����Ƿ���ͬһ���ϣ�
	(q.top-i)==abs(q.GetTop()-q.data[i])��仰����˼�ǣ�������Ĳ��Ƿ�����������ľ���ֵ��������ڶԽ�����
	ע��abs()Ϊ����ֵ����*/
	for(int i=0;i<q.top;i++)
		if(q.GetTop()==q.data[i]||(q.top-i)==abs(q.GetTop()-q.data[i]))
			return 0;
	return 1;
}
template<class DataType>
void SeqStack<DataType>::OutPut(SeqStack &q)
{
	bool flag=true;//����һ����־
	q.count++;//ÿ����һ�ν������һ
	fileout<<"�ڷŷ�ʽ"<<q.count<<"Ϊ:"<<endl;
	for(int row=0;row<QueenSize;row++)//�������
	{
		flag=true;//�����־Ϊ1
		for(int col=0;col<QueenSize;col++)//�������
		{	
			if(col==q.data[row]&&flag)//q.data[row]Ϊÿ��Ԫ����Queen���ڵ�����������������Ѿ������һ��Ů������ô�Ͳ��ڴ����ٽ���Ů���ıȽϣ�flag=0��
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
	fileout<<"����"<<q.count<<"���"<<endl;
	fileout.close();
	cout<<"����ͬ·���µ�result.txt�в鿴"<<endl;
	return 0;
}
