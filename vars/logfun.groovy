def call(message)
{
// println(message)
  Date date = new Date() 
  sh " echo '${date}' AZURE ${message} >>log.txt"
}
