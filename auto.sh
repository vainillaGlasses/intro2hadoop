mysql --host=localhost --user=hadoop --password=hadoop123 < "Database/crearTablasProcedimientosV6.sql"
mysql --host=localhost --user=hadoop --password=hadoop123  HadoopClimate < "Database/InsertsVariablesContinentes.sql"
mysql --host=localhost --user=hadoop --password=hadoop123  HadoopClimate < "Database/insertPaises.sql"
mysql --host=localhost --user=hadoop --password=hadoop123  HadoopClimate < "Database/insertEstaciones.sql"

# Si no existe el directorio de input, se crea
if [ ! -d "/var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir" ]
    then
    	hadoop fs -mkdir /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir
fi

# Se sobreescribe el data.txt en la carpeta de input
hadoop fs -put -f /var/www/html/HadoopClimate/WebCrawler/data.txt /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir




#########################
#    JOBS DE HADOOP     #
#########################

# Se ejecuta el job del top ten de maximos
hadoop jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/output.jar hadoop.TopTenMaxAverages /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_top_max

# Solo saca un archivo
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_top_max/part-r-00000 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/top_ten_max.txt

java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/top_ten_max a

# Borramos el archivo
rm -rf /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_top_max/


# Se ejecuta el job del top ten de minimos
hadoop jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/output.jar hadoop.TopTenMinAverages /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_top_min

# Solo saca un archivo
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_top_min/part-r-00000 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/top_ten_min.txt

java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/top_ten_min b

# Borramos el archivo
rm -rf /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_top_min/

# Se ejecuta el job de por pais los anos donde las variables fueron la máxima
hadoop jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/output.jar hadoop.MaximumVars /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_max_year > output.log

# Solo saca un archivo
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_max_year/part-00000 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_values_years.txt

java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_values_years c

# Borramos el archivo
rm -rf /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_max_year/


# Se ejecuta el job de por pais los anos donde las variables fueron la minima
hadoop jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/output.jar hadoop.MinimumVars /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_min_year > output.log

# Solo saca un archivo
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_min_year/part-00000 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_values_years.txt

java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_values_years d

# Borramos el archivo
rm -rf /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_min_year/


# Se ejecuta el job de por continente el promedio de cada variable en grupos de 10 anos
hadoop jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/output.jar hadoop.ContinentAverage /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg > output.log

# Solo saca un archivo
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/part-r-00000 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1920s.txt
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/part-r-00001 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1930s.txt
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/part-r-00002 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1940s.txt
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/part-r-00003 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1950s.txt
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/part-r-00004 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1960s.txt
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/part-r-00005 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1970s.txt
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/part-r-00006 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1980s.txt
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/part-r-00007 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1990s.txt
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/part-r-00008 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_2000s.txt
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/part-r-00009 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_2010s.txt

java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1920s e 1920
java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1930s e 1930
java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1940s e 1940
java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1950s e 1950
java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1960s e 1960
java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1970s e 1970
java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1980s e 1980
java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_1990s e 1990
java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_2000s e 2000
java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_average_2010s e 2010

# Borramos el archivo
rm -rf /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_cont_avg/


# Se ejecuta el job de por pais, la estacion con los maximos valores por variable
hadoop jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/output.jar hadoop.MaximumVarsStation /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_max_station > output.log

# Solo saca un archivo
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_max_station/part-00000 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_vars_station.txt

java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_vars_station f

# Borramos el archivo
rm -rf /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_max_station/

# Se ejecuta el job de por pais, la estacion con los minimos valores por variable
hadoop jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/output.jar hadoop.MinimumVarsStation /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_min_station > output.log

# Solo saca un archivo
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_min_station/part-00000 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_vars_station.txt

java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_vars_station g

# Borramos el archivo
rm -rf /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_min_station/

# Se ejecuta el job de por continente los paises con los máximos valores de las variables
hadoop jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/output.jar hadoop.MaximumVarsCountry /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_max_country > output.log

# Solo saca un archivo
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_max_country/part-00000 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_vars_country.txt

java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_vars_country h

# Borramos el archivo
rm -rf /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_max_country/

# Se ejecuta el job de por continente los paises con los máximos valores de las variables
hadoop jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/output.jar hadoop.MinimumVarsCountry /var/www/html/HadoopClimate/Hadoop/src/hadoop/input_dir /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_min_country > output.log

# Solo saca un archivo
mv /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_min_country/part-00000 /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_vars_country.txt

java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_vars_country i

# Borramos el archivo
rm -rf /var/www/html/HadoopClimate/Hadoop/src/hadoop/out_min_country/


# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/top_ten_max a
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/top_ten_min b
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_values_years c
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_vars_year d
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1920s e 1920
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1930s e 1930
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1940s e 1940
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1950s e 1950
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1960s e 1960
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1970s e 1970
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1980s e 1980
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_1990s e 1990
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_2000s e 2000
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/max_averages_continent/max_average_2010s e 2010
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_vars_station f
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_vars_station g
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/maximum_vars_country h
# java -jar txt2sql.jar /var/www/html/HadoopClimate/Hadoop/src/hadoop/results/minimum_vars_country i