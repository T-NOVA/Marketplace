#!/usr/bin/env bash
if [ "$0" != "bin/dumpDatabase.sh" ]; then
	echo "Must be executed from project root"
	exit 1
fi
mysqldump -d -p -u atossla atossla > sla-repository/src/main/resources/sql/atossla.sql
