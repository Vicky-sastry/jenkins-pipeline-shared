import groovy.json.*

@NonCPS
create(String projectName){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/azure/out.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def projectId = resultJson.id
sh """
curl --location --request GET 'https://dev.azure.com/vickysastryvs/_apis/projects/${projectId}?api-version=5.1' \
--header 'Accept: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ=='
-H 'content-type: application/json'  | json_reformat
"""
}

def call(jsondata){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
println(jsonObj.environments.environment)

String a=jsonObj.environments.environment.deploy.key
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
env.name = projectName
}
