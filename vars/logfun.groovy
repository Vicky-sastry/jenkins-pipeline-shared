def call(message,jsondata)
{
 String a=jsonObj.environments.environment.deploy.key
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
env.name = projectName

// println(message)
  Date date = new Date() 
  sh " echo '${date}' AZURE project with ${projectName} ${message} >>log.txt"
}
