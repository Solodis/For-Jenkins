set HUB_HOSTNAME=EPRURYAW0075
set GRID=selenium-server-standalone-2.53.1.jar
set HUB_URL=http://%HUB_HOSTNAME%:4444/grid/register
set BR_FF="browserName=firefox, version=46.0, maxInstances=3, platform=WINDOWS"

java -jar %GRID% -role node -hub %HUB_URL% -browser %BR_FF% -timeout 900000 -port5555