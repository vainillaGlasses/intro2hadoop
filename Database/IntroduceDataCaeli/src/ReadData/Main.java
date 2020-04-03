package ReadData;

import java.io.IOException;
import java.util.Objects;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Txt2Sql myFile = new Txt2Sql(args[0]);
		if(Objects.equals(args[1], "e")){
			myFile.setDecade(args[2]);
			myFile.setType(args[1]);
		}else{
			myFile.setType(args[1]);
		}
		
	}
}

/*
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/top_ten_max a
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/top_ten_min b
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_values_years c
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_vars_year d
 * 
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1920s e 1920
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1930s e 1930
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1940s e 1940
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1950s e 1950
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1960s e 1960
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1970s e 1970
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1980s e 1980
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1990s e 1990
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_2000s e 2000
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_2010s e 2010 
 * 
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_vars_station f
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_vars_station g
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_vars_country h
 * /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_vars_country i
 * 
 */