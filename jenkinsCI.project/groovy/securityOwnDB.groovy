import java.lang.System
import jenkins.model.*
import hudson.security.*

def home_dir = System.getenv("JENKINS_HOME")
def properties = new ConfigSlurper().parse(new File("$home_dir/config/authentication.properties").toURI().toURL())

if(properties.owndb.enabled) {
  println "############################ STARTING INTERNAL DATABASE SECURITY SETUP ############################"

  HudsonPrivateSecurityRealm realm = new HudsonPrivateSecurityRealm(false)
  properties.owndb.users.each() { key, value ->
    realm.createAccount(value.userId, value.password)
    println ">>> Added user ${value.userId}"
  }

  Jenkins.instance.setSecurityRealm(realm)
  Jenkins.instance.save()
}
