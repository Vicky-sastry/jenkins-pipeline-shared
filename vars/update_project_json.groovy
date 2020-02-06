import groovy.json.*

@NonCPS
create(String update_key){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/azure/out.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def projectId = resultJson.id
sh """
curl --location --request PATCH 'https://dev.azure.com/vickysastryvs/_apis/projects/${projectId}?api-version=5.1' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ==' \
--data-raw '{
 "name":"${update_key}",
}'"""
}

def call(jsondata){
def jsonString = jsondata

def jsonObj = readJSON text: jsonString

String a = jsonObj.environments.environment.deploy.update_key
String update_key=a.replaceAll("\\[", "").replaceAll("\\]","");
create(update_key)

}
