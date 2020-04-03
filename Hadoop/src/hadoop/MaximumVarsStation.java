package hadoop; 

import java.util.*; 

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

import hadoop.VariableStation;

public class MaximumVarsStation { 
   //Mapper class 
   public static class E_EMapper extends MapReduceBase implements 
   Mapper<LongWritable , /*Input key Type */ 
   Text,                 /*Input value Type*/ 
   Text,                 /*Output key Type*/ 
   VariableStation>         /*Output value Type*/ 
   {
	   
      //Map function 
      public void map(LongWritable key, Text value, 
      OutputCollector<Text, VariableStation> output,   
      Reporter reporter) throws IOException {
         String line = value.toString();
         StringTokenizer s = new StringTokenizer(line, ";"); 
         
         String continent = s.nextToken();
         String country = "";
         
         if (!continent.equals("Antarctica")) {
        	 country = s.nextToken();
         }
         
         // Sacamos la estación (ubicación e ID)
         Text station = new Text(s.nextToken() + ";" + s.nextToken());
         
         // No nos interesa las coordenadas ni el año, los desechamos
         s.nextToken();
         s.nextToken();
         s.nextToken();
         
         // Creamos un nuevo objeto y empezamos a llenarlo
         VariableStation newVarStation = new VariableStation();
         
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
    	 newVarStation.setStationT(station);
    	 newVarStation.setStationTM(station);
    	 newVarStation.setStationTm(station);
    	 newVarStation.setStationPP(station);
    	 newVarStation.setStationV(station);
    	 newVarStation.setStationRA(station);
    	 newVarStation.setStationSN(station);
    	 newVarStation.setStationTS(station);
    	 newVarStation.setStationFG(station);
    	 newVarStation.setStationTN(station);
    	 newVarStation.setStationGR(station);
         
    	 // En caso de ser un -, ponemos la temperatura minima
    	 newVarStation.setValueT(new FloatWritable(tokenT.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenT)));
    	 newVarStation.setValueTM(new FloatWritable(tokenTM.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTM)));
    	 newVarStation.setValueTm(new FloatWritable(tokenTm.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTm)));
    	 newVarStation.setValuePP(new FloatWritable(tokenPP.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenPP)));
    	 newVarStation.setValueV(new FloatWritable(tokenV.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenV)));
    	 newVarStation.setValueRA(new FloatWritable(tokenRA.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenRA)));
    	 newVarStation.setValueSN(new FloatWritable(tokenSN.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenSN)));
    	 newVarStation.setValueTS(new FloatWritable(tokenTS.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTS)));
    	 newVarStation.setValueFG(new FloatWritable(tokenFG.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenFG)));
    	 newVarStation.setValueTN(new FloatWritable(tokenTN.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTN)));
    	 newVarStation.setValueGR(new FloatWritable(tokenGR.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenGR)));
    	 
    	 // Y lo enviamos al output
         output.collect(new Text(continent + ";" + country), newVarStation);
      }
   } 
   
   
   //Reducer class 
   public static class E_EReduce extends MapReduceBase implements 
   Reducer< Text, VariableStation, Text, VariableStation> {  
   
      //Reduce function
      public void reduce(Text key, Iterator <VariableStation> values, 
    		  OutputCollector<Text, VariableStation> output, Reporter reporter) throws IOException {
         
    	 // Creamos las variables de almacenamiento temporal
    	 float maxValT = -Float.MAX_VALUE;
    	 String maxStationT = "";
    	 
    	 float maxValTM = -Float.MAX_VALUE;
    	 String maxStationTM = "";
    	 
    	 float maxValTm = -Float.MAX_VALUE;
    	 String maxStationTm = "";
    	 
    	 float maxValPP = -Float.MAX_VALUE;
    	 String maxStationPP = "";

    	 float maxValV = -Float.MAX_VALUE;
    	 String maxStationV = "";
    	 
    	 float maxValRA = -Float.MAX_VALUE;
    	 String maxStationRA = "";
    	 
    	 float maxValSN = -Float.MAX_VALUE;
    	 String maxStationSN = "";
    	 
    	 float maxValTS = -Float.MAX_VALUE;
    	 String maxStationTS = "";
    	 
    	 float maxValFG = -Float.MAX_VALUE;
    	 String maxStationFG = "";
    	 
    	 float maxValTN = -Float.MAX_VALUE;
    	 String maxStationTN = "";
    	 
    	 float maxValGR = -Float.MAX_VALUE;
    	 String maxStationGR = "";
    	 

    	 VariableStation currVarStation = null;
    	     	 
    	 while (values.hasNext()) {
    		 currVarStation = values.next();
    		 
    		 // Sacamos todos los valores y los comparamos
    		 // Si en efecto son mayores, los guardamos
    		 float currValueT = Float.parseFloat(currVarStation.getValueT().toString());
    		 float currValueTM = Float.parseFloat(currVarStation.getValueTM().toString());
    		 float currValueTm = Float.parseFloat(currVarStation.getValueTm().toString());
    		 float currValuePP = Float.parseFloat(currVarStation.getValuePP().toString());
    		 float currValueV = Float.parseFloat(currVarStation.getValueV().toString());
    		 float currValueRA = Float.parseFloat(currVarStation.getValueRA().toString());
    		 float currValueSN = Float.parseFloat(currVarStation.getValueSN().toString());
    		 float currValueTS = Float.parseFloat(currVarStation.getValueTS().toString());
    		 float currValueFG = Float.parseFloat(currVarStation.getValueFG().toString());
    		 float currValueTN = Float.parseFloat(currVarStation.getValueTN().toString());
    		 float currValueGR = Float.parseFloat(currVarStation.getValueGR().toString());
    		 
    		 System.out.println(currValueTN);
    		 
    		 // Comparamos T
    		 if (currValueT > maxValT) {
    			 maxValT = currValueT;
    			 maxStationT = currVarStation.getStationT().toString();
    		 }

    		 // Comparamos TM
    		 if (currValueTM > maxValTM) {
    			 maxValTM = currValueTM;
    			 maxStationTM = currVarStation.getStationTM().toString();
    		 }
    		 
    		 // Comparamos Tm
    		 if (currValueTm > maxValTm) {
    			 maxValTm = currValueTm;
    			 maxStationTm = currVarStation.getStationTm().toString();
    		 }
    		 
    		 // Comparamos PP
    		 if (currValuePP > maxValPP) {
    			 maxValPP = currValuePP;
    			 maxStationPP = currVarStation.getStationPP().toString();
    		 }
    		 
    		 // Comparamos V
    		 if (currValueV > maxValV) {
    			 maxValV = currValueV;
    			 maxStationV = currVarStation.getStationV().toString();
    		 }
    		 
    		// Comparamos RA
    		 if (currValueRA > maxValRA) {
    			 maxValRA = currValueRA;
    			 maxStationRA = currVarStation.getStationRA().toString();
    		 }
    		 
    		// Comparamos SN
    		 if (currValueSN > maxValSN) {
    			 maxValSN = currValueSN;
    			 maxStationSN = currVarStation.getStationSN().toString();
    		 }
    		 
    		// Comparamos TS
    		 if (currValueTS > maxValTS) {
    			 maxValTS = currValueTS;
    			 maxStationTS = currVarStation.getStationTS().toString();
    		 }
    		 
    		// Comparamos FG
    		 if (currValueFG > maxValFG) {
    			 maxValFG = currValueFG;
    			 maxStationFG = currVarStation.getStationFG().toString();
    		 }
    		 
    		// Comparamos TN
    		 if (currValueTN > maxValTN) {
    			 maxValTN = currValueTN;
    			 maxStationTN = currVarStation.getStationTN().toString();
    		 }
    		 
    		// Comparamos GR
    		 if (currValueGR > maxValGR) {
    			 maxValGR = currValueGR;
    			 maxStationGR = currVarStation.getStationGR().toString();
    		 }
    	 }
    	
    	 // Creamos un nuevo objeto variableyear, el de salida
    	 VariableStation outputVar = new VariableStation();
    	 
    	 // Seteamos todos los parametros
    	 outputVar.setStationT(new Text(maxStationT));
    	 outputVar.setValueT(new FloatWritable(maxValT));
    	 
    	 outputVar.setStationTM(new Text(maxStationTM));
    	 outputVar.setValueTM(new FloatWritable(maxValTM));
    	 
    	 outputVar.setStationTm(new Text(maxStationTm));
    	 outputVar.setValueTm(new FloatWritable(maxValTm));
    	 
    	 outputVar.setStationPP(new Text(maxStationPP));
    	 outputVar.setValuePP(new FloatWritable(maxValPP));
    	 
    	 outputVar.setStationV(new Text(maxStationV));
    	 outputVar.setValueV(new FloatWritable(maxValV));
    	 
    	 outputVar.setStationRA(new Text(maxStationRA));
    	 outputVar.setValueRA(new FloatWritable(maxValRA));
    	 
    	 outputVar.setStationSN(new Text(maxStationSN));
    	 outputVar.setValueSN(new FloatWritable(maxValSN));
    	 
    	 outputVar.setStationTS(new Text(maxStationTS));
    	 outputVar.setValueTS(new FloatWritable(maxValTS));
    	 
    	 outputVar.setStationFG(new Text(maxStationFG));
    	 outputVar.setValueFG(new FloatWritable(maxValFG));
    	 
    	 outputVar.setStationTN(new Text(maxStationTN));
    	 outputVar.setValueTN(new FloatWritable(maxValTN));
    	 
    	 outputVar.setStationGR(new Text(maxStationGR));
    	 outputVar.setValueGR(new FloatWritable(maxValGR));
    	 
    	 output.collect(key, outputVar);
      }
   }  
   
   
   //Main function 
   public static void main(String args[]) throws Exception {
	  
      JobConf conf = new JobConf(MaximumVarsStation.class); 
      
      conf.setJobName("maximum_vars_station");
      
      conf.setOutputKeyClass(Text.class);
      conf.setOutputValueClass(VariableStation.class); 
      
      conf.setMapperClass(E_EMapper.class);
      conf.setReducerClass(E_EReduce.class);
      
      conf.setInputFormat(TextInputFormat.class); 
      conf.setOutputFormat(TextOutputFormat.class); 
      
      FileInputFormat.setInputPaths(conf, new Path(args[0])); 
      FileOutputFormat.setOutputPath(conf, new Path(args[1])); 
      
      JobClient.runJob(conf); 
   } 
} 