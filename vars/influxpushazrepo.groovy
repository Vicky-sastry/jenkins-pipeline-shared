import groovy.json.*

@NonCPS
create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/commit.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def count = resultJson.count
  
def read = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/pullrequests.json"),"UTF-8"))
def result = jsonSlurper.parse(read)
def pullreqcount = result.count

def reader2 = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/pushes.json"),"UTF-8"))
def result2 = jsonSlurper.parse(reader2)
def pushcount = result2.count
  
echo "$count, $pullreqcount, $pushcount"
sh """curl -i -XPOST 'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' --data-binary 'azurerepo commits=${count},pullrequests=${pullreqcount},pushes=${pushcount}'
"""
}


def call()
{
create()
}
