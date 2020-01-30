import groovy.json.JsonSlurper 

@NonCPS
createProject(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = resultJson.key 
   httpRequest authentication: 'azurecred', 
    customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']], 
    httpMode: 'POST', requestBody: """{
     "name": "${projectName}",
 "description": "Testing for Project creation using curl",
  "capabilities": {
    "versioncontrol": {
      "sourceControlType": "Git"
    },
    "processTemplate": {
      "templateTypeId": "6b724908-ef14-45cf-84f8-768b5384da45"
    }
  }
}""", 
   /*responseHandle: 'NONE',*/ url: "https://dev.azure.com/vickysastryvs/_apis/projects?api-version=5.1"

 
}
def call(){
 def request = libraryResource 'data.json'
 createProject(request)
   echo 'The project "$projectName" is created'
}


  
 /*def call(name){
  echo "My project name is ${name}"

            sh """    
            echo "My project name is ${name}"
            curl --location --request POST 'https://dev.azure.com/vickysastryvs/_apis/projects?api-version=6.0-preview.4' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ==' \
--data-raw '{
  "name": "${name}",
  "description": "Testing for Project creation using curl",
  "capabilities": {
    "versioncontrol": {
      "sourceControlType": "Git"
    },
    "processTemplate": {
      "templateTypeId": "6b724908-ef14-45cf-84f8-768b5384da45"
    }
  }
}'"""
}*/

