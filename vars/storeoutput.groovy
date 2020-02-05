def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.environments.environment)

String a=jsonObj.environments.environment.deploy.key
//String a=jsonObj.alm.projects.project.name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
env.name = projectName

 sh """
       curl  --request GET 'https://dev.azure.com/vickysastryvs/_apis/projects/${projectName}?api-version=5.1' 
             --header 'Accept: application/json' \
             --header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ==' -o out.json
    """
}
