package hadoop;

import java.io.*;
import java.util.StringTokenizer;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

import org.apache.hadoop.util.*;

public class ContinentAverage extends Configured implements Tool {
   
   public static class ContinentAvgMapper extends Mapper <LongWritable, Text, Text, VariablesContinent> {
      public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	  String line = value.toString();
          StringTokenizer s = new StringTokenizer(line, ";"); 
          
          String continent = s.nextToken();
          
          System.out.println(line);
          
          // En caso de que NO seamos Antartica, hay que volarnos el pais
          if (!continent.equals("Antarctica")) {
        	  s.nextToken();
          }
          
          // No nos interesa la estacion, los desechamos
          s.nextToken();
          s.nextToken();
          s.nextToken();
          s.nextToken();
          
          // Creamos un nuevo objeto y empezamos a llenarlo
          VariablesContinent newVarContinent = new VariablesContinent();
          
          // Guardamos el año
          newVarContinent.setYear(new IntWritable(Integer.parseInt(s.nextToken())));
          
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
               	 
          
     	  // En caso de ser un -, ponemos la temperatura minima
     	  newVarContinent.setValueT(new FloatWritable(tokenT.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenT)));
     	  newVarContinent.setValueTM(new FloatWritable(tokenTM.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTM)));
     	  newVarContinent.setValueTm(new FloatWritable(tokenTm.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTm)));
     	  newVarContinent.setValuePP(new FloatWritable(tokenPP.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenPP)));
     	  newVarContinent.setValueV(new FloatWritable(tokenV.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenV)));
     	  newVarContinent.setValueRA(new FloatWritable(tokenRA.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenRA)));
     	  newVarContinent.setValueSN(new FloatWritable(tokenSN.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenSN)));
     	  newVarContinent.setValueTS(new FloatWritable(tokenTS.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTS)));
     	  newVarContinent.setValueFG(new FloatWritable(tokenFG.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenFG)));
     	  newVarContinent.setValueTN(new FloatWritable(tokenTN.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenTN)));
     	  newVarContinent.setValueGR(new FloatWritable(tokenGR.equals("-") ? -Float.MAX_VALUE : Float.parseFloat(tokenGR)));
     	 
     	  // Y lo enviamos al context
          context.write(new Text(continent), newVarContinent);
      }
   } 
   
   //Reducer class
   
   public static class ContinentAvgReducer extends Reducer <Text, VariablesContinent, Text, VariablesContinent> {
      public void reduce(Text key, Iterable <VariablesContinent> values, Context context) throws IOException, InterruptedException {
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
          for (VariablesContinent currVarContinent : values) {
         	 
         	 // Los pasamos a flotante de una vez, esto para agilizar el proceso y no tener que convertirlos dos veces
         	 float currValueT = Float.parseFloat(currVarContinent.getValueT().toString());
     		 float currValueTM = Float.parseFloat(currVarContinent.getValueTM().toString());
     		 float currValueTm = Float.parseFloat(currVarContinent.getValueTm().toString());
     		 float currValuePP = Float.parseFloat(currVarContinent.getValuePP().toString());
     		 float currValueV = Float.parseFloat(currVarContinent.getValueV().toString());
     		 float currValueRA = Float.parseFloat(currVarContinent.getValueRA().toString());
     		 float currValueSN = Float.parseFloat(currVarContinent.getValueSN().toString());
     		 float currValueTS = Float.parseFloat(currVarContinent.getValueTS().toString());
     		 float currValueFG = Float.parseFloat(currVarContinent.getValueFG().toString());
     		 float currValueTN = Float.parseFloat(currVarContinent.getValueTN().toString());
     		 float currValueGR = Float.parseFloat(currVarContinent.getValueGR().toString());
     		 
         	 // Sumamos todos los valores y los contamos
     		 // Hay que sumar solo aquellos valores en los que NO son el valor minimo
     		 // Ya que estos representan numeros en blanco, y afectaria el total
         	 
     		 // T
     		 if (currValueT != -Float.MAX_VALUE) {
         		 averageT += currValueT;
         		 counterT++;
         	 }
     		 
     		 // TM
     		 if (currValueTM != -Float.MAX_VALUE) {
         		 averageTM += currValueTM;
         		 counterTM++;
         	 }
         	 
     		 // Tm
     		 if (currValueTm != -Float.MAX_VALUE) {
         		 averageTm += currValueTm;
         		 counterTm++;
         	 }
     		 
     		 // PP
     		 if (currValuePP != -Float.MAX_VALUE) {
         		 averagePP += currValuePP;
         		 counterPP++;
         	 }
     		 
     		 // V
     		 if (currValueV != -Float.MAX_VALUE) {
         		 averageV += currValueV;
         		 counterV++;
         	 }
     		 
     		 // RA
     		 if (currValueRA != -Float.MAX_VALUE) {
         		 averageRA += currValueRA;
         		 counterRA++;
         	 }
     		 
     		 // SN
     		 if (currValueSN != -Float.MAX_VALUE) {
         		 averageSN += currValueSN;
         		 counterSN++;
         	 }
     		 
     		 // TS
     		 if (currValueTS != -Float.MAX_VALUE) {
         		 averageTS += currValueTS;
         		 counterTS++;
         	 }
     		 
     		 // FG
     		 if (currValueFG != -Float.MAX_VALUE) {
         		 averageFG += currValueFG;
         		 counterFG++;
         	 }
     		 
     		 // TN
     		 if (currValueTN != -Float.MAX_VALUE) {
         		 averageTN += currValueTN;
         		 counterTN++;
         	 }
     		 
     		 // GR
     		 if (currValueGR != -Float.MAX_VALUE) {
         		 averageGR += currValueGR;
         		 counterGR++;
         	 }
          }
          
          // Calculamos los promedios y los metemos en un objeto VariablesContinent
          VariablesContinent outputVarContinent = new VariablesContinent();
          
          outputVarContinent.setValueT(new FloatWritable(averageT / counterT));
     	  outputVarContinent.setValueTM(new FloatWritable(averageTM / counterTM));
     	  outputVarContinent.setValueTm(new FloatWritable(averageTm / counterTm));
     	  outputVarContinent.setValuePP(new FloatWritable(averagePP / counterPP));
     	  outputVarContinent.setValueV(new FloatWritable(averageV / counterV));
     	  outputVarContinent.setValueRA(new FloatWritable(averageRA / counterRA));
     	  outputVarContinent.setValueSN(new FloatWritable(averageSN / counterSN));
     	  outputVarContinent.setValueTS(new FloatWritable(averageTS / counterTS));
     	  outputVarContinent.setValueFG(new FloatWritable(averageFG / counterFG));
     	  outputVarContinent.setValueTN(new FloatWritable(averageTN / counterTN));
     	  outputVarContinent.setValueGR(new FloatWritable(averageGR / counterGR));
     	 
     	  context.write(key, outputVarContinent);
      }
   }
   
   // El chiste del partitioner es que vamos a mandar a un reducer solo los valores dentro de un cierto rango
   public static class ContinentAvgPartitioner extends Partitioner <Text, VariablesContinent> {

      @Override
      public int getPartition(Text key, VariablesContinent value, int numReduceTasks) {
    	  int yearToCheck = Integer.parseInt(value.getYear().toString());
    	  
    	  // Revisamos el año al que corresponde el reporte
    	  
    	  // Decada de los 20's (la pagina indica que tienen reportes que datan de 1929)
    	  if (1920 <= yearToCheck && yearToCheck <= 1929) {
    		  return 0;
    		  
    	  // Decada de los 30's
    	  } else if (1930 <= yearToCheck && yearToCheck <= 1939) {
    		  return 1;
    		  
    	  // Decada de los 40's
    	  } else if (1940 <= yearToCheck && yearToCheck <= 1949) {
    		  return 2;
    	  
    	  // Decada de los 50's
    	  } else if (1950 <= yearToCheck && yearToCheck <= 1959) {
    		  return 3;
    	  
    	  // Decada de los 60's
    	  } else if (1960 <= yearToCheck && yearToCheck <= 1969) {
    		  return 4;
    	  
    	  // Decada de los 70's
    	  } else if (1970 <= yearToCheck && yearToCheck <= 1979) {
    		  return 5;
    	  
    	  // Decada de los 80's
    	  } else if (1980 <= yearToCheck && yearToCheck <= 1989) {
    		  return 6;
    	  
    	  // Decada de los 90's
    	  } else if (1990 <= yearToCheck && yearToCheck <= 1999) {
    		  return 7;
    	  
    	  // Decada del 2000
    	  } else if (2000 <= yearToCheck && yearToCheck <= 2009) {
    		  return 8;
    	  
    	  // Decada del 2010
    	  } else {
    		  return 9;
    	  }
    	  
      }
   }
   
   @Override
   public int run(String[] args) throws Exception {
      Configuration conf = getConf();
      
      @SuppressWarnings("deprecation")
	  Job job = new Job(conf, "ContinentAverage");
      job.setJarByClass(ContinentAverage.class);
      
      FileInputFormat.setInputPaths(job, new Path(args[0]));
      FileOutputFormat.setOutputPath(job,new Path(args[1]));
      
      job.setMapperClass(ContinentAvgMapper.class);
      
      job.setMapOutputKeyClass(Text.class);
      job.setMapOutputValueClass(VariablesContinent.class);
      
      // El partitioner lo que nos permite es poder distribuir los años
      job.setPartitionerClass(ContinentAvgPartitioner.class);
      job.setNumReduceTasks(10);

      job.setReducerClass(ContinentAvgReducer.class);
      
      job.setInputFormatClass(TextInputFormat.class);      
      job.setOutputFormatClass(TextOutputFormat.class);

      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(Text.class);
      
      return job.waitForCompletion(true)? 0 : 1;
   }
   
   public static void main(String args[]) throws Exception {
      int response = ToolRunner.run(new Configuration(), new ContinentAverage(), args);
      System.exit(response);
   }
}