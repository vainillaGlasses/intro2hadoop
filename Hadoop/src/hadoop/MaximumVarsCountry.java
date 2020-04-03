package hadoop; 

import java.util.*; 

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

import hadoop.VariableCountry;

public class MaximumVarsCountry { 
   //Mapper class 
   public static class E_EMapper extends MapReduceBase implements 
   Mapper<LongWritable , /*Input key Type */ 
   Text,                 /*Input value Type*/ 
   Text,                 /*Output key Type*/ 
   VariableCountry>         /*Output value Type*/ 
   {
	   
      //Map function 
      public void map(LongWritable key, Text value, 
      OutputCollector<Text, VariableCountry> output,   
      Reporter reporter) throws IOException {
         String line = value.toString();
         StringTokenizer s = new StringTokenizer(line, ";"); 
         
         String continent = s.nextToken();
         
         // Hay que fijarnos si somos Antarctica, ya que no tenemos paises
         
         String countryTemp = "";
         
         if (continent.equals("Antarctica")) {
            countryTemp = "Antarctica";
         } else {
            countryTemp = s.nextToken();
         }
         
         // Sacamos el país
         Text country = new Text(countryTemp);
         
         // No nos interesa la estacion ni el año, los desechamos
         s.nextToken();
         s.nextToken();
         s.nextToken();
         s.nextToken();
         s.nextToken();
         
         // Creamos un nuevo objeto y empezamos a llenarlo
         VariableCountry newVarCountry = new VariableCountry();
         
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
    	 newVarCountry.setCountryT(country);
    	 newVarCountry.setCountryTM(country);
    	 newVarCountry.setCountryTm(country);
    	 newVarCountry.setCountryPP(country);
    	 newVarCountry.setCountryV(country);
    	 newVarCountry.setCountryRA(country);
    	 newVarCountry.setCountrySN(country);
    	 newVarCountry.setCountryTS(country);
    	 newVarCountry.setCountryFG(country);
    	 newVarCountry.setCountryTN(country);
    	 newVarCountry.setCountryGR(country);
         
    	 // En caso de ser un -, ponemos la temperatura minima
    	 newVarCountry.setValueT(new FloatWritable(tokenT.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenT)));
    	 newVarCountry.setValueTM(new FloatWritable(tokenTM.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTM)));
    	 newVarCountry.setValueTm(new FloatWritable(tokenTm.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTm)));
    	 newVarCountry.setValuePP(new FloatWritable(tokenPP.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenPP)));
    	 newVarCountry.setValueV(new FloatWritable(tokenV.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenV)));
    	 newVarCountry.setValueRA(new FloatWritable(tokenRA.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenRA)));
    	 newVarCountry.setValueSN(new FloatWritable(tokenSN.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenSN)));
    	 newVarCountry.setValueTS(new FloatWritable(tokenTS.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTS)));
    	 newVarCountry.setValueFG(new FloatWritable(tokenFG.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenFG)));
    	 newVarCountry.setValueTN(new FloatWritable(tokenTN.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTN)));
    	 newVarCountry.setValueGR(new FloatWritable(tokenGR.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenGR)));
    	 
    	 // Y lo enviamos al output
         output.collect(new Text(continent), newVarCountry);
      }
   }
   
   
   
   //Reducer class 
   public static class E_EReduce extends MapReduceBase implements 
   Reducer< Text, VariableCountry, Text, VariableCountry> {  
   
      //Reduce function
      public void reduce(Text key, Iterator <VariableCountry> values, 
    		  OutputCollector<Text, VariableCountry> output, Reporter reporter) throws IOException {
         
    	 // Creamos las variables de almacenamiento temporal
    	 float maxValT = -Float.MAX_VALUE;
    	 String maxCountryT = "";
    	 
    	 float maxValTM = -Float.MAX_VALUE;
    	 String maxCountryTM = "";
    	 
    	 float maxValTm = -Float.MAX_VALUE;
    	 String maxCountryTm = "";
    	 
    	 float maxValPP = -Float.MAX_VALUE;
    	 String maxCountryPP = "";

    	 float maxValV = -Float.MAX_VALUE;
    	 String maxCountryV = "";
    	 
    	 float maxValRA = -Float.MAX_VALUE;
    	 String maxCountryRA = "";
    	 
    	 float maxValSN = -Float.MAX_VALUE;
    	 String maxCountrySN = "";
    	 
    	 float maxValTS = -Float.MAX_VALUE;
    	 String maxCountryTS = "";
    	 
    	 float maxValFG = -Float.MAX_VALUE;
    	 String maxCountryFG = "";
    	 
    	 float maxValTN = -Float.MAX_VALUE;
    	 String maxCountryTN = "";
    	 
    	 float maxValGR = -Float.MAX_VALUE;
    	 String maxCountryGR = "";
    	 

    	 VariableCountry currVarCountry = null;
    	     	 
    	 while (values.hasNext()) {
    		 currVarCountry = values.next();
    		 
    		 // Sacamos todos los valores y los comparamos
    		 // Si en efecto son mayores, los guardamos
    		 float currValueT = Float.parseFloat(currVarCountry.getValueT().toString());
    		 float currValueTM = Float.parseFloat(currVarCountry.getValueTM().toString());
    		 float currValueTm = Float.parseFloat(currVarCountry.getValueTm().toString());
    		 float currValuePP = Float.parseFloat(currVarCountry.getValuePP().toString());
    		 float currValueV = Float.parseFloat(currVarCountry.getValueV().toString());
    		 float currValueRA = Float.parseFloat(currVarCountry.getValueRA().toString());
    		 float currValueSN = Float.parseFloat(currVarCountry.getValueSN().toString());
    		 float currValueTS = Float.parseFloat(currVarCountry.getValueTS().toString());
    		 float currValueFG = Float.parseFloat(currVarCountry.getValueFG().toString());
    		 float currValueTN = Float.parseFloat(currVarCountry.getValueTN().toString());
    		 float currValueGR = Float.parseFloat(currVarCountry.getValueGR().toString());
    		 
    		 System.out.println(currValueTN);
    		 
    		 // Comparamos T
    		 if (currValueT > maxValT) {
    			 maxValT = currValueT;
    			 maxCountryT = currVarCountry.getCountryT().toString();
    		 }

    		 // Comparamos TM
    		 if (currValueTM > maxValTM) {
    			 maxValTM = currValueTM;
    			 maxCountryTM = currVarCountry.getCountryTM().toString();
    		 }
    		 
    		 // Comparamos Tm
    		 if (currValueTm > maxValTm) {
    			 maxValTm = currValueTm;
    			 maxCountryTm = currVarCountry.getCountryTm().toString();
    		 }
    		 
    		 // Comparamos PP
    		 if (currValuePP > maxValPP) {
    			 maxValPP = currValuePP;
    			 maxCountryPP = currVarCountry.getCountryPP().toString();
    		 }
    		 
    		 // Comparamos V
    		 if (currValueV > maxValV) {
    			 maxValV = currValueV;
    			 maxCountryV = currVarCountry.getCountryV().toString();
    		 }
    		 
    		// Comparamos RA
    		 if (currValueRA > maxValRA) {
    			 maxValRA = currValueRA;
    			 maxCountryRA = currVarCountry.getCountryRA().toString();
    		 }
    		 
    		// Comparamos SN
    		 if (currValueSN > maxValSN) {
    			 maxValSN = currValueSN;
    			 maxCountrySN = currVarCountry.getCountrySN().toString();
    		 }
    		 
    		// Comparamos TS
    		 if (currValueTS > maxValTS) {
    			 maxValTS = currValueTS;
    			 maxCountryTS = currVarCountry.getCountryTS().toString();
    		 }
    		 
    		// Comparamos FG
    		 if (currValueFG > maxValFG) {
    			 maxValFG = currValueFG;
    			 maxCountryFG = currVarCountry.getCountryFG().toString();
    		 }
    		 
    		// Comparamos TN
    		 if (currValueTN > maxValTN) {
    			 maxValTN = currValueTN;
    			 maxCountryTN = currVarCountry.getCountryTN().toString();
    		 }
    		 
    		// Comparamos GR
    		 if (currValueGR > maxValGR) {
    			 maxValGR = currValueGR;
    			 maxCountryGR = currVarCountry.getCountryGR().toString();
    		 }
    	 }
    	
    	 // Creamos un nuevo objeto variableyear, el de salida
    	 VariableCountry outputVar = new VariableCountry();
    	 
    	 // Seteamos todos los parametros
    	 outputVar.setCountryT(new Text(maxCountryT));
    	 outputVar.setValueT(new FloatWritable(maxValT));
    	 
    	 outputVar.setCountryTM(new Text(maxCountryTM));
    	 outputVar.setValueTM(new FloatWritable(maxValTM));
    	 
    	 outputVar.setCountryTm(new Text(maxCountryTm));
    	 outputVar.setValueTm(new FloatWritable(maxValTm));
    	 
    	 outputVar.setCountryPP(new Text(maxCountryPP));
    	 outputVar.setValuePP(new FloatWritable(maxValPP));
    	 
    	 outputVar.setCountryV(new Text(maxCountryV));
    	 outputVar.setValueV(new FloatWritable(maxValV));
    	 
    	 outputVar.setCountryRA(new Text(maxCountryRA));
    	 outputVar.setValueRA(new FloatWritable(maxValRA));
    	 
    	 outputVar.setCountrySN(new Text(maxCountrySN));
    	 outputVar.setValueSN(new FloatWritable(maxValSN));
    	 
    	 outputVar.setCountryTS(new Text(maxCountryTS));
    	 outputVar.setValueTS(new FloatWritable(maxValTS));
    	 
    	 outputVar.setCountryFG(new Text(maxCountryFG));
    	 outputVar.setValueFG(new FloatWritable(maxValFG));
    	 
    	 outputVar.setCountryTN(new Text(maxCountryTN));
    	 outputVar.setValueTN(new FloatWritable(maxValTN));
    	 
    	 outputVar.setCountryGR(new Text(maxCountryGR));
    	 outputVar.setValueGR(new FloatWritable(maxValGR));
    	 
    	 output.collect(key, outputVar);
      }
   }  
   
   
   //Main function 
   public static void main(String args[]) throws Exception {
	  
      JobConf conf = new JobConf(MaximumVarsCountry.class); 
      
      conf.setJobName("maximum_vars_country");
      
      conf.setOutputKeyClass(Text.class);
      conf.setOutputValueClass(VariableCountry.class); 
      
      conf.setMapperClass(E_EMapper.class);
      conf.setReducerClass(E_EReduce.class);
      
      conf.setInputFormat(TextInputFormat.class); 
      conf.setOutputFormat(TextOutputFormat.class); 
      
      FileInputFormat.setInputPaths(conf, new Path(args[0])); 
      FileOutputFormat.setOutputPath(conf, new Path(args[1])); 
      
      JobClient.runJob(conf); 
   } 
}