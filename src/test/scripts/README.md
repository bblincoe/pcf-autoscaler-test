# Overview

The bash script in this directory gives a WIP approach to test the autoscale capability

It assumes you are logged in via the cf command line

# Future enhancements

The autoscale service needs to be manually unpaused via the App Manager gui, which in turn does this via an undocumented API

This means we can leverage the same API to automate it. Some details about the API follow which were established by interrogating via browser debug tools


UNPAUSE - Note that the packet contains a field enabled=true to unpause
Request URL:https://autoscale.<sys-domain>/api/bindings/435e9bf5-7ebf-4679-90bf-03e669a3d21d
Request Method:POST

Tail of autoscale AI logs..

Sat Jun 18 2016 10:43:27 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:27 [Scaling Agent] Making scaling decision -----> {ID:2750 ServiceBindingGuid:435e9bf5-7ebf-4679-90bf-03e669a3d21d AppName:pcf-autoscaler-test CPUUtilization:60 ExpectedInstanceCount:1 RunningInstanceCount:1 State:RUNNING CreatedAt:2016-06-18 17:43:27 +0000 UTC}
Sat Jun 18 2016 10:43:27 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:27 [Scaling Director] Agent has completed -----> true
Sat Jun 18 2016 10:43:27 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:27 [Scaling Agent] Notifying director of completion -----> true
Sat Jun 18 2016 10:43:28 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:28 [SchedulingDirector] Looking up events between 2016-06-18 17:42:27.989411518 +0000 UTC -> 2016-06-18 17:43:28.000059751 +0000 UTC...
Sat Jun 18 2016 10:43:36 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:36 [Metrics Agent 435e9] Received KILL -----> true
Sat Jun 18 2016 10:43:36 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:36 [Metrics Director] Received delete binding -----> 435e9bf5-7ebf-4679-90bf-03e669a3d21d
Sat Jun 18 2016 10:43:46 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:46 [Metrics Director] Received new binding -----> 435e9bf5-7ebf-4679-90bf-03e669a3d21d
Sat Jun 18 2016 10:43:46 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:43:46 [Metrics Agent 435e9] Received Timing Event -----> true
etc

{
  "app_name": "pcf-autoscaler-test", 
  "cpu_max_threshold": 80, 
  "cpu_min_threshold": 20, 
  "enabled": true, 
  "guid": "435e9bf5-7ebf-4679-90bf-03e669a3d21d", 
  "max_instances": 5, 
  "min_instances": 2, 
  "next_scheduled_event_time": "No Upcoming Events", 
  "scaling_event_description": "NOT FOUND", 
  "scaling_event_time": "2016-06-18T17:41:36.724261923Z", 
  "scheduled_rules_count": 0
}


PAUSE - Note that the packet contains a field enabled=false to pause
https://autoscale.<sys-domain>/api/bindings/435e9bf5-7ebf-4679-90bf-03e669a3d21d
Method:POST


Sat Jun 18 2016 10:47:18 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:47:18 [Metrics Director] Received new Reading -----> {ID:0 ServiceBindingGuid:435e9bf5-7ebf-4679-90bf-03e669a3d21d AppName:pcf-autoscaler-test CPUUtilization:60 ExpectedInstanceCount:2 RunningInstanceCount:2 State:RUNNING CreatedAt:2016-06-18 17:47:18.591759809 +0000 UTC}
Sat Jun 18 2016 10:47:18 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:47:18 [Metrics Agent 435e9] Sending Reading -----> {ID:0 ServiceBindingGuid:435e9bf5-7ebf-4679-90bf-03e669a3d21d AppName:pcf-autoscaler-test CPUUtilization:60 ExpectedInstanceCount:2 RunningInstanceCount:2 State:RUNNING CreatedAt:2016-06-18 17:47:18.591759809 +0000 UTC}
Sat Jun 18 2016 10:47:18 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:47:18 [Scaling Director] Received new Reading -----> {ID:2758 ServiceBindingGuid:435e9bf5-7ebf-4679-90bf-03e669a3d21d AppName:pcf-autoscaler-test CPUUtilization:60 ExpectedInstanceCount:2 RunningInstanceCount:2 State:RUNNING CreatedAt:2016-06-18 17:47:18 +0000 UTC}
Sat Jun 18 2016 10:47:18 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:47:18 [Scaling Agent] Making scaling decision -----> {ID:2758 ServiceBindingGuid:435e9bf5-7ebf-4679-90bf-03e669a3d21d AppName:pcf-autoscaler-test CPUUtilization:60 ExpectedInstanceCount:2 RunningInstanceCount:2 State:RUNNING CreatedAt:2016-06-18 17:47:18 +0000 UTC}
Sat Jun 18 2016 10:47:18 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:47:18 [Scaling Agent] Notifying director of completion -----> true
Sat Jun 18 2016 10:47:18 GMT-0700 (Pacific Daylight Time) [APP] OUT [0m[autoscale] 2016/06/18 17:47:18 [Scaling Director] Agent has completed -----> true

{
  "app_name": "pcf-autoscaler-test", 
  "cpu_max_threshold": 80, 
  "cpu_min_threshold": 20, 
  "enabled": false, 
  "guid": "435e9bf5-7ebf-4679-90bf-03e669a3d21d", 
  "max_instances": 5, 
  "min_instances": 2, 
  "next_scheduled_event_time": "No Upcoming Events", 
  "scaling_event_description": "NOT FOUND", 
  "scaling_event_time": "2016-06-18T17:41:36.724261923Z", 
  "scheduled_rules_count": 0
}
