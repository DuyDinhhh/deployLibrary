def call(Map config){
  stage('Deploy java') {
    
        sh "java -jar ${config.NEXUS_ARTIFACT_ID}-${config.ARTIFACT_VERS}-${config.DEPLOY_TAG}.jar &"
    
  }
}
