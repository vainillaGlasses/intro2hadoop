package Generator;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;

public class Main {
	
    public static void main(String [] args) throws FileNotFoundException {
        File fileName2 = new File("data.txt");
        String line = null;
        ArrayList<String> latitudes = new ArrayList<String>();
        ArrayList<String> longitudes = new ArrayList<String>();
        ArrayList<String> estaciones = new ArrayList<String>();
        ArrayList<String> paises = new ArrayList<String>();
        ArrayList<String> paises2 = new ArrayList<String>();
        ArrayList<String> numeros = new ArrayList<String>();
        ArrayList<String> continentes = new ArrayList<String>();
        String[] words2;
        int pais = 0;
        int continente = 0;
        String myInsert = "";
        String myInsert1 = "";
        
        /**************************************************************************/
        //datos para el segundo archivo

        try {
            FileReader fileReader2 = 
                new FileReader(fileName2);

            BufferedReader bufferedReader2 = 
                new BufferedReader(fileReader2);

            while((line = bufferedReader2.readLine()) != null) {
            }   

            bufferedReader2.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName2 + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName2 + "'");
        }
        
        Scanner scanner2 = new Scanner(fileName2);
        
        //leo cada linea del archivo separada por enter
        while (scanner2.hasNext()) {
            words2 = scanner2.nextLine().split(";");
            
            boolean esta2 = false;
            
            ///////////////////////////////////////////////
            for (int i = 0; i < paises.size(); i++) {
                if (paises.get(i).equals(words2[1])) {
                    esta2 = true;
                    break;
                }
            }           
            if (!esta2) {
            	//System.out.println(words2[0]);
            	//System.out.println(words2[1]);
            	
            	continentes.add(words2[0]);
            	paises.add(words2[1]);
            }
            
            ///////////////////////////////////////////////
            //agregar estaciones y coordenadas
            esta2 = false;
            for (int i = 0; i < estaciones.size(); i++) {
                if (estaciones.get(i).equals(words2[2])) {
                    esta2 = true;
                    break;
                }
            }           
            if (!esta2) {
            	paises2.add(words2[1]);
            	estaciones.add(words2[2]);
	        	numeros.add(words2[3]);
	        	latitudes.add(words2[4]);
	        	longitudes.add(words2[5]);
            }
        }
        
//-------------------------------------------------------------------------------------------------------------------------
        String finalFile = "insertEstaciones.sql";
        String finalFile1 = "insertPaises.sql";
        
        /**************************************************************************/
        //escritura de insert paises
        /*try {
            FileWriter fileWriter1 =
                new FileWriter(finalFile1);

            BufferedWriter bufferedWriter1 =
                new BufferedWriter(fileWriter1);*/
            
            //for(int i = 0; i < paises.size(); i++){
            //System.out.println(continentes.size());
        try{
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/HadoopClimate","hadoop","hadoop123");
    		Statement statement=con.createStatement();
            //System.out.println(continentes.size());
            
            for(int j = 0; j < continentes.size(); j++){
            	if(continentes.get(j).equals("Africa")){
            		continente = 1;
            	}else if(continentes.get(j).equals("Antarctica")){
            		continente = 2;
            	}else if(continentes.get(j).equals("Asia")){
            		continente = 3;
            	}else if(continentes.get(j).equals("Europe")){
            		continente = 4;
            	}else if(continentes.get(j).equals("North America")){
            		continente = 5;
            	}else if(continentes.get(j).equals("Oceania")){
            		continente = 6;
            	}else if(continentes.get(j).equals("South America")){
            		continente = 7;
            	}
	            	
            	myInsert = "INSERT INTO Country(country, idContinent) VALUES ('" +
            						paises.get(j) + "', " + Integer.toString(continente) + ");";
            }
            
            statement.executeUpdate(myInsert);
            System.out.println("listo");
            
            con.close();
		}
		catch(Exception e){
			//System.out.println(myInsert);
			System.out.println("Error: Insert on database\n"+myInsert+"\n were not posible");
			System.out.println("Error:\n" + e);
		}

            /*bufferedWriter1.close();
            
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + finalFile1 + "'");
        }
        
        /**************************************************************************/
        //escritura de insert paises
        /*try {
            FileWriter fileWriter =
                new FileWriter(finalFile);

            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
            
            //int i = 0;*/
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/HadoopClimate","hadoop","hadoop123");
    		Statement statement1 = con1.createStatement();
            //System.out.println(estaciones.size());
            for(int i = 0; i < estaciones.size(); i++){
                for (int j = 0; j < paises.size(); j++) {
                    if (paises.get(j).equals(paises2.get(i))) {
                        pais = j;
                    }
                }

                if(latitudes.get(i).equals("-")){
                	latitudes.get(i).replace("-", "null");
                }
                if(longitudes.get(i).equals("-")){
                	longitudes.get(i).replace("-", "null");
                }

                myInsert1 = "INSERT INTO Station(stationNumber, stationOrCityName, idCountry, Latitude, Longitude) VALUES ('" + 
            						numeros.get(i) + "', " + "'" + 
            						estaciones.get(i) + "', " + Integer.toString(pais+1) + ", " +
            						latitudes.get(i) + ", " + longitudes.get(i) + ");";
            }
            statement1.executeUpdate(myInsert1);
            System.out.println("listo");
            con1.close();
		}
		catch(Exception e){
			//System.out.println(myInsert);
			System.out.println("Error: Insert on database\n"+myInsert1+"\n were not posible");
			System.out.println("Error:\n" + e);
		}

         /*   bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + finalFile + "'");
        }*/
    }
}

