import java.util.*;
import java.*;


public class DfaOptimzer
{
	String iparr[][];
	int c;
	String str[];
	int strc;
	int fin[];
	int finc;
	String symlst[];
	int smcnt;
	HashMap<String, String> h=new HashMap();;
	HashMap<String, String> iphsh;
	int tag[]=new int[20];
	int cur=2;
	int count=0;

	public DfaOptimzer(String iparr[][],int c,String str[],int strc,int fin[],int finc,String symlst[],int smcnt)
	{
		this.iparr=iparr;
		this.c=c;
		this.str=str;
		this.strc=strc;
		this.fin=fin;
		this.finc=finc;
		this.symlst=symlst;
		this.smcnt=smcnt;

		statecount();
		cnvrt2Hash();

		optimise();
		//System.out.println(iparr+" "+c);
	}

	public DfaOptimzer(HashMap<String, String> iphsh,String strtstate,String symlst[],String finalst[])
	{
		/*System.out.println(iphsh1.size());
		Set<Map.Entry<String, String>> Set = iphsh1.entrySet();
   	//System.out.println("works");
	//Printing the HashMap.
	System.out.println("DFA optimised Production rules ::");
	for(Map.Entry<String, String> display1 : Set)
			{
			System.out.print(display1.getKey()+ " --> ");
			System.out.println(display1.getValue());
			}*/


		//iphsh.putAll(iphsh1);
		this.iphsh=iphsh;
		count=iphsh.size();
		System.out.println(count);
		System.out.println("CHK12");
		str=new String[20];
		str[0]=strtstate;
		System.out.println("CHK132");
		strc=1;
		int finc=0;
		while(finalst[finc]=="-1")
			finc++;

		System.out.println("CHK123");
		this.finc=finc;
		for(int i=0;i<finc;i++)
			fin[i]=Integer.parseInt(finalst[i]);
		int smcnt=0;
		while(symlst[smcnt]=="-1")
			smcnt++;

		this.symlst=symlst;
		this.smcnt=smcnt;

		optimise();

	}

	public void statecount()
	{
		HashMap<String, String> tmp=new HashMap();

		for(int i=0;i<c;i++)
		{
			if(tmp.containsKey(iparr[i][0])){}
			else
			{
				tmp.put(iparr[i][0],iparr[i][0]);
			}

			if(tmp.containsKey(iparr[i][2])){}
			else
			{
				tmp.put(iparr[i][2],iparr[i][2]);
			}

		}

		/*System.out.println(tmp.size());
		System.out.println("1");*/

		count=tmp.size();
		tmp.clear();


	}




	public void cnvrt2Hash()
	{
		iphsh= new HashMap();
		String oldVal;
		for(int i=0;i<c;i++)
		{

				iphsh.put(iparr[i][0]+";"+iparr[i][1],iparr[i][2]);

		}
		/*	Set<Map.Entry<String, String>> Set = iphsh.entrySet();
				//Printing the HashMap.
				for(Map.Entry<String, String> display : Set)
					{
					System.out.print(display.getKey()+ " --> ");
					System.out.println(display.getValue());
					}*/
	}

	public void optimise()
	{
			System.out.println("finc="+fin[finc]);
			for(int j=0;j<finc;j++)
			{
				int tmp=fin[j];
				tag[tmp]=1;
				System.out.println(fin[j]+"g "+tag[fin[j]]);

			}
		
		formsubgrp();

		for(int i=0;i<count;i++)
		{
			System.out.println(i+" "+tag[i]);
		}

		formtransition();
	}

	public void formtransition()
	{
		HashMap <String,String> tmp=new HashMap() ;
		
		for(int i=0;i<=count;i++)
		{
			System.out.println("Tag" + i+ "="+tag[i]);
		}

		for(int i=0;i<count;i++)
		{
			int buf=tag[i];
			System.out.println("BUf ="+buf);
			for(int j=0;j<smcnt;j++)
			{
				String temp=chknxt(i,j);
				if(!temp.equals("-1"))
				{
					int a=Integer.parseInt(temp);

					if(!tmp.containsKey(Integer.toString(buf)+","+symlst[j]))
					{
						tmp.put(Integer.toString(buf)+","+symlst[j],Integer.toString(tag[a]));
					}

				}


			}
		}
		
		//cnvrt2arr(tmp);
		System.out.println("hello "+fin[0]);
		String finalState[]=new String[30];
		/*for(int i=0;i<finc;i++)
		{
			finalState[i]=Integer.toString(fin[i]);
		}*/
		
		//finalState[0]=Integer.toString(tag[1]);
		
		Set<String> k= tmp.keySet();
		//String ars[]=new String [20];
		
		String[] ars=k.toArray(new String[20]);
		HashMap<String,String> tempo=new HashMap();
		for(int i=0;i<tmp.size();i++)
		{
			StringTokenizer tok=new StringTokenizer(ars[i],",");
			
			String t=tok.nextToken()+","+tmp.get(ars[i]);
			System.out.println("t "+t);
			if(!tempo.containsKey(t))
			{
				tempo.put(t,tok.nextToken());
			}
			else
			{
				String a=tempo.get(t);
				a=a+"/"+tok.nextToken();
				tempo.put(t,a);
			}
		}
		
	/*	Set<Map.Entry<String, String>> Set1 = tempo.entrySet();
				//Printing the HashMap.
				for(Map.Entry<String, String> display : Set1)
					{
					System.out.print(display.getKey()+ " --> ");
					System.out.println(display.getValue());
					}*/
					
		Set<String> k1= tempo.keySet();
		String ars1[]=k1.toArray(new String[20]);
		tmp.clear();
		for(int i=0;i<tempo.size();i++)
		{
			StringTokenizer tok=new StringTokenizer(ars1[i],",");
			
			String t=tok.nextToken()+","+tempo.get(ars1[i]);
			System.out.println("t "+t);
			if(!tmp.containsKey(t))
			{
				tmp.put(t,tok.nextToken());
			}
			else
			{
				String a=tmp.get(t);
				a=a+"/"+tok.nextToken();
				tmp.put(t,a);
			}
		}
		
		System.out.println("Printing the HashMap "+tmp.size());
		Set<Map.Entry<String, String>> Set = tmp.entrySet();
				//Printing the HashMap.
				for(Map.Entry<String, String> display : Set)
					{
					System.out.print(display.getKey()+ " --> ");
					System.out.println(display.getValue());
					}
		
			
		finalState[0]="1";
		
		
		finalState[1]="-1";
		System.out.println("Optimize DFA "+str[0]);
		Cnvrt obj=new Cnvrt(tmp,finalState,Integer.toString(tag[0]));
	}

	public void cnvrt2arr(HashMap<String,String> ip)
	{

		Set<String> k= ip.keySet();
		//String ars[]=new String [20];

		String[] ars=k.toArray(new String[20]);
		String op[][]=new String[20][3];
		int i=ip.size();
		System.out.println(i);

		for(int j=0;j<i;j++)
		{
			StringTokenizer ob=new StringTokenizer(ars[j],",");

			op[j][0]=ob.nextToken();
			op[j][1]=ob.nextToken();
			op[j][2]=ip.get(ars[j]);
			System.out.println(op[j][0]+" "+op[j][1]+" "+op[j][2]);
		}

		//DsgnGraph obj= new DsgnGraph(op,i);


	}

	public void formsubgrp()
	{
		System.out.println(fin[0]);
		
			/*	Set<Map.Entry<String, String>> Set = iphsh.entrySet();
				//Printing the HashMap.
				for(Map.Entry<String, String> display : Set)
					{
					System.out.print(display.getKey()+ " --> ");
					System.out.println(display.getValue());
					}*/

		//System.out.println("CHK124"+count);
		/*for (int i=0;i<smcnt;i++)
		{
			String tmp=chknxt(0,i);
			System.out.println(tmp);
			for(int j=1;j<count;j++)
			{
				System.out.println(chknxt(j,i)+" "+tmp);
				if(chknxt(j,i).equals(tmp))
				{
					tmp=chknxt(j,i);
					System.out.println("12");
				}
				else
				{
					System.out.println(cur+" cur b4 updation "+j);
					tag[j]=cur++;
					System.out.println("chk100 "+j+" , "+cur);
				}
			}
		}*/
		
		HashMap<String,String> tm=new HashMap();
		System.out.println(count);
		for(int i=0;i<count;i++)
		{
			System.out.println(i+" "+tag[i]);
			if(tm.containsKey(Integer.toString(tag[i])))
			{
				String t=tm.get(Integer.toString(tag[i]));
				//System.out.println(t);
				t=Integer.toString(i)+","+t;
				tm.put(Integer.toString(tag[i]),t);
			}
			else
			{
				tm.put(Integer.toString(tag[i]),Integer.toString(i));
			}
		}
		
		Set<String> k= tm.keySet();
		//String ars[]=new String [20];

		String[] ars=k.toArray(new String[20]);
		//String op[][]=new String[20][3];
		//int i=ip.size();
		
		
		String t=tm.get(Integer.toString(0));
		
		
		for(int i=0;i<smcnt;i++)
			{
				//System.out.println(i);
				StringTokenizer tok=new StringTokenizer(t,",");
				while(tok.hasMoreTokens())
				{
					String te=tok.nextToken();
			
				//System.out.println(te+" "+symlst[i]+" "+ iphsh.get(te+";"+symlst[i]));
					String grbg=iphsh.get(te+";"+symlst[i]);
					if(grbg!=null)
					{
						if(tag[Integer.parseInt(te)]!=tag[Integer.parseInt(grbg)])
						{
							tag[Integer.parseInt(te)]=cur++;
						}
					//System.out.println("tags "+te +" "+ tag[Integer.parseInt(te)]);
					}
				}
			
			
			}
		for(int i=0;i<=count;i++)
		{
			System.out.println("Tag" + i+ "="+tag[i]);
		}	
		
		
		Set<Map.Entry<String, String>> Set = tm.entrySet();
				//Printing the HashMap.
				for(Map.Entry<String, String> display : Set)
					{
					System.out.print(display.getKey()+ " --> ");
					System.out.println(display.getValue());
					}
	}

	public String chknxt(int j,int i)
	{
		String temp= Integer.toString(j)+";"+symlst[i];
		temp= iphsh.get(temp);
		System.out.println(j+","+symlst[i]+" "+temp);
		if(temp==null)
			return "-1";
		else
			return temp;


		/*System.out.println(j+","+symlst[i]+" "+temp);
		return 0;*/
	}

	public static void main(String ar[])
	{
		String a[][]=new String[20][4];
		String b[]=new String[3];
		int c[]=new int[3];
		String symlst[]=new String[20];

		/*a[0][0]="0";
		a[0][1]="a";
		a[0][2]="1";
		a[1][0]="0";
		a[1][1]="b";
		a[1][2]="2";
		a[2][0]="1";
		a[2][1]="a";
		a[2][2]="2";
		a[3][0]="2";
		a[3][1]="^";
		a[3][2]="2";

		b[0]="0";
		c[0]=2;

		symlst[0]="a";
		symlst[1]="b";
		symlst[2]="^";

		DfaOptimzer obj=new DfaOptimzer(a,4,b,1,c,1,symlst,3);*/

		a[0][0]="0";
		a[0][1]="a";
		a[0][2]="1";
		a[1][0]="0";
		a[1][1]="b";
		a[1][2]="2";

		a[2][0]="1";
		a[2][1]="a";
		a[2][2]="1";
		a[3][0]="1";
		a[3][1]="b";
		a[3][2]="3";

		a[4][0]="2";
		a[4][1]="a";
		a[4][2]="1";
		a[5][0]="2";
		a[5][1]="b";
		a[5][2]="2";

		a[6][0]="3";
		a[6][1]="a";
		a[6][2]="1";
		a[7][0]="3";
		a[7][1]="b";
		a[7][2]="4";

		a[8][0]="4";
		a[8][1]="a";
		a[8][2]="1";
		a[9][0]="4";
		a[9][1]="b";
		a[9][2]="2";

		b[0]="0";
		c[0]=4;

		symlst[0]="a";
		symlst[1]="b";
		//symlst[2]="^";

		DfaOptimzer obj=new DfaOptimzer(a,10,b,1,c,1,symlst,2);
	}
}

