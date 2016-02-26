#!/bin/sh


if [ -f /deployment/init.sh ];
then
	echo "Running custom init script"
	chmod +x /deployment/init.sh
	/deployment/init.sh
fi

if [ -d /deployment ];
then
	echo "Mapping deployed wars"
	rm -rf /var/lib/tomcat7/webapps
	ln -s /deployment /var/lib/tomcat7/webapps
	chown tomcat7:tomcat7 /deployment
fi

if [ -n "${Xmx}" ];
then
	sed -i s/Xmx.*\ /Xmx${Xmx}\ /g /etc/default/tomcat7
fi

if [ -n "${JAVA_OPTS}" ];
then
	# Add any Java opts that are set in the container
	echo "Adding JAVA OPTS"
	echo "JAVA_OPTS=\"\${JAVA_OPTS} ${JAVA_OPTS} \"" >> /etc/default/tomcat7
fi

service influxdb start
service rabbitmq-server start
service tomcat7 restart

#Override the exit command to prevent accidental container distruction 
echo 'alias exit="echo Are you sure? this will kill the container. use Ctrl + p, Ctrl + q to detach or ctrl + d to exit"' > ~/.bashrc

#Run bash to keep container running and provide interactive mode
# bash
while true; do sleep 1; done
