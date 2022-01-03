#!/bin/zsh
# https://twpower.github.io/131-simple-shell-script-syntax

echo "INIT DB SHELL"

arg_cnt=$#
arg_list=("member" "todo")
completed_list=()

if ((${arg_cnt} == 0)); then
  echo "Generate all tables and data"
  for arg in "${arg_list[@]}"; do
    aws dynamodb create-table --cli-input-json file://files/"${arg}"_table.json --endpoint-url http://localhost:8000
    aws dynamodb batch-write-item --request-items file://files/"${arg}"_data.json --endpoint-url http://localhost:8000
    completed_list+=("${arg}")
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
        completed_list+=("${param}")
        failed_list=("${failed_list[@]/$param}")
      fi
    done
  done
  echo "Not Found this parameters: ${failed_list}"
fi

for i in "${completed_list[@]}"; do
  if [ "${i}" = "member" ]; then
    aws dynamodb update-table \
        --table-name dummy_member \
        --attribute-definitions AttributeName=memberId,AttributeType=S \
        --global-secondary-index-updates \
            "[{\"Create\":{\"IndexName\": \"memberId-index\",\"KeySchema\":[{\"AttributeName\":\"memberId\",\"KeyType\":\"HASH\"}], \
            \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]" \
        --endpoint-url http://localhost:8000
    aws dynamodb update-table \
        --table-name dummy_member \
        --attribute-definitions AttributeName=emailAddress,AttributeType=S \
        --global-secondary-index-updates \
            "[{\"Create\":{\"IndexName\": \"emailAddress-index\",\"KeySchema\":[{\"AttributeName\":\"emailAddress\",\"KeyType\":\"HASH\"}], \
            \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]" \
        --endpoint-url http://localhost:8000
    aws dynamodb update-table \
        --table-name dummy_member \
        --attribute-definitions AttributeName=nickName,AttributeType=S \
        --global-secondary-index-updates \
            "[{\"Create\":{\"IndexName\": \"nickName-index\",\"KeySchema\":[{\"AttributeName\":\"nickName\",\"KeyType\":\"HASH\"}], \
            \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]" \
        --endpoint-url http://localhost:8000
    aws dynamodb update-table \
        --table-name dummy_member \
        --attribute-definitions AttributeName=memberStatus,AttributeType=S \
        --global-secondary-index-updates \
            "[{\"Create\":{\"IndexName\": \"memberStatus-index\",\"KeySchema\":[{\"AttributeName\":\"memberStatus\",\"KeyType\":\"HASH\"}], \
            \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5      },\"Projection\":{\"ProjectionType\":\"ALL\"}}}]" \
        --endpoint-url http://localhost:8000
  fi
done

# result
if [ "$?" -eq 0 ];then
    echo "CMD Success!"
else
    echo "CMD Failure!"
    exit 9
fi