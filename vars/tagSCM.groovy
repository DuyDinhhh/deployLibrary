def call() {
         withCredentials([usernamePassword(credentialsId: env.credentialsId, usernameVariable: 'user', passwordVariable: 'pass')]) {
            sh """
            git config --global user.email "duy.nguyentadinh@gmail.com"
            git config --global user.name "duydinhhh"
            git tag -a ${env.deployTag} -m "Deployed ${env.PIPELINE_TYPE} environment"
            git push https://$user:$pass@github.com/${env.repo}.git ${env.deployTag}
            """
    }
}
