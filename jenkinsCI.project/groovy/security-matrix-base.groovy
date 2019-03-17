import jenkins.model.*
import hudson.security.*
import com.cloudbees.plugins.credentials.*


def instance = Jenkins.getInstance()
def strategy = new GlobalMatrixAuthorizationStrategy()

// Roles based on https://wiki.jenkins-ci.org/display/JENKINS/Matrix-based+security
//Overall - http://javadoc.jenkins-ci.org/jenkins/model/Jenkins.html
strategy.add(Jenkins.ADMINISTER, 'system-account')
strategy.add(Jenkins.RUN_SCRIPTS, 'system-account')
strategy.add(Jenkins.READ, 'system-account')


strategy.add(Jenkins.ADMINISTER, 'admingroup')
strategy.add(Jenkins.RUN_SCRIPTS, 'admingroup')
strategy.add(Jenkins.READ, 'admingroup')


// root# grep anonymous /local/jenkins/config.xml
// hudson.model.Hudson.Read:anonymous
// hudson.model.Item.Read:anonymous
// hudson.model.View.Read:anonymous
strategy.add(Jenkins.READ, 'anonymous')
strategy.add(hudson.model.Item.READ, 'anonymous')
strategy.add(hudson.model.View.READ, 'anonymous')




// Agent (Slave < 2.0) - http://javadoc.jenkins-ci.org/jenkins/model/Jenkins.MasterComputer.html
strategy.add(Jenkins.MasterComputer.BUILD, 'admingroup')
strategy.add(Jenkins.MasterComputer.CONFIGURE, 'admingroup')
strategy.add(Jenkins.MasterComputer.CONNECT, 'admingroup')
strategy.add(Jenkins.MasterComputer.CREATE, 'admingroup')
strategy.add(Jenkins.MasterComputer.DELETE, 'admingroup')
strategy.add(Jenkins.MasterComputer.DISCONNECT, 'admingroup')


// Job - http://javadoc.jenkins-ci.org/hudson/model/Item.html
strategy.add(hudson.model.Item.BUILD, 'admingroup')
strategy.add(hudson.model.Item.CANCEL, 'admingroup')
strategy.add(hudson.model.Item.CONFIGURE, 'admingroup')
strategy.add(hudson.model.Item.CREATE, 'admingroup')
strategy.add(hudson.model.Item.DELETE, 'admingroup')
strategy.add(hudson.model.Item.DISCOVER, 'admingroup')
strategy.add(hudson.model.Item.EXTENDED_READ, 'admingroup')
strategy.add(hudson.model.Item.READ, 'admingroup')
strategy.add(hudson.model.Item.WIPEOUT, 'admingroup')
strategy.add(hudson.model.Item.WORKSPACE, 'admingroup')


// Run - http://javadoc.jenkins-ci.org/hudson/model/Run.html
strategy.add(hudson.model.Run.DELETE, 'admingroup')
strategy.add(hudson.model.Run.UPDATE, 'admingroup')
strategy.add(hudson.model.Run.ARTIFACTS, 'admingroup')


// View - http://javadoc.jenkins-ci.org/hudson/model/View.html
strategy.add(hudson.model.View.CONFIGURE, 'admingroup')
strategy.add(hudson.model.View.CREATE, 'admingroup')
strategy.add(hudson.model.View.DELETE, 'admingroup')
strategy.add(hudson.model.View.READ, 'admingroup')


// SCM - http://javadoc.jenkins-ci.org/hudson/model/View.html
strategy.add(hudson.scm.SCM.TAG, 'admingroup')


// // Credentials - https://github.com/jenkinsci/credentials-plugin/blob/master/src/main/java/com/cloudbees/plugins/credentials/CredentialsProvider.java
// strategy.add(CredentialsProvider.CREATE, "my-user")
// strategy.add(CredentialsProvider.UPDATE, "my-user")
// strategy.add(CredentialsProvider.VIEW, "my-user")
// strategy.add(CredentialsProvider.DELETE, "my-user")
// strategy.add(CredentialsProvider.MANAGE_DOMAINS, "my-user")
//
// Plugin Manager http://javadoc.jenkins-ci.org/hudson/PluginManager.html
//strategy.add(hudson.model.Hudson.UPLOAD_PLUGINS, 'admingroup')
strategy.add(hudson.PluginManager.UPLOAD_PLUGINS, 'admingroup')
strategy.add(hudson.PluginManager.CONFIGURE_UPDATECENTER, 'admingroup')

// ADICIONANDO MEUS PRÓPRIOS USUÁRIOS
strategy.add(Jenkins.ADMINISTER, 'admin')
strategy.add(hudson.model.Item.BUILD, 'dev')
strategy.add(hudson.model.Item.CANCEL, 'dev')
strategy.add(hudson.model.Item.READ, 'dev')
strategy.add(hudson.model.Run.REPLAY, 'dev')

instance.setAuthorizationStrategy(strategy)
instance.save()