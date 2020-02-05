import groovy.json.JsonSlurper 

@NonCPS
deleteProject(){
def jsonSlurper = new JsonSlurper() 
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/azd/out.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def projectid = resultJson.id 
   httpRequest authentication: 'azurecred', 
    customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']], 
    httpMode: 'DELETE', url: "https://dev.azure.com/vickysastryvs/_apis/projects/${projectid}?api-version=5.1"
}
def call(){
 //def filePath = readFile "${WORKSPACE}/var/lib/jenkins/workspace/azd/output.json"
  // def props = readJSON file: 'output.json'
 //def props = readFileFromWorkspace(String 'output.json')
   //shell(readFileFromWorkspace('build.sh'))
 // def prop= jsonParse(readFile('output.json'))
 //def request = Workspace 'out.json'
 
 

 deleteProject()
   //echo "The project ${projectid} is deleted"
}
