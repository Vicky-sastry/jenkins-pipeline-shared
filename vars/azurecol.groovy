import groovy.json.JsonSlurper 

@NonCPS
showRepo(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def coll = resultJson.name
httpRequest authentication: 'azurecred', contentType: "APPLICATION_JSON", 
    
    httpMode: 'GET', url: "https://dev.azure.com/vickysastryvs/_apis/projects?api-version=5.1"
}
	def call(){
def request = libraryResource 'data2.json'
showRepo(request)
}
/*def call()
{
  sh 'curl -u vickysastry.vs@outlook.com:zsxapkj3zwk6rtz7zm4tyli7ayk7yt5yehp5ic7erlec4xsf7tya --request GET https://dev.azure.com/vickysastryvs/_apis/projects?api-version=5.1'
}*/
