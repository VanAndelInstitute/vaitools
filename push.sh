#!/bin/bash
ssh root@tools.vai.org "rm -r /tmp/tools.old; mv /opt/tomcat/webapps/tools /tmp/tools.old"
sleep 10;
scp -r war root@tools.vai.org:/opt/tomcat/webapps/tools
