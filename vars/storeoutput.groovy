def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.environments.environment)

String a=jsonObj.environments.environment.deploy.key
//String a=jsonObj.alm.projects.project.name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");

String b=jsonObj.environments.environment.deploy.organization
String orgname=b.replaceAll("\\[", "").replaceAll("\\]","");

 sh """
      curl --location --request GET 'https://dev.azure.com/${orgname}/${projectName}/_apis/git/repositories/${projectName}/commits?api-version=5.1' \
--header 'Accept: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ==' -o commit.json
    """
}
