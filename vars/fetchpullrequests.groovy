import groovy.json.*
  
def call(jsondata){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
println(jsonObj.environments.environment)
  
String a=jsonObj.environments.environment.deploy.organization
String orgname=a.replaceAll("\\[", "").replaceAll("\\]","");

String b=jsonObj.environments.environment.deploy.key
String projectName=b.replaceAll("\\[", "").replaceAll("\\]","");
  
   sh """ 
curl --request GET 'https://dev.azure.com/${orgname}/${projectName}/_apis/git/pullrequests?creationDate=14/02/2020T11:00~14/02/2020T12:00&api-version=5.1' \
--header 'Accept: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ==' -o pullrequests.json
"""


}
