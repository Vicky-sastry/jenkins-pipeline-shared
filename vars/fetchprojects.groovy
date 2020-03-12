import groovy.json.*

@NonCPS
create(String orgname){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/commit.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def count = resultJson.count
List<String> JSON = new ArrayList<String>()
for(i=0;i<count;i++)
{
def val = resultJson.value[i].name
def id = resultJson.value[i].id
JSON.("name":val,"id":id)  
}
println(JSON)
 sh """
 curl --location --request GET 'https://dev.azure.com/${orgname}/_apis/projects?api-version=5.0%0A' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206eDIyYXpoejRweHBzbmltMjJod295dzJkNG9xdjZtbzJ3czRsemgyNzZpc2trdW5ueXR5YQ=='
"""
}
 
def call(jsondata){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
println(jsonObj.environments.environment)

String a=jsonObj.environments.environment.deploy.organization
String orgname=a.replaceAll("\\[", "").replaceAll("\\]","");
}
