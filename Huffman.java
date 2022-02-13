import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class HuffmanNode
{
int data;
char c;
HuffmanNode left;
HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode>
{
public int compare(HuffmanNode x, HuffmanNode y)
{
return x.data - y.data;
}
}

public class Huffman
{
static String[] codewords=new String[10];
static int k=0;
static public void setCode(String s)
{
codewords[k]=s;
k++;
}
public static void printCode(HuffmanNode root, String s,int n)
{
if (root.left== null && root.right== null&& Character.isLetter(root.c))
{
setCode(s);
return;
}
printCode(root.left, s + "1",n);
printCode(root.right, s + "0",n);

}
public static void main(String[] args)
{
Scanner s = new Scanner(System.in);
System.out.println("Enter the String : ");
String text=s.nextLine();
Map<Character,Integer> map=new HashMap<>();

for(int i=0;i<text.length();i++)
{
char ch=text.charAt(i);
if(map.containsKey(ch))
{
map.put(ch,map.get(ch)+1);
}
else
{
map.put(ch,1);
}
}
char[] charArray = new char[map.size()];
int[] charfreq = new int[map.size()];
float[] probabilities=new float[map.size()];
float[] informations=new float[map.size()];
int k=0;
for (Map.Entry<Character,Integer> entry : map.entrySet())
{
charfreq[k]=entry.getValue();
charArray[k]=entry.getKey();
probabilities[k]=(float)entry.getValue()/text.length();
informations[k]=(float)(Math.log(1/probabilities[k])/Math.log(2));
k++;
}
int n = charArray.length;
PriorityQueue<HuffmanNode> q= new PriorityQueue<HuffmanNode>(n, new MyComparator());
for (int i = 0; i < n; i++)
{
HuffmanNode hn = new HuffmanNode();
hn.c = charArray[i];
hn.data = charfreq[i];
hn.left = null;
hn.right = null;
q.add(hn);
}
HuffmanNode root = null;
while (q.size() > 1)
{
HuffmanNode x = q.peek();
q.poll();
HuffmanNode y = q.peek();
q.poll();
HuffmanNode f = new HuffmanNode();
f.data = x.data + y.data;
f.c = '-';
f.left = x;
f.right = y;
root = f;
q.add(f);
}
printCode(root, "",map.size());
float entropy=0,alc=0,cr,redundancy;
int bitsused=0;
for(int i=0;i<probabilities.length;i++)
{
float t=probabilities[i]*informations[i];
entropy+=t;
t=probabilities[i]*codewords[i].length();
alc+=t;

}
redundancy=entropy-alc;

int i=0;
for (Map.Entry<Character,Integer> entry : map.entrySet())
{
System.out.println(" "+entry.getKey()+" : "+entry.getValue()+" "+probabilities[i]+" "+informations[i]+
" "+codewords[i]);
bitsused+=(entry.getValue()*codewords[i].length());
i++;
}
cr=(float)bitsused/(text.length()*8);
System.out.println("\n Entropy is : "+entropy+" bits/symbol\n");
System.out.println(" Compression Ratio is : "+cr*100+"%\n");
System.out.println(" Average Length Code is : "+alc+" bits/symbol\n");
System.out.println(" Redundancy is : "+redundancy+" bits/symbol\n");
}
}
