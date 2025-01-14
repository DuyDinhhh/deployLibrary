def call() {
     stage('Pull Artifact from Nexus') {
          withCredentials([usernamePassword(credentialsId: env.NEXUS_CREDENTIALS_ID, passwordVariable: 'pass', usernameVariable: 'user')]) {
             sh """
             curl -u $user:$pass -O \
             http://${env.NEXUS_URL}/repository/${env.NEXUS_REPOSITORY}/${env.NEXUS_GROUP}/${env.NEXUS_ARTIFACT_ID}/${env.ARTIFACT_VERS}-${env.DEPLOY_TAG}/${env.NEXUS_ARTIFACT_ID}-${env.ARTIFACT_VERS}-${env.DEPLOY_TAG}.jar
             """
          }
     }
}
