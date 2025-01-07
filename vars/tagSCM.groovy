def call(Map config) {
    stage('Tag and Push to GitHub') {
        withCredentials([usernamePassword(credentialsId: config.credentialsId, usernameVariable: 'user', passwordVariable: 'pass')]) {
            sh """
            git config --global user.email "duy.nguyentadinh@gmail.com"
            git config --global user.name "duydinhhh"
            git tag -a ${config.deployTag} -m "Deployed ${config.PIPELINE_TYPE} environment"
            git push https://$user:$pass@github.com/${config.repo}.git ${config.deployTag}
            """
        }
    }
}
