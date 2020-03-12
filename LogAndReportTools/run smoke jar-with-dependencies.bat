cd target
java -jar demo-jar-with-dependencies.jar -b chrome -grid true -smk ./testng-smoke.xml -prop ./log4j.properties -s SMOKE
echo
pause