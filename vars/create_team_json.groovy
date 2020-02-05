import groovy.json.*

@NonCPS
create(String team_name){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/azure/out.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def projectId = resultJson.id
	sh """
            curl --location --request POST 'https://dev.azure.com/vickysastryvs/_apis/projects/${projectId}/teams?api-version=5.1' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ==' \
--data-raw '{
  "name": "${team_name}"
}'
"""
}


	
def call(jsondata){
def jsonString = jsondata

def jsonObj = readJSON text: jsonString

String a = jsonObj.environments.environment.deploy.team_name
String team_name=a.replaceAll("\\[", "").replaceAll("\\]","");
create(team_name)

}
