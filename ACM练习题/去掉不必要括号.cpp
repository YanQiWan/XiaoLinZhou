#include<stdio.h>
#include<string.h>

char expression[255]={'\0'};
bool foreward[4][4]={{1,0,0,0},{1,0,0,0},{1,1,1,0},{1,1,1,0}};//需判断运算符在前 
bool backward[4][4]={{1,1,0,0},{1,1,0,0},{1,1,1,1},{1,1,1,1}};//需判断运算符在后 
int operation(char op)
{
	switch(op)
	{
	case '+':return 0;
	case '-':return 1;
	case '*':return 2;
	case '/':return 3;
	default:return -1;
	}
}
void cut(char a[])
{
     int length=strlen(a);
     int left=0,right=0,temp=0;
	 char left_op,right_op;
	 int i=0;
	 int flag;
     while(left<length)
     {
		  flag=true;//默认能去
          while(a[left]!='('&&left<length)
				left++;
		  if(left==length)
			  break;
		  right=left+1;
		  temp=0;
		  while(a[right]!=')'||temp!=0)
		  {
			  if(a[right]=='(')
				  temp++;
			  if(a[right]==')'&&temp!=0)
				  temp--;
			  right++;
		  }
		  if(left!=0&&a[left-1]!='(')
			  left_op=a[left-1];
		  else
			  left_op='#';
		  if(right!=length-1&&a[right+1]!=')')
			  right_op=a[right+1];
		  else
			  right_op='#';
		  if(left_op=='#')
		  {
			  if(right_op=='#')
				  flag=1;
			  else
			  {
				  for(i=left+1;i<right;i++)
				  {
					  if(a[i]=='(')
						  temp++;
					  else if(a[i]==')')
						  temp--;
					  else if(temp==0&&(a[i]<'a'||a[i]>'z'))
						  flag=backward[operation(a[i])][operation(right_op)];
					if(!flag)
						 break; 
				  }
			  }
		  }
			else if(right_op=='#')
			{
			  for(i=left+1;i<right;i++)
				  {
					  if(a[i]=='(')
						  temp++;
					  else if(a[i]==')')
						  temp--;
					  else if(temp==0&&(a[i]<'a'||a[i]>'z'))
						  flag=foreward[operation(a[i])][operation(left_op)];
						  if(!flag)
						  	break; 
				  }
			}
			else
			{
				for(i=left+1;i<right;i++)
				{
				if(a[i]=='(')
					temp++;
				else if(a[i]==')')
					temp--;
				else if(temp==0&&(a[i]<'a'||a[i]>'z'))
					flag=foreward[operation(a[i])][operation(left_op)]&&
						backward[operation(a[i])][operation(right_op)];
					if(!flag)
						break; 	
				}
			}
			if(flag)
			{
				for(i=left;i<length;i++)
						a[i]=a[i+1];
				for(i=right-1;i<length-1;i++)
						a[i]=a[i+1];
				length-=2;
			}
			else
				left++;
	}
}

int main()
{
    int i,n;
    scanf("%d",&n);
    for(i=0;i<n;i++)
    {
        scanf("%s",expression);
        cut(expression);
        printf("%s\n",expression);
    }
    return 0;
}