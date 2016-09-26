@echo off 


set NOW=%DATE:~0,4%.%DATE:~5,2%.%DATE:~8,2%
echo %NOW%%

mysqldump -h localhost -u mz-gallery-root -p mg-prod > ./mg-data-prod-%NOW%.sql

pause & exit
