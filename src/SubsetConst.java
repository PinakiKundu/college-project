/**
 * @(#)SubsetConst.java
 *
 *
 * @author  Sujit K. Biswas.(04CSE43)
 * @version 1.00 2008/3/8
 */

import java.util.*;
import java.util.StringTokenizer;
import java.*;
public class SubsetConst {

	String[] dStates;
	int mark[];
	int crnt;
	Stack stateStck;
	StringTokenizer token,token1;
	String temp4,temp3,temp5,temp6;

	String[] eClosure;
	String state;

	HashMap<String, String> dTrans;
	String[] finalStates=new String[100];
	String input[];
	String strSt;
	int fin[]=new int[100];
	int finc=0;
	
	String str[]=new String[5];

	public boolean IsStateEqual(String dfaState,String eClosureState)
		{

		String element;
		/*if(dfaState.compareTo(eClosureState)!= 0)
			{
			//System.out.println("Different length");
			return false;
			}
		else
			{*/
			StringTokenizer token2=new StringTokenizer(eClosureState,",");
			while(token2.hasMoreTokens())
				{
				element=token2.nextToken();
				if(dfaState.indexOf(element)==-1)
					{
					//System.out.println("NOT EQUAL");
					return false;
					}
				else
					{
					//System.out.println("EQUAL");
					continue;
					}

				}
			//System.out.println("returning true");
			return true;
			//}


		}
	public String epsilonClosure(String state,HashMap<String, String> nfaTrans) {
    	stateStck =new Stack();
    	String temp1;
    	Object temp2;
    	token =new StringTokenizer(state,",");
    	int i=0;

    	while(token.hasMoreTokens())
    		{
    		temp1=token.nextToken();
    		stateStck.push(temp1);
    		eClosure[i++]= temp1;
    		}

    	while(!stateStck.empty())
    		{
			temp2=stateStck.pop();
			try{

				state=nfaTrans.get(temp2.toString()+",^");
				token1=new StringTokenizer(state,",");
				while(token1.hasMoreTokens())
					{
					temp1=token1.nextToken();
					for(int j=0;j<i;j++)
						{
						if(eClosure[j].equals(temp1))
							break;
						else if(j==(i-1))
								{
								eClosure[i++]=temp1;
								stateStck.push(temp1);
								}
						else
							continue;

						}//end of for loop.
					}
				}catch(NullPointerException e){
					//if a NullPointerException arises then continue
					//from the begining of the loop.
					continue;
					}

    		}
    //Coping contents of eClosure[] to a String object.
    state=eClosure[0];
    for(int j=1;j<i;j++)
    	state=state + ","+ eClosure[j];

	return state;

    }

	public void addToDStates(String dfaState)
		{
		dStates[crnt++]=dfaState;
		}

	public String move(String nfaState ,String inptSmbl,HashMap<String, String> nfaTrans)
		{

		return nfaTrans.get(nfaState + "," + inptSmbl);//"stateTable" parameter needed to be passed.

		}

    public SubsetConst(HashMap<String, String> nfaTrans,String startState,String input[],String finalStates[])
    	{
    		strSt=startState;
    	this.finalStates=finalStates;
    	this.input=input;
		int flag=0;
    	crnt=0;
    	mark = new int[100];
    	dStates =new String[100];
    	eClosure =new String[50];
    	StringTokenizer token1;
    	dTrans = new HashMap<String, String>();
    	for(int k=0;k<50;k++)
    		mark[k]=0;
		System.out.println("NFA Start State:" + startState);
    	this.addToDStates(this.epsilonClosure(startState,nfaTrans));

    	for(int i=0;i<crnt;i++)
    		{
			//flag=0;
    		//System.out.print("control here");
    		if(mark[i]==0)
    			{
				//flag=0;
    			mark[i]=1;
    			for(int j=0;!input[j].equals("-1");j++)
    				{
					flag=0;
    				//System.out.print("reaching1");
    				token1=new StringTokenizer(dStates[i],",");
    				temp4="-1";
    				while(token1.hasMoreTokens())
    					{
    					//System.out.print("reaching2");
    					temp5=token1.nextToken();

    					try{

							temp3=this.epsilonClosure(this.move(temp5,input[j],nfaTrans),nfaTrans);
							if(temp4.equals("-1"))
								temp4=temp3;
							else
								temp4=temp4 +","+ temp3;
							}catch(NullPointerException e){
    							//if a NullPointerException arises then continue
								//from the begining of the loop.
    							continue;
    							}//end of catch block.
    					}//end of while

						for(int k=0;k<crnt;k++)
							{

							//System.out.println(i+" "+k+" "+crnt);

							if(this.IsStateEqual(dStates[k],temp4) && (!temp4.equals("-1")))
								{
								flag=1;
								//System.out.println("i,k same");
								dTrans.put(dStates[i]+","+ input[j],dStates[k]);//adding transition rule to DFA state table.
								break;
								}
							/*else
								continue; //continue with inner for loop.
							*/
							}
						if(flag!=1 && (!temp4.equals("-1")))
							{
							this.addToDStates(temp4);//then add temp4 to dStates
							dTrans.put(dStates[i]+","+ input[j],temp4);//adding transition rule to DFA state table.
							//System.out.println("dStates:"+temp4);
							}
						else
							continue; //continue with outer for loop.



    				//System.out.print("reaching3");
    				}//end of for(int j=0;!input[j].equals("-1");j++)

    			}

    		}
	//System.out.println("chk1");
    
    System.out.println("DFA"+finalStates[0]);
    Cnvrt obj=new Cnvrt(dTrans,finalStates,startState) ;
	cnvrt2arr(startState);
	System.out.println("crnt value :" +crnt);
	for(int x=0;x<crnt;x++)// printing the dStates.
		System.out.println("dStates[x]:"+ dStates[x]);
	//Creating a Set view of Hash Map.
   	Set<Map.Entry<String, String>> Set = dTrans.entrySet();
   	//System.out.println("works");
	//Printing the HashMap.
	System.out.println("DFA Production rules ::");
	for(Map.Entry<String, String> display : Set)
			{
			System.out.print(display.getKey()+ " --> ");
			System.out.println(display.getValue());
			}

}
/*public static void main(String[] a)
	{
	HashMap<String, String> nfaTrans= new HashMap<String, String>();
	String startState="0";String[] input=new String[50];
	SubsetConst obj=new SubsetConst(nfaTrans,startState,input);
	}*/



	public void cnvrt2arr(String startState)
	{


		Set<String> k= dTrans.keySet();
		String str[]=new String[20];
		str[0]=startState;

		String[] ars=k.toArray(new String[20]);
		String oparr[][]=new String[20][3];
		int i=dTrans.size();
		System.out.println("hello:"+i);
		HashMap<String,String> pack= new HashMap();
	
		int cnt=0;
			for(int j=0;j<dTrans.size();j++)
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
				for(int l=0;l<c-1;l++)
				{
					if(l!=0)
						tm=tm+",";
						
					tm=tm+temp[l];
				}
				
				//System.out.println("Temp "+tm+" "+ars[j]);
				
				if(!pack.containsKey(tm))
				{
					pack.put(tm,Integer.toString(cnt));
					chk(cnt,tm);
					oparr[j][0]=Integer.toString(cnt++);
				}
				else
				{
					oparr[j][0]=pack.get(tm);
				}
				
				tm=dTrans.get(ars[j]);
				//System.out.println("Temp2"+tm);
				
				if(!pack.containsKey(tm))
				{
					pack.put(tm,Integer.toString(cnt));
					chk(cnt,tm);
					oparr[j][2]=Integer.toString(cnt++);
				}
				else
				{
					oparr[j][2]=pack.get(tm);
				}	
			
			
			System.out.println("Subset"+oparr[j][0]+" "+oparr[j][1]+" "+oparr[j][2]);

		}
		int j=0;
		//int fin[]=new int[20];
		/*while(finalStates[j]!="-1")
		{
			fin[j]=Integer.parseInt(finalStates[j]);
			System.out.println("chk "+fin[j]+" "+finalStates[j]);
			j++;
		}*/

		System.out.println("final state cnt:"+j);
		int l=0;
		while(input[l]!="-1")
		{
			System.out.println("chk1="+input[l]);
			l++;
		}

		DfaOptimzer obj=new DfaOptimzer(oparr,i,str,1,fin,finc,input,l);



	}
	
	public void chk(int cnt,String tm)
	{
		//System.out.println("hello123");
		StringTokenizer token=new StringTokenizer(tm,",");
		String temp;
		while(token.hasMoreTokens()){
					temp=token.nextToken();
				
		for(int g=0;!finalStates[g].equals("-1");g++)
		{
			//System.out.println("hello1235");
			if (temp.equals(finalStates[g]))
				fin[finc++]=cnt;
		
		}
		
		if(temp.equals(strSt))
			str[0]=Integer.toString(cnt);
			
		}//while ends
		//System.out.println("hello1234");
	}
}