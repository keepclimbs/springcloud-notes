###启动

#!/bin/sh

basepath=$(cd `dirname $0`; pwd)

moduleName="ch623-eureka-server-1.0-SNAPSHOT"

pidPath="$moduleName-tpid"

rm -f $pidPath

java -jar $basepath/$moduleName.jar --spring.profiles.active=prod  -server -Xms512m -Xmx1024m -Xss256k &

echo $! > $pidPath

