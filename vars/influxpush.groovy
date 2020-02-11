import groovy.json.*

@NonCPS
create(){
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/commit.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def count = resultJson.count
echo "$count"
sh """curl -i -XPOST 'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' --data-binary 'azurerepo commits=${count}'
"""
}


def call()
{
create()
}
