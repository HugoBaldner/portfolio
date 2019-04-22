#./bin/bash

#this script takes in at least 2 files and out puts the date and file names to #the first file

if [[ $# -lt 2 ]]
then
    echo "$0: error: not enough file names" >&2
    exit 1
fi
totaln=$#
out=$1
shift

date >>$out

while [[ $# -gt 0 ]]
do
    if [[ $totaln -gt 2 ]]
    then
       cat $1 >>total

    fi
    wc -l $1 >>$out
    shift

done

wc -l total >>$out
rm total
exit 0





