package ReadData;
/*
 * Create by Samantha Arburola on November 2016
 * */
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.sql.*; 
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;


public class Txt2Sql {
	
	private String directoryFileName;
	private Boolean flagExistFile;
	private String[] file2Array;
	private String decade;
	private String decimalPattern = "(-*[0-9]*)\\.([0-9]*)";  

	
	public Txt2Sql(String pDirectoryFileName) throws IOException{
		directoryFileName = pDirectoryFileName;
		Path filePath = new File(directoryFileName+".txt").toPath();
		
		if (Files.notExists(filePath)){
			JOptionPane.showMessageDialog(null, "Error: Not find\n"+directoryFileName);
			flagExistFile = false;
		}else{
			Charset charset = Charset.defaultCharset();
			List<String> stringLines = Files.readAllLines(filePath, charset);
			file2Array = stringLines.toArray(new String[]{});
			flagExistFile = true;
		}
	}
	
	public void setType(String typeOfTxt){
		/*
		 * This Check is for call a function to the save the information
		 * corresponding to the data type of the calculus made in Hadoop
		*/
		if(Objects.equals(typeOfTxt,"a") || Objects.equals(typeOfTxt,"b")){
			top(typeOfTxt);
		}
		if(Objects.equals(typeOfTxt,"c") || Objects.equals(typeOfTxt,"d")){
			year(typeOfTxt);
		}
		if(Objects.equals(typeOfTxt,"e")){
			insertDecade();
		}
		
		if(Objects.equals(typeOfTxt,"f") || Objects.equals(typeOfTxt,"g")){
			station(typeOfTxt);
		}
		if(Objects.equals(typeOfTxt,"h") || Objects.equals(typeOfTxt,"i")){
			country(typeOfTxt);
		}
	}
	
	public void setDecade(String pDecade){
		decade=pDecade;
	}
	
	private void top(String typeOfTxt){		
		String line;
		String[] lineArray;
		String gepgraphic;
		String info;
		String[] colum;
		String filter;
		String value;
		
		if(flagExistFile){			
			for(int x=0; x < file2Array.length;x++){
				line=file2Array[x];
				line.replace("'","");
				lineArray = line.split("\\t");
				gepgraphic = lineArray[0];
				
				for(int y=1; y < lineArray.length;y++){
					info=lineArray[y];
					colum = info.split(";");
					filter = colum[0];
					value = colum[1];
					
					if(Objects.equals(filter, "Côte d'Ivoire")){filter="Côte dIvoire";}
					if(Objects.equals(filter, "Antarctica")){filter="Sin País";}
					
					boolean match = Pattern.matches(decimalPattern, value);
					if(match == false){
						value="NULL";
					}
					
					if(Objects.equals(typeOfTxt,"a")){commit("CALL insertTop('"+filter+"','"+gepgraphic+"',"+value+",'Max');");}//Max
					if(Objects.equals(typeOfTxt,"b")){commit("CALL insertTop('"+filter+"','"+gepgraphic+"',"+value+",'Min');");}//min					
				}
			}
		}
	}
	
	private void country(String typeOfTxt){		
		String line;
		String[] lineArray;
		String geographic;
		String info;
		String[] column;
		String filter;
		String value;
		
		if(flagExistFile){			
			for(int x=0; x < file2Array.length;x++){
				line=file2Array[x];
				line.replace("'","");
				lineArray = line.split("\\t");
				geographic = lineArray[0];
				
				for(int y=1; y < lineArray.length;y++){
					info=lineArray[y];
					column = info.split(";");
					filter = column[0];
					value = column[1];
					
					boolean match = Pattern.matches(decimalPattern, value);
					if(match == false){
						value="NULL";
					}
					
					if(Objects.equals(filter, "Côte d'Ivoire")){filter="Côte dIvoire";}
					if(Objects.equals(geographic, "Antarctica")){filter="Sin País";}
					if(Objects.equals(typeOfTxt,"h")){commit("CALL insertVarsCountry('"+filter+"',"+y+","+value+",'Max');");}//max
					if(Objects.equals(typeOfTxt,"i")){commit("CALL insertVarsCountry('"+filter+"',"+y+","+value+",'Min');");}//min
				}
				
			}
		}
	}
	
	private void station(String typeOfTxt){		
		String line;
		String[] lineArray;
		String continent;
		String station;
		String info;
		String[] column;
		String country;
		String value;
		
		if(flagExistFile){			
			for(int x=0; x < file2Array.length;x++){
				line=file2Array[x];
				line.replace("'","");
				lineArray = line.split("\\t");
				String temp = lineArray[0];
				String[] continentCountry = temp.split(";");
				continent = continentCountry[0];
				if(Objects.equals(continent, "Antarctica")){country="Sin País";}
				else{country = continentCountry[1];}
				if(Objects.equals(country, "Côte d'Ivoire")){country="Côte dIvoire";}
				
				for(int y=1; y < lineArray.length;y++){
					info=lineArray[y];
					column = info.split(";");
					station = column[0];
					value = column[column.length-1];
					
					boolean match = Pattern.matches(decimalPattern, value);
					if(match == false){
						value="NULL";
					}
					
					if(Objects.equals(typeOfTxt,"f")){commit("CALL insertVarStation('"+country+"','"+station+"',"+y+","+value+",'Max');");}//Max
					if(Objects.equals(typeOfTxt,"g")){commit("CALL insertVarStation('"+country+"','"+station+"',"+y+","+value+",'Min');");}//Max
				}
				
			}
		}
	}
	
	private void insertDecade(){
		String line;
		String[] lineArray;
		String continent;
		String value;
		
		
		if(flagExistFile){			
			for(int x=0; x < file2Array.length;x++){
				line=file2Array[x];
				line.replace("'","");
				lineArray = line.split("\\t");
				continent = lineArray[0];
								
				for(int y=1; y < lineArray.length;y++){
					value = lineArray[y];
					boolean match = Pattern.matches(decimalPattern, value);
					if(match == false){
						value="NULL";
					}
					//System.out.println("cont="+continent+" dec= "+decade+" var="+y+" val="+value);
					commit("CALL insertContinentDecade('"+continent+"','"+decade+"',"+y+","+value+")");
				}
				
			}
		}
	}
	
	private void year(String typeOfTxt){	
		if(flagExistFile){	
			String line;
			String[] lineArray;
			String country;
			String continent;
			String filter;
			String info;
			String[] column;
			String[] geoData;
			String year;
			String value;
			
			for(int x=0; x < file2Array.length;x++){
				line=file2Array[x];
				line.replace("'","");
				lineArray = line.split("\\t");
				filter = lineArray[0];
				geoData = filter.split(";");
				continent = geoData[0];
				
				if(Objects.equals(continent,"Antarctica")){	
					country="Sin País";
				}else{
					country = geoData[1];
					if (country.equals("Côte d'Ivoire")){
						country = "Côte dIvoire";
					}
				}
				
				for(int y=1; y < lineArray.length;y++){
					info=lineArray[y];
					column = info.split(";");
					year = column[0];
					value = column[column.length-1];
					
					boolean match = Pattern.matches(decimalPattern, value);
					if(match == false){
						value="NULL";
					}
					if(Objects.equals(typeOfTxt,"c")){commit("CALL insertVarYear('"+country+"',"+y+","+year+","+value+",'Max');");}//Max
					if(Objects.equals(typeOfTxt,"d")){commit("CALL insertVarYear('"+country+"',"+y+","+year+","+value+",'Min');");}//Min
				}
			}
		}
	}
	
	public void showTypes(){
		JOptionPane.showMessageDialog(null, "Tipos\n"+
				"a Los 10 países	con los	máximos	promedios generales\n"+	
				"b Los 10 países	con los	mínimos	promedios generales\n"+	
				"c Para cada país el año en que cada uno	de las variables fue la	máxima\n"+		
				"d Para cada país el año en que cada uno	de las variables fue la	minima\n"+	
				"e El promedio de temperatura para cada continente, en grupos de 10 años\n"+	
				"f Por País la estación que tiene los valores máximos\n"+	
				"g Por País la estación que tiene los valores mínimos\n"+	
				"h Por Continente los países con los valores máximos\n"+	
				"i Por Continente los países con los valores mínimos");

	}
	
	public void printAll(){
		if(flagExistFile){
			for(int x=0; x < file2Array.length;x++){
				System.out.println(file2Array[x]);
			}
		}
	}

	public void commit(String myInsert){
		//System.out.println(myInsert);
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/HadoopClimate","hadoop","hadoop123");
		Statement statement=con.createStatement();

		statement.executeUpdate(myInsert);

		con.close();
		}
		catch(Exception e){
			//System.out.println(myInsert);
			JOptionPane.showMessageDialog(null, "Error: Insert on database\n"+myInsert+"\n were not posible\n\nError on DB:\n"+e);
		}
		
	}
	
}