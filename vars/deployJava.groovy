def call(){
    sh "java -jar ${env.NEXUS_ARTIFACT_ID}-${env.ARTIFACT_VERS}-${env.DEPLOY_TAG}.jar &"   
}
