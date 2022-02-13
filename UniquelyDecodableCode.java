import java.util.Scanner;
public class UniquelyDecodableCode {
 public static boolean isPrefixCode(String[] codeword,int k)
 {
 boolean isPrefixcode=true;
 for(int i=0;i<k-1;i++)
 {
 String s=codeword[i];
 int n=s.length();
 for(int j=i+1;j<k;j++)
 {
 String substring=codeword[j].substring(0,n);
 if(s.equals(substring))
 {
 System.out.println(" The Code "+s+" is a prefix of "+codeword[j]);
 isPrefixcode=false;
 break;
 }
 } 
 if(isPrefixcode!=true)
 {
 break;
 }
 }
 return isPrefixcode;
 }
 public static void main(String args[])
 {
 String[] codeword=new String[100];
 Scanner sc=new Scanner(System.in);
 System.out.println(" Enter the number of CodeWords : ");
 int size=sc.nextInt();
 sc.nextLine();
 for(int i=0;i<size;i++)
 {
 codeword[i]=sc.nextLine();
 }
 for(int i=size;i<codeword.length;i++)
 {
 codeword[i]=" ";
 }
 boolean isUDC=isPrefixCode(codeword,size);
 if(isUDC)
 {
 System.out.println("The Codeword is a Uniquely Decodable Code "); 

 for(String a:codeword)
 {
 System.out.print(""+a+" ");
 }
 }

 else
 {
 int t=size;
 isUDC=true;
 for(int i=0;i<size-1;i++)
 {
 String s=codeword[i];
 int n=s.length();
 for(int j=i+1;j<t;j++)
 {
 String substring=codeword[j].substring(0,n);
 if(s.equals(substring))
 {
 String temp=codeword[j].substring(n);
 for(int z=0;z<size;z++)
 {
 if(codeword[z].equals(temp))
 {
 System.out.println(" Suffix "+temp+" is already in the Codewords");
 System.out.println(" The Codeword is not a UDC"); 

 System.out.println(" Codeword is : ");
 isUDC=false;
 codeword[size]=temp;
 size++;
 for(int count=0;count<size;count++)
 {
 System.out.print(""+codeword[count]+" ");
 }
 System.out.println("");
 break;
 }
 }
 if(isUDC)
 {
 System.out.println(" Suffix "+temp+" is added in the Codewords");
 codeword[size]=temp;
 size++;
 for(int count=0;count<size;count++)
 {
 System.out.print(""+codeword[count]+" ");
 }
 }
 else
 {
 break;
 } 

 }
 }
 if(isUDC==false)
 {
 break;
 }
 }
 if(isUDC)
 {
 System.out.println("\n The Code is a Uniquely Decodable Code ");
 for(int count=0;count<size;count++)
 {
 System.out.print(""+codeword[count]+" ");
 }
 System.out.println("");
 }
 }
 }
} 