import groovy.json.JsonSlurper 

@NonCPS
showRepo(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectid = resultJson.id
httpRequest authentication: 'azurecred', contentType: "APPLICATION_JSON", 
    
    httpMode: 'GET', url: "https://dev.azure.com/vickysastryvs/_apis/projects/${projectid}?api-version=5.1"
}
	def call(){
def request = libraryResource 'data4.json'
showRepo(request)
}
