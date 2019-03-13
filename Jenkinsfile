pipeline {
  agent any
  stages {
    stage('Restore Dependencies') {
      steps {
        sh '''cd dotnet.project
dotnet restore dotnet.project.sln'''
      }
    }
    stage('Build') {
      steps {
        sh '''ls
dotnet build --no-restore dotnet.project.api/dotnet.project.api.csproj'''
      }
    }
    stage('Publish') {
      steps {
        sh '''mkdir temp
dotnet publish --output temp --no-restore --no-build dotnet.project.api/dotnet.project.api.csproj
cd temp
ls'''
      }
    }
  }
}