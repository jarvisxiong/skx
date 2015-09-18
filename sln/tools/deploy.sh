#!/usr/bin/env bash
pushd ..
svn up .

#set web site name
if [ ! -n "$1" ]
then
echo 'default site: www'
site='web'
else
echo "deploying site: $1"
site=$1
fi

#set web site port
if [ ! -n "$2" ]
then
echo 'default port: ""'
tomcat_port=''
else
echo "port is $2"
tomcat_port=$2
fi

#set project name which is going to be deployed
project_name=`echo ykx-$site| sed 'y/ABCDEFGHIJKLMNOPQRSTUVWXYZ/abcdefghijklmnopqrstuvwxyz/'`
#if [ $project_name = "ykx-visi" ]
#then
#project_name=$project_name"-web"
#fi
echo "deployed web site is " $project_name

##copy fixed configuration files to project
#tomcat_configuration_file_location=$(awk -F'=' '/tomcat_configuration_file_location/{print $2}' ./gradle.properties|tr  '\r' ' '|awk '{print $1}')
#echo cp $tomcat_configuration_file_location"/log4j.properties" $project_name/src/main/resources/log4j.properties
#cp $tomcat_configuration_file_location"/log4j.properties" $project_name/src/main/resources/log4j.properties
#echo cp $tomcat_configuration_file_location"/database.properties" $project_name/src/main/resources/conf/database.properties
#cp $tomcat_configuration_file_location"/database.properties" $project_name/src/main/resources/conf/database.properties

#set tomcat home
tomcat_home=$(awk -F'=' '/tomcat_home_on_linux/{print $2}' ./gradle.properties|tr  '\r' ' '|awk '{print $1}')
tomcat_home="$tomcat_home$tomcat_port"
echo $tomcat_home
shutdown=$tomcat_home/bin/shutdown.sh
startup=$tomcat_home/bin/startup.sh

#shutdown tomcat
eval $shutdown
sleep 3s

echo rm ROOT folder and ROOT.war file...
mv $tomcat_home/webapps/ROOT.war /data/ROOT_War_Backup
rm -rf $tomcat_home/webapps/ROOT

#excute gradle deploy method
eval "./gradlew deploy$site" -Ptomcat_port=$tomcat_port

#startup tomcat
eval $startup
popd
