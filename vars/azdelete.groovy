import groovy.json.JsonSlurper 

@NonCPS
deleteProject(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectid = resultJson.id 
   httpRequest authentication: 'azurecred', 
    customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']], 
    httpMode: 'DELETE', url: "https://dev.azure.com/vickysastryvs/_apis/projects/${projectid}?api-version=5.1"
}
def call(){
 //def filePath = readFile "${WORKSPACE}/var/lib/jenkins/workspace/azd/output.json"
   def props = readJSON file: 'dir/output.json'
 def request = libraryResource 'props'
 deleteProject(request)
   //echo "The project ${projectid} is deleted"
}
