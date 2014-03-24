#! /bin/sh
#  Location of directory containing  dist/compiler488.jar
WHERE=.

# Location of legal test cases
PASS='tests/pass'
# Location of invalid test cases
FAIL='tests/fail'

# Executing legal test cases
echo "-------------------------------------"
echo "    Executing legal test cases       "
echo "-------------------------------------"
for i in `ls $PASS`
do
  echo "Running $i"
  java -jar $WHERE/dist/compiler488.jar  $PASS'/'$i
  echo
done

# Executing invalid test cases
echo "-------------------------------------"
 echo "    Executing invalid test cases       "
 echo "-------------------------------------"
for i in `ls $FAIL`
 do
   echo "Running $i"
   java -jar $WHERE/dist/compiler488.jar  $FAIL'/'$i
   echo
 done

exit 0
