def call(Map config) {
         withCredentials([usernamePassword(credentialsId: config['for-nexus'], passwordVariable: 'pass', usernameVariable: 'user')]) {
            sh """
            curl -u $user:$pass -O \
            http://${config.NEXUS_URL}/repository/${config.NEXUS_REPOSITORY}/${config.NEXUS_GROUP}/${config.NEXUS_ARTIFACT_ID}/${config.ARTIFACT_VERS}-${config.DEPLOY_TAG}/${config.NEXUS_ARTIFACT_ID}-${config.ARTIFACT_VERS}-${config.DEPLOY_TAG}.jar
            """
    }
}
