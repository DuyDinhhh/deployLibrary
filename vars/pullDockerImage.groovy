def call(){
  stage('Pull Docker Image'){
    def artifactVersion = "${env.ARTIFACT_VERS}-${env.DEPLOY_TAG}"
    withCredentials([usernamePassword(credentialsId: env.NEXUS_CREDENTIALS_ID, passwordVariable: 'pass', usernameVariable: 'user')]) {
      sh "docker login -u $user -p $pass ${env.NEXUS_URL_DOCKER}"
      sh "docker pull ${env.NEXUS_URL_DOCKER}/docker-releases/${env.NEXUS_ARTIFACT_ID}:${artifactVersion}"
    }
  }
}
