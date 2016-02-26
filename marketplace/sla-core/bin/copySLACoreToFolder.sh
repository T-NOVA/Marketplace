#!/usr/bin/env bash
#
# Copy slacore project to another location.
#
# The following tasks are performed:
#   copy source tree to $1
#     from sla-personalization only pom.xml and src/main/resources/personalization-context.xml
#   rename groudId to $2
#
# Usage: 
#   $0 <dir> <groupId>
#     <dir>: destination directory; parent pom.xml will be here
#     <groupId>: if specified, change the groupId of slacore projects to this value.
#
if [ "$0" != "bin/copySLACoreToFolder.sh" ]; then
	echo "Must be executed from project root"
	exit 1
fi

if [ $# -lt 1 ]; then
	echo "Usage: $0 <destdir> [<groupId>]"
	exit 1
fi

if [ -e "$1" ]; then
	echo "$1 exists; exiting"
	exit 1
fi

### copy source tree ###
echo -e "Starting copy: \n  source=$(pwd)\n  dest=$1"

EXCLUDE=$(cat << 'EOF'
target
.*
configuration.properties
sla-personalization
EOF
)

echo "$EXCLUDE" | rsync -a --exclude-from=- ./* "$1"

# sla-personalization is an special case
res="sla-personalization/src/main/resources"
mkdir -p "$1/$res"
cp "sla-personalization/pom.xml" "$1/sla-personalization"
cp "$res/personalization-context.xml" "$1/$res"

### rename groupId ###
if [ -n "$2" ]; then
	echo "Renaming groupId to $2"
	find "$1" -name pom.xml -exec sed -i -e"s/<groupId>eu.atos.sla<\/groupId>/<groupId>$2<\/groupId>/" {} \;
fi
