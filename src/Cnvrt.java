import java.util.*;
import java.*;


public class Cnvrt
{
	String oparr[][]=new String[100][4];
	HashMap <String,String> iphsh;
	String finsts[];
	String strtState;
	String fin[]=new String[100];
	int finc;
	String strSt;
	public Cnvrt(HashMap <String,String> iphsh,String finsts[],String strtState)
	{
		System.out.println("CHK!");
		this.iphsh=iphsh;
		this.finsts=finsts;
		this.strtState=strtState;
		strSt=strtState;
		Set<String> k= iphsh.keySet();
		//String ars[]=new String [20];

		String[] ars=k.toArray(new String[20]);
		HashMap <String,String> pack= new HashMap();
		/*int cnt=0;

		for(int i=0;i<iphsh.size();i++)
		{
			String tempo="";
			for(int j=0;j<ars[i].length()-2;j++)
			{
				tempo=tempo+ars[i].charAt(j);
			}
			/*oparr[i][0]=tempo;
			oparr[i][1]=Character.toString(ars[i].charAt(ars[i].length()-1));
			oparr[i][2]=iphsh.get(ars[i]);*
			int ln=ars[i].length();
			if(!pack.containsKey(tempo))
			{
				pack.put(tempo,Integer.toString(cnt));
				oparr[i][0]=Integer.toString(cnt);
				cnt++;
			}
			else
			{
				oparr[i][0]=pack.get(tempo);
			}


			oparr[i][1]=Character.toString(ars[i].charAt(ln-1));

			tempo=iphsh.get(ars[i]);
			if(!pack.containsKey(tempo))
			{
				pack.put(tempo,Integer.toString(cnt));
				oparr[i][2]=Integer.toString(cnt);
				cnt++;
			}
			else
			{
				oparr[i][2]=pack.get(tempo);
			}

			/*pack[cnt]=tempo;
			op[j][0]=cnt;*
			System.out.println(oparr[i][0]+" "+oparr[i][1]+" "+oparr[i][2]);


		}*/
		
		
		
		
		
		int cnt=0;
			for(int j=0;j<iphsh.size();j++)
			{
				int ln=ars[j].length();
				int c=0;
				StringTokenizer token=new StringTokenizer(ars[j],",");
				String temp[]=new String[20];
				while(token.hasMoreTokens()){
					temp[c++]=token.nextToken();
				}
				
				
					oparr[j][1]=temp[c-1];
				//	pack.put(temp[c-1],
				String tm="";
				for(int i=0;i<c-1;i++)
				{
					if(i!=0)
						tm=tm+",";
						
					tm=tm+temp[i];
				}
				
				System.out.println("Temp"+tm+" "+ars[j]);
				
				if(!pack.containsKey(tm))
				{
					pack.put(tm,Integer.toString(cnt));
					//chk(cnt,tm);
					oparr[j][0]=Integer.toString(cnt++);
				}
				else
				{
					oparr[j][0]=pack.get(tm);
				}
				
				tm=iphsh.get(ars[j]);
				System.out.println("Temp2"+tm);
				
				if(!pack.containsKey(tm))
				{
					pack.put(tm,Integer.toString(cnt));
					//chk(cnt,tm);
					oparr[j][2]=Integer.toString(cnt++);
				}
				else
				{
					oparr[j][2]=pack.get(tm);
				}
				
				System.out.println("oparr "+oparr[j][0]+" "+oparr[j][1]+" "+oparr[j][2]);
			}
		
		
		
		String stateList[]=new String[100];
		Set<String> k1= pack.keySet();
		String[] ar=k1.toArray(new String[100]);
		int t;
		for(int i=0;i<pack.size();i++)
		{
			//stateList[i]=ar[i];
			t=Integer.parseInt(pack.get(ar[i]));
			stateList[t]=ar[i];
			System.out.println(ar[i]+" h");
		}
		stateList[pack.size()]="-1";
		
		System.out.println("Start Ste=" + strSt);
		fin[finc]="-1";
		SetAttributes obj=new SetAttributes(pack.size(),strtState, stateList, finsts, oparr, iphsh.size());
	}
	
	public void chk(int cnt,String tm)
	{
		System.out.println("hello123");
		StringTokenizer token=new StringTokenizer(tm,",");
		String temp;
		while(token.hasMoreTokens()){
					temp=token.nextToken();
				
		for(int g=0;!finsts[g].equals("-1");g++)
		{
			System.out.println("hello1235");
			if (temp.equals(finsts[g]))
				fin[finc++]=Integer.toString(cnt);
		
		}
		
		if(temp.equals(strtState))
			strSt=Integer.toString(cnt);
			
		}//while ends
		System.out.println("hello1234");
	}
	public Cnvrt(HashMap <String,String> iphsh,String finsts[],String strtState,int def)
	{
		System.out.println("CHK");
		this.iphsh=iphsh;
		this.finsts=finsts;
		this.strtState=strtState;
		Set<String> k= iphsh.keySet();
		//String ars[]=new String [20];

		String[] ars=k.toArray(new String[20]);
		HashMap <String,String> pack= new HashMap();
		int cnt=0;
			for(int j=0;j<iphsh.size();j++)
			{
				int ln=ars[j].length();
				int c=0;
				StringTokenizer token=new StringTokenizer(ars[j],",");
				String temp[]=new String[20];
				while(token.hasMoreTokens()){
					temp[c++]=token.nextToken();
				}
				System.out.println("temps "+temp[0]+ " "+temp[1]);
				String tmp=iphsh.get(ars[j]);
				StringTokenizer token1=new StringTokenizer(tmp,",");

				while(token1.hasMoreTokens())
				{
					oparr[cnt][0]=temp[0];
					oparr[cnt][1]=temp[1];
					oparr[cnt++][2]=token1.nextToken();
					System.out.println("oparr "+oparr[cnt-1][0]+" "+oparr[cnt-1][1]+ " "+oparr[cnt-1][2]);
				}

					//cnt=j;


			}
			for(int j=0;j<cnt;j++)
			{
				System.out.println("oparr"+oparr[j][0]+" "+oparr[j][1]+ " "+oparr[j][2]);
			}
			System.out.println("Start State: "+strtState);
			SetAttributes obj=new SetAttributes(iphsh.size()+1,strtState,finsts,oparr,cnt);
	}
}