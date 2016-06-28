#!/bin/bash

set -e

echo "Please check you your api setting is correct.."
cf target

: "${WORKSPACE:?Need to set WORKSPACE non-empty which contains the app ${app_name}}"

prefix="API endpoint: https://api."
api_out=$(cf api)
tmp=${api_out#$prefix} # prefix removal
cf_host=${tmp% * * *} # suffix removal assuming structure like " (API version: 2.54.0)"
app_name=pcf-autoscaler-test
service_instance_name=pcf-autoscaler-test
autoscaler_home="${WORKSPACE}/${app_name}"

set -o errexit
project_version=$(mvn -f $autoscaler_home -q -Dexec.executable="echo" -Dexec.args='${project.version}' --non-recursive org.codehaus.mojo:exec-maven-plugin:1.3.1:exec)
echo $project_version

mvn -f $autoscaler_home package

read -p "App built. Ready to cf push (y/n)?" choice
   if [ "$choice" != "y" ]; then
       exit 1
   fi

cf push -p ${autoscaler_home}/target/${app_name}-${project_version}.jar $app_name -i 1

#cf app "pcf-autoscaler-test"
cf app $app_name

echo "Set up autoscale services.. (note this is idempotent)"

# create the autoscaler service in the marketplace
# Gold - Monitors app load every 30 seconds
cf create-service app-autoscaler gold $service_instance_name

# bind the service
cf bind-service $app_name $service_instance_name

echo "Ensure the autoscale instance bound to your app is unpaused. Also set min instances to 1 and high cpu threshold to something like 50%. This is done via the Apps Manager GUI."
read -p "Have you configured your autoscaler instance as described above (y/n)?" choice
   if [ "$choice" != "y" ]; then
       exit 1
   fi

# manually unpause the service for the moment
# To automate using a REST call via POST - See scripts README.MD for an undocumented approach

echo "Current event logs.."
cf events $app_name | grep 'instances:'
echo "Invoking CPU endpoint to increase CPU load"
curl -k -X POST https://${app_name}.${cf_host}/cpu
echo

echo "Give the autoscaler a few moments to autoscale..(The gold service monitors app load every 30 seconds)"
sleep 70

# TODO: automated assert that it increases to 2 instances
# custom actuator info page?
# or 
# do cf events as above
# or
# note autoscaler logs..
#Sat Jun 18 2016 10:43:47 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:47 [Scaling Agent] 
	# Making scaling decision -----> {ID:2751 ServiceBindingGuid:435e9bf5-7ebf-4679-90bf-03e669a3d21d 
	# AppName:pcf-autoscaler-test CPUUtilization:29 ExpectedInstanceCount:2 RunningInstanceCount:2 State:RUNNING CreatedAt:2016-06-18 17:43:47 +0000 UTC}
#Sat Jun 18 2016 10:43:47 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:47 [Scaling Director] Received new Reading -----> {ID:2751 ServiceBindingGuid:435e9bf5-7ebf-4679-90bf-03e669a3d21d AppName:pcf-autoscaler-test CPUUtilization:29 ExpectedInstanceCount:2 RunningInstanceCount:2 State:RUNNING CreatedAt:2016-06-18 17:43:47 +0000 UTC}
#Sat Jun 18 2016 10:43:47 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:47 [Scaling Director] Agent has completed -----> true

# So could do cf logs autoscale | grep ExpectedInstanceCount:2 RunningInstanceCount:2

echo
echo "So was the last event a scale up????? Check the event logs.."
cf events $app_name | grep 'instances:'