import groovy.json.*

/*@NonCPS
create(String orgname){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/pushes.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def pushcount = resultJson.count*/
  

def call(jsondata){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
println(jsonObj.environments.environment)

String a=jsonObj.environments.environment.deploy.organization
String orgname=a.replaceAll("\\[", "").replaceAll("\\]","");
env.name = orgname

String b=jsonObj.environments.environment.deploy.key
String projectName=b.replaceAll("\\[", "").replaceAll("\\]","");
  
   sh """ 
curl --location --request GET 'https://dev.azure.com/${orgname}/${projectName}/_apis/git/repositories/${projectName}/pushes?api-version=5.1' \
--header 'Accept: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ==' -o pushes.json
"""


}
