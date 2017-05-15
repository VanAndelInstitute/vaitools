#!/bin/bash
ssh root@dashboard.hpc.vai.org "rm -r /tmp/tools.old; mv /var/lib/tomcat/webapps/tools /tmp/tools.old"
sleep 10;
scp -r war root@dashboard.hpc.vai.org:/var/lib/tomcat/webapps/tools
