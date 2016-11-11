#!/bin/bash
ssh root@dashboard.hpc.vai.org mv /var/lib/tomcat/webapps/tools /var/lib/tomcat/webapps/tools.old
scp -r war root@dashboard.hpc.vai.org:/var/lib/tomcat/webapps/tools
