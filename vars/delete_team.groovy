import groovy.json.*

@NonCPS
delete_team(){
def jsonSlurper = new JsonSlurper()
def read = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/azure/out.json"),"UTF-8"))
def resultJs = jsonSlurper.parse(read)
def projectid = resultJs.id
  
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/azure/team.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def teamid = resultJson.value[0].id

  sh """ 
    curl --location --request DELETE 'https://dev.azure.com/vickysastryvs/_apis/projects/${projectid}/teams/${teamid}?api-version=5.1' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ==' 
  """
}
def call()
{
delete_team()
}
