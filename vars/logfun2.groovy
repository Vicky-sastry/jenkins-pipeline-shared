import groovy.json.JsonSlurper 
@NonCPS

def call(message)
{
 println(message)
 def request = libraryResource 'data2.json'
 def jsonSlurper = new JsonSlurper() 
 def resultJson = jsonSlurper.parseText(request)
 def projectName = resultJson.key
  Date date = new Date() 
  sh " echo '${date}' Azure project details' ${message} >>log.txt"
}
