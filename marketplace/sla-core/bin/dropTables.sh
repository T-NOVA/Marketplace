DROP=$(mysql -p"_atossla_" -u atossla atossla <<< "show tables" | grep -v "Tables" | sed -e's/\(.*\)/drop table \1; /')
SQL=$(echo "SET FOREIGN_KEY_CHECKS=0;" && echo $DROP)
echo "$SQL" | mysql -p -u atossla atossla
