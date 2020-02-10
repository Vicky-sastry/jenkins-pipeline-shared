import groovy.json.*

@NonCPS
deleteProject(String orgname){
def jsonSlurper = new JsonSlurper() 
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/out.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def projectid = resultJson.id 
   sh """
   curl --location --request DELETE 'https://dev.azure.com/${orgname}/_apis/projects/${projectid}?api-version=5.1' \
--header 'Accept: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ=='
"""
}

def call(jsondata){
   
def jsonString = jsondata

def jsonObj = readJSON text: jsonString
String b=jsonObj.environments.environment.deploy.organization
String orgname=b.replaceAll("\\[", "").replaceAll("\\]","");

 deleteProject(orgname)
}
