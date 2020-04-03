package hadoop; 

import java.util.*; 

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

import hadoop.VariableYear;

public class MaximumVars { 
   //Mapper class 
   public static class E_EMapper extends MapReduceBase implements 
   Mapper<LongWritable , /*Input key Type */ 
   Text,                 /*Input value Type*/ 
   Text,                 /*Output key Type*/ 
   VariableYear>         /*Output value Type*/ 
   {
	   
      //Map function 
      public void map(LongWritable key, Text value, 
      OutputCollector<Text, VariableYear> output,   
      Reporter reporter) throws IOException {
         String line = value.toString();
         System.out.println(line);
         StringTokenizer s = new StringTokenizer(line, ";"); 
         
         String continent = s.nextToken();
         String country = "";
         
         if (!continent.equals("Antarctica")) {
        	 country = s.nextToken();
         }
         
         // No nos es relevante la estación, su ID y ubicación la desechamos
         s.nextToken();
         s.nextToken();
         s.nextToken();
         s.nextToken();
         
         // Sacamos el año
         IntWritable year = new IntWritable(Integer.parseInt(s.nextToken()));
         
         // Creamos un nuevo objeto y empezamos a llenarlo
         VariableYear newVarYear = new VariableYear();
         
         // Deberian haber 11 tokens más
    	 String tokenT  = s.nextToken();
    	 String tokenTM = s.nextToken();
    	 String tokenTm = s.nextToken();
    	 String tokenPP = s.nextToken();
    	 String tokenV  = s.nextToken();
    	 String tokenRA = s.nextToken();
    	 String tokenSN = s.nextToken();
    	 String tokenTS = s.nextToken();
    	 String tokenFG = s.nextToken();
    	 String tokenTN = s.nextToken();
    	 String tokenGR = s.nextToken();
         
    	 // Todos los años son iguales al principio
    	 newVarYear.setYearT(year);
    	 newVarYear.setYearTM(year);
    	 newVarYear.setYearTm(year);
    	 newVarYear.setYearPP(year);
    	 newVarYear.setYearV(year);
    	 newVarYear.setYearRA(year);
    	 newVarYear.setYearSN(year);
    	 newVarYear.setYearTS(year);
    	 newVarYear.setYearFG(year);
    	 newVarYear.setYearTN(year);
    	 newVarYear.setYearGR(year);
         
    	 // En caso de ser un -, ponemos la temperatura minima
    	 newVarYear.setValueT(new FloatWritable(tokenT.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenT)));
    	 newVarYear.setValueTM(new FloatWritable(tokenTM.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTM)));
    	 newVarYear.setValueTm(new FloatWritable(tokenTm.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTm)));
    	 newVarYear.setValuePP(new FloatWritable(tokenPP.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenPP)));
    	 newVarYear.setValueV(new FloatWritable(tokenV.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenV)));
    	 newVarYear.setValueRA(new FloatWritable(tokenRA.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenRA)));
    	 newVarYear.setValueSN(new FloatWritable(tokenSN.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenSN)));
    	 newVarYear.setValueTS(new FloatWritable(tokenTS.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTS)));
    	 newVarYear.setValueFG(new FloatWritable(tokenFG.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenFG)));
    	 newVarYear.setValueTN(new FloatWritable(tokenTN.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTN)));
    	 newVarYear.setValueGR(new FloatWritable(tokenGR.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenGR)));
    	 
    	 // Y lo enviamos al output
         output.collect(new Text(continent + ";" + country), newVarYear);
      }
   } 
   
   
   //Reducer class 
   public static class E_EReduce extends MapReduceBase implements 
   Reducer< Text, VariableYear, Text, VariableYear> {  
   
      //Reduce function
      public void reduce(Text key, Iterator <VariableYear> values, 
    		  OutputCollector<Text, VariableYear> output, Reporter reporter) throws IOException {
         
    	 // Creamos las variables de almacenamiento temporal
    	 float maxValT = -Float.MAX_VALUE;
    	 int maxYearT = 0;
    	 
    	 float maxValTM = -Float.MAX_VALUE;
    	 int maxYearTM = 0;
    	 
    	 float maxValTm = -Float.MAX_VALUE;
    	 int maxYearTm = 0;
    	 
    	 float maxValPP = -Float.MAX_VALUE;
    	 int maxYearPP = 0;

    	 float maxValV = -Float.MAX_VALUE;
    	 int maxYearV = 0;
    	 
    	 float maxValRA = -Float.MAX_VALUE;
    	 int maxYearRA = 0;
    	 
    	 float maxValSN = -Float.MAX_VALUE;
    	 int maxYearSN = 0;
    	 
    	 float maxValTS = -Float.MAX_VALUE;
    	 int maxYearTS = 0;
    	 
    	 float maxValFG = -Float.MAX_VALUE;
    	 int maxYearFG = 0;
    	 
    	 float maxValTN = -Float.MAX_VALUE;
    	 int maxYearTN = 0;
    	 
    	 float maxValGR = -Float.MAX_VALUE;
    	 int maxYearGR = 0;
    	 

    	 VariableYear currVarYear = null;
    	     	 
    	 while (values.hasNext()) {
    		 currVarYear = values.next();
    		 
    		 // Sacamos todos los valores y los comparamos
    		 // Si en efecto son mayores, los guardamos
    		 float currValueT = Float.parseFloat(currVarYear.getValueT().toString());
    		 float currValueTM = Float.parseFloat(currVarYear.getValueTM().toString());
    		 float currValueTm = Float.parseFloat(currVarYear.getValueTm().toString());
    		 float currValuePP = Float.parseFloat(currVarYear.getValuePP().toString());
    		 float currValueV = Float.parseFloat(currVarYear.getValueV().toString());
    		 float currValueRA = Float.parseFloat(currVarYear.getValueRA().toString());
    		 float currValueSN = Float.parseFloat(currVarYear.getValueSN().toString());
    		 float currValueTS = Float.parseFloat(currVarYear.getValueTS().toString());
    		 float currValueFG = Float.parseFloat(currVarYear.getValueFG().toString());
    		 float currValueTN = Float.parseFloat(currVarYear.getValueTN().toString());
    		 float currValueGR = Float.parseFloat(currVarYear.getValueGR().toString());
    		 
    		 System.out.println(currValueTN);
    		 
    		 // Comparamos T
    		 if (currValueT > maxValT) {
    			 maxValT = currValueT;
    			 maxYearT = Integer.parseInt(currVarYear.getYearT().toString());
    		 }

    		 // Comparamos TM
    		 if (currValueTM > maxValTM) {
    			 maxValTM = currValueTM;
    			 maxYearTM = Integer.parseInt(currVarYear.getYearTM().toString());
    		 }
    		 
    		 // Comparamos Tm
    		 if (currValueTm > maxValTm) {
    			 maxValTm = currValueTm;
    			 maxYearTm = Integer.parseInt(currVarYear.getYearTm().toString());
    		 }
    		 
    		 // Comparamos PP
    		 if (currValuePP > maxValPP) {
    			 maxValPP = currValuePP;
    			 maxYearPP = Integer.parseInt(currVarYear.getYearPP().toString());
    		 }
    		 
    		 // Comparamos V
    		 if (currValueV > maxValV) {
    			 maxValV = currValueV;
    			 maxYearV = Integer.parseInt(currVarYear.getYearV().toString());
    		 }
    		 
    		// Comparamos RA
    		 if (currValueRA > maxValRA) {
    			 maxValRA = currValueRA;
    			 maxYearRA = Integer.parseInt(currVarYear.getYearRA().toString());
    		 }
    		 
    		// Comparamos SN
    		 if (currValueSN > maxValSN) {
    			 maxValSN = currValueSN;
    			 maxYearSN = Integer.parseInt(currVarYear.getYearSN().toString());
    		 }
    		 
    		// Comparamos TS
    		 if (currValueTS > maxValTS) {
    			 maxValTS = currValueTS;
    			 maxYearTS = Integer.parseInt(currVarYear.getYearTS().toString());
    		 }
    		 
    		// Comparamos FG
    		 if (currValueFG > maxValFG) {
    			 maxValFG = currValueFG;
    			 maxYearFG = Integer.parseInt(currVarYear.getYearFG().toString());
    		 }
    		 
    		// Comparamos TN
    		 if (currValueTN > maxValTN) {
    			 maxValTN = currValueTN;
    			 maxYearTN = Integer.parseInt(currVarYear.getYearTN().toString());
    		 }
    		 
    		// Comparamos GR
    		 if (currValueGR > maxValGR) {
    			 maxValGR = currValueGR;
    			 maxYearGR = Integer.parseInt(currVarYear.getYearGR().toString());
    		 }
    	 }
    	
    	 // Creamos un nuevo objeto variableyear, el de salida
    	 VariableYear outputVar = new VariableYear();
    	 
    	 // Seteamos todos los parametros
    	 outputVar.setYearT(new IntWritable(maxYearT));
    	 outputVar.setValueT(new FloatWritable(maxValT));
    	 
    	 outputVar.setYearTM(new IntWritable(maxYearTM));
    	 outputVar.setValueTM(new FloatWritable(maxValTM));
    	 
    	 outputVar.setYearTm(new IntWritable(maxYearTm));
    	 outputVar.setValueTm(new FloatWritable(maxValTm));
    	 
    	 outputVar.setYearPP(new IntWritable(maxYearPP));
    	 outputVar.setValuePP(new FloatWritable(maxValPP));
    	 
    	 outputVar.setYearV(new IntWritable(maxYearV));
    	 outputVar.setValueV(new FloatWritable(maxValV));
    	 
    	 outputVar.setYearRA(new IntWritable(maxYearRA));
    	 outputVar.setValueRA(new FloatWritable(maxValRA));
    	 
    	 outputVar.setYearSN(new IntWritable(maxYearSN));
    	 outputVar.setValueSN(new FloatWritable(maxValSN));
    	 
    	 outputVar.setYearTS(new IntWritable(maxYearTS));
    	 outputVar.setValueTS(new FloatWritable(maxValTS));
    	 
    	 outputVar.setYearFG(new IntWritable(maxYearFG));
    	 outputVar.setValueFG(new FloatWritable(maxValFG));
    	 
    	 outputVar.setYearTN(new IntWritable(maxYearTN));
    	 outputVar.setValueTN(new FloatWritable(maxValTN));
    	 
    	 outputVar.setYearGR(new IntWritable(maxYearGR));
    	 outputVar.setValueGR(new FloatWritable(maxValGR));
    	 
    	 output.collect(key, outputVar);
      }
   }  
   
   
   //Main function 
   public static void main(String args[]) throws Exception {
	  
      JobConf conf = new JobConf(MaximumVars.class); 
      
      conf.setJobName("maximum_vars");
      
      conf.setOutputKeyClass(Text.class);
      conf.setOutputValueClass(VariableYear.class); 
      
      conf.setMapperClass(E_EMapper.class);
      conf.setReducerClass(E_EReduce.class);
      
      conf.setInputFormat(TextInputFormat.class); 
      conf.setOutputFormat(TextOutputFormat.class); 
      
      FileInputFormat.setInputPaths(conf, new Path(args[0])); 
      FileOutputFormat.setOutputPath(conf, new Path(args[1])); 
      
      JobClient.runJob(conf); 
   } 
} 