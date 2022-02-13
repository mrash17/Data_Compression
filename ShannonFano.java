import java.util.*;
class Counts<T>
{
private Map<T,Integer> map;
private Set<T> elements = null;
private Integer sum = null;;

public Counts(Map<T,Integer> map)
{
this.map=Collections.unmodifiableMap(map);
}
public Set<T> getElements()
{
if(elements==null) {
elements=map.keySet();
}
return elements;
}
public Integer getCount(T t)
{
Integer returnValue = map.get(t);
if(returnValue==null) returnValue=0;
return returnValue;
}
}

public class ShannonFano{
static public <T> Map<T,List<Boolean>> getCodeTable(Counts<T> counts)
{
List<T> list = buildList(counts);
Map<T,List<Boolean>> codeTable = buildEmptyCodeTable(counts);
populateCodeTable(codeTable,list,counts);
return codeTable;
}

private static <T> List<T> buildList(final Counts<T> counts)
{
Set<T> countsKeys = counts.getElements();
List<T> list = new ArrayList<T>(countsKeys.size());
for(T t : countsKeys)
list.add(t);
Collections.sort(list, new Comparator<T>()
    {
    public int compare(T o1, T o2)
    {
    return - counts.getCount(o1).compareTo(counts.getCount(o2));
    }
    }
);
return list;
}

private static <T> Map<T,List<Boolean>> buildEmptyCodeTable(Counts<T> counts)
{
Set<T> countsKeys = counts.getElements();
Map<T,List<Boolean>> codeTable = new HashMap<T,List<Boolean>>(countsKeys.size());
for(T t : countsKeys)
codeTable.put(t,new ArrayList<Boolean>());
return codeTable;
}

private static <T> void populateCodeTable(Map<T,List<Boolean>> codeTable,List<T> list, Counts<T> counts)
{

if(list.size()<=1) return;
int sum = 0;
int fullSum = 0;
for(T t : list)
fullSum+=counts.getCount(t);
float bestdiff=5;
int i=0;
out: while(i<list.size())
{
float prediff=bestdiff;
sum+=counts.getCount(list.get(i));
bestdiff = Math.abs((float)sum/fullSum-0.5F);
if(prediff<bestdiff) break out;
i++;
}
for(int j=0;j<list.size();j++)
{
if(j<i)
codeTable.get(list.get(j)).add(Boolean.FALSE);
else
codeTable.get(list.get(j)).add(Boolean.TRUE);
}
populateCodeTable(codeTable, list.subList(0, i), counts);
populateCodeTable(codeTable, list.subList(i, list.size()), counts);
}

public static void main(String[] args)
{
Scanner sc=new Scanner(System.in);
System.out.println(" Enter the String : ");
String text=sc.nextLine();
int originallength=8*text.length();
int compressedlength=0;
Map<Character,Integer> countsMap=new LinkedHashMap<Character, Integer>();
for(int i=0;i<text.length();i++)
{
char ch=text.charAt(i);
if(countsMap.containsKey(ch))
{
countsMap.put(ch,countsMap.get(ch)+1);
}
else
{
countsMap.put(ch,1);
}
}

float[] probabilities=new float[countsMap.size()];
float[] informations=new float[countsMap.size()];
int[] noofbitsused=new int[countsMap.size()];
String[] codewords=new String[countsMap.size()];
int k=0;

Counts<Character> counts=new Counts<Character>(countsMap);
Map<Character,List<Boolean>> table=new ShannonFano().getCodeTable(counts);
k=0;
for(Character c : countsMap.keySet())
{
String s="";
for(Boolean b : table.get(c))
{
s += (b==Boolean.FALSE?'0':'1');
}
codewords[k]=s;
k++;
}
k=0;
for (Map.Entry<Character,Integer> entry : countsMap.entrySet())
{

float temp=(float)entry.getValue()/text.length();
probabilities[k]=temp;
informations[k]=(float)(Math.log(1/temp)/Math.log(2));
noofbitsused[k]=codewords[k].length()*entry.getValue();
compressedlength+=noofbitsused[k];
k++;
}

float compressionratio,entropy=0,alc=0;
float redundancy;
compressionratio=(float)compressedlength/originallength;

for(int i=0;i<probabilities.length;i++)
{
float t=probabilities[i]*informations[i];
entropy+=t;
t=probabilities[i]*codewords[i].length();
alc+=t;
}
redundancy=entropy-alc;
k=0;
System.out.println("\n Table : \n");
for(Map.Entry<Character,Integer> entry : countsMap.entrySet())
{
System.out.println(" "+entry.getKey()+" "+entry.getValue()+" "+probabilities[k]+" "+informations[k]+" "+codewords[k]);
k++;
}

System.out.println("\n Entropy is : "+entropy+" bits/symbol\n");
System.out.println(" Compression Ratio is : "+compressionratio*100+"%\n");
System.out.println(" Average Length Code is : "+alc+" bits/symbol\n");
System.out.println(" Redundancy is : "+redundancy+" bits/symbol\n");
}
}
