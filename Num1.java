import java.io.*;
import java.util.*;
class Lexical {
	static File file;
	static String line;
   static int position=0;   
   static int strLength;
   static Scanner scanner;
   static int sideL;
   static int sideR;
   static int linenumber;
   static String token;
	static char[] str;	
   static boolean charLit(String s){ 
      char[] str=s.toCharArray();	
      int i, strLength = s.length(); 
      if(strLength == 0){
      	return false;
      }
      if(str[0]=='\''){
         if((strLength==3&&str[2]=='\'')||(strLength==4&&str[1]=='\\'&&(str[2]=='t'||str[2]=='n')&&str[3]=='\'')){
         	return true;
         }
      }
      return false;
   }
	static String tkKind(String tkn){ 
		if(fOperator(tkn)){ 
         return tkn; 
      }   
      if(stringLit(tkn)){
         return("string");
      }
      if(intLit(tkn)){
         return("number");
      }
		if(floatLit(tkn)){
         return("float");
      }
      if(tkn.equals("ending")){
         return tkn;
      }
		if(tkn.startsWith("#")){
         return("comment"); 
      }
		if(fIdentifier(tkn)){
         return("id");
      }
		if(fKeyword(tkn)){
         return("keyword");
      }
		if(charLit(tkn)){
         return("character");
      }
		if(tkn.equals(";")||tkn.equals("(")||tkn.equals(")")||tkn.equals("{")||tkn.equals("}")||tkn.equals(",")||tkn.equals("..")){
			return tkn;
      }
      else{
		   return("invalid");
      }
	}
   static boolean fIdentifier(String tk){ 
		char[] tkn=tk.toCharArray();
		if((tkn[0]=='$'||tkn[0]=='@')&&((tkn[1]>='a'&&tkn[1]<='z')||(tkn[1]>='A'&&tkn[1]<='Z'))){
   			for(int i=2; i<tk.length();i++){
   				if((tkn[i]=='_')||(tkn[i]>='A'&&tkn[i]<='Z')||(tkn[i] >= '0' && tkn[i]<='9')||(tkn[i]>='a'&&tkn[i]<='z')){
                  continue;
               }
               else{
                  return false;
               }
   			} 
       		return true;
		   
      }
		return false;
	}
   static boolean intLit(String s){
      char[] str=s.toCharArray();	
      int i, strLength = s.length(); 
      if(strLength == 0) 
      return false;
      if(str[0]=='0'){
         if(strLength>0&&(str[1]=='x'||str[1]=='X')){
         for(i = 2; i < strLength; i++) {
            if(!(str[i]>='0'&& str[i]<='9')&& !(str[i]>='a'&&str[i]<='f')&& !(str[i]>='A'&&str[i]<='F')){
               return false; 
            }
         } 
         return true;
         }
         else if(strLength>2&&(str[1]=='b'||str[1]=='B')&&(str[2]=='0'||str[2]=='1')){
            for(i = 3; i < strLength; i++){ 
               if(str[i]!='0'&&str[i]!='1'){
                  return false;
               }
            }
            return true;
         }
         else{
            for(i=1;i<strLength;i++){ 
               if(!(str[i]>='0'&&str[i]<='7')||(str[i]=='-'&&i>0)){
                  return false;
               }
            } 
         return true;
         }
      }
      else {
         for(i=0;i<strLength;i++){ 
            if(!(str[i]>='0'&&str[i]<='9')){
               break;
            }
         } 
         if(i==strLength){
             return true;
         }
         else{
            String endstr=s.substring(i,strLength);
            String[] lst={"l","L","u","U","ul","UL","ll","LL","ull","ULL"};
            for(int z=0;z<lst.length;z++){
               if(endstr.equals(lst[z])){
                  return true;
               }
            }
         }
      
      }
      return false;
   
   } 
	static boolean fKeyword(String str) { 
    		String[] keywords={"if","print","foreach","return","elseif","else","do","for","while","my"};
         for(int i=0; i<keywords.length;i++){
            if(str.equals(keywords[i])){
               return true;
            }
         }
    		return false; 
	} 
   
   static boolean floatLit(String s){ 
      char[] str=s.toCharArray();	
      int i, strLength = s.length(); 
      if (strLength == 0){ 
         return false;
      }
      int j=0;
      while (j<strLength && str[j]>='0' && str[j]<='9'){
         j++;
      }
      if (j<strLength){
         if (str[j]=='.'){
            for (i = j+1 ; i < strLength; i++) { 
               if (!(str[i] >= '0' && str[i]<='9')){
                  break;
               }
            }
            if (i==strLength){
               return true;
            }
            j=i;
         }
         
         if (str[j]=='e' || str[j]=='E'){
            j++;
            if (j<strLength)
            if(str[j]>='0' && str[j]<='9') {
            
            j++;
            if (j<strLength)
               if(j==strLength-1 && str[j]>='0' && str[j]<='9'){
                  return true;
               }
               else{
                  return false;
               }
               return true;
            }
            return false;
         }
      }
      return false;
   }

   static boolean fDelimiter(char symb) 
	{ 
      char[] delimiters={' ','*','/','+','-','>','<','=','{','}','%','.',',',';','!','(',')'};
      for(int i=0; i<delimiters.length;i++){
         if(symb==delimiters[i]){
            return true;
         }
      }
 		return false; 
	} 
   static boolean stringLit(String s) 
	{ 
    		char[] str=s.toCharArray();
		int i, strLength = s.length(); 
		if (strLength == 0) 
        		return false;
		if (str[0]=='\"' && str[strLength-1]=='\"')
			return true;
		return false;
	}
   static class POS{
		int lNumb;
		int oSet;
		POS(){
			lNumb=1;
			oSet=0;
		}
	}
   static POS position(){
		POS Position=new POS();
		Position.lNumb=linenumber;	 
		Position.oSet=position+1;
		return Position;
	}	
	static String nextTk(){	
		if (sideL<strLength && str[sideL]==' '){
			while (sideL <strLength && (str[sideL]==' '|| str[sideL]=='\t')) sideL++;
			 sideR=sideL;
		}
		if (sideR>=strLength) {
			if (scanner.hasNextLine()){
				 line=scanner.nextLine();
				 linenumber++;
			}
			else{
            return("ending");
			}
         strLength=line.length();
			str=line.toCharArray();
			sideL=0;
			while (sideL<strLength && (str[sideL]==' '|| str[sideL]=='\t')) sideL++;
			sideR=sideL;
		} 
    		if (sideR < strLength && sideL <= sideR) {
			if (str[sideL]=='#'){
				sideR=strLength;
				return(line.substring(sideL,sideR));
			}
			if (str[sideL]=='\"'){
				for(int z=sideL+1;z<strLength;z++){
					if (str[z]=='\"'){
						int oldsideL=sideL;
						sideL=z+1; 
						sideR=sideL;
						return(line.substring(oldsideL,z+1));
					}
				}
			}	
		
 			if(str[sideL]==':'){
				if(sideL==strLength-1){
					String tk=line.substring(sideL,sideL+1);
					position=sideL;
					sideL++;
					sideR=sideL;
					return (tk);
				}
				else if(sideL<strLength && str[sideL+1]=='=') {
					position=sideL;
					String tk=line.substring(sideL,sideL+2);
					sideL=sideL+2;
					sideR=sideL;
					return (tk);
				}
			}
			if((sideL+1<strLength && str[sideL+1]=='=')&&(str[sideL]=='>'||str[sideL]=='<'||str[sideL]=='!')){
				String tk=line.substring(sideL,sideL+2);
				position=sideL;
				sideL=sideL+2;
				sideR=sideL;
				return (tk);	
			}	
        		while(sideR <strLength && fDelimiter(str[sideR]) == false) {
            			sideR++;   
			}	
			if(sideR==strLength){
				position=sideL;
				return(line.substring(sideL,sideR));
			}
        		if(fDelimiter(str[sideR]) == true && sideL == sideR) { 
            			String tk;
				position=sideR;
				tk=new String(str,sideR,1); 
				
            			sideR++; 
            			sideL = sideR;
				return (tk);
        		} else if(fDelimiter(str[sideR]) == true && sideL != sideR || (sideR == strLength && sideL != sideR)) { 
            			String subStr = line.substring(sideL, sideR);
				position=sideL; 
				if(str[sideR]==' ') sideR++; 
            			sideL = sideR;
				
				return(subStr); 
        		} 
    		} 
		return null; 
	} 
	static boolean fOperator(String symb) 
	{ 
         String[] operators={"=","!=","<",">","<=",">=","+","-","*","/","%","!","||","&&",".."};
         for(int i=0; i<operators.length;i++){
            if(symb.equals(operators[i])){
               return true;
            }
         }
    		return false; 
	} 
	public static void main(String[] args) throws Exception {
      sideR=0;
      sideL=0; 
      linenumber=1;
      final String COMMENT="comment";
      final String ID="id";
      final String NUM="number";
      final String STRING="string";
      final String INV="invalid";
      final String FLOAT="float";
      final String END="ending";
      final String CHAR="character";
		file=new File("input.txt");
      if (!file.exists()) {
         System.out.println("File not found.");
         return;
      }
      else{
   		scanner= new Scanner(file);
   		if (scanner.hasNextLine()){
            line=scanner.nextLine();
         }
   		else{
            return;
         }
   		strLength=line.length();
   		str=line.toCharArray();
   		token=nextTk();
   		
   		String tokenT=tkKind(token);
   		
   		while (!tokenT.equals("ending")){
   			if (tokenT==COMMENT || tokenT==ID || tokenT==NUM || tokenT==FLOAT || tokenT==CHAR || tokenT==STRING) {
   					System.out.println(token);	
   				}			
   				else if (tokenT==INV){				
   					System.out.println("ERROR: "+token+" is invalid");
   				}
   				else {
   					System.out.println(token);
   				}
   		        token=nextTk();
   			if (token==null) break;
   			tokenT=tkKind(token);
   		}
      }
		
	}
}	