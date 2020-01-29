def call(name){

  echo "My project name is ${name}"
  sh '''
  
  echo "My project name is ${name}"
                curl --location --request POST 'https://dev.azure.com/vickysastryvs/_apis/projects?api-version=6.0-preview.4' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--header 'Authorization: Basic dmlja3lzYXN0cnkudnNAb3V0bG9vay5jb206enN4YXBrajN6d2s2cnR6N3ptNHR5bGk3YXlrN3l0NXllaHA1aWM3ZXJsZWM0eHNmN3R5YQ==' \
--data-raw '{
  "name": ${name},
  "description": "Testing for Project creation using curl",
  "capabilities": {
    "versioncontrol": {
      "sourceControlType": "Git"
    },
    "processTemplate": {
      "templateTypeId": "6b724908-ef14-45cf-84f8-768b5384da45"
    }
  }
}'
'''
}
