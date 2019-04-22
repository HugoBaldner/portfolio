#!/bin/bash

#this script combines all files given into a new file of the first name. errors if given
#less than two files and also errors if the first file is already made

if [[ $# -lt 2 ]]
then
  echo '$0: error: not enough file names' >&2
  exit 1
fi

if [[ -f $1 ]]
then
  echo 'Error: first file exists' >&2
  exit 0
fi

out=$1
shift
while [[ $# -gt 0 ]]
do
  if [[ ! -e $1 ]]
  then
      echo 'File dosent exist $1' >>$out
  fi
  cat $1 >&1
  shift
done


