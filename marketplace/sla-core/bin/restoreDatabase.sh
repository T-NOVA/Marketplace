#!/usr/bin/env bash
if [ "$0" != "bin/restoreDatabase.sh" ]; then
	echo "Must be executed from project root"
	exit 1
fi

function get_var() {
  local result

  result=$(grep "$1" configuration.properties | tr -d '\r' | sed -e 's/.*=[\t ]*\([^\t #]*\).*$/\1/')
  echo $result
}

DB=$(get_var "db.name")
USER=$(get_var "db.username")
PWD=$(get_var "db.password")
echo "Cleaning database: DB='$DB' USER='$USER'"
mysql -p"$PWD" -u "$USER" "$DB" < sla-repository/src/main/resources/sql/atossla.sql
