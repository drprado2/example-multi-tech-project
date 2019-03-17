# Partir da imagem oficial do jenkins, com versão fixa
FROM jenkins/jenkins:2.167

# Alterar para executar tudo como usuário root
USER root

# instalar dependências do Docker
RUN apt-get update && \
    apt-get install -y \
    apt-transport-https \ 
    ca-certificates \
    curl \
    gnupg2 \
    software-properties-common 

# instalar Docker
RUN curl -fsSL https://get.docker.com | bash
RUN usermod -aG docker jenkins

# instalar git
RUN apt-get install -y git

# instalar jdk 8
RUN apt-get install -y openjdk-8-jdk

# instalar node 
RUN apt-get install -y nodejs

# instalar dotnet core 
RUN wget -qO- https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > microsoft.asc.gpg && \
    mv microsoft.asc.gpg /etc/apt/trusted.gpg.d/ && \
    wget -q https://packages.microsoft.com/config/debian/9/prod.list && \
    mv prod.list /etc/apt/sources.list.d/microsoft-prod.list && \
    chown root:root /etc/apt/trusted.gpg.d/microsoft.asc.gpg && \
    chown root:root /etc/apt/sources.list.d/microsoft-prod.list && \
    apt-get update && \
    apt-get install -y dotnet-sdk-2.2

# install kubectl
RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl; chmod +x ./kubectl; mv ./kubectl /usr/local/bin/kubectl

# Setting the number of executors
COPY executors.groovy /usr/share/jenkins/ref/init.groovy.d/executors.groovy

# Criação do diretório para backup
RUN mkdir /srv/backup 

# Desabilita primeiro login e desabilita wizard de plugins
RUN echo 2.112 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state
RUN echo 2.112 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion

# Instala os plugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Configurar JAVA_HOME
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

COPY --chown=jenkins:jenkins config_jenkins /var/jenkins_home
