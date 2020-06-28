import java.nio.file.*;
import java.io.IOException;
import java.util.StringTokenizer; 

class FileHelper {
  /*
    Takes a path to a file and returns all of the lines in the file as an
    array of strings, printing an error if it failed.
  */
  static String[] getLines(String path) {
    try {
      return Files.readAllLines(Paths.get(path)).toArray(String[]::new);
    }
    catch(IOException e) {
      System.err.println("Error reading file " + path + ": " + e);
      return new String[]{"Error reading file " + path + ": " + e};
    }
  }
}
class StringSearch{
  public static void main(String[] args){
    FileHelper f = new FileHelper();
    Query q = new Query();
    Transform t= new Transform();
    String[] array = f.getLines(args[0]);
    //System.out.println(args.length);
    if(args.length>1){
      StringTokenizer trial = new StringTokenizer(args[1],"&"); 
      StringTokenizer thisForNot = new StringTokenizer(args[1],"(");
      String useThis = thisForNot.nextToken();
    
    
    while(trial.hasMoreTokens()){
      String tempword = trial.nextToken();
      boolean sendThis = false;
      //System.out.println("tempword is "+tempword);
      String word="";
      String theValue="";
      StringTokenizer st = new StringTokenizer(tempword,"=");
      if(tempword.contains("not")){
        StringTokenizer newST = new StringTokenizer(tempword,"(");
        String nextword = newST.nextToken();
        StringTokenizer newST2 = new StringTokenizer(newST.nextToken(),"=");
       word = newST2.nextToken();
       StringTokenizer lastTok = new StringTokenizer(newST2.nextToken(),")");
       theValue = lastTok.nextToken();
       //System.out.println(word);
      }
      else{
       word = st.nextToken();
        sendThis=true;
        theValue = st.nextToken();
      }
    
      
      //System.out.println("word "+word);
      if(word.equals("length")){
        int numToPass = Integer.parseInt(theValue);
        //System.out.println("hi "+numToPass);
        array= q.length(array, numToPass, sendThis);
      }
      if(word.equals("word")){
        StringTokenizer last = new StringTokenizer(theValue,"'");
        String wordToPass = last.nextToken();
        array= q.word(array, wordToPass, sendThis);
      }
      if(word.equals("contains")){
        StringTokenizer last = new StringTokenizer(theValue,"'");
        String wordToPass = last.nextToken();
        //System.out.println(wordToPass);
        array= q.contains(array, wordToPass, sendThis);
        //for(int i=0; i<array.length; i++){
        //System.out.println(array[i]);
        //}
      }
     if(word.equals("greater")){
       int numToPass = Integer.parseInt(theValue);
        //System.out.println("hi "+numToPass);
        array= q.greater(array, numToPass, sendThis);
      }
      if(word.equals("less")){
        int numToPass = Integer.parseInt(theValue);
        //System.out.println("hi "+numToPass);
        array= q.less(array, numToPass, sendThis);
      }
      if(word.equals("starts")){
        StringTokenizer st2 = new StringTokenizer     (theValue,"'");
        String wordToPass = st2.nextToken();
       array=q.starts(array, wordToPass, sendThis );
      }
      if(word.equals("ends")){
        StringTokenizer st2 = new StringTokenizer(theValue,"'");
        String wordToPass = st2.nextToken();
        array=q.ends(array, wordToPass, sendThis);
      }
      
    
    //q.length(array, wordToPass);   
    
    

    }
  }
  
  if(args.length>2){
      StringTokenizer trial2 = new StringTokenizer(args[2],"&"); 
    
      while(trial2.hasMoreTokens()){
        StringTokenizer st2 = new StringTokenizer (trial2.nextToken(),"=");
        String secondWord=st2.nextToken();
      
        if(secondWord.equals("upper")){
          String[] finalArr = t.upper(array);
          array = finalArr;
        }
        if(secondWord.equals("lower")){
           String[] finalArr = t.lower(array);
           array = finalArr;
        }
        if(secondWord.equals("range")){
          StringTokenizer st3 = new StringTokenizer (st2.nextToken(),",");
          int num1 = Integer.parseInt(st3.nextToken());
          int num2= Integer.parseInt(st3.nextToken());
          array = t.range(array, num1, num2);
        }
        if(secondWord.equals("first")){
          int num = Integer.parseInt(st2.nextToken());
         //System.out.println(num);
        
          array = t.first(array,num);
        }
         if(secondWord.equals("last")){
           int num = Integer.parseInt(st2.nextToken());
           array = t.last(array,num);
      
        }
        if(secondWord.equals("replace")){
           String initial = st2.nextToken();
           //System.out.println(initial);
           StringTokenizer thisCase = new StringTokenizer(initial,"'");
            String firstWord = thisCase.nextToken();
            //System.out.println(firstWord);
           StringTokenizer nextCase = new StringTokenizer(initial,";");
           String thirdtolastWord = nextCase.nextToken();
            //System.out.println("here "+thirdtolastWord);
           String secondtoLastWord = nextCase.nextToken();
            //System.out.println(secondtoLastWord);
            StringTokenizer lastCase = new StringTokenizer(secondtoLastWord,"'");
            String lastWord = lastCase.nextToken();
            //System.out.println(lastWord);
            array = t.replace(array, firstWord, lastWord);

        }

      }
    }
    for(int i=0; i<array.length; i++){
      System.out.println(array[i]);
    }
  }
}



class Query{
  public String[] word(String[] array, String compString, boolean bool){
    int counter = 0;
    int index = 0;
    int index2 = 0;
    String comparisonString = " "+compString+" ";
   // System.out.println("HERE"+comparisonString);
    for(int i=0; i<array.length; i++){
      StringTokenizer use = new StringTokenizer(array[i]," ");
      while(use.hasMoreTokens()){
        String currWord = use.nextToken();
        if(currWord.equals(compString)){
          counter++;
          break;
        }
        
      }
    }
    String[] newArr = new String[counter];
    for(int i=0; i<array.length; i++){
      StringTokenizer use2 = new StringTokenizer(array[i]," ");
      while(use2.hasMoreTokens()){
        String currWord = use2.nextToken();
        if(currWord.equals(compString)){
          newArr[index]=array[i];
          index++;
          break;
        }
        
      }
    }
    
    //if(bool==true){
      return newArr;
    //}
    //else{
    //  return otherArr;
    //}


    
      

    }
    
   


    
    
    

  
  public String[] contains(String[] array, String comparisonString, boolean bool){
    int index = 0;
    int index2=0;
    int counter=0;
    for(int i=0; i<array.length; i++){
      if(array[i].contains(comparisonString)){
        counter++;    
      }
    }
    String[] newArr = new String[counter];
    String[] otherArr = new String[array.length-counter];
    for(int i=0; i<array.length; i++){
      if(array[i].contains(comparisonString)&&bool==true){
        newArr[index]= array[i];
        index++;
      }
      if(bool==false){
        if(array[i].contains(comparisonString)==false){
          otherArr[index2]= array[i];
          index2++;
        }
      }
    }
    if(bool==true){
      return newArr;
    }
    else{
      return otherArr;
    }
    
  }
  public String[] length(String[]array, int lengthNum, boolean bool){
    int index = 0;
    int counter=0;
    int index2=0;
    for(int i=0; i<array.length; i++){
      if(array[i].length() == lengthNum){
        counter++;    
      }
    }
    String[] newArr = new String[counter];
    String[] otherArr = new String[array.length-counter];
    for(int i=0; i<array.length; i++){
      if(array[i].length() == lengthNum && bool==true){
        newArr[index]= array[i];
        index++;
      }
      if(array[i].length()!=lengthNum && bool==false){
        otherArr[index2]= array[i];
          index2++;
      }
    }
   if(bool==true){
      return newArr;
    }
    
    else{
      return otherArr;
      }
  }
  
  public String[] greater(String[] array, int lengthNum, boolean bool){
   
    int index = 0;
    int counter=0;
    int index2=0;
    for(int i=0; i<array.length; i++){
      if(array[i].length() > lengthNum){
        counter++;    
      }
    }
    String[] newArr = new String[counter];
    String[] otherArr = new String[array.length-counter];
    for(int i=0; i<array.length; i++){
      if(array[i].length() > lengthNum && bool==true){
        newArr[index]= array[i];
        index++;
        
      }
      if(array[i].length() < lengthNum && bool==false){
        otherArr[index2]= array[i];
          index2++;
      }
    }
    if(bool==true){
      return newArr;
      }
    
    
      return otherArr;
      
    }
  
  public String[] less(String[] array, int lengthNum, boolean bool){
   
    int index = 0;
    int counter=0;
    int index2=0;
    for(int i=0; i<array.length; i++){
      if(array[i].length() < lengthNum){
        counter++;    
      }
    }
    String[] newArr = new String[counter];
    String[] otherArr = new String[array.length-counter];
    for(int i=0; i<array.length; i++){
      if(array[i].length() < lengthNum && bool==true){
        newArr[index]= array[i];
        index++;
        
      }
      if(array[i].length() > lengthNum && bool==false){
        otherArr[index2]= array[i];
        index2++;
      }
    }
    if(bool==true){
      return newArr;
      }
    
   
      return otherArr;
      
      
    }
  
  public String[] starts(String[] array, String comparisonString, boolean bool){
    int index = 0;
    int counter=0;
    int index2=0;
    int numOfChars = comparisonString.length();
    //System.out.println("comparison is "+comparisonString);
    String newWord="";
    for(int i=0; i<array.length; i++){
      String newString = array[i];
      for(int j=0; j<numOfChars; j++){
        newWord = newWord + newString.charAt(j);
      }
      //System.out.println("newwordis + "+newWord);
      if(newWord.equals(comparisonString)){
        counter++;
      }
      newWord="";
    }
    index=0;
    newWord = "";
    //System.out.println("counter "+counter);
    //System.out.println("here "+numOfChars);
    String[] newArr = new String[counter];
    String[] otherArr = new String[array.length-counter];
    for(int i=0; i<array.length; i++){
      String newString = array[i];
      for(int j=0; j<numOfChars; j++){
        newWord = newWord + newString.charAt(j);
      }
      //System.out.println("hi");
      //System.out.println(newWord);
      if(newWord.equals(comparisonString)){
       //System.out.println(array[i]);
        //System.out.println(index);
        newArr[index]= array[i];
        index++;
      }
      if(newWord.equals(comparisonString)==false){
        otherArr[index2]= array[i];
        index2++;
      }
      newWord="";
    }
    
    if(bool==true){
      return newArr;
      }
    
   
      return otherArr;
      
    }
  
  public String[] ends(String[] array, String comparisonString, boolean bool){
    //System.out.println("comparisonString is "+comparisonString);
    
    int index = 0;
    int counter = 0;
    int index2=0;
    boolean ans = false;
    for(int i=0; i<array.length; i++){
      //System.out.println(array[i]);
      if(array[i].endsWith(comparisonString)){
        ans = true;
      }
      if(ans == true){
        counter++;
      }
      ans=false;
    }
    index=0;
    //System.out.println(counter);
    ans = false;
    String[] newArr = new String[counter];
    String[] otherArr = new String[array.length-counter];
    //System.out.println(comparisonString);
    for(int i=0; i<array.length; i++){
      if(array[i].endsWith(comparisonString)){
        ans = true;
      }
      if(ans == true){
        newArr[index]=array[i];
        index++;
      }
      if(ans==false){
        otherArr[index2]=array[i];
        index2++;
      }
      //System.out.println("boolean is "+ans);
      ans=false;
      
    }
    if(bool==true){
      return newArr;
      }
    
   
      return otherArr;
      
    }
  
   
  
}
class Transform{

  public String[] range(String[] array, int start, int end){
    String[] newArr = new String[array.length];
    int index=0;
    for(int i = 0; i<array.length; i++){
      if(start>array[i].length()){
        String empty = new String("");
        newArr[index]=empty;
        index++;
      }
      if(end>array[i].length()){
        newArr[index]=array[i].substring(start);
        index++;
      }
      if(end<=array[i].length()){
        String addThis = array[i].substring(start,end);
        newArr[index]= addThis;
        index++;
      }
      
    }
    return newArr;

  }
  public String[] upper(String[] array){
    int index = 0;
    String[] newArr = new String[array.length];
    for(int i=0; i<array.length; i++){
      newArr[index]= array[i].toUpperCase();
      index++;
    }
    return newArr;
  }
  public String[] lower(String[] array){
    String[] newArr = new String[array.length];
    int index = 0;
    for(int i=0; i<array.length; i++){
      newArr[index]= array[i].toLowerCase();
      index++;
    }
    return newArr;
  }
  public String[] first(String[]array, int numOfChars){
    String[] newArr = new String[array.length];
    int index = 0; 
    for(int i=0; i<array.length; i++){
      String firstLine = array[i];
      String newLine="";
      for(int j = 0; j<numOfChars; j++){
        if(numOfChars>array[i].length()){
          newLine = array[i];
        }
        else{
          newLine =newLine+firstLine.charAt(j);
        }
        
        //System.out.println(newLine+firstLine.charAt(j));
      }
      newArr[i] = newLine;
      //System.out.println(newArr[i]);
      
    }
    return newArr;
  }
  public String[] last(String[]array, int numOfChars){
    String[] newArr = new String[array.length];
    int index = 0; 
    //System.out.println("hi");
    for(int i=0; i<array.length; i++){
      String firstLine = array[i];
      String newLine="";
      for(int j=firstLine.length() - numOfChars; j<firstLine.length(); j++){
        newLine=newLine+firstLine.charAt(j);
        //System.out.println(newLine);

      }
      newArr[i]=newLine;
      
    }
    return newArr;
  }
  public String[] replace(String[] array, String removeWord, String replacementWord){
    //String[] newArr = new String[array.length];
    int index = 0; 
    for(int i = 0; i<array.length; i++){
      if(array[i].contains(removeWord)){
        array[i]=array[i].replace(removeWord, replacementWord);
      }
    }
    return array;
  }

}