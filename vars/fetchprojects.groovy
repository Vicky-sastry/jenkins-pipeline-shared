import groovy.json.*

@NonCPS
create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/projectlist.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def count = resultJson.count
  print(count)
List<String> JSON = new ArrayList<String>()
for(i=0;i<count;i++)
{
  print("zcda")
def val = resultJson.value[i].name
def id = resultJson.value[i].id
JSON.add("name":val,"id":id)  
}
print(JSON)
  def jsonBuilder = new groovy.json.JsonBuilder()
   jsonBuilder.AZURE(
  
  "ID&NAMES" : JSON
     )
  File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/OUTPUT.json")
file.write(jsonBuilder.toPrettyString())
}
 
def call(jsondata){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
println(jsonObj.environments.environment)

String a=jsonObj.environments.environment.deploy.organization
String orgname=a.replaceAll("\\[", "").replaceAll("\\]","");
  sh """
 curl --location --request GET 'https://dev.azure.com/${orgname}/_apis/projects?api-version=5.0%0A' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206eDIyYXpoejRweHBzbmltMjJod295dzJkNG9xdjZtbzJ3czRsemgyNzZpc2trdW5ueXR5YQ==' -o projectlist.json
"""
 create()
}
