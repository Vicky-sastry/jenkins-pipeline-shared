import groovy.json.JsonSlurper  
def call(jsondata){
def jsonSlurper = new JsonSlurper() 
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/azd/out.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def projectid = resultJson.id
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.environments.environment)

String a=jsonObj.environments.environment.deploy.team_name
//String a=jsonObj.alm.projects.project.name
String teamName=a.replaceAll("\\[", "").replaceAll("\\]","");
env.name = teamName

sh """    
            echo "My project name is ${name}"
            curl --location --request POST 'https://dev.azure.com/vickysastryvs/_apis/projects/${projectid}/teams?api-version=5.1' \
            --header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ==' \
--data-raw '{
"name": "${teamName}"

"""
}
