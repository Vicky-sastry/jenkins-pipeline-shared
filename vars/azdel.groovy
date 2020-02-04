import groovy.json.JsonSlurper 

@NonCPS
deleteProject(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectid = resultJson.key 
   httpRequest authentication: 'azurecred', 
    customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']], 
    httpMode: 'DELETE', url: "https://dev.azure.com/vickysastryvs/_apis/projects/${projectid}?api-version=5.1"
}
def call(){
 def request = libraryResource 'data3.json'
 deleteProject(request)
   //echo "The project ${projectid} is deleted"
}
