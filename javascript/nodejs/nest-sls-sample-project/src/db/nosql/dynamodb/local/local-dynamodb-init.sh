#!/bin/zsh
# https://twpower.github.io/131-simple-shell-script-syntax

echo "INIT DB SHELL"

arg_cnt=$#
arg_list=("member" "todo")

if ((${arg_cnt} == 0)); then
  echo "Generate all tables and data"
  for arg in "${arg_list[@]}"; do
    aws dynamodb create-table --cli-input-json file://files/"${arg}"_table.json --endpoint-url http://localhost:8000
    aws dynamodb batch-write-item --request-items file://files/"${arg}"_data.json --endpoint-url http://localhost:8000
  done
else
  echo "Generate only specific tables"
  param_list=("$@") # command param list
  failed_list="${param_list}"
  for arg in "${arg_list[@]}"; do
    for param in "${param_list[@]}"; do
      if [ "${param}" = "${arg}" ]; then
        aws dynamodb create-table --cli-input-json file://files/"${arg}"_table.json --endpoint-url http://localhost:8000
        aws dynamodb batch-write-item --request-items file://files/"${arg}"_data.json --endpoint-url http://localhost:8000
        failed_list=("${failed_list[@]/$param}")
      fi
    done
  done
  echo "Not Found this parameters: ${failed_list}"
fi

# result
if [ "$?" -eq 0 ];then
    echo "CMD Success!"
else
    echo "CMD Failure!"
    exit 9
fi