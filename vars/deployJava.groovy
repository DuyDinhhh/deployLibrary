def call(Map config){
  stage('Deploy java') {
    steps{
        sh "java -jar ${config.NEXUS_ARTIFACT_ID}-${config.ARTIFACT_VERS}-${config.DEPLOY_TAG}.jar &"
    }
  }
}
