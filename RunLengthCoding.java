import java.util.*;
public class RunLengthCoding {
public static String Encoding(String input)
{
String output="";
int count=1;
int flag=0;
for(int i=0;i<input.length()-1;)
{
flag=0;
char ch=input.charAt(i);
char t=input.charAt(i+1);
if(ch==t)
{
count++;
i++;
flag=1;
}
else
{
flag=0;
i++;
}
if(flag==0 || count==1)
{
output+=ch;
output+=count;
count=1;
}
}
char ch=input.charAt(input.length()-1);
char t=input.charAt(input.length()-2);
if(t==ch)
{
output=output.substring(0,output.length());
output+=ch;
output+=count;
}
else
{
output+=ch;
output+=1;
}
return output;
}
public static String Decoding(String output)
{
String decoded="";
for(int i=0;i<output.length();)
{
char ch=output.charAt(i);
char temp=output.charAt(i+1);
int val=Integer.parseInt(""+temp);
for(int j=1;j<=val;j++)
{
decoded+=ch;
}
i=i+2;
}
return decoded;
}
public static void main(String[] args) {
String input="";
String output="";
Scanner sc=new Scanner(System.in);
System.out.println(" Enter the String : ");
input=sc.nextLine();
output=Encoding(input);
System.out.println(" Coded String is : "+output);
String decoded=Decoding(output);
System.out.println(" Decoded String is : "+decoded);
}
}
