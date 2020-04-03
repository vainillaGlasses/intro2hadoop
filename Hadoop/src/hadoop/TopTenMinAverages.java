package hadoop;

import java.io.*;

import java.util.TreeMap;
import java.util.StringTokenizer;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;

public class TopTenMinAverages extends Configured implements Tool {
   
   
   public static class TopTenMapper extends Mapper<LongWritable, Text, Text, VariableCountry> {
	   
      public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
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
     	 newVarCountry.setValueT(new FloatWritable(tokenT.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenT)));
     	 newVarCountry.setValueTM(new FloatWritable(tokenTM.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenTM)));
     	 newVarCountry.setValueTm(new FloatWritable(tokenTm.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenTm)));
     	 newVarCountry.setValuePP(new FloatWritable(tokenPP.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenPP)));
     	 newVarCountry.setValueV(new FloatWritable(tokenV.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenV)));
     	 newVarCountry.setValueRA(new FloatWritable(tokenRA.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenRA)));
     	 newVarCountry.setValueSN(new FloatWritable(tokenSN.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenSN)));
     	 newVarCountry.setValueTS(new FloatWritable(tokenTS.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenTS)));
     	 newVarCountry.setValueFG(new FloatWritable(tokenFG.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenFG)));
     	 newVarCountry.setValueTN(new FloatWritable(tokenTN.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenTN)));
     	 newVarCountry.setValueGR(new FloatWritable(tokenGR.equals("-") ? Float.MAX_VALUE : Float.parseFloat(tokenGR)));
     	 
     	 // Y lo enviamos al context
         context.write(new Text(country), newVarCountry);
      }
   } 
   
   //Reducer class
   
   public static class TopTenReducer extends Reducer <Text, VariableCountry, Text, TopTenCountry> {
      //Store the top ten
      private TreeMap<Float, String> topTenT = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenTM = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenTm = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenPP= new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenV = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenRA = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenSN = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenTS = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenFG = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenTN = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenGR = new TreeMap<Float, String>();
      
      @Override
      public void reduce(Text key, Iterable <VariableCountry> values, Context context) throws IOException, InterruptedException {
      	 // Aqui vamos sumando los valores
         float averageT = 0;
         float averageTM = 0;
         float averageTm = 0;
         float averagePP = 0;
         float averageV = 0;
         float averageRA = 0;
         float averageSN = 0;
         float averageTS = 0;
         float averageFG = 0;
         float averageTN = 0;
         float averageGR = 0;
         
         // Ocupamos contadores para poder hacer el promedio
         int counterT = 0;
         int counterTM = 0;
         int counterTm = 0;
         int counterPP = 0;
         int counterV = 0;
         int counterRA = 0;
         int counterSN = 0;
         int counterTS = 0;
         int counterFG = 0;
         int counterTN = 0;
         int counterGR = 0;

         // Iteramos por cada variable asociada
         for (VariableCountry currVarCountry : values) {
        	 
        	 // Los pasamos a flotante de una vez, esto para agilizar el proceso y no tener que convertirlos dos veces
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
    		 
        	 // Sumamos todos los valores y los contamos
    		 // Hay que sumar solo aquellos valores en los que NO son el valor minimo
    		 // Ya que estos representan numeros en blanco, y afectaria el total
        	 
    		 // T
    		 if (currValueT != Float.MAX_VALUE) {
        		 averageT += currValueT;
        		 counterT++;
        	 }
    		 
    		 // TM
    		 if (currValueTM != Float.MAX_VALUE) {
        		 averageTM += currValueTM;
        		 counterTM++;
        	 }
        	 
    		 // Tm
    		 if (currValueTm != Float.MAX_VALUE) {
        		 averageTm += currValueTm;
        		 counterTm++;
        	 }
    		 
    		 // PP
    		 if (currValuePP != Float.MAX_VALUE) {
        		 averagePP += currValuePP;
        		 counterPP++;
        	 }
    		 
    		 // V
    		 if (currValueV != Float.MAX_VALUE) {
        		 averageV += currValueV;
        		 counterV++;
        	 }
    		 
    		 // RA
    		 if (currValueRA != Float.MAX_VALUE) {
        		 averageRA += currValueRA;
        		 counterRA++;
        	 }
    		 
    		 // SN
    		 if (currValueSN != Float.MAX_VALUE) {
        		 averageSN += currValueSN;
        		 counterSN++;
        	 }
    		 
    		 // TS
    		 if (currValueTS != Float.MAX_VALUE) {
        		 averageTS += currValueTS;
        		 counterTS++;
        	 }
    		 
    		 // FG
    		 if (currValueFG != Float.MAX_VALUE) {
        		 averageFG += currValueFG;
        		 counterFG++;
        	 }
    		 
    		 // TN
    		 if (currValueTN != Float.MAX_VALUE) {
        		 averageTN += currValueTN;
        		 counterTN++;
        	 }
    		 
    		 // GR
    		 if (currValueGR != Float.MAX_VALUE) {
        		 averageGR += currValueGR;
        		 counterGR++;
        	 }
         }
         
         // Y los insertamos en el mapa que hace el acomodo por nosotros
         insertIntoTreeMap(key.toString(), averageT, counterT, topTenT);
         insertIntoTreeMap(key.toString(), averageTM, counterTM, topTenTM);
         insertIntoTreeMap(key.toString(), averageTm, counterTm, topTenTm);
         insertIntoTreeMap(key.toString(), averagePP, counterPP, topTenPP);
         insertIntoTreeMap(key.toString(), averageV, counterV, topTenV);
         insertIntoTreeMap(key.toString(), averageRA, counterRA, topTenRA);
         insertIntoTreeMap(key.toString(), averageSN, counterSN, topTenSN);
         insertIntoTreeMap(key.toString(), averageTS, counterTS, topTenTS);
         insertIntoTreeMap(key.toString(), averageFG, counterFG, topTenFG);
         insertIntoTreeMap(key.toString(), averageTN, counterTN, topTenTN);
         insertIntoTreeMap(key.toString(), averageGR, counterGR, topTenGR);
      }

      private void insertIntoTreeMap(String country, float totalSum, int counted, TreeMap<Float, String> mapToInsert) {
    	  if (counted != 0) {
    		  // Calculamos el promedio
    		  float average = totalSum / counted;
    		  
    		  // Lo guardamos
    		  mapToInsert.put(average, country);
    		  
    		  // En caso de que ya hayan más de 10 valores, hay que limpiar el arbol
    		  if (mapToInsert.size() > 10) {
    			  mapToInsert.remove(mapToInsert.lastKey());
    		  }
    	  }
      }
      
      @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            TopTenCountry topCountryT = getTopTenFromMap(topTenT);
            TopTenCountry topCountryTM = getTopTenFromMap(topTenTM);
            TopTenCountry topCountryTm = getTopTenFromMap(topTenTm);
            TopTenCountry topCountryPP = getTopTenFromMap(topTenPP);
            TopTenCountry topCountryV = getTopTenFromMap(topTenV);
            TopTenCountry topCountryRA = getTopTenFromMap(topTenRA);
            TopTenCountry topCountrySN = getTopTenFromMap(topTenSN);
            TopTenCountry topCountryTS = getTopTenFromMap(topTenTS);
            TopTenCountry topCountryFG = getTopTenFromMap(topTenFG);
            TopTenCountry topCountryTN = getTopTenFromMap(topTenTN);
            TopTenCountry topCountryGR = getTopTenFromMap(topTenGR);

            context.write(new Text("T"), topCountryT);
            context.write(new Text("TM"), topCountryTM);
            context.write(new Text("Tm"), topCountryTm);
            context.write(new Text("PP"), topCountryPP);
            context.write(new Text("V"), topCountryV);
            context.write(new Text("RA"), topCountryRA);
            context.write(new Text("SN"), topCountrySN);
            context.write(new Text("TS"), topCountryTS);
            context.write(new Text("FG"), topCountryFG);
            context.write(new Text("TN"), topCountryTN);
            context.write(new Text("GR"), topCountryGR);
            
      }
      
      private TopTenCountry getTopTenFromMap(TreeMap<Float, String> inputMap) {
    	  TopTenCountry outputTTC = new TopTenCountry();
    	  
		  Float[] indexList = inputMap.keySet().toArray(new Float[inputMap.size()]);
    	  
		  outputTTC.setCountryOne(new Text(inputMap.get(indexList[0])));
    	  outputTTC.setValueOne(new FloatWritable(indexList[0]));
    	  
    	  outputTTC.setCountryTwo(new Text(inputMap.get(indexList[1])));
    	  outputTTC.setValueTwo(new FloatWritable(indexList[1]));
    	  
    	  outputTTC.setCountryThree(new Text(inputMap.get(indexList[2])));
    	  outputTTC.setValueThree(new FloatWritable(indexList[2]));
    	  
    	  outputTTC.setCountryFour(new Text(inputMap.get(indexList[3])));
    	  outputTTC.setValueFour(new FloatWritable(indexList[3]));
    	  
    	  outputTTC.setCountryFive(new Text(inputMap.get(indexList[4])));
    	  outputTTC.setValueFive(new FloatWritable(indexList[4]));
    	  
    	  outputTTC.setCountrySix(new Text(inputMap.get(indexList[5])));
    	  outputTTC.setValueSix(new FloatWritable(indexList[5]));
    	  
    	  outputTTC.setCountrySeven(new Text(inputMap.get(indexList[6])));
    	  outputTTC.setValueSeven(new FloatWritable(indexList[6]));
    	  
    	  outputTTC.setCountryEight(new Text(inputMap.get(indexList[7])));
    	  outputTTC.setValueEight(new FloatWritable(indexList[7]));
    	  
    	  outputTTC.setCountryNine(new Text(inputMap.get(indexList[8])));
    	  outputTTC.setValueNine(new FloatWritable(indexList[8]));
    	  
    	  outputTTC.setCountryTen(new Text(inputMap.get(indexList[9])));
    	  outputTTC.setValueTen(new FloatWritable(indexList[9]));
    	  
    	  return outputTTC;
      }
   }
   
   @Override
   public int run(String[] arg) throws Exception {      
      @SuppressWarnings("deprecation")
	  Job job = new Job(getConf());
      job.setJarByClass(TopTenMinAverages.class);
      
      FileInputFormat.setInputPaths(job, new Path(arg[0]));
      FileOutputFormat.setOutputPath(job,new Path(arg[1]));
      
      job.setMapperClass(TopTenMapper.class);
      job.setReducerClass(TopTenReducer.class);
      
      job.setMapOutputKeyClass(Text.class);
      job.setMapOutputValueClass(VariableCountry.class);
      
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(TopTenCountry.class);
      
      job.setInputFormatClass(TextInputFormat.class);
      job.setOutputFormatClass(TextOutputFormat.class);
      
      
      return job.waitForCompletion(true)? 0 : 1;
   }
   
   public static void main(String args[]) throws Exception {
      
	  int response = ToolRunner.run(new Configuration(), new TopTenMinAverages(), args);
      System.exit(response);
   }
}